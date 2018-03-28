/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.resources;

import com.marius.rocket.chemistry.Molecules.Molecule;

/**
 *
 * @author n5823a
 */
public class Resource {
    
    private double amount;
    private double density;
    private final int unit; // what unit amount is in default = mass
    protected Molecule[] content;
    
    public static final int UNIT_KILOGRAM = 0;
    public static final int UNIT_LITER = 1;
    public static final int UNIT_METER = 2;
    public static final int UNIT_AMP_HOUR = 3;
    
    public Resource() {
        this.unit = 0;
    }
    
    public Resource(double amount) {
        this.amount = amount;
        this.unit = 0;
    }
    
    public Resource(double amount, int unit) {
        this.amount = amount;
        this.unit = unit;
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
    
    public double getMass() {
        return conversionToMass()*this.amount;
    }
    
    public double conversionToMass() {
        if(unit == 0) {
            return 1;
        }
        return 1/density;
    }  
    
    public Molecule[] getContent() {
        return content;
    }
    
}
