/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import com.marius.rocket.Math.ODE;
import com.marius.rocket.physics.Body;
import com.marius.rocket.physics.Frame;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Simulation {
    protected double start;
    protected double end;
    protected double t;
    private Frame origin = new Frame();
    public ArrayList<Body> bodies = new ArrayList<>();
    
    public Simulation() {      
    }
    
    public void setStart(double start) {
        this.start = start;
    }
    
    public double getStart() {
        return start;
    }
    
    public void setEnd(double end) {
        this.end = end;
    }
    
    public double getEnd() {
        return end;
    }
    
    public void changeFrame(Frame origin) {
        this.origin = origin;
    }
    
    public void Prepare() {
        t = start;
    }
    
    public void Run() {
        while(t < end) {
            double dt = 1;
            t += dt;
        }
    }
    
}
