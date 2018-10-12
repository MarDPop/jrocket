/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Utils.pic;

/**
 *
 * @author mpopescu
 */
public class Node {
    public double Q;
    public double V;
    public double E;
    
    public final double[] pos;
    
    public Node(double[] pos) {
        this.pos = pos;
    }
}
