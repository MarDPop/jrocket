/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.C;
import com.marius.rocket.chemistry.Atoms.H;

/**
 *
 * @author n5823a
 */
public class Methane extends Molecule {
    public Methane(double moles) {
        super(new Atom[]{new C(),new H()}, new int[] {1,4}, -74.87310, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1300,new Double[]{-0.703029,108.4773,-42.52157,5.862788,0.678565,-76.84376,158.7163,-74.87310});
        this.shomate.put((double)6000,new Double[]{85.81217,11.26467,-2.114146,0.138190,-26.42221,-153.5327,224.4143,-74.87310});
    }
}