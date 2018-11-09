/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

import com.marius.rocket.Math.dynamics.Dynamics;
import com.marius.rocket.physics.Objects.Body;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mpopescu
 */
public abstract class IntegratorBody {
    
    /* Parameters */
    // Required
    protected double startTime;
    protected double endTime; 
    protected double[][] initialCovariance;
    protected double[][] sigma;
    
    // Dynamics
    protected Body[] bodies;
    
    // Calculated
    protected double time;
    protected double dt;
    protected double[] x_old;
    //protected double[] dx;
    protected double[] errorBounds;
    public ArrayList output;
    protected int steps;
    
    // Options
    protected final double maxDt;
    protected final double minDt;
    protected final double relativeTolerance;
    protected final double absoluteTolerance;
    protected double eps; // norm error
    protected final boolean debug;
    protected final double[] optionalValues;
    
    // Static Constants
    private static final double[] DEFAULT_OPTIONS = new double[]{10,1e-6,1e-4,1e-3,1};
    private static final int MAX_STEPS = 100000;
    
    public IntegratorBody(double startTime, double endTime) {
        this(startTime,endTime,new double[6][6],DEFAULT_OPTIONS,false);
    }

    public IntegratorBody(double startTime, double endTime, double[][] initialCovariance, double[] options, boolean debug) {
        this.startTime = startTime;
        this.time = startTime;
        this.endTime = endTime;
        this.initialCovariance = initialCovariance;
        this.sigma = initialCovariance;
        this.maxDt = options[0];
        this.minDt = options[1];
        this.relativeTolerance = options[2];
        this.absoluteTolerance = options[3];
        this.eps = absoluteTolerance;
        this.optionalValues = new double[options.length-4];
        for(int i = 4; i < options.length; i++) {
            this.optionalValues[i-4] = options[i];
        }
        this.output = new ArrayList<>();
        this.debug = debug;
        this.steps = 0;
    }
    
    /**
    * This performs the actual integration step. The method uses the dynamics set to change the state vector.
    * this method takes no parameters and returns no value, the values at each step being stored in temporary variables 
    * that may be stored as part of the run() method. This is done in the interest of memory if performed for the sake 
    * of finding final state only.
    */
    abstract void step();
    
    /**
     * This method provides the stop condition for the run method. Unless overrided, this checks
     * the condition if the time has not exceeded desired run time or max steps exceeded. If 
     * the dynamics have a stop condition (ie. like hitting a target or ground) then that is included.
     * debug options are included.
     * 
     * @return  stop condition met  
     */
    protected boolean stop(){
        if(steps > MAX_STEPS) {
            System.out.println("max integration steps reached");
            return true;
        }
        return time > endTime;
    }
    
    /**
     *  the main method for the integrator class. 
     */
    public final void run() {
        while(!this.stop()){
            step();
            steps++;
            postStep();
        }
    }
    
    /**
     *  performs processing after step, unless overrided just saves state at each step.
     */
    public void postStep() {
        
    }
    
    /**
     * allows the user to define new norm error other than default (absolute tolerance).
     * @param eps 
     */
    public void overrideNormError(double eps) {
        this.eps = eps;
    }
    
    public double[][] getCovariance() {
        return sigma;
    }
    
    public double getTime() {
        return time;
    }
    
    public double[] getOptions() {
        double[] options = new double[optionalValues.length + 4];
        options[0] = maxDt;
        options[1] = minDt;
        options[1] = relativeTolerance;
        options[2] = absoluteTolerance;
        for(int i = 3; i < options.length; i++) {
            options[i] = optionalValues[i-4];
        }
        return options;
    }
    
    
    
}
