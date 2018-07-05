/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.chemistry.Molecules.Molecule;
import com.marius.rocket.physics.Objects.Fluid;
import com.marius.rocket.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class CombustionChamber {
    protected double temperature;
    protected double pressure;
    public Resource[] input;
    public Fluid exitGas;
    protected double volume;
    protected double length;
    protected double radius;
    protected double crossArea;
    
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
    
    public void setSize(double l, double r) {
        this.length = l;
        this.radius = r;
        this.crossArea = Math.PI*r*r;
        this.volume = crossArea*l;
    }
    
}
