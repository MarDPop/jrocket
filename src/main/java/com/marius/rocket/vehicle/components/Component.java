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
    public ArrayList<Force> forces;
    protected Part[] parts;
    public ArrayList<Connection> connections;
    public ArrayList<Resource> resources;
    protected double mass;
    public double[][] Inertia;
    protected double[] COG; //in referance to vehicle frame (shouldn't be final as could move)
    
    public Component() {
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
    
}
