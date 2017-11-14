/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.physics.forces.Force;
import com.marius.rocket.Math.LA;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Body extends Frame{
    
    protected double mass; 
    protected double netcharge;
    protected double[] netforces;
    public ArrayList<Force> forces = new ArrayList<>();;
    protected double[] netmoments;
    protected double[][] Inertia; //normalized inertia in BODY frame
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
    
    public double[] getNetForces(){
        return netforces;
    }
    
    public void setNetForces(double[] netforces) {
        this.netforces = netforces;
    }   
    
    public double[] getMoments(){
        return netmoments;
    }
    
    public void setMoments(double[] netmoments) {
        this.netmoments = netmoments;
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
    
    public void fullupdate(){
        
    }
    
    public void update(double time, double dt) {
        double[] sum = new double[3];
        forces.forEach((force) -> {
            force.update(time,dt);
            LA.add(sum,force.get());
        });
        LA.multiply(sum, 1/this.mass);
        this.xyz[2][0] = this.orientation[0][0]*sum[0]+this.orientation[1][0]*sum[1]+this.orientation[2][0]*sum[2];
        this.xyz[2][1] = this.orientation[0][1]*sum[0]+this.orientation[1][1]*sum[1]+this.orientation[2][1]*sum[2];
        this.xyz[2][2] = this.orientation[0][2]*sum[0]+this.orientation[1][2]*sum[1]+this.orientation[2][2]*sum[2];
    }
    
    public void getAccelerationFromForces() {
        
    }
    
    public double[] getState() {
        return this.xyz[0];
    }
    
    public void setState(double[] in) {
        
    }
    
}
