/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

import com.marius.rocket.Math.LA;
import java.util.HashMap;

/**
 *
 * @author n5823a
 */
public class Environment {
    
    public double[] freestream_velocity;
    
    public double local_T;
    
    public double total_T;

    public double local_P;
    
    public double Q;
    
    public double enthalpy;
    
    public double Mach;
    
    public double downstream_location;
    
    public double Reynolds;
    
    public double Lewis;
    
    public double Prandtl;
    
    private Atmosphere atm; 
    
    private Body body;
    
    public HashMap<String,Object> Registry = new HashMap<>();
    
    public void setAtm(Atmosphere atm) {
        this.atm = atm;
    }
    
    public void setBody(Body body) {
        this.body = body;
    }
    
    public boolean testBody(){
        return body != null;
    }
    
    public void calc() {
        atm.setAltitude(body.spherical[0][0]-atm.getPlanet().getRadiusFromLatitude(body.spherical[0][2]));
        freestream_velocity = LA.cross(atm.getPlanet().getAngularVelocity(), body.getXYZ()[0]); 
        freestream_velocity = LA.add(LA.multiply(freestream_velocity,-1), body.getXYZ()[1]);
        Q = 0.5*atm.getDens()*LA.dot(freestream_velocity,freestream_velocity);
        System.out.println("Geoaltitude: "+atm.getGeoAltitude() + "m");
        System.out.println("Freestream: "+ LA.mag(freestream_velocity)+"m/s");
    }
    
}
