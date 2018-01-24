/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.chemistry.Molecules.Molecule;

/**
 *
 * @author n5823a
 */
public class Fluid {
    //ASSUME IDEAL GAS
    public final static double R = 8.3144598; // Gas Constant kg m2 / s2 K mol
    
    protected Molecule[] species;
    protected double temp;
    protected double pres;
    protected double dens;
    protected double mass;
    protected double Cp;
    protected double Cv;
    protected double Gam;
    protected double a;
    protected double internal_energy;
    protected double enthalpy;
    protected double viscosity;
    protected double MM;
    protected double R_specific;
    
    
    public void setMolarMass(double MM) {
        this.MM = MM;
    }
    
    public double getMolarMass() {
        return MM;
    }
    
    public void setGamma(double Gam) {
        this.Gam = Gam;
        //default to setting CP
        this.Cp = Gam*R_specific/(Gam-1);
        this.Cv = Cp/Gam;
    }
    
    public double getGamma() {
        return Gam;
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
    
    public void setCv(double Cv) {
        this.Cv = Cv;
        if(Gam > 0) {
            this.Cp = Cv*Gam;
        } else if(Cp > 0) {
            this.Gam = this.Cp/this.Cv;
        }
    }
    
    public void setCp(double Cp) {
        this.Cp = Cp;
        if(Gam > 0) {
            this.Cv = Cp/Gam;
        } else if(Cv > 0) {
            this.Gam = this.Cp/this.Cv;
        }
    }
    
    public double calcSpeedOfSound() {
        return a = Math.sqrt(Gam*R_specific*temp);
    }
    
}
