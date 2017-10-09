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
public class Force {
    private double[] vec;
    
    public Force(){}
    
    public void set(double[] vec){
        this.vec=vec;
    }
    
    public double[] get() {
        return vec;
    }
    
}
