/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import static com.marius.rocket.Math.LA.*;
import java.util.Arrays;
import com.marius.rocket.physics.*;

/**
 *
 * @author n5823a
 */
public class Sim {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test();
    }
    
    private static void test2(){
        Frame ECI = new Frame();
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
