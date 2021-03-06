// PLEASE CHECK MEMORY LEAKS!!!!
// --------------------------
package com.marius.rocket;

import com.marius.rocket.Utils.equilibrium.Equilibrium;
import com.marius.rocket.physics.Objects.solarsystem.Earth;
import com.marius.rocket.physics.Objects.Environment;
import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;
import com.marius.rocket.Math.Euler;
import com.marius.rocket.Math.LA;
import static com.marius.rocket.Math.LA.*;
import com.marius.rocket.Math.Order2euler;
import com.marius.rocket.Math.RK2;
import com.marius.rocket.Math.tools.Coordinates;
import com.marius.rocket.Utils.Recorder;
import com.marius.rocket.chemistry.Molecules.Hydrogen;
import com.marius.rocket.chemistry.Molecules.Hydroxy;
import com.marius.rocket.chemistry.Molecules.Molecule;
import com.marius.rocket.chemistry.Molecules.MonoHydride;
import com.marius.rocket.chemistry.Molecules.Oxygen;
import com.marius.rocket.chemistry.Molecules.Water;
import java.util.Arrays;
import com.marius.rocket.physics.*;
import com.marius.rocket.physics.forces.Gravity;
import com.marius.rocket.physics.Objects.vehicle.components.thrusters.CombustionChamberWithSimpleStartup;
import com.marius.rocket.physics.Objects.vehicle.components.thrusters.IdealNozzle;
import com.marius.rocket.physics.Objects.vehicle.presets.SimpleRocket;
import com.marius.rocket.physics.Objects.vehicle.presets.SugarRocket;
import com.marius.rocket.physics.Objects.vehicle.resources.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author n5823a
 */
public class Sim {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       test8();
    }
    
    private static void test8() {
        double[][] A = new double[][]{{17,24,1,8,15},{23,5,7,14,16},{4,6,13,20,22},{10,12,19,21,3},{11,18,25,2,9}};
        double[] eig = LA.QReig(A);
    }
    
    private static void test7() {
        double[] r  = new double[]{7.866667556962524e6, 1.586685839678611e6,0.159199603426781e6};
        double[] v  = new double[]{-1.280158840048e3, 7.565966948785e3,0.759128812816e3};
        double mu = 3.986e14;
        double[] oe = Coordinates.rv2oe(r, v, mu); // ans: [1e7 0.2 0.1 0 0 0.2]
        double[][] rv = Coordinates.oe2rv(oe, mu);
        System.out.println(Arrays.toString(oe));
        System.out.println(Arrays.deepToString(rv));
        
    }
    
    private static void test6() {
        double[][] A = new double[][]{{17,24,1,8,15},{23,5,7,14,16},{4,6,13,20,22},{10,12,19,21,3},{11,18,25,2,9}};
        double[][] A2 = new double[][]{{4,2,2},{2,4,2},{2,2,4}};
        double[] b = new double[]{3,4,1};
        LA.HouseholderSolve(A2,b);
        System.out.println(Arrays.deepToString(A2)); 
        System.out.println(Arrays.toString(b)); 
    }
    
    private static void test5() {
        System.out.println(CombustionChamberWithSimpleStartup.thermalConductivityAir(500,10000000));
        
    }
    
    private static void test4(){
        CombustionChamberWithSimpleStartup chamber = new CombustionChamberWithSimpleStartup();
        Hydrogen LH = new Hydrogen(2);
        Oxygen LOX = new Oxygen(1);
        LH.setTemp(40);
        LOX.setTemp(100);
        Resource LiquidHydrogen = new Resource(LH);
        Resource LiquidOxygen = new Resource(LOX);
        chamber.init(LiquidHydrogen, LiquidOxygen);
        chamber.setSize(1, 1);
        chamber.run(5000000);
    }
    
    
    private static void test3(){
        Equilibrium flame = new Equilibrium();
        ArrayList<Molecule> selected = new ArrayList<>();
        Hydrogen LH = new Hydrogen(2);
        Oxygen LOX = new Oxygen(1);
        Water H2O = new Water(0);
        MonoHydride H = new MonoHydride(0);
        Hydroxy OH = new Hydroxy(0);
        LH.setTemp(300);
        LOX.setTemp(300);
        H2O.setTemp(3000);
        H.setTemp(3000);
        OH.setTemp(3000);
        selected.add(LH);
        selected.add(LOX);
        selected.add(H2O);
        selected.add(H);
        selected.add(OH);
        flame.species = selected;
        flame.init(2500);
        flame.AdiabaticFlame(101325);
        
        System.out.println();
    }
    
    private static void test(){
        Globals.time = new Date();
        Globals.outputtoscreen = false;
        
        Earth earth = new Earth();
        double[][] ksc = earth.KSCXYZ();
        
        Environment env = new Environment();
        env.setAtm(earth.getAtm());
        
        SimpleRocket rocket_1 = new SimpleRocket(env);
        rocket_1.setXYZ(ksc); 
        rocket_1.updateMass();
        rocket_1.initUp();
        System.out.println(rocket_1.forces.size());
        rocket_1.g = new Gravity(rocket_1,new SolarsystemBody[]{earth});
        rocket_1.forces.add(rocket_1.g);
        
        final double dt = 0.05;
        rocket_1.calcSphericalFromCartesian();
        rocket_1.update(0);
        RK2 ode = new RK2(dt);
        ode.bodies = new Body[]{rocket_1};
        ode.setEndTime(120);
        ode.init();
        
        System.out.println(rocket_1.forces.size());
        try {
            Recorder rec = new Recorder("record.csv");
            while(ode.getTime() < ode.getEndTime()){
                rocket_1.calcSphericalFromCartesian();
                if(Globals.outputtoscreen) {
                    System.out.println("TIME: "+ ode.getTime());
                    System.out.println("------------Rocket------------");
                    System.out.println("Position: " + Arrays.toString(rocket_1.getXYZ()[0]));
                    System.out.println("Speed: " + Arrays.toString(rocket_1.getXYZ()[1]));
                    System.out.println("Acceleration: " + Arrays.toString(rocket_1.getXYZ()[2]));
                    //System.out.println("Mass: " + rocket_1.getMass());
                }
                ode.step();
                rec.record(ode.getTime(), ode.old[0]);
            }
            rec.finish();
        } catch(Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    
    private static void test2() {
//1,3,3,4
//2,2,8,2
//0,3,7,1
//0,1,2,12
        double[][] arr = randSquare(100,1);
        for(int i = 0; i< 100; i++) {
            arr[i][i] = 10;
        }
        double[] b = randVec(100,1);
        double[] b1 = new double[100];
        //for(int l = 0; l < 50000; l++) {
          double[][] arr1 = copy(arr);
          System.arraycopy(b, 0, b1, 0, 100);
          //double[][] arr = {{1,3,3,4},{2,2,8,2},{0,3,7,1},{0,1,2,12}};
          //double[] b = {4,5,1,10};
          //double[][] arr = {{1,3,6,3,4},{2,2,4,8,2},{1,3,7,7,1},{0,1,2,1,12},{5,1,11,2,1}};
          //double[] b = {4,5,1,10,2};
//          double[][] arr = {{9,3,3,3,1},{2,8,4,2,2},{1,3,9,3,1},{0,1,2,11,9},{2,1,1,2,17}};
//          double[] b = {4,5,5,10,2};
          //sol  1.731 1.105 -0.802 0.101 0.867
          //double[][][] out = LU(arr);
          //LUsolve(out[0],out[1],b);
          double[] x1 = GEPP(arr1,b1);
          double[] x2 = SOR(arr,b,0.8);
          //double x = inveig2(arr, 13.1);
          //double x = rayeig(arr);
          //double[][] Q = {{-0.447, 0.440,0.776,-0.056}, {-0.894,-0.220,-0.388,0.028},{0.000 ,0.826, -0.489, -0.282},{0.000,0.275 ,-0.087 , 0.957}};
          //double[][] R = {{-2.236,-3.130,-8.497,-3.578},{0.000,3.633,5.890,5.450},{0.000,0.000,-4.371,0.801},{0.000, 0.000,0.000,11.039}};
                  
        //}
        //System.out.println(x);
        //System.out.println(Arrays.deepToString(out[0])); 
        //System.out.println(Arrays.deepToString(out[1])); 
        System.out.println(Arrays.toString(x1)); 
        System.out.println(Arrays.toString(x2)); 
    }
    
}
