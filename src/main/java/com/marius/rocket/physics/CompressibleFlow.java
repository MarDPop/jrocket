/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class CompressibleFlow extends Flow {
    private double M;
    private double A;
    private double B;
    private double C;
    private double D;
    private double Beta;
    private double T_0;
    private double P_0;
    
    public void setStagnationTempAndPres(double T_0, double P_0) {
        this.T_0 = T_0;
        this.P_0 = P_0;
    }
    
    public void setCompressiblity(double Gam, double M) {
        setSpecificHeatRatio(Gam);
        setMach(M);
        calcBeta();
    }
    
    private void setSpecificHeatRatio(double Gam) {
        this.Gam = Gam;
        this.A = (Gam+1)/2;
        this.B = 1/(Gam-1);
        this.C = this.A*this.B;
        this.D = Gam*this.B;
    }
    
    private void setMach(double M) {
        this.M = M;
    }
    
    private void calcBeta() {
        this.Beta = beta(this.Gam,this.M);
    }
    
    public double getBeta() {
        return Beta;
    }
    
    public double calcPressureRatio() {
        return Math.pow(getBeta(), this.D);
    }
    
    public double calcAstarRatio() {
        return Math.pow(A,C)*M*Math.pow(getBeta(),C);
    }
    
    public static double beta(double gam, double M){
        return 1/(1 + M*M*(gam-1)/2);
    }
    
}
