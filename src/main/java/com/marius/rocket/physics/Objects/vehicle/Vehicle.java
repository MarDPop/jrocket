/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle;

import com.marius.rocket.Math.LA;
import com.marius.rocket.physics.Objects.vehicle.resources.Resource;
import com.marius.rocket.physics.Objects.vehicle.subsystems.Subsystem;
import com.marius.rocket.physics.Objects.vehicle.components.Component;
import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Objects.Environment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author n5823a
 */
public class Vehicle extends Body {
    
    protected double massRate;
    protected Resource[] resourceRate;
    
    public int nStates = 7;
    
    protected ArrayList<Subsystem> subsystems = new ArrayList<>();
    public HashMap<Resource,HashMap<Integer,Integer>> totalResources = new HashMap<>();
    public ArrayList<Component> ComponentList = new ArrayList<>();
    public Environment environment = new Environment();

    public Vehicle() {
        super(0);
    }
    
    @Override
    public void setState(double[] state) {
        super.setState(state);
        this.mass = state[6];
        int i = 7;
        for(Resource r : resourceRate) {
            r.setAmount(state[i]);
            i++;
        }
    }
    
    @Override
    public double[] getStateRate() {
        double[] out = new double[nStates];
        out[0] = xyz[1][0];
        out[1] = xyz[1][1];
        out[2] = xyz[1][2];
        out[3] = xyz[2][0];
        out[4] = xyz[2][1];
        out[5] = xyz[2][2];
        out[6] = massRate;
        int i = 7;
        for(Resource r : resourceRate) {
            out[i] = r.getAmount();
            i++;
        }
        return out;
    }
    
    public void addSubsystem(Subsystem in) {
        subsystems.add(in);
    }
    
    public void removeSubsystem(Subsystem in) {
        subsystems.remove(in);
    }
    
    public final void collectComponents() { 
        forces.clear();
        totalResources.clear();
        for(int k = 0; k < ComponentList.size(); k++){
            Component c = ComponentList.get(k);
            c.forces.forEach(this.forces::add);
            //Resources
            for(int i = 0; i < c.resources.length; i++) {
                Resource resource =  c.resources[i];
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
        nStates = 7+totalResources.keySet().size();
    }
    
    @Override
    public double updateMass() {
        this.mass = 0;
        this.COG = new double[3];
        this.Inertia = new double[3][3];
        ComponentList.parallelStream().forEach((c) -> {
            this.mass += c.getMass();
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
            LA.add(this.Inertia, c.getInertia());
            LA.add(this.COG,LA.multiply(x, mass));
        });
        LA.multiply(this.COG,1/this.mass);
        return this.mass;
    }
    
    public final void recalcResources() {
        totalResources.entrySet().forEach((pair)->{
            double sum = 0;
            for(HashMap.Entry<Integer,Integer> k : pair.getValue().entrySet()) {
                sum += ComponentList.get(k.getKey()).resources[k.getValue()].getAmount();
            }
            pair.getKey().setAmount(sum);
        });
    }
    
    @Override
    public void update(double t) {
        this.environment.update();
        this.ComponentList.parallelStream().forEach((c)-> {
            c.update(t);
        });
        super.update(t);
    }
    
}
