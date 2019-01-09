/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime;

import com.meicompany.grid.util.NodeMap;
import com.meicompany.trajectory.Trajectory;
import org.json.JSONObject;


/**
 *
 * @author mpopescu
 */
public class RunPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Trajectory traj = new Trajectory();
        traj.load("src/main/resources/trajplot_i28_asc_m100_ksc15.csv");
        double[][] state = traj.getState(1);
        long start = System.nanoTime();
        PiCalc2 pi = new PiCalc2(state[0],state[1],1);
        pi.getCentroids(12);
        NodeMap map = pi.map();
        long finish = System.nanoTime();
        map.printCsv();
        JSONObject jmap = map.toJson();
        System.out.println((finish-start)/1e6+" ms run time");
    }
   
    
    
}
