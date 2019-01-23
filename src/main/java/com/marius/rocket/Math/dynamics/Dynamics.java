/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.dynamics;

/**
 *
 * @author mpopescu
 */
public abstract class Dynamics {
    public double[] dx;
    
    public Dynamics(int nStates){
        this.dx = new double[nStates];
    }
    
    public abstract double[] calc(double[] x, double t);
    
    public abstract boolean stop();
}
