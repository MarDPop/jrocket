/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.*;

/**
 *
 * @author n5823a
 */
public class Force {
    //NOTE THIS IS A DISCRETE FORCE (point), NEED TO ADD SURFACE AND BODY FORCE
    protected Frame ref;
    protected double[] vec = new double[3]; //vector in ref frame
    protected double mag;
    protected double[] center = new double[3]; //point at which sum of force passes through
    public boolean internal = true; // not necessary if frame is in body
    
    public void setFrame(Frame ref) {
        this.ref = ref;
    }
    
    public Frame getFrame() {
        return ref;
    }
    
    public void set(double[] vec){
        this.vec=vec;
    }
    
    public double[] get() {
        return vec;
    }
    
    public void overrideMag(double mag) {
        this.mag = mag;
    }
    
    public double magnitude() {
        this.mag = LA.mag(vec);
        return mag;
    }
    
    public void setCenter(double[] center) {
        this.center = center;
    }
    
    public double[] getCenter() {
        return center;
    }
    
    public void update(double time, double dt){}
    
}
