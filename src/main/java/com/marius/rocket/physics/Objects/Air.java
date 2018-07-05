/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects;

import com.marius.rocket.chemistry.Molecules.Argon;
import com.marius.rocket.chemistry.Molecules.Nitrogen;
import com.marius.rocket.chemistry.Molecules.Oxygen;

/**
 *
 * @author mpopescu
 */
public class Air extends Fluid {
    public Air() {
        this.species.add(new Nitrogen(78));
        this.species.add(new Oxygen(21));
        this.species.add(new Argon(1));
    }
}
