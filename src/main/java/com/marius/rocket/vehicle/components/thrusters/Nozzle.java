/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

/**
 *
 * @author n5823a
 */
public class Nozzle {
    protected CombustionChamber chamber;
    protected double A_c; //chamber area
    protected double A_t; //throat area
    protected double A_e; //exit area
    protected double A_star;
    protected double AreaRatio; 
    protected double exitAngle;
    
}
