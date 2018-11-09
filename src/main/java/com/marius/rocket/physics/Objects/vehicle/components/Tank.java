/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components;

import com.marius.rocket.physics.Objects.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class Tank extends Component {
    
    protected Resource fluid;
    protected double emptymass;
    protected double flowrate;
    
    public Tank(double emptymass) {
        super(emptymass);
        this.emptymass = emptymass;
    }
    
    public void setResource(Resource in) {
        this.fluid = in;
    }
    
    public Resource getResource() {
        return fluid;
    }
    
    public boolean isNotEmpty() {
        return fluid.getAmount() > 0;
    }
    
    public void setFlowrate(double x) {
        this.flowrate = x;
    }
    
    public double getFlowrate() {
        return this.flowrate;
    }
    
    @Override
    public double getMass() {
        return this.mass;
    }
    
    @Override
    public double updateMass() {
        this.mass = emptymass+fluid.getMass();
        return this.mass;
    }
    
}
