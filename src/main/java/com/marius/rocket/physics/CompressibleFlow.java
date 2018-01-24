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
    private double E;
    private double M_2;
    private double Beta;
    private double T_0;
    private double P_0;
    private double A_star_ratio;
    
    public void setStagnationTempAndPres(double T_0, double P_0) {
        this.T_0 = T_0;
        this.P_0 = P_0;
    }
    
    public double getStagnationTemperature() {
        return T_0;
    }
    
    public double getStagnationPressure() {
        return P_0;
    }
    
    public void init(double Gam, double M) {
        setSpecificHeatRatio(Gam);
        setMach(M);
        calcBeta();
    }
    
    private void setSpecificHeatRatio(double Gam) {
        setGamma(Gam);
        this.A = (Gam+1)/2;
        this.B = 1/(Gam-1);
        this.C = A*B;
        this.D = Gam*B;
        this.E = Math.pow(A,C);
    }
    
    public double getSpecificHeatRatio() {
        return this.Gam;
    }
    
    public double getA() {
        return this.A;
    }
    
    public double getB() {
        return this.B;
    }
    
    public double getC() {
        return this.C;
    }
    
    public double getD() {
        return this.D;
    }
    
    private void setMach(double M) {
        this.M = M;
        this.M_2 = M*M;
        calcBeta();
        pres = P_0/calcPressureRatio();
        temp = T_0/Beta;
        calcAstarRatio();
    }
    
    public double getMach() {
        return this.M;
    }
    
    private void calcBeta() {
        this.Beta = M_2*(Gam-1)/2+1; 
    }
    
    public double getBeta() {
        return Beta;
    }
    
    public double calcPressureRatio() {
        return Math.pow(getBeta(), this.D);
    }
    
    public double machAngle() {
        return Math.asin(1/M);
    }
    
    public double PMAngle() {
        double x = Math.sqrt(M*M-1);
        double y = Math.sqrt((Gam+1)*B);
        return y*Math.atan(x/y)-Math.atan(x);
    }
    
    public double calcAstarRatio() {
        return A_star_ratio = E*M*Math.pow(getBeta(),-C);
    }
    
    public double Astar() {
        return area*A_star_ratio;
    }
    
    public static double beta(double gam, double M){
        return (1 + M*M*(gam-1)/2);
    }
    
    public double setMachFromStaticPressure(double p) {
        double ex = Math.pow(P_0/p,1/D);
        setMach(Math.sqrt(0.5*(ex-1)*B));
        return M;
    }
    
    public double chokedRate() {
        return P_0*Math.sqrt(Gam/(R_specific*T_0)*A_star_ratio/E);
    }
    
    public void intializeMachFromAstarRatio(double AR, boolean supersonic) {
        //this gives a rough estimate to start with
        double M_high = 8.01;
        double M_low = 1.01;
        double sign = 1;
        if(!supersonic) {
            M_high = 0.99;
            M_low = 0.33;
            if((E*M_low*Math.pow(beta(Gam,M_low),-C)-AR)>0){
                setMach(M_low);
                return;
            }
            sign = -1;
        } else {   
            if((E*M_high*Math.pow(beta(Gam,M_high),-C)-AR)>0){
                setMach(M_high);
                return;
            }
        }
        for(int i = 0; i < 4; i++) {
            setMach((M_high+M_low)/2);
            double c_i = sign*(calcAstarRatio()-AR);
            if(c_i > 0){
                M_low = M;
            } else {
                M_high = M;
            }
        }

    }
    
    public double getNewMachFromAstarRatio(double A_star_ratio_desired, double tol) {
        //M is the initial guess
        for(int i = 0; i < 30; i++) {
            double dF = E*(Math.pow(getBeta(), -C) - M*M*A*Math.pow(getBeta(),-(C+1)));
            double M_old = M;
            setMach(M_old-(A_star_ratio-A_star_ratio_desired)/dF);
            if(Math.abs(M_old-M) < tol){
                return M;
            }
        }
        return -1; //not found condition
    }
    
    public double getNewMachFromAstarRatio(double AR) {
        return getNewMachFromAstarRatio(AR, 1e-7);
    }
    
    public double normalShockPressureRatio() { //p2/p1
        return (2*Gam*M_2-(Gam-1))/(Gam+1);
    }
    
    public double normalShockDensityRatio() { //rho2/rho1
        return A*M_2/Beta;
    }
    
    public double normalShockTemperatureRatio() { //T2/T1
        return normalShockPressureRatio()/normalShockDensityRatio();
    }
    
    public double normalShockNewMach() { //T2/T1
        return Math.sqrt(2*Beta/(2*Gam*M_2-(Gam-1)));
    }
    
    public double normalShockTotalPressureRatio() { //T2/T1
        return Math.pow(normalShockDensityRatio(),D)*Math.pow(normalShockPressureRatio(),-B);
    }
    
    public double normalShockSpecialPressureRatio() { //P02/P1
        return 1/Math.pow(2*(Gam+1)*M_2,D)*Math.pow(normalShockPressureRatio(),-B);
    }
    
    public double[] normalShock() {
        double[] out = new double[5];
        out[0] = normalShockPressureRatio();
        out[1] = normalShockDensityRatio();
        out[2] = out[0]/out[1];
        out[3] = Math.pow(out[1],D)*Math.pow(out[0],-B);
        out[4] = M_2/out[1]/out[0]; //M2^2
        this.P_0 = out[3]*P_0;
        setMach(Math.sqrt(out[4]));        
        return out;
    }
    
    public static double getMachExitShockInNozzle(double k, double throat_to_exit_area, double chamber_to_exit_pressure) {
        double a = (k-1);
        double X = (k+1)/2;
        X = Math.pow(X,X/a);
        double c = throat_to_exit_area*chamber_to_exit_pressure/X;
        c *= -c;
        return Math.sqrt((-1+Math.sqrt(1-2*a*c))/a);
    }
    
    public static double getShockArea(double k, double throat_to_exit_area, double chamber_to_exit_pressure) {
        double mach = getMachExitShockInNozzle(k, throat_to_exit_area, chamber_to_exit_pressure);
        double p_ratio = Math.pow(beta(k,mach),(k/(k-1)));
    }
    
    
}
