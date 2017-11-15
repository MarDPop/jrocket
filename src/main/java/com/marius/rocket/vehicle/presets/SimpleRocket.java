/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.vehicle.components.SimpleThruster;
import com.marius.rocket.vehicle.*;
import com.marius.rocket.vehicle.components.Component;
/**
 *
 * @author n5823a
 */
public class SimpleRocket extends Rocket {
    
    public SimpleRocket() {
        super();
        this.ComponentList.add(new SimpleThruster(100,150,2,1));
        Component shell = new Component();
        shell.setMass(1);
        this.ComponentList.add(shell);
        this.collectComponents();
    }
    
    public void initUp() {
        this.calcSphericalFromCartesian();
        this.calcSphericalUnitVectors();
        this.orientation = this.spherical_unit_vectors;
    }
    
}
