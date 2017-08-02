/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle;

import com.marius.rocket.physics.Body;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author n5823a
 */
public class Vehicle extends Body {
    
    protected ArrayList<Subsystem> subsystems = new ArrayList<>();
    protected ArrayList<Resource> totalResources = new ArrayList<>();
    protected HashMap ComponentList = new HashMap();
    
    public Vehicle() {
        super(0);
    }
    
    public void addSubsystem(Subsystem in) {
        subsystems.add(in);
    }
    
    public void removeSubsystem(Subsystem in) {
        subsystems.remove(in);
    }
    
}
