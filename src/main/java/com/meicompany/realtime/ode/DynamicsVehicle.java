/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime.ode;

import com.meicompany.realtime.vehicle.Vehicle;

/**
 *
 * @author mpopescu
 */
public class DynamicsVehicle extends Dynamics {
    private Vehicle v;
    
    public DynamicsVehicle(Vehicle v) {
        this.v = v;
    }

    @Override
    public double[] calc(double[] state, double time) {
        v.setState(state,time);
        return v.update();
    }
}
