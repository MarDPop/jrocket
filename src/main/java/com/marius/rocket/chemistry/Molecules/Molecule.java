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
    public final double chemical_potential; // J/mol
    public final double heat_fusion; // J/mol
    public final double heat_vaporization; // J/mol
    public final double boiling_point; // K
    public final double melting_point; // K
    protected double Temp; // K
    protected double Pres; // Pa
    protected double Concentration; // mol/m3
    protected double Enthalpy;  // J/mol
    protected double Entropy; // J/mol
    protected double Gibbs; // J/mol
    protected double InternalEnergy; // J/mol
    protected double CP; // J/mol/K
    protected double CV; // J/mol/K
    protected double gamma; //specific heat ratio
    protected TreeMap<Double,Double[]> shomate = new TreeMap<>();
    protected double EA; //electron affinity
    protected double PA; //proton affinity;
    
    public Molecule(Atom[] atoms, int[] quantity, double chemical_potential, double heat_vaporization, double heat_fusion, double boiling_point, double melting_point, double[] critical_point, double[] triple_point, double[] vapor_pressure){
        this.elList = new HashMap<>();
        double s = 0;
        for(int i = 0; i < atoms.length; i++) {
            elList.put(atoms[i], quantity[i]);
            s+= quantity[i]*atoms[i].atomic_mass;
        }
        weight = s;
        this.chemical_potential = chemical_potential;
        this.heat_fusion = heat_fusion;
        this.heat_vaporization = heat_vaporization;
        this.boiling_point = boiling_point;
        this.melting_point = melting_point;
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
        this.Temp = Temp;
    }
    
    public final void setPres(double Pres) {
        this.Pres = Pres;
    }
    
    public final void setSpecificHeatRatio(double gamma) {
        this.gamma = gamma;
    }
    
    public final void setIdealCP(double CP) {
        this.CP = CP;
        this.CV = CP-Fluid.R;
        this.gamma = CP/CV;
    }
    
    public final void setIdealCV(double CV) {
        this.CP=CV;
        this.CP = CV+Fluid.R;
        this.gamma = CP/CV;
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
    
    public final double getCP() {
        return CP;
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
    
    public double shomateCp(double[] constants){
        double t = this.Temp/1000;
        this.CP = constants[0] + constants[1]*t + constants[2]*t*t + constants[3]*t*t*t + constants[4]/t/t;
        //this.CV = CP-Fluid.R; //FOR IDEAL ONLY
        return this.CP;
    }
    
    public double shomateSensibleEnthalpy(double[] constants){
        double t = this.Temp/1000;
        return constants[0]*t + constants[1]*t*t/2 + constants[2]*t*t*t/3 + constants[3]*t*t*t*t/4 - constants[4]/t + constants[5] ;
    }
    
    public double shomateEnthalpy(double[] constants) {
        this.Enthalpy = shomateSensibleEnthalpy(constants)*1000 + constants[7];
        return this.Enthalpy;
    }
    
    public double shomateEntropy(double[] constants){
        double t = this.Temp/1000;
        this.Entropy = constants[0]*Math.log(t) + constants[1]*t + constants[2]*t*t/2 + constants[3]*t*t*t/3 - constants[4]/(2*t*t) + constants[6];
        return this.Entropy;
    }
    
    public void SetAndCalcAll(double Temp ,double Pres) {
        this.Temp = Temp;
        this.Pres = Pres;
        calc();
    }
    
    private void calcShomate() {
        Double[] constants = shomate.ceilingEntry(Temp).getValue(); //conversion to primative might be slow, please check
        double t = this.Temp/1000;
        double t2 = t*t;
        double t3 = t2*t;
        double t4 = t3*t;
        this.CP = constants[0] + constants[1]*t + constants[2]*t2 + constants[3]*t3 + constants[4]/t2;
        double H = constants[0]*t + constants[1]*t2/2 + constants[2]*t3/3 + constants[3]*t4/4 - constants[4]/t + constants[5];
        this.Enthalpy = H*1000 + constants[7];
        this.Entropy = constants[0]*Math.log(t) + constants[1]*t + constants[2]*t2/2 + constants[3]*t3/3 - constants[4]/(2*t2) + constants[6];
    }
    
}
