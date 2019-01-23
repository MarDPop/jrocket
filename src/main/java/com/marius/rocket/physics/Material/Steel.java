/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Material;

import com.marius.rocket.chemistry.Atoms.C;
import com.marius.rocket.chemistry.Atoms.Fe;

/**
 *
 * @author Me
 */
public class Steel extends Material {
    public Steel() {
        super(200e9,
        1800,
        7900,
        121,
        420e6,
        350e6,
        140e9,
        0.25,
        80e9,
        470,
        50,
        12e-6);
        Composition.put(new C(), 0.01);
        Composition.put(new Fe(), 0.99);
        this.emmisivity = 0.9;
    }
   
}
