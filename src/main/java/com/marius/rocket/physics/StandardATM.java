/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author n5823a
 */
public class StandardATM extends Atmosphere{
    
    protected double[] altitudes = {0,11000,20000,32000,47000,53000,79000,90000}; //breakpoints
    protected double[] temperatures = {288.16,216.66,216.66,282.66,282.66,165.66,165.66};
    protected double[] pressures = null;
    protected double[] densities = null;
    protected double[] lapses = null;
    protected double[] MMs = null;
    protected double[] gammas = null;
    private double atmLimit = 600000;
    private double spaceTemp = 298;
    private double sealevelpress = 101325;
    private double tempOffset = 0;
    private boolean assumeIdeal = true; 
    
    public StandardATM() {
        this.Radius = 6371000;
        this.G0 = 9.809;
        this.MM = 0.02897; //kg/mol
        this.gamma = 1.4;
        this.R_air = Physics.R/this.MM;
        precalc();
    }
    
    public StandardATM(String filename) {
        /*
        Notes on file standard
        ----------------------
        Altitudes must go in increasing orders
        
        Required:
            - 1st Pressure (SLPres = ) 
            - 1st MM (SLMolarMass = )
            - 1st Gamma (SLGamma = )
            - Max altitude (MaxAlt = )
            - Temperatures (Temperatures = ) comma delimited
            - Altitudes (Altitudes = ) comma delimited
            
        Optional:
            - Pressures (Pressures = ) comma delimited
            - Molar Masses (MolarMasses = ) comma delimited
            - Gammas (Gammas = ) comma delimited
            - Space Temperature (SpaceTemp = )
            - tempOffset (OffsetTeup = )
            
        Not input:
            - Density
            - Lapses
        */
        Properties cfg = new Properties();
        try {
            cfg.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Error reading file. Must be .properties");
        }
        // no checks for now... be careful should throw nullpointerexception if bad file honestly
        this.sealevelpress = Double.parseDouble(cfg.getProperty("SLPres"));
        this.MM = Double.parseDouble(cfg.getProperty("SLMolarMass"));
        this.gamma = Double.parseDouble(cfg.getProperty("SLGamma"));
        this.atmLimit = Double.parseDouble(cfg.getProperty("MaxAlt"));
        this.altitudes = Arrays.stream(cfg.getProperty("Altitudes").split(","))
                            .mapToDouble(Double::parseDouble)
                            .toArray();
        this.temperatures = Arrays.stream(cfg.getProperty("Temperatures").split(","))
                            .mapToDouble(Double::parseDouble)
                            .toArray();
        precalc();
    }
    
    private void precalc() {
        int n = altitudes.length;
        lapses = new double[n];
        densities = new double[n];
        boolean calcPressures = true;
        if(pressures == null) {
            pressures = new double[n];
        }
        if(pressures != null && pressures.length < n){
            System.out.println("Pressures given is not long enough to fit data. Number of pressure points must match altitude points");
            pressures = new double[n];
        } else {
            calcPressures = false;
        }
        
        for(int i = 0; i < altitudes.length; i++) {
            double deltaH = altitudes[i+1]-altitudes[i];
            lapses[i] = (temperatures[i+1]-temperatures[i])/deltaH;
            if(calcPressures) {
                if(i == 1) {
                    pressures[i] =  sealevelpress;
                } else {
                    if(assumeIdeal) {
                        if(lapses[i] == 0) {
                            pressures[i] = pressures[i-1]*isoPresRatio(altitudes[i], altitudes[i-1], temperatures[i]);
                        } else {
                            pressures[i] = pressures[i-1]*lapsePresRatio(lapses[i], temperatures[i], temperatures[i-1]);
                        }
                    }
                }
                
            } 
            if(assumeIdeal) {
                if(i == 1) {
                    densities[i] = pressures[i]/(R_air*temperatures[i]);
                } else {
                    if(MMs != null && MMs.length > altitudes.length){
                        densities[i] = pressures[i]/((2*Physics.R/(MMs[i]+MMs[i-1]))*temperatures[i]);
                    } else {
                        densities[i] = pressures[i]/(R_air*temperatures[i]);
                    }
                }
            }
        }
    }
    
    private double isoPresRatio(double hf, double h1, double Temp) {
        return Math.exp(-G0/(R_air*Temp)*(hf-h1));
    }
    
    private double lapsePresRatio(double lapse, double Tf, double T1) {
        return (Tf/T1)*Math.exp(-G0/(R_air*lapse));
    }
    
    @Override
    void calc() {   
        if(this.altitude > atmLimit) {
            this.temp = spaceTemp;
            this.pres = 0;
            this.dens = 0;
            return;
        }   
        //note calculations are made on GEOPOTENTIAL ALTITUDE
        int i = 0;
        while(this.geoaltitude < altitudes[i]){i++;}
        double dh = this.geoaltitude - altitudes[i];
        double hratio = dh/(altitudes[i+1]-altitudes[i]);
        this.temp = lapses[i]*dh+temperatures[i];
        if(lapses[i] == 0) {
            this.pres = pressures[i]*isoPresRatio(this.geoaltitude, altitudes[i], temperatures[i]); //temperature offset here? 
        } else {
            this.pres = pressures[i]*lapsePresRatio(lapses[i], this.temp, temperatures[i]);
        }
        this.temp += tempOffset;
        if(MMs != null && MMs.length > altitudes.length) {
            this.MM = (MMs[i+1]-MMs[i])*hratio+MMs[i];
            this.R_air = Physics.R/this.MM;
        }
        this.dens = this.pres/(this.R_air*this.temp);
        if(gammas != null && gammas.length > altitudes.length) {
            this.gamma = (gammas[i+1]-gammas[i])*hratio+gammas[i];
        }
    }
    
}
