/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.forces;

/**
 *
 * @author n5823a
 */
public class Thrust extends Force{   
    private double isp;
    private double massflow;
    private double exitV; //NOTE: exitV =/= isp*g !! isp accounts for losses and pressure
    
    public Thrust() {
        
    }
    
    public void setISP(double isp){
        this.isp = isp;
        this.massflow = this.magnitude()/(9.806*isp);
    }
    
    public void setMassflow(double massflow){
        this.massflow = massflow;
        this.isp = this.magnitude()/(massflow*9.806);
    }
    
    public double getMassflow() {
        return massflow;
    }
    
    public double getISP() {
        return isp;
    }
    
}
