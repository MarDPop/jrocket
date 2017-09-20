/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import java.util.HashMap;
/**
 *
 * @author n5823a
 */
public abstract class Molecule {
    
    public final HashMap atomlist = new HashMap();
    public final double weight; // kg
    private double moles;
    public double[] ionization_energy; //in eV
    public final double heat_formation; // J
    public final double heat_fusion; // J
    public final double heat_vaporization; // J
    public final double boiling_point; // K
    public final double melting_point; // K
    private double Temp; // K
    private double Pres; // Pa
    private double Concentration; // mol/m3
    private double Enthalpy;  // J/mol
    private double Entropy; // J/mol
    private double Gibbs; // J/mol
    private double InternalEnergy; // J/mol
    private double CP; // J/mol/K
    private double CV; // J/mol/K
    
    public Molecule(Atom[] atoms, int[] quantity, double heat_formation, double heat_vaporization, double heat_fusion, double boiling_point, double melting_point){
        double s = 0;
        for(int i = 0; i < atoms.length; i++) {
            atomlist.put(atoms[i], quantity[i]);
            s+= quantity[i]*atoms[i].atomic_mass;
        }
        weight = s;
        this.heat_formation = heat_formation;
        this.heat_fusion = heat_fusion;
        this.heat_vaporization = heat_vaporization;
        this.boiling_point = boiling_point;
        this.melting_point = melting_point;
    }
    
    public final void setMoles(double moles) {
        this.moles = moles;
    }
    
    public final void changeMoles(double dx) {
        this.moles += dx;
    }
    
    public final double getMoles() {
        return moles;
    }
    
    public final void setTemp(double Temp) {
        this.Temp = Temp;
    }
    
    public final void setPres(double Pres) {
        this.Pres = Pres;
    }
    
    public final void setConcentration(double Concentration) {
        this.Concentration = Concentration;
    }
    
    public final double getTemp() {
        return Temp;
    }
    
    public final double getPres() {
        return Pres;
    }
    
    public final double getConcentration() {
        return Concentration;
    }
    
    public final double getEnthalpy() {
        return Enthalpy;
    }
    
    public final double getEntropy() {
        return Entropy;
    }
    
    public final double getGibbs() {
        return Gibbs;
    }
    
    public final double getInternalEnergy() {
        return InternalEnergy;
    }
    
    public abstract void CalcAll();
    public abstract void CalcAll(double Temp);
    public abstract void CalcAll(double Temp ,double Pres);
    
}
