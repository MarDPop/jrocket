/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.physics.Environment;

/**
 *
 * @author n5823a
 */
public class Nozzle {
    public CombustionChamber chamber;
    public Environment env;
    protected double A_c; //chamber area
    protected double A_t; //throat area
    protected double A_e; //exit area
    protected double A_star;
    protected double AreaRatio; 
    protected double exitAngle;
    protected double massFlow;
    protected double ambientPressure;
    protected double exit_p;
    protected double exit_v;
    public double thrust; //READ ONLY
    
    public void setThroatArea(double A_t) {
        this.A_t = A_t;
    }
    
    public void setExitArea(double A_e) {
        this.A_e = A_e;
    }
    
    public double calcAreaRatio(){
        return AreaRatio = A_e/A_t;
    }
    
    public void setExitAngle(double exitAngle) {
        this.exitAngle = exitAngle;
    }
    
    public void setAmbientPressure(double ambientPressure) {
        this.ambientPressure = ambientPressure;
    }
    
    public double getExitVelocity() {
        return exit_v;
    }
    
    public double getMassFlow() {
        return massFlow;
    }
    
    public double getExitAngle() {
        return this.exitAngle;
    }
    
}
