/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.Globals;
import java.time.Instant;

/**
 *
 * @author n5823a
 */
public class Earth extends Planet {
    //Remember distinction between ECEF and ECI 
    // J2000 is a common ECI and GCRF is the ecliptic version 
    private double sidereal = 86164.1;

    public Earth(){
        super(3.986004418e14, 6371000);
        setAtm(new StandardATM());
        this.rotationalSpeed = 2*Math.PI/sidereal;
        setAngularVelocity(new double[]{0,0,1}, this.rotationalSpeed );
        this.minorRadius = 6356752.3;
        this.majorRadius =  6378137;
    }
    
    public double[] KSC() {
        // 28 31 26.61 N 80 39 3.06 W
        double theta = getRotationFromUTC(Globals.time.getTime());
        double psi = Math.PI/2 - Globals.Deg2Rad(28,31,26.61);
        theta = theta-Globals.Deg2Rad(80,39,3.06);
        return new double[]{getRadiusFromLatitude(psi),theta,psi};
    }
    
    public double[][] KSCXYZ() {
        double[] loc = KSC();
        double[][] vec = new double[3][3];
        vec[0] = Spherical2CartesianLocation(loc);
        vec[1] = transform(vec[0]);
        return vec;
    }
    
    public double getRotationFromUTC(Long utc) {
        return Globals.Deg2Rad(80,39,3.06);
        //return rotation[1][2];
    }
    
}
