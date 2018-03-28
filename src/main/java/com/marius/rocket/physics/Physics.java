/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import static java.lang.Math.*;
import com.marius.rocket.Math.*;
/**
 *
 * @author n5823a
 */
public class Physics {
    
    public final static double G = 6.67408313e-11; // Gravitational  Constant N m2 / kg2
    public final static double K = 8.98755179e-11; //Coloumb Constant N m2 / kg2
    public final static double eta = 8.854187817e-12; // Permittivity of Vacuum F/m
    public final static double mu = 4*PI*1e-7; // Permeability of Vacuum
    public final static double amu = 1.66053904e-27; //Atomic Mass Unit  kg
    public final static double m_e = 9.10938356e-31; //Mass of electron kg
    public final static double m_p = 1.672621898e-27; //Mass of Proton kg
    public final static double m_n = 1.674927471e-27; //Mass of Neutron kg
    public final static double c = 299792458; // Speed of Light m/s
    public final static double eV = 1.6021766208e-19; //Electron Volt C or J
    public final static double h = 6.626070040e-34; //Planck Constant j s 
    public final static double SB = 5.670373e-8; // Stefen Boltzmann W/ m2 k4
    public final static double avo = 6.022140857e23; // Avogadros Number
    public final static double kB = 1.38064852e-23; //Boltzmann Constant J/K
    
    public static double lorentz(double v) {
        return 1/(1-(v*v/(c*c)));
    }
    
    public static double[] getGravity(double Mu, double[] r){
        return LA.multiply(r, -Mu/Math.pow(LA.dot(r,r), 1.5));
    }
    
    public static double[] getElectricField(double q, double[] r) {
        return LA.multiply(r, q/Math.pow(LA.dot(r,r), 1.5));
    }
    
    public static double getESPotential(double q, double[] r) {
        return q/LA.mag(r);
    }
    
    public static double getEnergyOfPhotonFromWavelength(double wl) {
        return h*c/wl;
    }
    
    
    
}
