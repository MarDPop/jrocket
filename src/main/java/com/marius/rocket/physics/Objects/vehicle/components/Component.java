/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components;

import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Objects.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class Component extends Body {
    
    protected double temp;
    protected double massRate;
    
    public Component[] parts;
    public Controller[] controllers;
    public Connection[] connections;
    public Resource[] resources;
    protected Resource[] resourceRate;
    
    public Component(double mass){
        super(mass);
        this.onrails = true;
    }
    
    @Override
    public void setState(double[] state) {
        super.setState(state);
        this.mass = state[6];
    }
    
    @Override
    public double[] getStateRate() {
        double[] out = new double[6];
        out[0] = xyz[1][0];
        out[1] = xyz[1][1];
        out[2] = xyz[1][2];
        out[3] = xyz[2][0];
        out[4] = xyz[2][1];
        out[5] = xyz[2][2];
        out[6] = massRate;
        return out;
    }
}
