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
public class Component {
    
    protected Part[] parts;
    protected Connection[] connections;
    protected Resource[] resources;
    protected double mass;
    protected double[] location; //in referance to vehicle frame
    
    public Component() {
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public double getMass(){
        return mass;
    }
    
    public void setConnections(Connection[] connections){
        this.connections = connections;
    }
    
    public Connection[] getConnections(){
        return connections;
    }
    
    public void setResources(Resource[] resources){
        this.resources = resources;
    }
    
    public Resource[] getResources(){
        return resources;
    }
    
    public void setParts(Part[] parts){
        this.parts = parts;
    }
    
    public Part[] getParts(){
        return parts;
    }
    
}
