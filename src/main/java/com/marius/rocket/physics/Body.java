/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class Body extends Frame{
    
    protected double mass; 
    protected double[][] rotation; // pitch, heading, roll and rates and accel
    protected double[] forces;
    protected double[] moments;
    protected double[][] Inertia; //normalized inertia
    protected Shape shape;
    
    public Body(double mass) {
        this.mass = mass;
    }
    
    public double getMass(){
        return mass;
    }
    
    public void setMass(double mass){
        this.mass = mass;
    }
    
    public void changeMass(double dx) {
        this.mass += dx;
    }
    
    public double[] getForces(){
        return forces;
    }
    
    public void setForces(double[] forces) {
        this.forces = forces;
    }
    
    public double[] getMoments(){
        return moments;
    }
    
    public void setMoments(double[] moments) {
        this.moments = moments;
    }
    
    public double[][] getRotation(){
        return rotation;
    }
    
    public void setRotation(double[][] rotation) {
        this.rotation = rotation;
        this.angular_velocity = rotation[1];
    }
    
    public double[][] getInertia(){
        return Inertia;
    }
    
    public void setInertia(double[][] Inertia) {
        this.Inertia = Inertia;
    }
    
    public void setShape(Shape shape){
        this.shape = shape;
    }
    
}
