/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.presets;

import com.marius.rocket.physics.Objects.Environment;
import com.marius.rocket.physics.forces.SimpleDrag;
import com.marius.rocket.vehicle.resources.Resource;
import com.marius.rocket.vehicle.*;
import com.marius.rocket.vehicle.components.Component;
import com.marius.rocket.vehicle.components.thrusters.SugarThruster;
import com.marius.rocket.vehicle.components.Tank;

/**
 *
 * @author n5823a
 */
public class SugarRocket extends Rocket {
    private int currentStage = 1;
    private SugarThruster currentThruster;
    
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
        SugarThruster vacThruster = new SugarThruster(400,200,0.5);
        vacThruster.setTank(vacTank);
        upperstage.list.add(vacThruster);
        upperstage.thrusteridx = 1;
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
        SugarThruster slThruster = new SugarThruster(1000,150,0.5);
        slThruster.setTank(slTank);
        lowerstage.list.add(slThruster);
        lowerstage.thrusteridx = 1;
        currentThruster = slThruster;
      
        //collect components
        super.addStage(upperstage);
        super.addStage(lowerstage);
        this.collectComponents();
    }
    
    @Override 
    public void update(double time, double dt) {
        if(currentThruster.isSpent() && currentStage > 0) {
            removeStage(currentStage);
            currentStage--;
            currentThruster = (SugarThruster) Stages.get(currentStage).list.get(Stages.get(currentStage).thrusteridx);
        }
        super.update(time,dt);
    }
    
}
