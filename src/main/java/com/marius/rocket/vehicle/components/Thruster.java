/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.physics.forces.Thrust;
import com.marius.rocket.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class Thruster extends Component {
    public final Thrust thrust;
    protected double currentThrust; // in newtons
    protected double massflow; //kg/s
    protected double isp; // s
    protected Resource[] requirements;
    protected double throttle;
    protected boolean active;
    
    public Thruster() { 
        this.thrust = new Thrust();
    }
    
    public void setRequirements(Resource[] requirements){
        this.requirements=requirements;
    }
    
    public Resource[] getRequirements(){
        return this.requirements;
    }
    
    public Thrust getThrust() {
        return thrust;
    }
    
    public double getMassFlow() {
        return massflow;
    }
    
    public void setThrottle(double throttle) {
        this.throttle = throttle;
    }
    
    public double getThrottle() {
        return this.throttle;
    }

}
