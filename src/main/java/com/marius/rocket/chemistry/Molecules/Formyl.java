/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.H;
import com.marius.rocket.chemistry.Atoms.O;
import com.marius.rocket.chemistry.Atoms.C;

/**
 *
 * @author n5823a
 */
public class Formyl extends Molecule {
    public Formyl(double moles) {
        super(new Atom[]{new C(),new H(),new O()}, new int[] {1,1,1}, 43.51402, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1200,new Double[]{21.13803,40.43610,-14.71337,0.969010,0.239639,36.34712,240.1695,43.51402});
        this.shomate.put((double)6000,new Double[]{52.79371,2.666155,-0.392339,0.023808,-7.457018,11.37797,267.2798,43.51402});
    }
}
