/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle;

import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class Rocket extends Vehicle {
    
    protected ArrayList<Stage> Stages = new ArrayList<>();
    
    public void addStage(Stage stage) {
        this.Stages.add(stage);
        stage.list.forEach((c) -> {
            ComponentList.add(c);
        });
    }
    
    public void removeStage(int i) {
        Stage stage = this.Stages.get(i);
        stage.list.forEach((c) -> {
            ComponentList.remove(c);
        });
        this.Stages.remove(i);
    }
    
    public void initUp() {
        this.calcSphericalFromCartesian();
        this.calcSphericalUnitVectors();
        this.orientation = this.spherical_unit_vectors;
    }
    
}
