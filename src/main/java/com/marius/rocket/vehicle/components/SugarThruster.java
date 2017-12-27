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
public class SugarThruster extends Thruster{
    public double availablethrust;
    private Tank fuel;
    
    public SugarThruster(double thrust, double isp, double mass) {
        super();
        this.availablethrust = thrust;
        this.isp = isp;
        this.mass = mass;
        this.thrust.set(new double[]{thrust,0,0});
        this.thrust.setISP(isp);
        setMassflowByISP();
    }
    
    public void setTank(Tank fuel) {
        this.fuel = fuel;
    }
    
    @Override
    public void update(double time, double dt) {
        if(this.active) {
            thrust.set(new double[]{availablethrust,0,0});
        } else {
            thrust.set(new double[]{0,0,0});
        }
    }
    
    public final double setMassflowByISP() {
        this.massflow = availablethrust/(9.806*isp);
        return massflow;
    }
}
