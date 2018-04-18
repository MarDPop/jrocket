/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Material;

import com.marius.rocket.chemistry.Atoms.Atom;
import java.util.HashMap;

/**
 *
 * @author Me
 */
public class Material {
    protected double youngsModulus; //Pa
    protected double meltingPoint; // K
    protected double density; // kg/m3
    protected double hardnessBrinell; 
    protected double tensileStrength_Ultimate; //Pa
    protected double tensileStrength_Yield; //Pa
    protected double bulkModulus; //Pa
    protected double poissonsRatio; 
    protected double shearModulus; // Pa
    protected double specificHeatCapacity; //J/kg k
    protected double thermalConductivity; //W/m K
    protected double linearThermalExpansion; // 1/ K
    protected double resistivity; //ohm m
    public final HashMap<Atom,Double> Composition = new HashMap<>();
    protected double temperature;
    protected double pressure;
    protected double emmisivity;
    
    /*
    public Material(String file) {
        
    }
    */
    
    public Material(double YoungsModulus,
                    double MeltingPoint,
                    double Density,
                    double HardnessBrinell,
                    double TensileStrength_Ultimate,
                    double TensileStrength_Yield,
                    double BulkModulus,
                    double PoissonsRatio,
                    double ShearModulus,
                    double SpecificHeatCapacity,
                    double ThermalConductivity,
                    double LinearThermalExpansion) {
        this.youngsModulus= YoungsModulus;
        this.meltingPoint=MeltingPoint;
        this.density=Density;
        this.hardnessBrinell=HardnessBrinell;
        this.tensileStrength_Ultimate=TensileStrength_Ultimate;
        this.tensileStrength_Yield=TensileStrength_Yield;
        this.bulkModulus=BulkModulus;
        this.poissonsRatio=PoissonsRatio;
        this.shearModulus=ShearModulus;
        this.specificHeatCapacity=SpecificHeatCapacity;
        this.thermalConductivity=ThermalConductivity;
        this.linearThermalExpansion = LinearThermalExpansion;
    }
    
    public double getDensity() {
        return this.density;
    }
    
    public double getSpecificHeatCapacity() {
        return this.specificHeatCapacity;
    }
    
    public double getThermalConductivity() {
        return this.thermalConductivity;
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
    public double getEmmisivity() {
        return this.emmisivity;
    }
    
    public static double hoopWallStress_ThinCylinder(double pres, double thickness, double rad) {
        return pres/thickness/rad;
    }
    
    public static double hoopWallStress_ThinSphere(double pres, double thickness, double rad) {
        return pres/thickness/rad/2;
    }
    
    public static double axialStress_Cylinder(double P, double t, double d) {
        return P*d*d/((d+2*t)*(d+2*t)+d*d);
    }
    
    public double getMinThicknessForPress(double P, double r, double safetyFactor) {
        return safetyFactor*P/r/tensileStrength_Yield;
    }
}
