/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.H;

/**
 *
 * @author n5823a
 */
public class MonoHydride extends Molecule{
    public MonoHydride(double moles) {
        super(new Atom[]{new H()}, new int[] {1}, 217.9994, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)6000,new Double[]{20.78603,4.850638e-10,-1.582916e-10,1.525102e-11,3.196347e-11,211.8020,139.8711,217.9994});
    }
}
