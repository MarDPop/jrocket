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
    private Atmosphere atm;
    
    public Planet(double MU){
        super(MU/Physics.G);
        this.MU = MU;
    }
    
    public void setAtm(Atmosphere atm) {
        this.atm = atm;
    }
    
    public Atmosphere getAtm() {
        return this.atm;
    }
    
}
