/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components.thrusters;

import com.marius.rocket.physics.Objects.vehicle.components.Tank;

/**
 *
 * @author n5823a
 */
public class SugarThruster extends Thruster{
    public double availablethrust;
    private Tank fuel;
    private boolean steady;
    
    public SugarThruster(double thrust, double isp, double mass) {
        super();
        this.availablethrust = thrust;
        this.isp = isp;
        this.mass = mass;
        this.thrust.set(new double[]{thrust,0,0});
        this.thrust.setISP(isp);
        this.steady = false;
        setMassflowByISP();
    }
    
    public void setTank(Tank fuel) {
        this.fuel = fuel;
    }
    
    @Override
    public void update(double t) {
        if(this.active && fuel.isNotEmpty()) {
            if(!this.steady) {
                thrust.set(new double[]{availablethrust,0,0});
                fuel.setFlowrate(massflow); //THIS MEANS THAT TANK SHOULD BE UPDATED AFTER ENGINE
                this.steady = true;
            }
            return;
        } 
        thrust.set(new double[]{0,0,0});
        super.update(t);
    }
    
    public boolean isSpent() {
        return !fuel.isNotEmpty();
    }
    
    public final double setMassflowByISP() {
        this.massflow = availablethrust/(9.806*isp);
        return massflow;
    }
}
