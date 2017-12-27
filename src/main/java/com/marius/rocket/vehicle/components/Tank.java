/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.vehicle.components.Component;
import com.marius.rocket.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class Tank extends Component {
    
    protected Resource liquid;
    protected double emptymass;
    
    public Tank(double emptymass) {
        this.emptymass = emptymass;
    }
    
    public void setResource(Resource in) {
        this.liquid = in;
    }
    
    public Resource getResource() {
        return liquid;
    }
    
    
    
}
