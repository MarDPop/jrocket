/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects;

import com.marius.rocket.chemistry.Molecules.Molecule;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Fluid {
    //ASSUME IDEAL GAS
    public final static double R = 8.3144598; // Gas Constant kg m2 / s2 K mol
    public final static double Atm2PA = 101325; // PA
    
    public ArrayList<Molecule> species  = new ArrayList<>();
    protected double temp;
    protected double pres;
    protected double dens;
    protected double mass;
    protected double cp;
    protected double cv;
    protected double gam;
    protected double a;
    protected double internal_energy;
    protected double enthalpy;
    protected double viscosity;
    protected double molar_mass; //in kg/mol
    protected double specific_gas_constant;
    
    
    public void setMolarMass(double molar_mass) {
        this.specific_gas_constant = R/molar_mass;
        this.molar_mass = molar_mass;
    }
    
    public double getMolarMass() {
        return molar_mass;
    }
    
    public void setGamma(double gam) {
        this.gam = gam;
        //default to setting CP
        this.cp = gam*specific_gas_constant/(gam-1);
        this.cv = cp/gam;
    }
    
    public double getGamma() {
        return gam;
    }
    
    public void setTemperature(double temp) {
        this.temp = temp;
    }
    
    public double getTemperature() {
        return pres;
    }
    
    public void setPressure(double pres) {
        this.pres = pres;
    }
    
    public double getPressure() {
        return pres;
    }
    
    public void setCv(double cv) {
        this.cv = cv;
        if(gam > 0) {
            this.cp = cv*gam;
        } else if(cp > 0) {
            this.gam = this.cp/this.cv;
        }
    }
    
    public void setCp(double Cp) {
        this.cp = Cp;
        if(gam > 0) {
            this.cv = cp/gam;
        } else if(cv > 0) {
            this.gam = this.cp/this.cv;
        }
    }
    
    public double getCp(){
        return this.cp;
    }
    
    public double getCv(){
        return this.cv;
    }
    
    public double calcSpeedOfSound() {
        return a = Math.sqrt(gam*specific_gas_constant*temp);
    }
    
    public double getGasConstant() {
        return specific_gas_constant;
    }
    
}
