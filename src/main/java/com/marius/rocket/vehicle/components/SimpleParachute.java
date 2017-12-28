/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components;

import com.marius.rocket.physics.Environment;
import com.marius.rocket.physics.forces.Force;
import com.marius.rocket.physics.forces.SimpleDrag;

/**
 *
 * @author n5823a
 */
public class SimpleParachute extends Component{
    private SimpleDrag drag;
    private final double ClosedArea;
    private final double OpenArea;
    private final double ClosedCD;
    private final double OpenCD;
    
    public SimpleParachute(double ClosedArea,double ClosedCD,double OpenArea,double OpenCD,double mass) {
        this.ClosedArea = ClosedArea;
        this.OpenArea = OpenArea;
        this.ClosedCD = ClosedCD;
        this.OpenCD = OpenCD;
        this.mass = mass;
    }
    
    public Force deploy(Environment env) {
        this.drag = new SimpleDrag(ClosedCD,ClosedArea,env); // need to add this force when deployed, done on purpose to avoid overcalculatiing parachute forces
        return this.drag;
    }
    
    public void open() {
        this.drag.setCD(OpenCD);
        this.drag.setCD(OpenArea);
    }
}
