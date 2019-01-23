/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Utils.pic;

/**
 *
 * @author mpopescu
 */
public class Proton extends Particle {
    public static final double MASS = 1.6726219e-27;
    
    public Proton(){
        super(MASS,Electron.EV);
    }
}
