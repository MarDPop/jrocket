/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.dynamics;

import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;
import com.marius.rocket.physics.Objects.vehicle.Vehicle;

/**
 *
 * @author mpopescu
 */
public class DynamicsBody extends Dynamics {
    
    Vehicle main;
    SolarsystemBody[] System;
    boolean hit = false;
    
    public DynamicsBody(Vehicle main, SolarsystemBody[] System) {
        super(main.nStates);
        this.main = main;
        this.System = System;
    }
    
    @Override
    public double[] calc(double[] x, double t) {
        main.setState(x);
        for(SolarsystemBody s: System) {
            s.update(t);
        }
        main.update(t);
        return main.getStateRate();
    }
    
    @Override
    public boolean stop() {
        return hit;
    }
    
}
