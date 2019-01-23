/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author mpopescu
 */
public class FluidDynamics {
    
    public static double prandtlNumber(double kinematicViscosity, double thermalDiffusivity) {
        return kinematicViscosity/thermalDiffusivity;
    }
    
    public static double prandtlNumber(double specificHeat, double dynamicViscosity, double thermalConductivity ) {
        return specificHeat*dynamicViscosity/thermalConductivity;
    }
    
    public static double grashofNumberHeat(double length, double surfaceTemp, double infTemp, double kinematicViscosity, double thermalExpansitionCoef) {
        return 9.81*thermalExpansitionCoef*length*length*length*(surfaceTemp-infTemp)/kinematicViscosity/kinematicViscosity;
    }
    
    public static double rayleighNumber(double length, double surfaceTemp, double infTemp, double kinematicViscosity, double thermalDiffusivity, double thermalExpansitionCoef) {
        return 9.81*thermalExpansitionCoef*length*length*length*(surfaceTemp-infTemp)/kinematicViscosity/thermalDiffusivity;
    }
    
    public static double reynoldsNumber(double length,double speed, double kinematicViscosity) {
        return speed*length/kinematicViscosity;
    }
}
