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
public class Order2euler extends ODE{
    
    public Order2euler(double dt) {
        this.dt = dt;
    }
    
    public static double step(double x, double dx, double a, double dt) {
        return x+=dx*dt + 0.5*a*dt*dt;
    }
    
    public static void step(double[] x, double[] dx, double[] a, double dt) {
        for(int i = 0; i < x.length; i++) {
            x[i] += dx[i]*dt + 0.5*a[i]*dt*dt;
            dx[i] += a[i]*dt;
        }
    }
    
    @Override
    public void step() {
        
    }
}
