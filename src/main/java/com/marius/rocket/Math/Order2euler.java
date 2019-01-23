/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

import static com.marius.rocket.Math.Euler.step;

/**
 *
 * @author n5823a
 */
public class Order2euler extends ODE{
    public double[] a; // a must be same length as x
    
    public Order2euler(double dt) {
        this.dt = dt;
    }
    
    public static double step(double x, double dx, double a, double dt) {
        return x+=dx*dt + 0.5*a*dt*dt;
    }
    
    public static void step(double[] _x, double[] _dx, double[] _a, double dt) {
        for(int i = 0; i < _x.length; i++) {
            _x[i] += _dx[i]*dt + 0.5*_a[i]*dt*dt;
            _dx[i] += _a[i]*dt;
        }
    }
    
    @Override
    public void init() {
        x = new double[3*this.bodies.length];
        dx = new double[3*this.bodies.length];
        a = new double[3*this.bodies.length];
    }
    
    public void stateFromBodies() {
        for(int i = 0; i < this.bodies.length; i++){
            x[3*i] = bodies[i].getXYZ()[0][0];
            x[3*i+1] = bodies[i].getXYZ()[0][1];
            x[3*i+2] = bodies[i].getXYZ()[0][2];
            dx[3*i] = bodies[i].getXYZ()[1][0];
            dx[3*i+1] = bodies[i].getXYZ()[1][1];
            dx[3*i+2] = bodies[i].getXYZ()[1][2];
            a[3*i] = bodies[i].getXYZ()[2][0];
            a[3*i+1] = bodies[i].getXYZ()[2][1];
            a[3*i+2] = bodies[i].getXYZ()[2][2];
        }
    }
    
    public void stateToBodies() {
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].getXYZ()[0][0] = x[3*i];
            bodies[i].getXYZ()[0][1] = x[3*i+1];
            bodies[i].getXYZ()[0][2] = x[3*i+2];
            bodies[i].getXYZ()[1][0] = dx[3*i];
            bodies[i].getXYZ()[1][1] = dx[3*i+1];
            bodies[i].getXYZ()[1][2] = dx[3*i+2];
            bodies[i].update(time);
        }
    }
    
    @Override
    public double step() {
        stateFromBodies();
        step(x,dx,a,dt);
        stateToBodies();
        return super.step();
    }
}
