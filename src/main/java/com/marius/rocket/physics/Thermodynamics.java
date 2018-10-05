/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.physics.Objects.Fluid;
import com.marius.rocket.physics.Objects.vehicle.components.Component;

/**
 *
 * @author n5823a
 */
public class Thermodynamics {
    public static final short IDEAL_GAS = 0;
    public static final short CALORICALLY_PERFECT = 1;
    public static final short ADIABATIC = 2;
    public static final short ISENTROPIC = 3;
    public static final short ISOBARIC = 4;
    public static final short VAN_DER_WAALS = 10;
    public static final short PENG_ROBINSON = 11;
    
    protected double enthalpy;
    protected double internalEnergy;
    protected double temperature;
    protected double pressure;
    
    private Component c;
    private Fluid f;
    
    public Thermodynamics(Component c) {
        this.c = c;
    }
    
    public Thermodynamics(Fluid f) {
        this.f = f;
    }
    
    
}
