/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime;

/**
 *
 * @author mpopescu
 */
public class BoundingBox {
    public final double[] longitudes = new double[4];
    public final double[] latitudes = new double[4];
    
    public BoundingBox(double[] stats){
        double p = Math.sqrt(stats[4]/stats[2]/stats[3]);
    }
}
