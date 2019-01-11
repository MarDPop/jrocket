/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime;

import com.meicompany.realtime.clustering.Centroid;

/**
 *
 * @author mpopescu
 */
public class PiRun {
    final double time;
    final Centroid[] centroids;
    int weight;
    
    public PiRun(double time, Centroid[] centroids) {
        this.time = time;
        this.centroids = centroids;
        weight = 0;
        for(int i = 0; i < centroids.length; i++) {
            weight += centroids[i].getNumber();
        }
    }
}
