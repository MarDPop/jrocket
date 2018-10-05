/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.atmospheres;

/**
 *
 * @author n5823a
 */
public class AtmosphereBasic extends Atmosphere{
    private final double sl_pressure;
    private final double sl_density;
    private final double scale_height;
    private final double cons;
    
    public AtmosphereBasic(){
        this.gamma = 1.4;
        this.temp = 288;
        this.sl_pressure = 101325;
        this.scale_height= 8500;
        this.cons = 287*temp;
        sl_density = this.sl_pressure/this.cons;
    }
    
    public AtmosphereBasic(double gamma, double temp, double sl_pressure, double scale_height, double R_air){
        this.gamma = gamma;
        this.temp = temp;
        this.sl_pressure = sl_pressure;
        this.scale_height= scale_height;
        this.cons = R_air*temp;
        sl_density = this.sl_pressure/this.cons;
    }
    
    @Override
    void calc() {
        this.pres = sl_pressure*Math.exp(-this.altitude/scale_height);
        this.dens = this.pres / cons;
    }
    
    public double getDensity(double h) {
        return this.sl_density*Math.exp(-h/scale_height);
    }
}
