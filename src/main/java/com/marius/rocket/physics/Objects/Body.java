/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects;

import com.marius.rocket.physics.forces.Force;
import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Physics;
import com.marius.rocket.physics.forces.Gravity;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Body extends Frame {
    
    public double MU;
    protected double mass; 
    protected double mass_dot;
    protected double charge;
    protected double charge_dot;
    protected double[] mageneticMoment;
    
    public Gravity g; 
    public ArrayList<Force> forces = new ArrayList<>();
    
    protected double[] netforces;
    protected double[] netmoments;
    protected double[] COG;
    protected double[][] Inertia; //normalized inertia in BODY frame
    
    protected Shape shape = null;
    public boolean onrails = false;
    
    public double[] state = new double[8];
    public double[] state_dot = new double[8];
    
    public Body(double mass) {
        this.mass = mass;
        this.MU = Physics.GRAVITY*mass;
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
        return charge;
    }
    
    public void setCharge(double netcharge){
        this.charge = netcharge;
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
    
    public void setState(double[] x) {
        System.arraycopy(x, 0, this.state, 0, 7);
        linkState();
    }
    
    public void linkState() {
        xyz[0][0] = state[0];
        xyz[0][1] = state[1];
        xyz[0][2] = state[2];
        xyz[1][0] = state[3];
        xyz[1][1] = state[4];
        xyz[1][2] = state[5];
        mass = state[6];
        charge = state[7];
    }
    
    public void linkStateDot() {
        state_dot[0] = xyz[1][0];
        state_dot[1] = xyz[1][1];
        state_dot[2] = xyz[1][2];
        state_dot[3] = xyz[2][0];
        state_dot[4] = xyz[2][1];
        state_dot[5] = xyz[2][2];
        state_dot[6] = mass_dot;
        state_dot[7] = charge_dot;
    }
    
    // Probably not necessary
    public void setCOG(double[] COG){
        this.COG = COG;
    }
    
    public double[] getCOG(){
        return COG;
    }
    
    public double updateMass() {
        return this.mass;
    }
    
    public double updateCharge() {
        return this.charge;
    }
    
    public void update(double time) {
        this.calcSphericalFromCartesian();
        if(!this.onrails) {
            double[] sum = new double[3];
            // check this
            forces.parallelStream().forEach((force) -> {
                force.update();
                if(force.internal){
                    LA.add(sum,force.get()); 
                } else {
                    LA.add(this.xyz[2], force.get()); 
                }
            });
            this.xyz[2][0] += this.orientation[0][0]*sum[0]+this.orientation[1][0]*sum[1]+this.orientation[2][0]*sum[2];
            this.xyz[2][1] += this.orientation[0][1]*sum[0]+this.orientation[1][1]*sum[1]+this.orientation[2][1]*sum[2];
            this.xyz[2][2] += this.orientation[0][2]*sum[0]+this.orientation[1][2]*sum[1]+this.orientation[2][2]*sum[2];
            LA.multiply(this.xyz[2], 1/updateMass());
            linkStateDot();
        } else {
            rails(time);
        }
    }
    
    public void rails(double time) {
        
    }
    
}
