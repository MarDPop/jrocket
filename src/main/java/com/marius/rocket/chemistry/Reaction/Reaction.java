/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Reaction;

import com.marius.rocket.chemistry.Molecules.Molecule;

/**
 *
 * @author n5823a
 */
public abstract class Reaction {
    
    public final double heat;
    public final Molecule[] reactants;
    public final Molecule[] products;
    private double Temp;
    private double Pres;
    
    public Reaction(Molecule[] reactants, Molecule[] products, double heat) {
        this.heat = heat;
        this.reactants = reactants;
        this.products = products;
    }
    
    public void setTemp(double Temp) {
        this.Temp = Temp;
    }
    
    public double getTemp() {
        return Temp;
    }
    
    public void setPres(double Pres) {
        this.Pres = Pres;
    }
    
    public double getPres() {
        return Pres;
    }
    
    public abstract double getRate();
    
}
