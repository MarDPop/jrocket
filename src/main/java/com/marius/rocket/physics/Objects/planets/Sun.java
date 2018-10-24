/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.planets;

/**
 *
 * @author mpopescu
 */
public class Sun extends Planet {
    
    public static final double SOLAR_NOMINAL_IRRADIANCE = 1360.9; // W m2
    public static final double SOLAR_IRRADIANCE_VARIATION = 1.3;
    
    
    public Sun() {
        super(1.32712440018e20,696392000);
    }
    
    public double nominalIrradiance(double R) {
        return SOLAR_NOMINAL_IRRADIANCE*(R*R)/(AU*AU);
    }
}
