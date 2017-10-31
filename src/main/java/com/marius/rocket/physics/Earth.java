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
        setAngularVelocity(new double[]{0,0,1}, 2*Math.PI/sidereal );
        this.minorRadius = 6356752.3;
        this.majorRadius =  6378137;
    }
    
    private double[] KSC() {
        // 28 31 26.61 N 80 39 3.06 W
        
        double theta = getRotationFromUTC(Globals.time.getTime());
        double psi = Math.PI - Globals.Deg2Rad(28,31,26.61);
        theta = theta-Globals.Deg2Rad(80,39,3.06);
        return new double[]{0,0,0};
    }
    
    public double[][] KSCXYZ() {
        
        double[][] xyz = new double[3][3];
        
        return xyz;
    }
    
    public double getRotationFromUTC(Long utc) {
        return rotation[1][2];
    }
    
}
