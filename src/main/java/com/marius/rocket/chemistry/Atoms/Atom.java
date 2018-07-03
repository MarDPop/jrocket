/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Atoms;

import com.marius.rocket.physics.Physics;
/**
 *
 * @author n5823a
 */
public abstract class Atom {
    //public final static double EV2KJ = 1312.0/13.59844;
    public final String symbol;
    public final int protons;
    public final int neutrons;
    public final double atomic_mass; //in dalton
    protected int ionization;
    // PLEASE NOTE, the following data is STANDARD, things like molecular behavior may affect electron affinity, ionization energy etc and is for reference only
    public final double[] ionization_energy; //in eV
    public final double[] electron_affinity; //in eV
    public final double atomic_radius; //pm
    public final double electronegativy; //paulin scale
    
    public Atom(String symbol,int protons, int neutrons, double atomic_mass, double[] ionization_energy, double[] electron_affinity, double atomic_radius, double electronegativy) {
        this.symbol = symbol;
        this.protons = protons;
        this.neutrons = neutrons;
        this.atomic_mass = atomic_mass;
        this.ionization_energy = ionization_energy;
        this.electron_affinity = electron_affinity;
        this.electronegativy = electronegativy;
        this.atomic_radius = atomic_radius;
    }
    
    public final int getIonization() {
        return this.ionization;
    }
    
    public final void setIonization(int ionization) {
        this.ionization = ionization;
    }
    
    public final double kgMass() {
        return this.atomic_mass*Physics.ATOMIC_MASS_UNIT;
    }
    
    
}
