/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Molecules;

import com.marius.rocket.chemistry.Atoms.*;

/**
 *
 * @author n5823a
 */
public class Hydrogen extends Molecule {
    
    public Hydrogen(double moles) {
        super(new Atom[]{new H()}, new int[] {2}, 0, 904, 117, 13.99, 20.271, new double[]{32.938,1.2858e6},new double[]{13.8033,7.041e3},new double[]{0,0,0,0});
        this.moles = moles;
        this.shomate.put((double)1000,new Double[]{33.066178,-11.363417,11.432816,-2.772874,-0.158558,-9.980797,172.707974,0.0});
        this.shomate.put((double)2500,new Double[]{18.563083,12.257357,-2.859786,0.268238,1.977990,-1.147438,156.288133,0.0});
        this.shomate.put((double)6000,new Double[]{43.413560,-4.293079,1.272428,-0.096876,-20.533862,-38.515158,162.081354,0.0});
    }
}
