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
    public final static double EV2KJ = 1312.0/13.59844;
    public final int protons;
    public final int neutrons;
    private int ionization;
    public final double[] ionization_energy; //in eV
    public final double atomic_mass; //in dalton
    //public final double electronegativity;
    
    public Atom(int protons, int neutrons, double atomic_mass, double[] ionization_energy ) {
        this.protons = protons;
        this.neutrons = neutrons;
        this.atomic_mass = atomic_mass;
        this.ionization_energy = ionization_energy;
    }
    
    public final int getIonization() {
        return this.ionization;
    }
    
    public final void setIonization(int ionization) {
        this.ionization = ionization;
    }
    
    public final double kgMass() {
        return this.atomic_mass*Physics.amu;
    }
    
    
}
