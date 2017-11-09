/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.physics.forces.Thrust;

/**
 *
 * @author n5823a
 */
public class SimpleThruster extends Thruster {
    double availablethrust;
    
    public SimpleThruster(double thrust, double isp, double mass) {
        super();
        this.availablethrust = thrust;
        this.isp = isp;
        this.mass = mass;
        this.thrust.setISP(isp);
        this.thrust.overrideMag(thrust);
    }
    
    
    public double setMassflowByISP() {
        this.massflow = currentThrust/(9.806*isp);
        return massflow;
    }
}
