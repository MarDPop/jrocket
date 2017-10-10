/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.vehicle.components.SimpleThruster;
import com.marius.rocket.vehicle.*;
/**
 *
 * @author n5823a
 */
public class SimpleRocket extends Rocket {
    
    private final double emptymass = 2;
    public double mass;
    public SimpleThruster engine;
    
    public SimpleRocket() {
        super();
        this.mass = 6;
        engine = new SimpleThruster(100,100,1);
    }
    
    public void start() {
        this.engine.run();
    }
    
    
}
