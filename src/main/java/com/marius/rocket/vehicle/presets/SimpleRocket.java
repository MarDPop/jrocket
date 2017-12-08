/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.physics.Environment;
import com.marius.rocket.physics.forces.SimpleDrag;
import com.marius.rocket.vehicle.components.SimpleThruster;
import com.marius.rocket.vehicle.*;
import com.marius.rocket.vehicle.components.Component;
/**
 *
 * @author n5823a
 */
public class SimpleRocket extends Rocket {
    
    public SimpleRocket(Environment env) {
        super();
        env.setBody(this);
        this.environment = env;
        this.ComponentList.add(new SimpleThruster(100,250,2,0.1));
        Component shell = new Component();
        shell.setMass(1);
        SimpleDrag d = new SimpleDrag(0.5, 0.2, env);
        shell.forces.add(d);
        this.ComponentList.add(shell);
        this.collectComponents();
        
    }
    
    public void initUp() {
        this.calcSphericalFromCartesian();
        this.calcSphericalUnitVectors();
        this.orientation = this.spherical_unit_vectors;
    }
    
}
