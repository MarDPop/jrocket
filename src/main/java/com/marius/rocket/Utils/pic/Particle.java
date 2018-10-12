/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Utils.pic;

/**
 *
 * @author mpopescu
 */
public class Particle {
    public final double m;
    public final double q;
    
    protected double cons;
    
    public double[] x;
    public double[] v;
    
    public Particle(double m, double q) {
        this.m = m;
        this.q = q;
    }
    
    public void setConstant(double dt) {
        this.cons = q/m*dt/2;
    }
    
    public void move(double[] E, double[] B){
        E[0] *= cons;
        E[1] *= cons;
        E[2] *= cons;
        B[0] *= cons;
        B[1] *= cons;
        B[2] *= cons;
        double temp = (2/(1+B[0]*B[0]+B[1]*B[1]+B[2]*B[2]));
        double[] s = new double[]{temp*B[0],temp*B[1],temp*B[2]};
        add(v,E);
        double[] v_pre = new double[3];
        System.arraycopy(v, 0, v_pre, 0, 3);
        add(v_pre, cross(v_pre,B));
        add(v,cross(v_pre,s));
        add(v,E);
    }
    
    private double[] cross(double[] u, double[] v) {
        double[] y = new double[3];
        y[0] = u[1]*v[2] - u[2]*v[1];
        y[1] = u[2]*v[0] - u[0]*v[2];
        y[2] = u[0]*v[1] - u[1]*v[0];
        return y;
    }
    
    private void add(double[] a, double[] b) {
        a[0] += b[0];
        a[1] += b[1];
        a[2] += b[2];
    }
    
}
