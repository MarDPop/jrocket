/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

import static java.lang.Math.sqrt;

/**
 *
 * @author mpopescu
 */
public class IntegratorRK45 extends Integrator {
    private double[] k1;
    private double[] k2;
    private double[] k3;
    private double[] k4;
    private double[] k5;
    private double[] k6;
    
    private final double[] x_temp_new;
    private final double[] residual;
    
    private final double MAX_POSITION_ERROR = 1e-2;
    
    public IntegratorRK45(double startTime, double endTime, double[] initialState) {
        this(startTime, endTime, initialState, new double[initialState.length][initialState.length],new double[]{1,1e-3,1e-4,1e-2,0});
        dt = 1; //initial step
    }
    
    public IntegratorRK45(double startTime, double endTime, double[] initialState, double[][] covariance, double[] options ) {
        super(startTime, endTime, initialState, covariance,options, true);
        k1 = new double[nStates];
        k2 = new double[nStates];
        k3 = new double[nStates];
        k4 = new double[nStates];
        k5 = new double[nStates];
        k6 = new double[nStates];
        x_temp_new = new double[nStates];
        residual = new double[nStates];
    }
    
    @Override
    public void step(){
        byte loop = 0;
        while(loop < 20) {
            System.arraycopy(x, 0, x_old, 0, nStates);
            System.arraycopy(dynamics.calc(x,time),0,k1,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + k1[i]*dt/4;
            }
            System.arraycopy(dynamics.calc(x,time+dt/4),0,k2,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + (k1[i]*3 + k2[i]*9)*dt/32;
            }
            System.arraycopy(dynamics.calc(x,time+dt*0.375),0,k3,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + (k1[i]*1932 - k2[i]*7200 + k3[i]*7296)*dt/2197;
            }
            System.arraycopy(dynamics.calc(x,time+dt*0.923076923076923),0,k4,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + (k1[i]*2.0324074074 - k2[i]*8 + k3[i]*7.17348927875 - k4[i]*0.205896686)*dt;
            }
            System.arraycopy(dynamics.calc(x,time+dt),0,k5,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + (-k1[i]*0.2962962963 + k2[i]*2 - k3[i]*1.38167641325536 + k4[i]*0.45297270955165692 - k5[i]*0.275)*dt;
            }
            System.arraycopy(dynamics.calc(x,time+dt/2),0,k6,0,nStates);
            for(int i = 0; i< 6; i++) {
                x[i] = x_old[i] + (k1[i]*2375 + k3[i]*11264 + k4[i]*10985 - k5[i]*4104)*dt/20520;
            }
            for(int i = 0; i< 6; i++) {
                x_temp_new[i] = x_old[i] + (k1[i]*33440 + k3[i]*146432 + k4[i]*142805 - k5[i]*50787 + k6[i]*10260)*dt/282150;
                residual[i] = x_temp_new[i] - x[i];
            }
            double R = maxPos(residual)/dt;
            if (R < MAX_POSITION_ERROR*0.001) {
                dt = 4*dt;
                loop = 20;
            } else {
                double delta = 0.84*sqrt(sqrt(MAX_POSITION_ERROR/R));
                if(R<MAX_POSITION_ERROR) {
                    dt = delta*dt;
                    loop = 20;
                } else {
                    x = x_old;
                    dt = delta*dt;
                    loop++;
                }
            }
        }
        if(dt > maxDt) {
            dt = maxDt;
        } else if(dt < minDt) {
            dt = minDt;
        }
        time += dt;
        //https://math.okstate.edu/people/yqwang/teaching/math4513_fall11/Notes/rungekutta.pdf
    }
    
    private double norm(double[] vec) {
        double sum = 0;
        for(int i = 0; i < nStates; i++) sum += vec[i]*vec[i];
        return sqrt(sum);
    }
    
    private double maxPos(double[] x) {
        double max = 0;
        for(int i = 0; i < 3; i++) {
            if (max < Math.abs(x[i])) {
                max = Math.abs(x[i]);
            }
        }
        return max;
    }
}
