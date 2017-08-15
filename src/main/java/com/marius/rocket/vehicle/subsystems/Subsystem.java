/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.subsystems;

import com.marius.rocket.vehicle.components.Component;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Subsystem {
    
    protected ArrayList<Component> components = new ArrayList<>();
    protected String Status;
    
    public Subsystem() {
        
    }
}
