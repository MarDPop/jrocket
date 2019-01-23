/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.H;
import com.marius.rocket.chemistry.Atoms.O;

/**
 *
 * @author n5823a
 */
public class Hydroperoxy extends Molecule{
    public Hydroperoxy(double moles) {
        super(new Atom[]{new H(),new O()}, new int[] {1,2}, 2.092001, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)2000,new Double[]{26.00960,34.85810,-16.30060,3.110441,-0.018611,-7.140991,250.7660,2.092001});
        this.shomate.put((double)6000,new Double[]{45.87510,8.814350,-1.636031,0.098053,-10.17380,-26.90210,266.5260,2.092001});
    }
}
