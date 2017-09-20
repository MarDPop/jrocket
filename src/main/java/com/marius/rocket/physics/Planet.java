/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class Planet extends Body {
    
    public final double MU;
    public final double RADIUS;
    protected Atmosphere atm;
    
    public Planet(double MU, double Radius){
        super(MU/Physics.G);
        this.MU = MU;
        this.RADIUS = Radius;
    }
    
    public void setAtm(Atmosphere atm) {
        this.atm = atm;
    }
    
    public Atmosphere getAtm() {
        return this.atm;
    }
    
    public double calcGeopotentialAltitude(double altitude) {
        return this.RADIUS*altitude/(this.RADIUS*altitude);
    }
    
}
