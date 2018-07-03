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
    protected double rotationalSpeed;
    
    public Planet(double MU, double Radius){
        super(MU/Physics.GRAVITY);
        this.MU = MU;
        this.AVGRADIUS = Radius;
    }
    
    public final void setAtm(Atmosphere atm) {
        this.atm = atm;
    }
    
    public final Atmosphere getAtm() {
        return this.atm;
    }
    
    public double calcGeopotentialAltitude(double altitude) {
        return this.AVGRADIUS*altitude/(this.AVGRADIUS*altitude);
    }
    
    public void setMajorMinorRadius(double major, double minor) {
        this.majorRadius = major;
        this.minorRadius = minor;
    }
    
    public double getRadiusFromLatitude(double lat) {
        double c = majorRadius*Math.cos(lat);
        double d = minorRadius*Math.sin(lat);
        double a = majorRadius*c;
        double b = minorRadius*d;
        a*=a;
        b*=b;
        c*=c;
        d*=d;
        return Math.sqrt((a+b)/(c+d));
    } 
    
    public double getMeridionalROC(double lat) {
        double c = majorRadius*Math.cos(lat);
        double d = minorRadius*Math.sin(lat);
        c*=c;
        d*=d;
        double a = majorRadius*minorRadius;
        a*=a;
        return a/Math.pow((c+d),1.5);
    }
    
    public double getPrimeVerticalROC(double lat) {
        double c = majorRadius*Math.cos(lat);
        double d = minorRadius*Math.sin(lat);
        c*=c;
        d*=d;
        return majorRadius*majorRadius/Math.sqrt(c+d);
    }
    
    public final void setRotationalSpeed(double speed) {
        this.rotationalSpeed = speed;
    }
    
    public double getRotationalSpeedFromLatitude(double lat) {
        // assumes rotation is always in 
        double R = getRadiusFromLatitude(lat);
        return R*Math.cos(lat)*rotationalSpeed;
    }
    
}
