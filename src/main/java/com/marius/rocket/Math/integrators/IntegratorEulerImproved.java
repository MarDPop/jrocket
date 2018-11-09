/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;
import com.marius.rocket.physics.Objects.vehicle.Vehicle;

/**
 *
 * @author mpopescu
 */
public class IntegratorEulerImproved extends IntegratorBody {

    private SolarsystemBody[] system;
    private Vehicle v;
    private double dt2;
    
    public IntegratorEulerImproved(double startTime, double endTime, Vehicle v, double dt) {
        super(startTime,endTime);
        this.v = v;
        this.dt = dt; //step size
        this.dt2 = dt*dt/2;
    }

    @Override
    public void step(){
        for(SolarsystemBody s : system) {
            s.update(time);
        }
        v.update(time);
        double[] dx = v.getStateRate();
        double[] x = new double[dx.length];
        x[0] += dx[0]*dt+dx[3]*dt2;
        x[1] += dx[1]*dt+dx[4]*dt2;
        x[2] += dx[2]*dt+dx[5]*dt2;
        x[3] += dx[3]*dt;
        x[4] += dx[4]*dt;
        x[5] += dx[5]*dt;
        v.setState(x);
        time += dt;
    }
    
    
}
