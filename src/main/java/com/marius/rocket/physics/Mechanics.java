/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.Math.LA;

/**
 *
 * @author mpopescu
 */
public class Mechanics {
    
    
    public static double[] getGravity(double Mu, double[] r){
        return LA.multiply(r, -Mu/Math.pow(LA.dot(r,r), 1.5));
    }
}
