/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

import com.marius.rocket.physics.Objects.Body;

/**
 *
 * @author n5823a
 */
public class RK2 extends ODE {
    public double[][] old;
    
    public RK2(double dt) {
        this.dt = dt;
    }
    
    @Override
    public void init() {
        old = new double[this.bodies.length][6];
        super.init();
    }
    
    @Override
    public double step() {
        
        double h = dt/2;
        for(int i = 0; i < this.bodies.length; i++){
            old[i][0] = bodies[i].getXYZ()[0][0];
            old[i][1] = bodies[i].getXYZ()[0][1];
            old[i][2] = bodies[i].getXYZ()[0][2];
            old[i][3] = bodies[i].getXYZ()[1][0];
            old[i][4] = bodies[i].getXYZ()[1][1];
            old[i][5] = bodies[i].getXYZ()[1][2];            
            bodies[i].getXYZ()[0][0] += h * bodies[i].getXYZ()[1][0];
            bodies[i].getXYZ()[0][1] += h * bodies[i].getXYZ()[1][1];
            bodies[i].getXYZ()[0][2] += h * bodies[i].getXYZ()[1][2];
            bodies[i].getXYZ()[1][0] += h * bodies[i].getXYZ()[2][0];
            bodies[i].getXYZ()[1][1] += h * bodies[i].getXYZ()[2][1];
            bodies[i].getXYZ()[1][2] += h * bodies[i].getXYZ()[2][2];
        }
        for(int i = 0; i < this.bodies.length; i++){
            bodies[i].update(time);
            bodies[i].getXYZ()[0][0] = old[i][0] + dt * bodies[i].getXYZ()[1][0];
            bodies[i].getXYZ()[0][1] = old[i][1] + dt * bodies[i].getXYZ()[1][1];
            bodies[i].getXYZ()[0][2] = old[i][2] + dt * bodies[i].getXYZ()[1][2];
            bodies[i].getXYZ()[1][0] = old[i][3] + dt * bodies[i].getXYZ()[2][0];
            bodies[i].getXYZ()[1][1] = old[i][4] + dt * bodies[i].getXYZ()[2][1];
            bodies[i].getXYZ()[1][2] = old[i][5] + dt * bodies[i].getXYZ()[2][2];
        }
        for (Body bodie : this.bodies) {
            bodie.update(time);
        }
        
        return super.step();
    }  
}
