/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle;

import com.marius.rocket.Math.LA;
import com.marius.rocket.vehicle.resources.Resource;
import com.marius.rocket.vehicle.subsystems.Subsystem;
import com.marius.rocket.vehicle.components.Component;
import com.marius.rocket.physics.Body;
import com.marius.rocket.physics.Environment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author n5823a
 */
public class Vehicle extends Body {
    
    protected ArrayList<Subsystem> subsystems = new ArrayList<>();
    public HashMap<Resource,HashMap<Integer,Integer>> totalResources = new HashMap<>();
    public ArrayList<Component> ComponentList = new ArrayList<>();
    protected ArrayList<Stage> Stages = new ArrayList<>();
    public Environment environment = new Environment();

    public Vehicle() {
        super(0);
    }
    
    public void addSubsystem(Subsystem in) {
        subsystems.add(in);
    }
    
    public void removeSubsystem(Subsystem in) {
        subsystems.remove(in);
    }
    
    public void addStage(Stage stage) {
        Stages.add(stage);
        stage.list.forEach((c) -> {
            ComponentList.add(c);
        });
    }
    
    public void removeStage(int i) {
        Stage stage = Stages.get(i);
        stage.list.forEach((c) -> {
            ComponentList.remove(c);
        });
        Stages.remove(i);
    }
    
    public final void collectComponents() { 
        forces.clear();
        totalResources.clear();
        for(int k = 0; k < ComponentList.size(); k++){
            Component c = ComponentList.get(k);
            c.forces.forEach(this.forces::add);
            //Resources
            for(int i = 0; i < c.resources.size(); i++) {
                Resource resource =  c.resources.get(i);
                boolean found = false;
                for(Resource key : totalResources.keySet()) {
                    if(resource.getClass().equals( key.getClass())) {
                        totalResources.get(key).put(k,i);
                        key.changeAmount(resource.getAmount());
                        found = true;
                    }
                }
                if(!found) {
                    HashMap<Integer,Integer> v = new HashMap<>();
                    v.put(k,i);
                    try {
                        Resource r = resource.getClass().newInstance();
                        r.setAmount(resource.getAmount());
                        totalResources.put(r , v);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }
    
    public final void recalcMass() {
        this.mass = 0;
        this.COG = new double[3];
        this.Inertia = new double[3][3];
        ComponentList.forEach((c) -> {
            this.mass+=c.getMass();
            double[] x = c.getCOG();
            double mR2 = c.getMass()*LA.dot(x,x);
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    if (i == j) {
                        this.Inertia[i][j] += mR2;
                    }
                    this.Inertia[i][j] -= c.getMass()*x[i]*x[j]; //probably not the most efficient way of doing this
                }
            }
            LA.add(this.Inertia, c.Inertia);
            LA.add(this.COG,LA.multiply(x, mass));
        });
        LA.multiply(this.COG,1/this.mass);
    }
    
    public final void recalcResources() {
        totalResources.entrySet().forEach((pair)->{
            double sum = 0;
            for(HashMap.Entry<Integer,Integer> k : pair.getValue().entrySet()) {
                sum += ComponentList.get(k.getKey()).resources.get(k.getValue()).getAmount();
            }
            pair.getKey().setAmount(sum);
        });
    }
    
    @Override
    public void update(double time, double dt) {
        this.calcSphericalFromCartesian();
        this.environment.calc();
        recalcMass();
        this.ComponentList.forEach((c)->c.update(time,dt));
        super.update(time,dt);
    }
    
}
