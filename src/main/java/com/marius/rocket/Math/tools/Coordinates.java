/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.tools;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author mpopescu
 */
public class Coordinates {
    
    
    // ****** Coordinate Transforms ******
    
    public static double[] rotateX(double[] v, double angle) {
        v[1] = cos(angle)*v[1]-sin(angle)*v[2];
        v[2] = cos(angle)*v[2]+sin(angle)*v[1];
        return v;
    }
    
    public static double[] rotateY(double[] v, double angle) {
        v[0] = cos(angle)*v[0]+sin(angle)*v[2];
        v[2] = cos(angle)*v[2]-sin(angle)*v[0];
        return v;
    }
    
    public static double[] rotateZ(double[] v, double angle) {
        v[0] = cos(angle)*v[0]-sin(angle)*v[1];
        v[1] = cos(angle)*v[1]+sin(angle)*v[0];
        return v;
    }
    
    public static double[] rotateAxis(double[] v, double[] u, double angle) {
        //u  = unit vector of rotation axis
        double c = cos(angle);
        double c1 = 1-c;
        double s = sin(angle);
        v[0] = (c + u[0]*u[0]*c1)*v[0] + (u[0]*u[1]*c1-u[2]*s)*v[1] + (u[0]*u[2]*c1+u[1]*s)*v[2];
        v[1] = (c + u[1]*u[1]*c1)*v[1] + (u[0]*u[1]*c1+u[2]*s)*v[0] + (u[1]*u[2]*c1-u[0]*s)*v[2];
        v[2] = (c + u[2]*u[2]*c1)*v[2] + (u[0]*u[2]*c1-u[1]*s)*v[0] + (u[1]*u[2]*c1+u[0]*s)*v[1];
        return v;
    }
    
    public static double[] rotateEulerAngles(double[] v, double pitch, double roll, double yaw) {
        return v; 
    }
    
    public static double[] spherical2cartesian(double[] spherical) {
        double[] xyz = new double[3];
        xyz[0] = spherical[0]*sin(spherical[2])*cos(spherical[1]);
        xyz[0] = spherical[0]*sin(spherical[2])*sin(spherical[1]);
        xyz[0] = spherical[0]*cos(spherical[2]);
        return xyz;
    }
    
    public static double[][] sphericalUnitVectors(double inclination, double azimuth) {
        double[][] out = new double[3][3];
        double st = sin(inclination);
        double sp = sin(azimuth);
        double ct = sin(inclination);
        double cp = cos(azimuth);
        out[0][0] = st*cp;
        out[0][1] = st*sp;
        out[0][2] = cp;
        out[1][0] = ct*cp;
        out[1][1] = ct*sp;
        out[1][2] = -sp;
        out[2][0] = cp;
        out[2][1] = -sp;
        out[2][2] = 0;
        return out;
    }
}
