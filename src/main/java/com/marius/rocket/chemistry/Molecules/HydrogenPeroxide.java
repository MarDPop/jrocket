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
public class HydrogenPeroxide extends Molecule{
    public HydrogenPeroxide(double moles) {
        super(new Atom[]{new H(),new O()}, new int[] {2,2}, 90.29114, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1500,new Double[]{34.25667,55.18445,-35.15443,9.087440,-0.422157,-149.9098,257.0604,-136.1064});
    }
}
