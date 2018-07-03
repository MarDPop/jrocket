/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.Math.LA;
import static com.marius.rocket.physics.Physics.*;
import com.marius.rocket.vehicle.components.Component;
import static java.lang.Math.PI;

/**
 *
 * @author mpopescu
 */
public class Electromagnetics {
    
    private Component C;
    private Fluid F;
    
    public Electromagnetics(Component c) {
        this.C = c;
    }
    
    public Electromagnetics(Fluid f) {
        this.F = f;
    }
    
    public static double[] getElectricField(double q, double[] r) {
        return LA.multiply(r, COLOUMB*q/Math.pow(LA.dot(r,r), 1.5));
    }
    
    public static double getESPotential(double q, double[] r) {
        return COLOUMB*q/LA.mag(r);
    }
    
    public static double getEnergyOfPhotonFromWavelength(double wl) {
        return COLOUMB*LIGHT_SPEED/wl;
    }
}
