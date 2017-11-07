/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

/**
 *
 * @author n5823a
 */
public class Euler extends ODE {   
    
    public Euler(double dt) {
        this.dt = dt;
    }
    
    public static double step(double x, double dx, double dt) {
        return x+=dx*dt;
    }
    
    public static void step(double[] x, double[] dx, double dt) {
        for(int i = 0; i < x.length; i++) {
            x[i] += dx[i]*dt;
        }
    }
    
    public void init() {
        x = new double[6*this.bodies.length];
        dx = new double[6*this.bodies.length];
    }
    
    public void stateFromBodies() {
        for(int i = 0; i < this.bodies.length; i++){
            x[6*i] = bodies[i].getXYZ()[0][0];
            x[6*i+1] = bodies[i].getXYZ()[0][1];
            x[6*i+2] = bodies[i].getXYZ()[0][2];
            x[6*i+3] = bodies[i].getXYZ()[1][0];
            x[6*i+4] = bodies[i].getXYZ()[1][1];
            x[6*i+5] = bodies[i].getXYZ()[1][2];
            dx[6*i] = bodies[i].getXYZ()[1][0];
            dx[6*i+1] = bodies[i].getXYZ()[1][1];
            dx[6*i+2] = bodies[i].getXYZ()[1][2];
            dx[6*i+3] = bodies[i].getXYZ()[2][0];
            dx[6*i+4] = bodies[i].getXYZ()[2][1];
            dx[6*i+5] = bodies[i].getXYZ()[2][2];
        }
    }
    
    public void stateToBodies() {
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].getXYZ()[0][0] = x[6*i];
            bodies[i].getXYZ()[0][1] = x[6*i+1];
            bodies[i].getXYZ()[0][2] = x[6*i+2];
            bodies[i].getXYZ()[1][0] = x[6*i+3];
            bodies[i].getXYZ()[1][1] = x[6*i+4];
            bodies[i].getXYZ()[1][2] = x[6*i+5];
            bodies[i].update();
        }
    }
    
    @Override
    public double step() {
        stateFromBodies();
        step(x,dx,dt);
        stateToBodies();
        return super.step();
    }  
    
    
    
}
