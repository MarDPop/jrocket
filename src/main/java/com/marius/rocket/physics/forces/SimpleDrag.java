/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Environment;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class SimpleDrag extends Force{
    public final double CD;
    public double A;
    private Environment env;
    
    public SimpleDrag(double CD, double A, Environment env) {
        this.CD = CD;
        this.A = A;
        this.env = env;
    }
    
    @Override
    public void update(double time, double dt) {
        double[] v = Arrays.copyOf(env.freestream_velocity, 3);
        this.vec = LA.multiply(v,-CD*env.Q*A/LA.mag(v));
    }
    
}
