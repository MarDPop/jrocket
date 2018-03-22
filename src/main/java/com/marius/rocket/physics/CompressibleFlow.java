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
    private double A_star;
    private double PMAngle;
    
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
    
    public void chokedFlow(double area) {
        setMach(1);
        this.area = area;
        this.A_star = area;
        this.massFlow = area*chokedRate();
    }
    
    public void setSpecificHeatRatio(double Gam) {
        setGamma(Gam);
        this.A = (Gam+1)/2;
        this.B = 1/(Gam-1);
        this.C = A*B;
        this.D = Gam*B;
        this.E = Math.pow(A,C);
    }
    
    public double getSpecificHeatRatio() {
        return this.gam;
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
    
    public void setMach(double M) {
        this.M = M;
        this.M_2 = M*M;
        calcBeta();
        pres = P_0/calcPressureRatio();
        temp = T_0/Beta;
        calcAstarRatio();
        PMAngle();
    }
    
    public double getMach() {
        return this.M;
    }
    
    private void calcBeta() {
        this.Beta = M_2*(gam-1)/2+1; 
    }
    
    public double getBeta() {
        return Beta;
    }
    
    public double calcPressureRatio() {
        return Math.pow(getBeta(), this.D);
    }
    
    public double getMachAngle() {
        return Math.asin(1/M);
    }
    
    private double PMAngle() {
        double x = Math.sqrt(M*M-1);
        double y = Math.sqrt((gam+1)*B);
        return PMAngle = y*Math.atan(x/y)-Math.atan(x);
    }
    
    public double getPMAngle() {
        return PMAngle;
    }
    
    private double calcAstarRatio() {
        return A_star_ratio = E*M*Math.pow(getBeta(),-C);
    }
    
    public double getAstarRatio() {
        return A_star_ratio;
    }
    
    public double getAstar() {
        return A_star;
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
        return P_0*Math.sqrt(gam/(specific_gas_constant*T_0)*A_star_ratio/E);
    }
    
    public void intializeMachFromAstarRatio(double AR, boolean supersonic) {
        //this gives a rough estimate to start with
        double M_high = 8.01;
        double M_low = 1.01;
        double sign = 1;
        if(!supersonic) {
            M_high = 0.99;
            M_low = 0.33;
            if((E*M_low*Math.pow(beta(gam,M_low),-C)-AR)>0){
                setMach(M_low);
                return;
            }
            sign = -1;
        } else {   
            if((E*M_high*Math.pow(beta(gam,M_high),-C)-AR)>0){
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
        return (2*gam*M_2-(gam-1))/(gam+1);
    }
    
    public double normalShockDensityRatio() { //rho2/rho1
        return A*M_2/Beta;
    }
    
    public double normalShockTemperatureRatio() { //T2/T1
        return normalShockPressureRatio()/normalShockDensityRatio();
    }
    
    public double normalShockNewMach() { //T2/T1
        return Math.sqrt(2*Beta/(2*gam*M_2-(gam-1)));
    }
    
    public static double normalShockNewMach(double M, double k) { //T2/T1
        double M_2 = M*M;
        return Math.sqrt((2+(k-1)*M_2)/(2*k*M_2-(k-1)));
    }
    
    public double normalShockTotalPressureRatio() { //T2/T1
        return Math.pow(normalShockDensityRatio(),D)*Math.pow(normalShockPressureRatio(),-B);
    }
    
    public double normalShockSpecialPressureRatio() { //P02/P1
        return 1/Math.pow(2*(gam+1)*M_2,D)*Math.pow(normalShockPressureRatio(),-B);
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
    
    public double isentropicExpansion(double M_2) {
        double AreaRatio = M/M_2*Math.pow(beta(gam,M_2)/Beta,C);
        area = area*AreaRatio;
        return AreaRatio;
    }
    
    public static double obliqueShockAngleFromDeflection(double d, double k, double M, boolean weak, double tol) {
        double s = d*1.1; // shock angle
        if(!weak)  {
            s = 85*Math.PI/180;
        } 
        double h = Math.tan(d);
        double M_2 = M*M;
        double kM = (k+1)*M_2;
        for(int i = 0; i < 30; i++) {
            double ss = Math.sin(s);
            double ss_2 = ss*ss;
            double x = M_2*ss_2-1;
            double y = kM-2*x;
            double z = Math.cos(s);
            z = M_2*z*z;
            double F = 2/Math.tan(s)*x/y-h;
            double s_old = s;
            double dF = 2/y*(4*z*x/y+2*z-x/ss_2);
            s = s_old - F/dF;
            if(Math.abs(s-s_old) < tol){
                return s;
            }
        }
        return -1;
    }
    
    public static double obliqueDeflectionFromShockAngle(double s, double k, double M) {
        double ss = Math.sin(s);
        double x = 2/Math.tan(s)*(M*M*ss*ss-1);
        double y = M*M*(k+Math.cos(2*s))+2;
        return Math.atan(x/y);
    }
    
    public static double obliqueMachAfterShock(double s, double d, double k, double M_1) {
        return normalShockNewMach(M_1*Math.sin(s),k)/Math.sin(s-d);
    }
    
    
}
