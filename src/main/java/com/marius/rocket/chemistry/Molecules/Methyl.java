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
public class Methyl extends Molecule {
    public Methyl(double moles) {
        super(new Atom[]{new C(),new H()}, new int[] {1,3}, 145.6873, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1400,new Double[]{28.13786,36.74736,-4.347218,-1.595673,0.001860,135.7118,217.4814,145.6873});
        this.shomate.put((double)6000,new Double[]{67.18081,7.846423,-1.440899,0.092685,-17.66133,92.47100,235.9023,145.6873});
    }
}
