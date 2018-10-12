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
public class Mesh2D {
    private double[][] Q;
    private double[][] V;
    private double[][] E;
    
    private double[] X;
    private double[] Y;
    
    Particle[] particles;
    
    private double dx = 0, dy = 0, d = 0;
    
    private final int m,n;
    
    private boolean isEvenSpaced = false;
    private boolean isEqualSpaced = false;
    
    public Mesh2D(double[] X, double[] Y) {
        this.m = X.length;
        this.n = Y.length;
        this.X = X;
        this.Y = Y;
    }
    
    public Mesh2D(int m, int n, double d) {
        this.m=m;
        this.n=n;
        this.isEqualSpaced = true;
    }
    
    public Mesh2D(int m, int n, double dx, double dy) {
        this.m=m;
        this.n=n;
        this.isEvenSpaced = true;
    }
    
    private void collect() {
        Q = new double[m][n];
        V = new double[m][n];
        if(isEqualSpaced){
            for(Particle p : particles) {
                int i = (int)Math.floor(p.x[0]/d);
                int j = (int)Math.floor(p.x[1]/d);
                double fact_x = (p.x[0]-d*i)/d;
                double fact_y = (p.x[1]-d*j)/d;
                Q[i][j] += (1-fact_x)*(1-fact_y)*p.q;
                Q[i][j+1] += (1-fact_x)*fact_y*p.q;
                Q[i+1][j] += (1-fact_y)*fact_x*p.q;
                Q[i+1][j+1] += fact_y*fact_x*p.q;
            }
        } else if(isEvenSpaced) {
            for(Particle p : particles) {
                int i = (int)Math.floor(p.x[0]/dx);
                int j = (int)Math.floor(p.x[1]/dy);
                double A = dx*dy;
                double fact_x = (p.x[0]-dx*i);
                double fact_y = (p.x[1]-dy*j);
                Q[i][j] += (dx-fact_x)*(dy-fact_y)*p.q/A;
                Q[i][j+1] += (dx-fact_x)*fact_y*p.q/A;
                Q[i+1][j] += (dy-fact_y)*fact_x*p.q/A;
                Q[i+1][j+1] += fact_y*fact_x*p.q/A;
            }
        } else {
            for(Particle p : particles) {
                
            }
        }
    }
    
    
}
