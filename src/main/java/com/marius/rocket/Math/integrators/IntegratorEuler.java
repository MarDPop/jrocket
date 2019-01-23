/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

/**
 *
 * @author mpopescu
 */
public class IntegratorEuler extends Integrator {
    private final double[] dx;
    
    public IntegratorEuler(double startTime, double endTime, double[] initialState, double dt) {
        this(startTime, endTime, initialState, new double[initialState.length][initialState.length],new double[]{1,1e-3,1e-4,1e-2,0});
        this.dt = dt; //step size
    }
    
    public IntegratorEuler(double startTime, double endTime, double[] initialState, double[][] sigma, double[] options ) {
        super(startTime, endTime, initialState, sigma,options, true);
        this.dx = new double[nStates];
    }
    
    @Override
    public void step(){
        System.arraycopy(dynamics.calc(x,time),0,dx,0,nStates);
        for(int i = 0; i< 6; i++) {
            x[i] += dx[i]*dt;
        }
        time += dt;
    }
}
