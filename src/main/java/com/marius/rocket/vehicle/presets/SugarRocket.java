/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.vehicle.resources.Resource;
import com.marius.rocket.vehicle.components.Thruster;
import com.marius.rocket.vehicle.*;

/**
 *
 * @author n5823a
 */
public class SugarRocket extends Rocket {
    
    public SugarRocket() {
        super();
        Resource sugarfuel = new Resource(2);
        Thruster sugarthruster = new Thruster();
    }
    
}
