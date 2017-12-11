// PLEASE CHECK MEMORY LEAKS!!!!
// --------------------------
package com.marius.rocket;

import com.marius.rocket.Math.Euler;
import com.marius.rocket.Math.LA;
import static com.marius.rocket.Math.LA.*;
import com.marius.rocket.Math.Order2euler;
import com.marius.rocket.Utils.Recorder;
import java.util.Arrays;
import com.marius.rocket.physics.*;
import com.marius.rocket.physics.forces.Gravity;
import com.marius.rocket.vehicle.presets.SimpleRocket;
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
       test2();
    }
    
    private static void test2(){
        Globals.time = new Date();
        Globals.outputtoscreen = false;
        
        Earth earth = new Earth();
        double[][] ksc = earth.KSCXYZ();
        
        Environment env = new Environment();
        env.setAtm(earth.getAtm());
        
        SimpleRocket rocket_1 = new SimpleRocket(env);
        rocket_1.setXYZ(ksc); 
        rocket_1.recalcMass();
        rocket_1.initUp();
        rocket_1.g = new Gravity(rocket_1,new Planet[]{earth});
        rocket_1.forces.add(rocket_1.g);
        
        final double dt = 0.05;
        rocket_1.calcSphericalFromCartesian();
        rocket_1.update(0,dt);
        Order2euler ode = new Order2euler(dt);
        ode.bodies = new Body[]{rocket_1};
        ode.setEndTime(80);
        ode.init();
        
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
                rec.record(ode.getTime(), ode.x);
            }
            rec.finish();
        } catch(Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    
    private static void test() {
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
