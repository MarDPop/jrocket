/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.vehicle.components.thrusters;

import com.marius.rocket.physics.CompressibleFlow;

/**
 *
 * @author n5823a
 */
public class IdealNozzle extends Nozzle {
    private CompressibleFlow flow;
    public boolean started = false;
    private double p_max;
    private double half_angle;
    private double other_loss;
    
    public void init() {
        this.flow.setMolarMass(chamber.getMolarMass()); //frozen flow
        this.flow.setStagnationTempAndPres(chamber.getTemperature(), chamber.getPressure());
        this.flow.setGamma(chamber.getGamma());
        this.flow.chokedFlow(A_t); 
    }
    
    public void run() {
        if(!started) {
            start();
        } else {
            if (ambientPressure < p_max) {
                main();
            } else {
                started = false;
                start();
            }
        }
        this.thrust = other_loss*(exit_v*massFlow*Math.cos(half_angle) + A_e*(exit_p-ambientPressure));
    }
    
    private void start() {
        //check if meets min pressure
        p_max = flow.getStagnationPressure()/Math.pow(flow.getA(),flow.getD());
        if(ambientPressure < p_max) {
            this.massFlow = flow.chokedRate()*A_t;
            //calc shock at nozzle exit
            flow.intializeMachFromAstarRatio(AreaRatio,true);
            flow.getNewMachFromAstarRatio(AreaRatio);
            p_max = flow.getPressure()*flow.normalShockPressureRatio();
            if(ambientPressure > p_max) {
                double M_exit = getMachExitShockInNozzle(chamber.getGamma(),this.A_t/this.A_e,chamber.getPressure()/ambientPressure);
                double T_exit = chamber.getTemperature()/CompressibleFlow.beta(1.4,M_exit);
                double a_exit = Math.sqrt(chamber.getGamma()*flow.getGasConstant()*T_exit);
                this.exit_v = M_exit*a_exit;
                exit_p = ambientPressure;
            } else {
                started = true;
                main();
            }
        } else {
            System.out.println("chamber pressure not high enough for sonic conditions");
            double a_exit = flow.setMachFromStaticPressure(ambientPressure);
            double T_exit = flow.getBeta()*flow.getStagnationTemperature();
            double rho_exit = ambientPressure/(flow.getGasConstant()*T_exit);
            exit_p = ambientPressure;
            this.exit_v = a_exit*flow.calcSpeedOfSound();
            this.massFlow = rho_exit*A_e*exit_v;
        }
        
    }
    
    private void main() {
        this.exit_v = Math.sqrt(2*flow.getCp()*chamber.getTemperature()*(1-Math.pow(chamber.getPressure()/ambientPressure,(chamber.getGamma()-1)/chamber.getGamma())));
        this.exit_p = flow.getPressure();
        if(ambientPressure > flow.getPressure()*1.1) {
            //underexpanded
            half_angle = -1;
        } else if (ambientPressure < flow.getPressure()*0.9) {
            //overexpanded
            
            half_angle = 1;
        } else {
            //design conditions
            exit_p = ambientPressure;
        }
    }
    
    public static double getMachExitShockInNozzle(double k, double throat_to_exit_area, double chamber_to_exit_pressure) {
        double a = (k-1);
        double X = (k+1)/2;
        X = Math.pow(X,X/a);
        double c = throat_to_exit_area*chamber_to_exit_pressure/X;
        c *= -c;
        return Math.sqrt((-1+Math.sqrt(1-2*a*c))/a);
    }
    
    public static double getNozzleShockMach(double k, double throat_to_exit_area, double chamber_to_exit_pressure, double tol) {
        double Mach = getMachExitShockInNozzle(k, throat_to_exit_area, chamber_to_exit_pressure);
        double p_ratio = Math.pow(CompressibleFlow.beta(k,Mach),(k/(k-1))); //p_total/p
        p_ratio = p_ratio/chamber_to_exit_pressure; // pressure ratio across shock
        Mach = 1.1;
        double k_1 = k-1;
        double k_2 = k+1;
        double k_3 = 1/k_1;
        double k_4 = k*k_3;  
        double dM = 0.05;
        for(int i = 0; i < 30; i++) {
            double M_old = Mach;
            double Mach_2 = M_old*M_old;
            double x = k_1*Mach_2+2;
            double y = 2*k*Mach_2-k_1;
            //double dF = 2*k_2*k_4*Mach*(Math.pow(k_2*k_2*Mach_2/x/y,k_3)*(1-k_1*Mach_2/x)/x-2*Math.pow(Mach_2*y/x,k_4)/y/y);
            double F = Math.pow((k_2*Mach_2)/x,k_4)*Math.pow(k_2/y,k_3);
            Mach_2 = (Mach+dM)*(Mach+dM);
            x = k_1*Mach_2+2;
            y = 2*k*Mach_2-k_1;
            double dF = (Math.pow((k_2*Mach_2)/x,k_4)*Math.pow(k_2/y,k_3)-F)/dM;
            System.out.println(Mach);
            if(i<5){
                Mach = M_old-(F-p_ratio)/dF/(6-i);
            } else {
                Mach = M_old-(F-p_ratio)/dF;
            }
            if(Math.abs(M_old-Mach) < tol){
                return Mach;
            }
        }
        return -1;
    }
    
    public static double getNozzleShockMach(double k, double throat_to_exit_area, double chamber_to_exit_pressure) {
        return getNozzleShockMach(k,throat_to_exit_area,chamber_to_exit_pressure,1e-5);
    }
    
}
