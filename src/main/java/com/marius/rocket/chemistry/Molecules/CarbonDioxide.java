/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Atoms.C;
import com.marius.rocket.chemistry.Atoms.O;
/**
 *
 * @author n5823a
 */
public class CarbonDioxide extends Molecule{
    public CarbonDioxide(double moles) {
        super(new Atom[]{new C(),new O()}, new int[] {1,2}, 0, 15326 , 8647, 216.6, 194.7 , new double[]{3014.18,7.38e6},new double[]{216.58,518.5e3},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1200,new Double[]{24.99735,55.18696,-33.69137,7.948387,-0.136638,-403.6075,228.2431,-393.5224});
        this.shomate.put((double)6000,new Double[]{58.16639,2.720074,-0.492289,0.038844,-6.447293,-425.9186,263.6125,-393.5224});
    }
}
