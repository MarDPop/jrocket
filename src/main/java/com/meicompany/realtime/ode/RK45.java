/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime.ode;

/**
 *
 * @author mpopescu
 */
public class RK45 extends GeneralOde {
    
    
    public RK45(Dynamics dynamics, double[] x, double time_start, double time_final) {
        super(dynamics,x,time_start,time_final);
    }
    
    @Override
    void step() {
        
    }
}
