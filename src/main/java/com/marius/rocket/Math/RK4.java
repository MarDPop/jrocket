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
public class RK4 extends ODE {
    private double[][] k1,k2,k3,k4;
    
    public RK4(double dt) {
        this.dt = dt;
    }
    
    @Override
    public void init() {
        k1 = new double[this.bodies.length][6];
        k2 = new double[this.bodies.length][6];
        k3 = new double[this.bodies.length][6];
        k4 = new double[this.bodies.length][6];
        super.init();
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
            bodies[i].update(time,dt);
        }
    }
    
    @Override
    public double step() {
        for(int i = 0; i < this.bodies.length; i++){

                    
        }
        return super.step();
    }  
}
