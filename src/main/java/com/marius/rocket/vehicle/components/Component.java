/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.physics.forces.Force;
import com.marius.rocket.vehicle.resources.Resource;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Component {
    protected Part[] parts;
    public ArrayList<Force> forces = new ArrayList<>();
    public ArrayList<Controller> controllers = new ArrayList<>();
    public ArrayList<Connection> connections = new ArrayList<>();
    public ArrayList<Resource> resources = new ArrayList<>();
    protected double mass;
    public double[][] Inertia;
    protected double[] COG; //in referance to vehicle frame (shouldn't be final as could move)
    
    public Component() {
        this.mass= 0;
        this.Inertia = new double[3][3];
        this.COG = new double[3];
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public double getMass(){
        return mass;
    }
    
    public void setCOG(double[] COG){
        this.COG = COG;
    }
    
    public double[] getCOG(){
        return COG;
    }
    
    public void setParts(Part[] parts){
        this.parts = parts;
    }
    
    public Part[] getParts(){
        return parts;
    }
    
    public void update(double time, double dt) {
        
    }
    
}
