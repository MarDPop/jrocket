/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components.thrusters;

import com.marius.rocket.physics.Objects.vehicle.resources.Resource;

/**
 *
 * @author Me
 */
public class BipropellantChamber extends CombustionChamber{
    protected Resource fuel;
    protected Resource oxydizer;
    
    public void init(Resource fuel, Resource oxydizer) {
        this.fuel = fuel;
        this.oxydizer = oxydizer;
        this.input = new Resource[]{fuel,oxydizer};
    }
}
