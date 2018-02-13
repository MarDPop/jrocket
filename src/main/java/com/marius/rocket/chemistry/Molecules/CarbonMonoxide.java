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
public class CarbonMonoxide extends Molecule{
    public CarbonMonoxide(double moles) {
        super(new Atom[]{new C(),new O()}, new int[] {1,1}, -110.5271, -0 , -0, -0, -0 , new double[]{-0,-0},new double[]{-0,-0},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1300,new Double[]{25.56759,6.096130,4.054656,-2.671301,0.131021,-118.0089,227.3665,-110.5271});
        this.shomate.put((double)6000,new Double[]{35.15070,1.300095,-0.205921,0.013550,-3.282780,-127.8375,231.7120,-110.5271});
    }
}
