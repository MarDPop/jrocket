/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Body;
import com.marius.rocket.physics.Physics;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class Gravity extends Force {
    private double mu;
    public final Body main;
    public final Body[] list;
    
    public Gravity(Body main, Body[] list) {
            this.ref = main;
            this.main = main;
            this.list = list;           
            calc();
    }
    
    public double[] calc() {
        this.mu = main.getMass()*Physics.G;
        //check if references
        double[] pos = Arrays.copyOf(main.getXYZ()[0],3);
        System.out.println("position of rocket");
        System.out.println(Arrays.toString(pos));
        vec = new double[3];
        for(Body body : list) {
            double[] R = Arrays.copyOf(body.getXYZ()[0],3);
            LA.subtract(R,pos);
            double cons = mu*body.getMass()/(Math.pow(LA.mag(R),3));
            LA.add(vec,LA.multiply(R,cons));
        }
        System.out.println("grav");
        System.out.println(Arrays.toString(vec));
        return vec;
    }
    
    @Override 
    public void update(double time, double dt){
        calc();
    }
    
}
