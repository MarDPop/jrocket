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
public class LeapFrog extends ODE {  
    
    public LeapFrog(double dt) {
        this.dt = dt;
    }
    
    @Override
    public double step() {
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].getXYZ()[0] = LA.multiply(bodies[i].getXYZ()[1], dt/2);
        }
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].update(time,dt);
            bodies[i].getXYZ()[1] = LA.multiply(bodies[i].getXYZ()[2], dt);
        }
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].getXYZ()[0] = LA.multiply(bodies[i].getXYZ()[1], dt/2);
        }
        return super.step();
    }
    
    
    
}
