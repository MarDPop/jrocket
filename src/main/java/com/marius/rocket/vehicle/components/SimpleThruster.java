/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

/**
 *
 * @author n5823a
 */
public class SimpleThruster extends Thruster {
    
    public SimpleThruster(double thrust, double isp, double mass) {
        this.thrust = thrust;
        this.isp = isp;
        this.mass = mass;
    }
    
    public double setMassflowByISP() {
        this.massflow = thrust/(9.806*isp);
        return massflow;
    }
}
