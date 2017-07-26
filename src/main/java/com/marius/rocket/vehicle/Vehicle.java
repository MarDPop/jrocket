/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle;

import com.marius.rocket.physics.Body;
/**
 *
 * @author n5823a
 */
public class Vehicle extends Body {
    
    private Subsystem[] subsystems;
    
    public Vehicle() {
        super(0);
    }
}
