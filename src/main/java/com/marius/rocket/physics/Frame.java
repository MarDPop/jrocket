/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;
import com.marius.rocket.Math.LA;
/**
 *
 * @author n5823a
 */
public class Frame {
    private Frame ref;
    protected double[][] state; // in x y z (x points to vega, theta is measured from x)
    protected double[][] spherical_state; // in r theta phi (aka radius, angle from vega [xz plane], angle from ecliptic plane [xy plane] ) see http://mathworld.wolfram.com/SphericalCoordinates.html
    protected double[] angular_velocity; // unit vector in langrange frame
    //double[][] rotation_matrix;
    protected double[] orientation; // unit vector in langrange frame (direction of x-axis)
    protected double[] lagrange_velocity; //current velocity of frame within reference frame
    
    public Frame() {
    }
    
    public void setState(double[][] state) {
        this.state = state;
    }
    
    public double[][] getState() {
        return state;
    }
    
    public double[][] changeState(double[][] dx) {
        for(int i = 0; i < 3; i++) {
            System.arraycopy(dx[i], 0, this.state[i], 0, 3);
        }
        return this.state;
    }
    
    public void setSphericalState(double[][] spherical_state) {
        this.spherical_state = spherical_state;
    }
    
    public double[][] getSphericalState() {
        return spherical_state;
    }
    
    public double[][] changeSphericalState(double[][] dx) {
        for(int i = 0; i < 3; i++) {
            System.arraycopy(dx[i], 0, this.spherical_state[i], 0, 3);
        }
        return this.spherical_state;
    }
    
    public void calcSpherical() {
        spherical_state[0][0] = LA.mag(state[0]);
        spherical_state[0][1] = Math.atan2(state[0][1], state[0][0]);
        spherical_state[0][2] = Math.cos(state[0][2]/spherical_state[0][0]);
    }
    
    public void calcXYZ() {
        state[0][0] = spherical_state[0][0]*Math.cos(spherical_state[0][1])*Math.sin(spherical_state[0][2]); 
        state[0][1] = spherical_state[0][0]*Math.sin(spherical_state[0][1])*Math.sin(spherical_state[0][2]); 
        state[0][2] = spherical_state[0][0]*Math.cos(spherical_state[0][2]); 
    }
    
    public void move() {
        
    }
    
    public double[] transform(double[] u) {
        return LA.cross(u,angular_velocity);
    }
    
    public void setRef(Frame ref) {
        this.ref = ref;
    }
    
    public double[] getOrientation(){
        return orientation;
    }
    
    public void setOrientation(double[] orientation) {
        this.orientation = orientation;
    }
    
    public void changeOrientation(double[] dx) {
        this.orientation[0] += dx[0];
        this.orientation[1] += dx[1];
        this.orientation[2] += dx[2];
    }
}
