/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class CombustionChamber {
    protected double temperature;
    protected double pressure;
    protected Resource fuel;
    protected Resource oxydizer;
    protected double gamma;
    protected double cp;
    protected double cv;
    protected double MolarMass;
    
    public double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
    public double getPressure() {
        return pressure;
    }
    
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
    
    public double getGamma() {
        return gamma;
    }
    
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
    
    public double getMM() {
        return MolarMass;
    }
    
    public void setMM(double MolarMass) {
        this.MolarMass = MolarMass;
    }
    
    
}
