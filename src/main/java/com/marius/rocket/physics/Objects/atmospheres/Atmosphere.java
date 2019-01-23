/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.atmospheres;

import com.marius.rocket.chemistry.Molecules.Molecule;
import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;

/**
 *
 * @author n5823a
 */
public abstract class Atmosphere {
    
    protected double altitude;
    protected double geoaltitude;
    protected double temp;
    protected double pres;
    protected double dens;
    protected double gamma;
    protected double MM;
    protected double R_air;
    protected double G0; 
    protected double Radius;
    protected double humidity;
    protected double viscosity;
    protected double dynamic_viscosity;
    protected double thermal_conductivity;
    protected double thermal_diffusivity;
    protected double cp;
    protected double cv;
    protected SolarsystemBody planet;
    protected Molecule[] species;
    
    abstract void calc();
    
    public SolarsystemBody getPlanet() {
        return planet;
    }
    
    public double getTemp() {
        return this.temp;
    }
    
    public double getPres() {
        return this.pres;
    }
    
    public double getDens() {
        return this.dens;
    }
    
    public double getGamma() {
        return this.gamma;
    }
    
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
    
    public double getMM() {
        return this.MM;
    }
    
    public void setSpecies(Molecule[] species) {
        this.species = species;
    }
    
    public Molecule[] getSpecies() {
        return this.species;
    }
    
    public void setMM(double MM) {
        this.MM = MM;
    }
    
    public double getHumidity() {
        return this.humidity;
    }
    
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    
    public double getAltitude() {
        return this.altitude;
    }
    
    public void setAltitude(double altitude) {
        this.altitude = altitude;
        this.geoaltitude = Radius*altitude/(Radius+altitude);
        calc();
    }
    
    public double getGeoAltitude() {
        return this.geoaltitude;
    }
    
    public double getRadius(){
        return Radius;
    }
    
}
