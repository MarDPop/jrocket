/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.resources;

import com.marius.rocket.chemistry.Molecules.Molecule;

/**
 *
 * @author n5823a
 */
public class Resource {
    
    private double amount;
    private double density;
    private final int unit; // what unit amount is in default = mass
    private final String name;
    private double cost;
    private Molecule[] content;
    
    public static final int UNIT_KILOGRAM = 0;
    public static final int UNIT_LITER = 1;
    public static final int UNIT_METER = 2;
    public static final int UNIT_AMP_HOUR = 3;
    public static final int UNIT_MOLES = 4;
    
    public Resource(String name) {
        this.name = name;
        this.unit = 0;
    }
    
    public Resource(String name, double amount) {
        this(name);
        this.amount = amount;
    }
    
    public Resource(String name, double amount, int unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }
    
    public Resource(Molecule content) {
        this.content = new Molecule[]{content};
        this.amount = content.getMoles();
        this.unit = 4;
        this.name = content.name;
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
    
    public void setContent(Molecule[] content) {
        this.content = content;
    }
    
    public Molecule[] getContent() {
        return content;
    }
    
}
