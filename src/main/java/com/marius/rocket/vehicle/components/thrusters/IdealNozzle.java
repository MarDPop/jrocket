/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.physics.CompressibleFlow;

/**
 *
 * @author n5823a
 */
public class IdealNozzle extends Nozzle {
    private CompressibleFlow flow;
    public boolean started = false;
    
    public void setIC() {
        this.flow.setStagnationTempAndPres(this.chamber.getTemperature(), this.chamber.getPressure());
        this.flow.init(this.chamber.getGamma(),1); //frozen flow
    }
    
    public void run() {
        if(!started) {
            //check if meets min pressure
            double p_max = flow.getStagnationPressure()/Math.pow(flow.getA(),flow.getD());
            if(ambientPressure < p_max) {
                this.massFlow = flow.chokedRate()*A_t;
                //calc shock in nozzle
                flow.intializeMachFromAstarRatio(AreaRatio,true);
                flow.getNewMachFromAstarRatio(AreaRatio);
                p_max = flow.getPressure()*flow.normalShockPressureRatio();
                if(ambientPressure > p_max) {
                    
                } else {
                    if(ambientPressure > flow.getPressure()*1.1) {
                        //underexpanded
                        
                    } else if (ambientPressure < flow.getPressure()*0.9) {
                        //overexpanded
                        
                    } else {
                        //design conditions
                        
                    }
                }
            } else {
                System.out.println("chamber pressure not high enough for sonic conditions");
                double a_exit = flow.setMachFromStaticPressure(ambientPressure);
                double T_exit = flow.getBeta()*flow.getStagnationTemperature();
                double v = a_exit*flow.calcSpeedOfSound();
            }
        } else {
            
        }
    }
    
}
