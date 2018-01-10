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
    
    public void setGamma(double Gam) {
        this.Gam = Gam;
    }
    
    public void setTemp(double temp) {
        this.temp = temp;
    }
    
    public void setPres(double pres) {
        this.pres = pres;
    }
    
    public void setCv(double Cv) {
        this.Cv = Cv;
    }
    
    public void setCp(double Cp) {
        this.Cp = Cp;
    }
    
    public double calcSpeedOfSound() {
        return a = Math.sqrt(Gam*R_specific*temp);
    }
    
}
