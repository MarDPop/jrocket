/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.dynamics;

import com.marius.rocket.physics.Objects.atmospheres.AtmosphereBasic;
import com.marius.rocket.physics.Objects.planets.Earth;

/**
 *
 * @author mpopescu
 */
public class DynamicsLifting extends Dynamics{
    private double BC_super;
    private double BC_sub;
    private double L2D;
    private final double LAMBDA = 1.4*8.314/0.029;
    private final double M_lower = 0.7;
    private final double M_upper = 1.4;
    private final double A_lower;
    private final double A_upper;
    private final double B_lower;
    private final double B_upper;
    private final double D;
    
    // temp variables
    double[] v = new double[3];
    private boolean hit;
    private double g;
    private double d;
    private double speed;
    
    public final int CYLINDER = 2;
    public final int SPHERE = 2;
    public final int PLATE = 3;
    public final int RECTANGLE = 1;
    public final int CONE = 1;
    
    private AtmosphereBasic atm = new AtmosphereBasic();
    
    public DynamicsLifting(int nStates, double BC_super, double BC_sub, int shape){
        super(nStates);
        if(nStates < 6) {
            throw new IllegalArgumentException("initial state vector must be at least 6 numbers for position and velocity");
        } 
        this.BC_super = BC_super*2;
        this.BC_sub = BC_sub*2;
        double x_0 = M_lower-1;
        this.A_lower = 2*BC_super/x_0/x_0/x_0;
        this.B_lower = -1.5*A_lower*x_0;
        x_0 = M_upper-1;
        this.A_upper = 2*BC_sub/x_0/x_0/x_0;
        this.B_upper = -1.5*A_upper*x_0;
        this.D = BC_super+BC_sub;
        this.hit = false;
        if(shape == 1) {
            this.L2D = 0.01;
        }
        if(shape == 2) {
            this.L2D = 0.03;
        }
        if(shape == 3) {
            this.L2D = 0.05;
        }
    }
    
    @Override
    public double[] calc(double[] x, double t) {
        dx[0] = x[3];
        dx[1] = x[4];
        dx[2] = x[5];
        g = Math.sqrt(x[0]*x[0]+x[1]*x[1]+x[2]*x[2]);
        if ((g-Earth.R_EARTH_MEAN) < 0) {
            hit = true;
        } else {
            v[0] = -x[3]+x[1]*Earth.STANDARD_ROTATION_RATE;
            v[1] = -x[4]-x[0]*Earth.STANDARD_ROTATION_RATE;
            v[2] = -x[5];
            speed = Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
            // Drag Coefficient smoothing
            double M = d/Math.sqrt(LAMBDA*298);
            double BC = BC_sub;
            if (M > M_lower) {
                if (M > M_upper) {
                    BC = BC_super;
                } else {
                    double M_temp = M-1;
                    double M_temp_2 = M_temp*M_temp;
                    if(M_temp < 0) {
                        BC = M_temp_2*(A_lower*M_temp + B_lower)+D;
                    } else {
                        BC = M_temp_2*(A_upper*M_temp + B_upper)+D;
                    }
                }
            }     
            d = atm.getDensity(g-Earth.R_EARTH_MEAN)*speed/BC;
            g = -Earth.STANDARD_GRAVITATIONAL_PARAMETER/(g*g*g)+d*speed*L2D; // l2d is assumed in direction of weight
            dx[3] = x[0]*g + v[0]*d;
            dx[4] = x[1]*g + v[1]*d;
            dx[5] = x[2]*g + v[2]*d;
        }
        return dx;
    }
    
    @Override
    public boolean stop() {
        return hit;
    }
    
    private void setLift2Drag(int shape) {
        
    }
}
