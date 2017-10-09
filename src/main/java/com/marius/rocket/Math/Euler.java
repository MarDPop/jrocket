/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

/**
 *
 * @author n5823a
 */
public class Euler extends ODE {    
    
    public Euler(double dt) {
        this.dt = dt;
    }
    
    public static void step(double[] x, double[] dx, double dt) {
        for(int i = 0; i < x.length; i++) {
            x[i] += dx[i]*dt;
        }
    }
    
    @Override
    public void step() {
        
    }  
    
}
