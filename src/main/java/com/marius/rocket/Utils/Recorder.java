/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author n5823a
 */
public class Recorder {
    BufferedWriter writer;
    StringBuilder sb = new StringBuilder();
    
    public Recorder(String filename) throws IOException {
        writer = new BufferedWriter(new FileWriter(filename));
    }
    
    public void record(double time, double[] state) throws IOException {
        sb.append(time);
         for (double el : state) {
            sb.append(el);
            sb.append(",");
        }
        writer.write(sb.toString());
    }
    
    public void finish() throws IOException {
        writer.flush();
        writer.close();
    }
    
}
