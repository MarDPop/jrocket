/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import com.marius.rocket.chemistry.Molecules.Molecule;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class AdiabaticFlame {
    public ArrayList<Molecule> species;
    
    
    public void precalc() {
        double totalMoles = 0;
        for(Molecule reactant : species) {
            reactant.calc();
        }
    }
    
    public void calc() {
        
    }
}
