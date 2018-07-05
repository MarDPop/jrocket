/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author n5823a
 */
public class StandardATM extends Atmosphere{
    
    protected double[] altitudes = {0,11000,20000,32000,47000,51000,71000,84852,90000,105000,1000000}; //breakpoints
    protected double[] temperatures = {288.15,216.65,216.65,228.65,270.65,270.65,214.65,186.95,186.95, 246.95,3.95};
    protected double[] pressures = null;
    protected double[] densities = null;
    protected double[] lapses = null;
    protected double[] MMs = null;
    protected double[] gammas = null;
    private double atmLimit = 1000000;
    private double spaceTemp = 298;
    private double sealevelpress = 101325;
    private double tempOffset = 0;
    private boolean assumeIdeal = true; 
    private int h;
    
    public StandardATM(Earth earth) {
        this.planet = earth;
        this.Radius = 6371000;
        this.G0 = 9.809;
        this.MM = 0.02897; //kg/mol
        this.gamma = 1.4;
        this.R_air = Fluid.R/this.MM;      
        precalc();
    }
    
    public StandardATM(String filename, Earth earth) {
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
        this.planet = earth;
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
        h = 0;
        lapses = new double[n];
        densities = new double[n];
        boolean calcPressures = true;
        if(pressures == null) {
            pressures = new double[n];
        } else if(pressures.length < n) {
            System.out.println("Pressures given is not long enough to fit data. Number of pressure points must match altitude points");
            pressures = new double[n];
        } else {
            calcPressures = false;
        }
        
        for(int i = 0; i < altitudes.length; i++) {
            if((i+1) < n) {
                double deltaH = altitudes[i+1]-altitudes[i];
                lapses[i] = (temperatures[i+1]-temperatures[i])/deltaH;
            }
            if(calcPressures) {
                if(i == 0) {
                    pressures[i] =  sealevelpress;
                } else {
                    if(assumeIdeal) {
                        if(lapses[i-1] == 0) {
                            pressures[i] = pressures[i-1]*isoPresRatio(altitudes[i], altitudes[i-1], temperatures[i]);
                        } else {
                            pressures[i] = pressures[i-1]*lapsePresRatio(lapses[i-1], temperatures[i], temperatures[i-1]);
                        }
                    }
                }               
            } 
            if(assumeIdeal) {
                if(i == 1) {
                    densities[i] = pressures[i]/(R_air*temperatures[i]);
                } else {
                    if(MMs != null && MMs.length > altitudes.length){
                        densities[i] = pressures[i]/((2*Fluid.R/(MMs[i]+MMs[i-1]))*temperatures[i]);
                    } else {
                        densities[i] = pressures[i]/(R_air*temperatures[i]);
                    }
                }
            }
        }
    }
    
    public double isoPresRatio(double hf, double h1, double Temp) {
        return Math.exp(-G0/(R_air*Temp)*(hf-h1));
    }
    
    public double lapsePresRatio(double lapse, double Tf, double T1) {
        return Math.pow((Tf/T1),-G0/(R_air*lapse));
    }
    
    @Override
    void calc() {   
        if(this.geoaltitude >= atmLimit) {
            this.temp = spaceTemp;
            this.pres = 0;
            this.dens = 0;
            return;
        }   
        if (this.geoaltitude < altitudes[h]) {
            while(this.geoaltitude < altitudes[h] ){
                h--;
                if(h < 0){h = 0; break;} //for some reason the short hand didn't seem to work
            }
        } else if(this.geoaltitude > altitudes[h+1]) {
            while(this.geoaltitude > altitudes[h+1] ){
                if(h++ >= altitudes.length){h--; break;}
            }
        }
        double dh = this.geoaltitude - altitudes[h];
        double hratio = dh/(altitudes[h+1]-altitudes[h]);
        this.temp = lapses[h]*dh+temperatures[h];
        if(lapses[h] == 0) {
            this.pres = pressures[h]*isoPresRatio(this.geoaltitude, altitudes[h], temperatures[h]); //temperature offset here? 
        } else {
            this.pres = pressures[h]*lapsePresRatio(lapses[h], this.temp, temperatures[h]);
        }
        this.temp += tempOffset;
        if(MMs != null && MMs.length > altitudes.length) {
            this.MM = (MMs[h+1]-MMs[h])*hratio+MMs[h];
            this.R_air = Fluid.R/this.MM;
        }       
        this.dens = this.pres/(this.R_air*this.temp); 
        if(gammas != null && gammas.length > altitudes.length) {
            this.gamma = (gammas[h+1]-gammas[h])*hratio+gammas[h];
        }
    }
    
}
