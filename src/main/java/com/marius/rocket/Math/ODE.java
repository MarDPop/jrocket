/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

import com.marius.rocket.physics.Body;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author n5823a
 */
public abstract class ODE {    
    public double[] x;
    public double[] dx;
    protected double dt;
    protected ArrayList times;
    ArrayList points;
    public Body[] bodies;
    
    protected HashMap<String,String> Options;
    
    public abstract void step();
    
    public void setOptions(HashMap<String,String> Options) {
        this.Options = Options;       
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
    
}
