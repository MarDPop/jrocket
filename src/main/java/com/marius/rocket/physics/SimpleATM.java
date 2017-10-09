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
    private final double sl_pressure;
    private final double scale_height;
    private final double cons;
    
    public SimpleATM(){
        this.gamma = 1.4;
        this.temp = 298;
        this.sl_pressure = 101325;
        this.scale_height= 8500;
        this.cons = 287*temp;
    }
    
    public SimpleATM(double gamma, double temp, double sl_pressure, double scale_height, double R_air){
        this.gamma = gamma;
        this.temp = temp;
        this.sl_pressure = sl_pressure;
        this.scale_height= scale_height;
        this.cons = R_air*temp;
    }
    
    @Override
    void calc() {
        this.pres = sl_pressure*Math.exp(-this.altitude/scale_height);
        this.dens = this.pres / cons;
    }
    
}
