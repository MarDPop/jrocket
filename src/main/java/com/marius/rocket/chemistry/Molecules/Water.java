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
public class Water extends Molecule {
    
    public Water(double moles) {
        super(new Atom[]{new H(),new O()}, new int[] {2,1}, -241.8264, 40660 , 333.55*18.01, 373.15, 273.15 , new double[]{647.096,22.064e6},new double[]{273.16,611.657},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1700,new Double[]{30.09200,6.832514,6.793435,-2.534480	,0.082139,-250.8810,223.3967,-241.8264});
        this.shomate.put((double)6000,new Double[]{41.96426,8.622053,-1.499780,0.098119,-11.15764,-272.1797,219.7809,-241.8264});
    }
}
