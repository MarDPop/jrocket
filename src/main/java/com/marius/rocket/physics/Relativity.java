/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import static com.marius.rocket.physics.Physics.LIGHT_SPEED;

/**
 *
 * @author mpopescu
 */
public class Relativity {
    
    
    public static double lorentz(double v) {
        return 1/(1-(v*v/(LIGHT_SPEED*LIGHT_SPEED)));
    }
}
