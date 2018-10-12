/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components;

import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Objects.vehicle.resources.Resource;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Component extends Body{
    public double temp;
    
    public ArrayList<Component> parts = new ArrayList<>();
    public ArrayList<Controller> controllers = new ArrayList<>();
    public ArrayList<Connection> connections = new ArrayList<>();
    public ArrayList<Resource> resources = new ArrayList<>();
    
    public Component(double mass){
        super(mass);
        this.onrails = true;
    }
    
}
