/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime.clustering;

/**
 *
 * @author mpopescu
 */
public class Gaussian2D {
    private double x;
    private double y;
    
    private int number;
    
    private double sigma_x;
    private double sigma_y;
    private double sigma_xy;
    private double correlation; //pearson's correlation coefficient
    
    private double cons_A;
    
    public Gaussian2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Gaussian2D(double[] def) {
        this.x = def[0];
        this.y = def[1];
        this.sigma_x = def[2];
        this.sigma_y = def[3];
        this.sigma_xy = def[4];
        this.correlation = def[4]/def[2]/def[3];
        this.cons_A = 2*Math.PI*sigma_x*sigma_y*Math.sqrt(1-correlation*correlation);
    }
    
    public double pdf(double x, double y) {
        x = (x-this.x)/sigma_x;
        y = (y-this.y)/sigma_y;
        return Math.exp(x*x+y*(y-2*correlation*x))/cons_A; //eliminates a multiplication in exponent
    }
}
