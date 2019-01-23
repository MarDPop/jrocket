/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.tools;

import com.marius.rocket.Math.LA;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;
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
    
    public static double[][] oe2rv(double[] oe, double mu){
        // oe: 
        double[][] rv = new double[2][3];
        double p = oe[0]*(1-oe[1]*oe[1]);
        double r = p/(1+oe[1]*cos(oe[5]));
        double h = sqrt(mu*p)/r; // actually h/r
        double th = oe[3]+oe[5];
        double co = cos(oe[4]);
        double so = sin(oe[4]);
        double ci = cos(oe[2]);
        double si = sin(oe[2]);
        double ct = cos(th);
        double st = sin(th);
        rv[0][0] = r*(co*ct-so*st*ci);
        rv[0][1] = r*(so*ct+co*st*ci);
        rv[0][2] = r*(si*st);
        double fact = oe[1]*sin(oe[5])/p;
        rv[1][0] = h*(rv[0][0]*fact - (co*st+so*ct*ci));
        rv[1][1] = h*(rv[0][1]*fact - (so*st-co*ct*ci));
        rv[1][2] = h*(rv[0][2]*fact + (ct*si));
        return rv;
    }
    
    public static double[] rv2oe(double[] r, double[] v, double mu){
        double[] oe = new double[6];
        
        double R = LA.mag(r);
        double[] r_unit = new double[]{r[0]/R,r[1]/R,r[2]/R};
        
        double[] h = LA.cross(r,v);
        double[] e = LA.multiply(LA.cross(v,h),1/mu); 
        LA.subtract(e,r_unit);
        //double[] n = new double[] {-h[1],h[0],0}; 
        
        oe[0] = 1/(2/R-LA.dot(v, v)/mu); // semi major axis
        oe[1] = LA.mag(e); // eccentricity
        LA.multiply(e,1/oe[1]);
                
        R = h[0]*h[0]+h[1]*h[1];
        oe[2] = acos(h[2]/sqrt(R+h[2]*h[2])); // inclination
        double n = sqrt(R);
        oe[3] = acos(-h[1]/n); // longitude of ascending node
        R = 2*Math.PI; 
        if (h[0] < 0) {
            oe[3] = R - oe[3];
        }
        oe[4] = acos((-h[1]*e[0] + h[0]*e[1])/n); // argument of periapsis
        if (e[2] < 0) {
            oe[4] = R - oe[4];
        }
        oe[5] = acos(LA.dot(e,r_unit)); // true anomaly
        if (LA.dot(r, v) < 0) {
            oe[5] = R - oe[5];
        }
        return oe;
    }
    
    
    
}
