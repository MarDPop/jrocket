/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.resources;

/**
 *
 * @author n5823a
 */
public class Resource {
    
    private double amount;
    private double density;
    
    public Resource(double amount) {
        this.amount = amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setDensity(double density) {
        this.density = density;
    }
    
    public double getDensity() {
        return density;
    }
    
    public double changeAmount(double dx) {
        this.amount += dx;
        return amount;
    }
    
}
