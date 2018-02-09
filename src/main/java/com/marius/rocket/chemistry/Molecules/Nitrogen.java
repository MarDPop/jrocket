/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.N;

/**
 *
 * @author n5823a
 */
public class Nitrogen extends Molecule{
    public Nitrogen(double moles) {
        super(new Atom[]{new N()}, new int[] {2}, 0, 5560 , 720, 63.15, 77.355, new double[]{126.192,3.39587e6},new double[]{63.151,12.52e3}, new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)500,new Double[]{28.98641,1.853978,-9.647459,16.63537,0.000117,-8.671914,226.4168,0.0});
        this.shomate.put((double)2000,new Double[]{19.50583,19.88705,-8.598535,1.369784,0.527601,-4.935202,212.3900,0.0});
        this.shomate.put((double)6000,new Double[]{35.51872,1.128728,-0.196103,0.014662,-4.553760,-18.97091,224.9810,0.0});
    }
}
