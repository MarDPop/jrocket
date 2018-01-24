/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class Flow extends Fluid {
    protected double speed;
    protected double area;
    
    protected double Reynolds; 
    protected double Lewis;  
    protected double Prandtl;
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
}
