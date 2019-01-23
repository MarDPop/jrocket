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
public class Electron extends Particle{
    public static final double EV = 1.6021766208e-19;
    public static final double MASS = 9.10938356e-31;
    
    public Electron() {
        super(MASS, -EV);
    }
}
