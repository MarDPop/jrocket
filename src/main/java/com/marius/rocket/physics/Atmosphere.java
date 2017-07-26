/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public abstract class Atmosphere {
    
    double altitude;
    double temp;
    double tempoffset;
    double pres;
    double dens;
    double gamma;
    double MM;
    double R_air;
    
    abstract void calc();
    
    public double getTemp() {
        return this.temp;
    }
    
    public double getPres() {
        return this.pres;
    }
    
    public double getDens() {
        return this.dens;
    }
    
    public double getGamma() {
        return this.gamma;
    }
    
    public double getAltitude() {
        return this.altitude;
    }
    
    public double getMM() {
        return this.MM;
    }
    
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
    
    public void setMM(double MM) {
        this.MM = MM;
    }
    
    public void setTempOffset(double tempoffset) {
        this.tempoffset = tempoffset;
    }
    
    public void setAltitute(double altitude) {
        this.altitude = altitude;
        calc();
    }
    
}
