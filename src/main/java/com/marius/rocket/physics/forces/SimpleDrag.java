/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

import com.marius.rocket.physics.Atmosphere;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class SimpleDrag extends Force{
    public final double CD;
    private Atmosphere atm;
    private double[] v;
    
    public SimpleDrag(double CD, Atmosphere atm, double[] v) {
        this.CD = CD;
        this.v = v;
    }
    
    @Override
    public void update(double time, double dt) {
        double[] temp = Arrays.copyOf(v,3);
    }
    
}
