/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

import com.marius.rocket.physics.Objects.Body;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author n5823a
 */
public class ODE {    
    public double[] x;
    public double[] dx;
    protected double dt;
    protected double time;
    protected double start_time = 0;
    protected double end_time;
    protected ArrayList<Double> times = new ArrayList<>();
    public ArrayList points = new ArrayList<>();
    public Body[] bodies;
    public int[] vehicles;
    
    protected HashMap<String,String> Options;
    
    public double step() {
        this.time += dt;
        this.points.add(x);
        this.times.add(time);
        return this.time;
    }
    
    public void run() {
        time = start_time;
        while(time < end_time) {
            
        }
    }
    
    public void init() {
        x = new double[6*this.bodies.length];
        dx = new double[6*this.bodies.length];
    }
    
    public void setOptions(HashMap<String,String> Options) {
        this.Options = Options;       
    } 
    
    public void setStartTime(double start) {
        this.start_time = start;
    }
    
    public double getStartTime() {
        return start_time;
    }
    
    public void setEndTime(double end_time) {
        this.end_time = end_time;
    }
    
    public double getEndTime() {
        return end_time;
    }
    
    public void setTime(double time) {
        this.time = time;
    }
    
    public double getTime() {
        return time;
    }
    
    public void setTimestep(double dt) {
        this.dt=dt;
    }
    
    public double getTimestep() {
        return dt;
    }
    
    public double[][] exportState(int[][] statemap) {
        int m = statemap.length;
        int n = statemap[0].length;
        double[][] out = new double[m][n];
        int count = 0;
        int i = 0;
        int j = 0;
        n--;
        m--;
        while(count++ < x.length) {
            out[i][j] = x[statemap[i][j]];
            if(i < m) {
                i++;
            } else {
                i = 0;
            }
            if(j < n) {
                j++;
            } else {
                j = 0;
            }  
        }
        return out;
    }
    
    public void updateForces(){
        for(Body body : bodies) {
            body.update(time,dt);
        }
    }
    
}
