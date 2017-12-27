/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.physics.Environment;
import com.marius.rocket.physics.forces.SimpleDrag;
import com.marius.rocket.vehicle.resources.Resource;
import com.marius.rocket.vehicle.components.Thruster;
import com.marius.rocket.vehicle.*;
import com.marius.rocket.vehicle.components.Component;
import com.marius.rocket.vehicle.components.SimpleThruster;
import com.marius.rocket.vehicle.components.SugarThruster;
import com.marius.rocket.vehicle.components.Tank;

/**
 *
 * @author n5823a
 */
public class SugarRocket extends Rocket {
    
    public SugarRocket(Environment env) {
        super();
        env.setBody(this);
        this.environment = env;
        // First stage
        Stage upperstage = new Stage();
        //Tank
        Resource sugarfuel = new Resource(2);
        Tank vacTank = new Tank(0.1);
        vacTank.setResource(sugarfuel);
        upperstage.list.add(vacTank);
        // Thruster
        SugarThruster vacThruster = new SugarThruster(100,200,0.5);
        upperstage.list.add(vacThruster);
        // Shell
        Component shell = new Component();
        shell.setMass(2);
        SimpleDrag d = new SimpleDrag(0.5, 0.01, env);
        shell.forces.add(d);
        upperstage.list.add(shell);
        // Second stage
        Stage lowerstage = new Stage();
        //Tank
        Resource sugarfuel2 = new Resource(5);
        Tank slTank = new Tank(0.2);
        slTank.setResource(sugarfuel2);
        lowerstage.list.add(slTank);
        // Thruster
        SugarThruster slThruster = new SugarThruster(100,150,0.5);
        lowerstage.list.add(slThruster);
      
        //collect components
        Stages.add(upperstage);
        Stages.add(lowerstage);
        this.collectComponents();
    }
    
}
