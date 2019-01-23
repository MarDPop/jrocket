/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.O;

/**
 *
 * @author n5823a
 */
public class Oxygen extends Molecule {
    public Oxygen(double moles) {
        super(new Atom[]{new O()}, new int[] {2}, 0, 6820 , 444, 54.36, 90.188, new double[]{154.581,5.043e6},new double[]{54.361,146.3},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)700,new Double[]{31.32234,-20.23531,57.86644,-36.50624,-0.007374,-8.903471,246.7945,0.0});
        this.shomate.put((double)2000,new Double[]{30.03235,8.772972,-3.988133,0.788313,-0.741599,-11.32468,236.1663,0.0});
        this.shomate.put((double)6000,new Double[]{20.91111,10.72071,-2.020498,0.146449,9.245722,5.337651,237.6185,0.0});
    }
}
