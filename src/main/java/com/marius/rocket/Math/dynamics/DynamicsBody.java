/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.dynamics;

import com.marius.rocket.physics.Objects.Body;

/**
 *
 * @author mpopescu
 */
public class DynamicsBody extends Dynamics {
    
    Body main;
    
    public DynamicsBody(Body main) {
        super(main.state.length);
    }
    
    public void calc(double[] x, double t) {
        
    }
    
}
