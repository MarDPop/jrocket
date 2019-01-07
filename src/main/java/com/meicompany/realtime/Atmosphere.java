/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mpopescu
 */
public class Atmosphere {
    private double temp;
    private double pres;
    private double density;
    private double soundSpeed;
    private double gamma;
    private double R_specific;
    
    
    private double hG;
    private double[] wind;
    
    private final ArrayList<Double[]> alt = new ArrayList<>();
    public final double[] heights;
    public final double[] temperatures;
    public final double[] pressures;
    public final double[] densities;
    public final double[] windStrengths;
    public final double[] windDirections;
    
    
    public Atmosphere() {
        heights = null;
        densities = null;
        temperatures = null;
        pressures = null;
        windStrengths = null;
        windDirections = null;
    }
    
    public Atmosphere(String filename) {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Double[] numbers = new Double[7];
            while ((line = br.readLine()) != null) {
                String[] state = line.split(cvsSplitBy);
                for(int i = 0; i < 7; i++){
                    numbers[i] = Double.parseDouble(state[i]);
                }
                alt.add(numbers);
            }
        } catch (IOException e) {
        }
        pressures = new double[alt.size()];
        heights = new double[alt.size()];
        densities = new double[alt.size()];
        temperatures = new double[alt.size()];
        windStrengths = new double[alt.size()];
        windDirections = new double[alt.size()];
        for(int i = 0; i < alt.size(); i++) {
            heights[i] = alt.get(i)[0];
            densities[i] = alt.get(i)[1]/2000; // PREDIVIDED BY 2
            temperatures[i] = alt.get(i)[2];
            windStrengths[i] = alt.get(i)[3];
            windDirections[i] = alt.get(i)[4];
            pressures[i] = alt.get(i)[5];
        }
    }
    
    public static double geometricAlt(double[] r) {
        double R = Helper.norm(r);
        double lat = Math.asin(r[2]/R);
        double seaLevel = Helper.seaLevel(lat);
        return (R-seaLevel)*seaLevel/R;
    }
}
