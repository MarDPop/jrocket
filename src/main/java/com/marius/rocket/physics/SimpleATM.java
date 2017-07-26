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
public class SimpleATM extends Atmosphere{
    private final double scale_pressure;
    private final double scale_height;
    private final double cons;
    
    public SimpleATM(double gamma, double temp, double scale_pressure, double scale_height, double R_air){
        this.gamma = gamma;
        this.temp = temp;
        this.scale_pressure = scale_pressure;
        this.scale_height= scale_height;
        this.cons = R_air*temp;
    }
    
    @Override
    void calc() {
        this.pres = scale_pressure*Math.exp(-this.altitude/scale_height);
        this.dens = this.pres / cons;
    }
    
}
