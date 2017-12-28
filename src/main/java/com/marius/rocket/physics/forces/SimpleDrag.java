/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.Globals;
import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Environment;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class SimpleDrag extends Force{
    public double CD;
    public double A;
    private Environment env;
    
    public SimpleDrag(double CD, double A, Environment env) {
        this.CD = CD;
        this.A = A;
        this.env = env;
        this.internal = false;
    }
    
    public void setEnv(Environment env) {
        this.env = env;
    }
    
    public void setCD(double CD){
        this.CD = CD;
    }
    
    public void setArea(double A){
        this.A = A;
    }
    
    @Override
    public void update(double time, double dt) {
        double[] v = Arrays.copyOf(env.freestream_velocity, 3);
        double v_ = LA.mag(v);
        this.vec = (v_ > 0) ? LA.multiply(v,-CD*env.Q*A/v_) : v;
        if(Globals.outputtoscreen) {
            System.out.println("---------- Drag -----------");
            System.out.println("Drag in Newtons " + Arrays.toString(vec));
            System.out.println("Dynamic Pressure " + env.Q + "Pa");
        }
        
    }
    
}
