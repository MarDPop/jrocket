/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.physics.Fluid;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author n5823a
 */
public abstract class Molecule {
    
    public final HashMap<Atom,Integer> elList; //could be strings instead of objects
    public final double weight; // kg
    protected double moles;
    protected short[] assumptions; // Assumptions to use
    public final double heat_formation; // J/mol @298.15
    public final double heat_fusion; // J/mol @stp
    public final double heat_vaporization; // J/mol @stp
    public final double boiling_point; // K @stp
    public final double melting_point; // K @stp
    protected double temp; // K
    protected double pres; // Pa
    protected double concentration; // mol/m3
    protected double enthalpy;  // J/mol  <---- THIS IS NOT Standard ENTHALPY
    protected double standardEntropy; // J/mol /k
    protected double standardGibbs; // J/mol
    protected double internalEnergy; // J/mol
    protected double cp; // J/mol/K
    protected double cv; // J/mol/K
    protected double gamma; //specific heat ratio
    protected TreeMap<Double,Double[]> shomate = new TreeMap<>();
    protected double ea; //electron affinity
    protected double pa; //proton affinity;
    protected double ie; //ionization energy
    public String name; // should be used especially with database
    // Perhaps it's better to use thermodynamics packages and set via database as "fluid"
    
    public Molecule(Atom[] atoms, int[] quantity, double heat_formation, double heat_vaporization, double heat_fusion, double boiling_point, double melting_point, double[] critical_point, double[] triple_point, double[] vapor_pressure){
        this.elList = new HashMap<>();
        double s = 0;
        for(int i = 0; i < atoms.length; i++) {
            elList.put(atoms[i], quantity[i]);
            s+= quantity[i]*atoms[i].atomic_mass;
        }
        weight = s;
        this.heat_formation = heat_formation*1000;
        this.heat_fusion = heat_fusion;
        this.heat_vaporization = heat_vaporization;
        this.boiling_point = boiling_point;
        this.melting_point = melting_point;
        // THIS SHOULD BE LOADED VIA DATABASE
    }
    
    public void calc() {
        if(shomate.isEmpty()){
            
        } else {
            calcShomate();
        }
    }
    
    public final void setMoles(double moles) {
        this.moles = moles;
    }
    
    public final double getMoles() {
        return moles;
    }
    
    public final void setTemp(double Temp) {
        this.temp = Temp;
    }
    
    public final void setPres(double Pres) {
        this.pres = Pres;
    }
    
    public final void setSpecificHeatRatio(double gamma) {
        this.gamma = gamma;
    }
    
    public final void setIdealCP(double CP) {
        this.cp = CP;
        this.cv = CP-Fluid.R;
        this.gamma = CP/cv;
    }
    
    public final void setIdealCV(double cv) {
        this.cv=cv;
        this.cp = cv+Fluid.R;
        this.gamma = cp/cv;
    }
    
    public final void setConcentration(double Concentration) {
        this.concentration = Concentration;
    }
    
    public final double getTemp() {
        return temp;
    }
    
    public final double getPres() {
        return pres;
    }
    
    public final double getConcentration() {
        return concentration;
    }
    
    public final double getEnthalpy() {
        return enthalpy;
    }
    
    public final double getCP() {
        return cp;
    }
    
    public final double getCV() {
        return cv;
    }
    
    public final double getEntropy() {
        return standardEntropy;
    }
    
    public final double getInternalEnergy() {
        return internalEnergy;
    }
    
    public double shomateCp(double[] constants){
        double t = this.temp/1000;
        this.cp = constants[0] + constants[1]*t + constants[2]*t*t + constants[3]*t*t*t + constants[4]/t/t;
        //this.CV = CP-Fluid.R; //FOR IDEAL ONLY
        return this.cp;
    }
    
    public double shomateSensibleEnthalpy(double[] constants){
        double t = this.temp/1000;
        return constants[0]*t + constants[1]*t*t/2 + constants[2]*t*t*t/3 + constants[3]*t*t*t*t/4 - constants[4]/t + constants[5] ;
    }
    
    public double shomateEnthalpy(double[] constants) {
        this.enthalpy = shomateSensibleEnthalpy(constants)*1000 + constants[7];
        return this.enthalpy;
    }
    
    public double shomateEntropy(double[] constants){
        double t = this.temp/1000;
        this.enthalpy = constants[0]*Math.log(t) + constants[1]*t + constants[2]*t*t/2 + constants[3]*t*t*t/3 - constants[4]/(2*t*t) + constants[6];
        return this.enthalpy;
    }
    
    public void SetAndCalcAll(double temp ,double pres) {
        this.temp = temp;
        this.pres = pres;
        calc();
    }
    
    private void calcShomate() {
        System.out.println("Internal Temp: "+temp);
        Double[] constants = shomate.ceilingEntry(temp).getValue(); //conversion to primative might be slow, please check
        double t = this.temp/1000;
        double t2 = t*t;
        double t3 = t2*t;
        double t4 = t3*t;
        this.cp = constants[0] + constants[1]*t + constants[2]*t2 + constants[3]*t3 + constants[4]/t2;
        double H = constants[0]*t + constants[1]*t2/2 + constants[2]*t3/3 + constants[3]*t4/4 - constants[4]/t + constants[5];
        this.enthalpy = H*1000; 
        //add internal energy
        this.standardEntropy = constants[0]*Math.log(t) + constants[1]*t + constants[2]*t2/2 + constants[3]*t3/3 - constants[4]/(2*t2) + constants[6];
        this.standardGibbs = this.enthalpy - temp*standardEntropy;
    }
    
    public double getStandardGibbs() {
        return this.standardGibbs;
    }
    
}
