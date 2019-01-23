/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

import static com.marius.rocket.Math.LA.multiply;
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
    private double[] k7;
    
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
        k7 = new double[nStates];
        residual = new double[nStates];
    }
    
    @Override
    public void step(){
        byte loop = 0;
        while(loop < 20) {
            System.arraycopy(x, 0, x_old, 0, nStates);
            System.arraycopy(multiply(dynamics.calc(x,time),dt),0,k1,0,nStates);
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + k1[i]/5.0;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt/5),dt),0,k2,0,nStates);
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + (k1[i]*3 + k2[i]*9)/40.0;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt*0.3),dt),0,k3,0,nStates);
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + (k1[i]*44 - k2[i]*168 + k3[i]*160)/45.0;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt*0.8),dt),0,k4,0,nStates);
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + (k1[i]*19372 - k2[i]*76080 + k3[i]*64448 - k4[i]*1908)/6561.0;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt*0.8888888888888),dt),0,k5,0,nStates);
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + k1[i]*2.84627525252525252525252525 - k2[i]*0.769696969696969696969696 - k3[i]*8.90642271774347246045359 + k4[i]*0.2784090909090909090909 - k5[i]*0.27353130360205831903945 ;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt),dt),0,k2,0,nStates); //k2 = k6
            for(int i = 0; i< nStates; i++) {
                x[i] = x_old[i] + k1[i]*9.114583333333333333333e-2 + k3[i]*0.449236298292902066486972 + k4[i]*0.651041666666666666666-k5[i]*0.3223761792452830188679245283+k2[i]*0.13095238095238095238095238;
            }
            System.arraycopy(multiply(dynamics.calc(x,time+dt),dt),0,k7,0,nStates); 
            for(int i = 0; i< nStates; i++) {
                //x_temp_new[i] = x_old[i] + k1[i]*0.08991319444444444444444444444444 + k3[i]*0.45348906858340820604971548367775 + k4[i]*0.6140625 -k5[i]*0.27151238207547169811320754716981 + k2[i]*0.08904761904761904761904761904762 + k7[i]*0.025;
                residual[i] = k1[i]*1.2326388888888888888888e-3 - k3[i]*4.25277029050613956274333632824e-3 + k4[i]*3.697916666666666666666666666667e-2 -k5[i]*5.086379716981132075471698113208e-2 + k2[i]*4.190476190476190476190476190476e-2 - k7[i]*2.5e-2;
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
