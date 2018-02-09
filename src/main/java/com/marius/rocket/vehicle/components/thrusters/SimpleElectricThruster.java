/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.physics.Physics;

/**
 *
 * @author n5823a
 */
public class SimpleElectricThruster extends Thruster {
    protected double P_in; //supply power
    protected double V_b; //beam voltage
    protected double J; //ion current
    protected double P_b; // beam Power
    protected double Ma; //ion mass
    protected double doubleIonFraction; // double ion fraction ie I++/I+
    protected double massUtilizationFactor; // double ionized factor
    protected double doubleCorrectionFactor; // double ionized factor
    protected double gamma; 
    protected double theta; //half angle
    protected double beamDivergence; 
    protected double n_m; //mass utilization efficiency
    protected double n_e; //electrical efficiency
    protected double n_t; //thruster efficiency
    protected double n_d; //discharge loss
    protected double n_g; //grid loss
    
    public void setDefaults() {
        useXenon();
        this.n_d = 0.1;
        useHalfAngle(10*Math.PI/180);
        setDoubleIonFraction(0.1);
        calcThrustCorrection();
    }
    
    public void setInputPower(double power){
        this.P_in = power;
    }
    
    public void setBeamVoltage(double V_b){
        this.V_b = V_b;
    }
    
    public void calcBeamPowerFromThrusterEfficiency() {
        this.J = P_in*n_t/V_b;
    }
    
    public void useXenon(){
        this.Ma = 131.29*1.6605e-27;
    }
    
    public void setIonMass(double Ma){
        this.Ma = Ma;
    }
    
    public void useHalfAngle(double theta){
        this.theta = theta;
        this.beamDivergence = Math.cos(theta);
    }
    
    public void setBeamDivergence(double beamDivergence){
        this.beamDivergence = beamDivergence;
    }
    
    public void calcThrustCorrection() {
        this.gamma = beamDivergence*doubleCorrectionFactor;
    }
    
    public void setDoubleIonFraction(double doubleIonFraction) {
        this.doubleIonFraction = doubleIonFraction;
        this.doubleCorrectionFactor = (1+0.707*doubleIonFraction)/(1+doubleIonFraction);
        this.massUtilizationFactor = (1+0.5*doubleIonFraction)/(1+doubleIonFraction);
    }
    
    public void setMassUtilizationFactor(double massUtilizationFactor){
        this.massUtilizationFactor = massUtilizationFactor;
    }
    
    public void calcMassUtilizationEfficiency(){
        this.n_m = massUtilizationFactor*J/Physics.eV*Ma/massflow;
    }
    
    public void setMassUtilizationEfficiency(double n_m){
        this.n_m = n_m;
    }
    
    public void setElectricalEfficiency(double n_e){
        this.n_e = n_e;
    }
    
    public void setDischargeLoss(double n_d){
        this.n_d = n_d; // eV/ion
    }
    
    public void setGridLoss(double n_g){
        this.n_g = n_g; // Amps/Amps
    }
    
    public void setThrusterEfficiency(double n_t){
        this.n_t = n_t;
    }
    
    public void calcIsp() {
        this.isp = gamma*n_m/9.806*Math.sqrt(2*Physics.eV*V_b/Ma);
    }
    
    public void calcThrust() {
        this.currentThrust = gamma*Math.sqrt(2*Ma*V_b/Physics.eV)*J;
    }
    
    public void calcElectricalEfficiency() {
        this.n_e = V_b/(V_b + n_d);
    }
    
    public void calcThrusterEfficiencySimple() {
        this.n_t = gamma*gamma*n_e*n_m;
    }
    
    
    
}
