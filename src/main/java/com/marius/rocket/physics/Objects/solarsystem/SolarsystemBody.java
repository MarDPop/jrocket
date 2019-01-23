/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics.Objects.solarsystem;

import com.marius.rocket.physics.Objects.Body;
import com.marius.rocket.physics.Objects.atmospheres.Atmosphere;
import com.marius.rocket.physics.Physics;

/**
 *
 * @author n5823a
 */
public class SolarsystemBody extends Body {

    public static final double AU = 1.495978707e11;
    
    
    public static final int CLASS_SUN = 0;
    public static final int CLASS_PLANET = 1;
    public static final int CLASS_DWARFPLANET = 2;
    public static final int CLASS_MOON = 3;
    public static final int CLASS_ASTEROID = 4;
    public static final int CLASS_COMET = 5;
    public static final int CLASS_MANMADE = 6;
    
    protected Atmosphere atm = null; 
    
    public final double AVGRADIUS;   
    protected double minorRadius;
    protected double majorRadius;
    protected double rotationalSpeed;
    
    public final int classification;    
    protected Body parent;
    
    public SolarsystemBody(double MU, double Radius, int classification){
        super(MU/Physics.GRAVITY);
        this.MU = MU;
        this.AVGRADIUS = Radius;
        this.classification = classification;
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
