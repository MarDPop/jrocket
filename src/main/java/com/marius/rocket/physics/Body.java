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
    protected double netcharge;
    protected double[][] rotation; // pitch, heading, roll and rates and accel
    protected double[] netforces;
    protected Force[] forces;
    protected double[] netmoments;
    protected double[][] Inertia; //normalized inertia
    protected Shape shape;
    public boolean onrails = false;
    public double massRate;
    public double chargeRate;
    
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
    
    public double getCharge(){
        return netcharge;
    }
    
    public void setCharge(double netcharge){
        this.netcharge = netcharge;
    }
    
    public double[] getForces(){
        return netforces;
    }
    
    public void setForces(double[] netforces) {
        this.netforces = netforces;
    }
    
    public double[] getMoments(){
        return netmoments;
    }
    
    public void setMoments(double[] netmoments) {
        this.netmoments = netmoments;
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
    
    public Shape getShape(){
        return shape;
    }
    
    public void update() {
        
    }
    
    public double[] getState() {
        return this.xyz[0];
    }
    
    public void setState(double[] in) {
        
    }
    
}
