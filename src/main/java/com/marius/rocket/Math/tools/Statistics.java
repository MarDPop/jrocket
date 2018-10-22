/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.tools;

import static java.lang.Math.exp;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import static java.lang.Math.log;

/**
 *
 * @author mpopescu
 */
public class Statistics {
    
    
    /**
     * Calculates the probability density of x from variance sigma and mean of normal distribution
     * 
     * @param x
     * @param var
     * @param mean
     * @return 
     */
    public static double normalPDF(double x, double var, double mean) {
        double c = (x-mean);
        return exp(-c*c/var)/sqrt(2*PI*var);
    }
    
    /**
     * Error function quick, error less than 2e-4
     * 
     * @param x
     * @return 
     */
    public static double erf(double x){
        double c = x*x;
        return sqrt(1 - exp(-0.147*c*(0.1872+c)/(0.147+c)));
    }
    
    /**
     * Error function quick, error less than 2e-4
     * 
     * @param x
     * @param terms
     * @return 
     */
    public static double erf2(double x, int terms){
        double c = x*x;
        double s = x;
        for(int n = 1; n <= terms; n++){
            double sn = 1;
            for(int k = 1; k <= n; k++){
                sn *= -c/k;
            }
            s += x/(2*n+1)*sn;
        }
        return 2/sqrt(PI)*s;
    }
    
    /**
     * Inverse Error function quick, error less than 2e-4
     * 
     * @param x
     * @return 
     */
    public static double inverf(double x){
        double c = log(1-x*x);
        double c2= 4.330746750799873+c/2;
        return sqrt(-4.330746750799873 - c/2 +sqrt(c2*c2-c/0.147));
    }
    
    /**
     * Complementary Error function don't use for less than x = 2 or terms less than 4
     * 
     * @param x
     * @param terms
     * @return 
     */
    public static double erfc(double x, int terms){
        double c = exp(-x*x)/sqrt(PI);
        double s = terms/x;
        while(--terms > 0) {
            s = x+terms/2/s;
        }
        return c/s;
    }
    
    
    
}
