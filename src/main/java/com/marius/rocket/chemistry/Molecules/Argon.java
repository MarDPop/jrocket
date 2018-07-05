/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Ar;
import com.marius.rocket.chemistry.Atoms.Atom;

/**
 *
 * @author mpopescu
 */
public class Argon extends Molecule{
    public Argon(double moles) {
        super(new Atom[]{new Ar()}, new int[] {1,1}, 38.98706, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1300,new Double[]{32.27768,-11.36291,13.60545,-3.846486,-0.001335,29.75113,225.5783,38.98706});
        this.shomate.put((double)6000,new Double[]{28.74701,4.714489,-0.814725,0.054748,-2.747829,26.41439,214.1166,38.98706});
    }
}
