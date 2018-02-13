/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.N;
import com.marius.rocket.chemistry.Atoms.O;

/**
 *
 * @author n5823a
 */
public class NitrogenMonoxide extends Molecule {
    public NitrogenMonoxide(double moles) {
        super(new Atom[]{new N(),new O()}, new int[] {1,1}, 90.29114, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1200,new Double[]{23.83491,12.58878,-1.139011,-1.497459,0.214194,83.35783,237.1219,90.29114});
        this.shomate.put((double)6000,new Double[]{35.99169,0.957170,-0.148032,0.009974,-3.004088,73.10787,246.1619,90.29114});
    }
}
