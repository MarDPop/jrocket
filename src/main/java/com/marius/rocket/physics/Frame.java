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
    protected Frame ref;
    protected double[][] xyz; // in x y z (x points to vega, theta is measured from x)
    protected double[][] spherical; // in r theta phi (aka radius, angle from vega [xz plane], angle from ecliptic plane [xy plane] ) see http://mathworld.wolfram.com/SphericalCoordinates.html
    protected double[] angular_velocity; // unit vector in langrange frame MUST HAVE SAME ORIGIN 
    //double[][] rotation_matrix;
    protected double[][] orientation; // unit vector in langrange frame (direction of x y z-axis)
    protected double[] lagrange_velocity; //current velocity of frame within reference frame
    
    public Frame() {
    }
    
    public void setXYZ(double[][] xyz) {
        this.xyz = xyz;
    }
    
    public double[][] getXYZ() {
        return xyz;
    }
    
    public double[][] changeXYZ(double[][] dx) {
        for(int i = 0; i < 3; i++) {
            System.arraycopy(dx[i], 0, this.xyz[i], 0, 3);
        }
        return this.xyz;
    }
    
    public void setSpherical(double[][] spherical) {
        this.spherical = spherical;
    }
    
    public double[][] getSphericalState() {
        return spherical;
    }
    
    public double[][] changeSphericalState(double[][] dx) {
        for(int i = 0; i < 3; i++) {
            System.arraycopy(dx[i], 0, this.spherical[i], 0, 3);
        }
        return this.spherical;
    }
    
    public void calcSpherical() {
        spherical[0][0] = LA.mag(xyz[0]);
        spherical[0][1] = Math.atan2(xyz[0][1], xyz[0][0]);
        spherical[0][2] = Math.cos(xyz[0][2]/spherical[0][0]);
    }
    
    public void calcXYZ() {
        xyz[0][0] = spherical[0][0]*Math.cos(spherical[0][1])*Math.sin(spherical[0][2]); 
        xyz[0][1] = spherical[0][0]*Math.sin(spherical[0][1])*Math.sin(spherical[0][2]); 
        xyz[0][2] = spherical[0][0]*Math.cos(spherical[0][2]); 
    }
    
    public double[] getAngularVelocity() {
        return this.angular_velocity;
    }
    
    public void setAngularVelocity(double[] axis, double rate) {
        this.angular_velocity = LA.multiply(axis, rate);
    }
    
    public void propagate() {
        
    }
    
    public double[] transform(double[] u) {
        return LA.cross(u,angular_velocity);
    }
    
    public void setMotion() {
        
    }
    
    public void setRef(Frame ref) {
        this.ref = ref;
    }
    
    public double[][] getOrientation(){
        return orientation;
    }
    
    public void setOrientation(double[][] orientation) {
        this.orientation = orientation;
    }
    
    public void changeOrientation(double[] axis, double angle) {
        this.orientation[0] = LA.RotateAxis(this.orientation[0], axis, angle);
        this.orientation[1] = LA.RotateAxis(this.orientation[1], axis, angle);
        this.orientation[2] = LA.RotateAxis(this.orientation[2], axis, angle);
    }
    
    
}
