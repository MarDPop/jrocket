/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.physics.forces.Thrust;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class SimpleThruster extends Thruster {
    public double availablethrust;
    private final double emptymass;
    
    public SimpleThruster(double thrust, double isp, double mass, double emptymass) {
        super();
        this.availablethrust = thrust;
        this.isp = isp;
        this.mass = mass;
        this.emptymass = emptymass;
        this.thrust.set(new double[]{thrust,0,0});
        this.thrust.setISP(isp);
        setMassflowByISP();
    }
    
    @Override
    public void update(double time, double dt) {
        if (this.mass > emptymass) {
            this.mass -= this.massflow*dt;
        } else {
            thrust.set(new double[]{0,0,0});
        }
    }
    
    public final double setMassflowByISP() {
        this.massflow = availablethrust/(9.806*isp);
        return massflow;
    }
}
