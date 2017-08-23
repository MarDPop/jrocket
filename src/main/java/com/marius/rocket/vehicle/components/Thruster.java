/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.vehicle.resources.Resource;

/**
 *
 * @author n5823a
 */
public class Thruster extends Component {
    
    protected double thrust; // in newtons
    protected double massflow; //kg/s
    protected double isp; // s
    protected Resource[] requirements;
    protected double throttle;
    private short massflowMethod = 0;
    
    public Thruster() {     
    }
    
    public Thruster(double thrust, double isp, double mass) {
        this.thrust = thrust;
        this.isp = isp;
        this.mass = mass;
        massflowMethod = 1;
    }
    
    public void setRequirements(Resource[] requirements){
        this.requirements=requirements;
    }
    
    public Resource[] getRequirements(){
        return this.requirements;
    }
    
    public double getThrust() {
        return thrust;
    }
    
    public double getMassFlow() {
        calcMassFlow();
        return massflow;
    }
    
    public void setThrottle(double throttle) {
        this.throttle = throttle;
    }
    
    private void calcMassFlow() {
        if(massflowMethod == 0) {
            double sum = 0;
            for(Resource fuel : requirements) {
                sum += fuel.getAmount();
            }
            this.massflow = sum;
        } else if (massflowMethod == 1) {
            this.massflow = thrust/(9.806*isp);
        }
    }
    
}
