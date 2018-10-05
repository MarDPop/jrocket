/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.vehicle.components.thrusters;

import com.marius.rocket.Equilibrium;
import com.marius.rocket.Math.LA;
import com.marius.rocket.chemistry.Molecules.Hydroxy;
import com.marius.rocket.chemistry.Molecules.Molecule;
import com.marius.rocket.chemistry.Molecules.MonoHydride;
import com.marius.rocket.chemistry.Molecules.Water;
import com.marius.rocket.physics.Material.Steel;
import com.marius.rocket.physics.Physics;
import java.util.Arrays;

/**
 *
 * @author n5823a
 */
public class CombustionChamberWithSimpleStartup extends BipropellantChamber {
    private double[] times;
    private double[] flameTemps;
    private double[] wallTemps;
    private double massChamber;
    private double areaWall; 
    private double thickness;
    private final double incompleteCombustionLoss = 0.98;
    private final double airHeatTransferCoef = 35; // W/m2 K at approx 20m/s
    private Equilibrium calc = new Equilibrium();
    private Steel metal = new Steel();
    
    public void run(double P) {
        this.pressure = P;
        if(check() > 0) {
            return;
        }
        setup();
        //init variables
        double dt = 0.2;
        double final_time = 60;
        int n = (int)Math.ceil(final_time/dt);
        times = new double[n];
        flameTemps = new double[n];
        wallTemps = new double[n];
        //initialize variables
        wallTemps[0] = 298;
        flameTemps[0] = calc.getTemperature()*incompleteCombustionLoss;
        int i = 0;
        for(double time = 0; time < final_time; time += dt) {
            System.out.println(time);
            times[i] = time;
            // assume inner wall temp is temp of flame
            //double dTdx = (flameTemps[i]-wallTemps[i])/(thickness/2); // wall temp is assumed to be average in middle
            //double q_to_wall = metal.getThermalConductivity()*dTdx*areaWall*dt;
            double q_to_wall = 100*(flameTemps[i] - wallTemps[i])*areaWall*dt; 
            double q_out_wall = airHeatTransferCoef*(wallTemps[i]-300)*areaWall*dt; //
            double q_radiation = metal.getEmmisivity()*Physics.STEFAN_BOLTZMANN*Math.pow(wallTemps[i],4)*areaWall*dt;
            System.out.println("heat to wall: "+q_to_wall);
            calc.calcNASAConstantPressure(pressure, q_to_wall);
            i++;
            
            flameTemps[i] = calc.getTemperature()*incompleteCombustionLoss;
            wallTemps[i] = wallTemps[i-1]+(q_to_wall - q_out_wall-q_radiation)/(massChamber*metal.getSpecificHeatCapacity());
            metal.setTemperature(wallTemps[i]);
        }
        System.out.println(Arrays.toString(flameTemps));
    }
    
    private void setup() {
        metal = new Steel();
        calc = new Equilibrium();
        for(Molecule specie : fuel.getContent()) {
            calc.species.add(specie);
        }
        for(Molecule specie : oxydizer.getContent()) {
            calc.species.add(specie);
        }
        Water H2O = new Water(0);
        MonoHydride H = new MonoHydride(0);
        Hydroxy OH = new Hydroxy(0);
        H2O.setTemp(3000);
        H.setTemp(3000);
        OH.setTemp(3000);
        calc.species.add(H2O);
        calc.species.add(H);
        calc.species.add(OH);
        this.areaWall = 2*radius*Math.PI*length + Math.PI*radius*radius;
        this.thickness = metal.getMinThicknessForPress(pressure, radius, 3);
        massChamber = areaWall*thickness*metal.getDensity();
        calc.init(3000);
        calc.AdiabaticFlame(pressure);
    }
    
    private double simpleHeatTransferCoefficient(double vel) {
        // works between 2 and 20 m/s and is in W/m2 K
        return 10.45 - vel + 10*Math.sqrt(vel);
    }
    
    public static double thermalConductivityAir(double K, double pres){
        double[] points = new double[4];
        points[0] = 1.4e-5*K*K*K -4.58e-2*K*K+98.9*K+364; //1bar in W/m/K
        points[1] = -0.0161*K*K + 79.101*K+4692; // 20bar
        points[2] = -0.0092*K*K + 68.257*K+9086.6; //50bar
        points[3] = -2.26e-5*K*K +4.6e-2*K*K+24.9*K+20800; // 100bar
        double[] c = LA.polyFit(new double[]{100000,2000000,5000000,10000000}, points, 2);
        return LA.poly(c,pres);
    }
    
    public int check() {
        int code = 0;
        if(fuel == null || oxydizer == null) {
            System.out.println("chamber resources not set");
            code += 1;
        }
        if(pressure < 1 || volume <= 0 ) {
            System.out.println("chamber pressure or volume not set");
            code += 2;
        }
        if(length <= 0 || radius <= 0 ) {
            System.out.println("chamber size not set");
            code += 4;
        }
        return code;
    }
    
}
