/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.Globals;
import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Physics;
import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class Gravity extends Force {
    private final double mu;
    private double m;
    public final Body main;
    public final SolarsystemBody[] list; // was formerly body, please note
    
    public Gravity(Body main, SolarsystemBody[] list) {
            this.ref = main;
            this.main = main;
            this.mu = main.getMass()*Physics.GRAVITY;
            this.m = main.getMass();
            this.list = list; 
            this.internal = false;
            calc();
    }
    
    public double[] calc() {
        double[] pos = Arrays.copyOf(main.getXYZ()[0],3);
        this.vec = new double[3];
        for(SolarsystemBody body : list) {
            double[] R = Arrays.copyOf(body.getXYZ()[0],3);
            LA.subtract(R,pos);
            double cons = body.MU/(Math.pow(LA.mag(R),3)); //most accurate to use MU
            LA.add(this.vec,LA.multiply(R,cons));
        }
        return LA.multiply(this.vec, m);
    }
    
    @Override 
    public void update(){
        this.m = main.getMass();
        calc();
        if(Globals.outputtoscreen) {
            System.out.println("---------- Gravity -----------");
            System.out.println("Gravity in Newtons: " + Arrays.toString(vec));
            System.out.println("Accel to grav: " + LA.mag(vec)/m);
        }
    }
    
}
