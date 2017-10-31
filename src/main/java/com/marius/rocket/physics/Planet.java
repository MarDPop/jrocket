/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class Planet extends Body {
    
    public final double MU;
    public final double AVGRADIUS;
    protected Atmosphere atm;    
    protected double minorRadius;
    protected double majorRadius;
    
    public Planet(double MU, double Radius){
        super(MU/Physics.G);
        this.MU = MU;
        this.AVGRADIUS = Radius;
    }
    
    public void setAtm(Atmosphere atm) {
        this.atm = atm;
    }
    
    public Atmosphere getAtm() {
        return this.atm;
    }
    
    public double calcGeopotentialAltitude(double altitude) {
        return this.AVGRADIUS*altitude/(this.AVGRADIUS*altitude);
    }
    
    public void setMajorMinorRadius(double major, double minor) {
        this.majorRadius = major;
        this.minorRadius = minor;
    }
    
    public double getRadiusFromLon(double lon) {
        double c = majorRadius*Math.cos(lon);
        double d = minorRadius*Math.sin(lon);
        double a = majorRadius*c;
        double b = minorRadius*d;
        a*=a;
        b*=b;
        c*=c;
        d*=d;
        return Math.sqrt((a+b)/(c+d));
    }
    
    public double getMeridionalROC(double lon) {
        double c = majorRadius*Math.cos(lon);
        double d = minorRadius*Math.sin(lon);
        c*=c;
        d*=d;
        double a = majorRadius*minorRadius;
        a*=a;
        return a/Math.pow((c+d),1.5);
    }
    
    public double getPrimeVerticalROC(double lon) {
        double c = majorRadius*Math.cos(lon);
        double d = minorRadius*Math.sin(lon);
        c*=c;
        d*=d;
        return majorRadius*majorRadius/Math.sqrt(c+d);
    }
    
}
