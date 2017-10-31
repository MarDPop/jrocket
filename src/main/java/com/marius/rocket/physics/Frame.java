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
    protected double[][] xyz = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; // in x y z (x points to vega, theta is measured from x)
    protected double[][] spherical = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; // in r theta phi (aka radius, angle from vega [xz plane], angle from ecliptic plane [xy plane] ) see http://mathworld.wolfram.com/SphericalCoordinates.html 
    protected double[][] spherical_unit_vectors = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}};
    protected double[] spherical_velocity = {0.0,0.0,0.0}; //velocity in the spherical coordinate system
    protected double[] spherical_acceleration = {0.0,0.0,0.0}; //acceleration in the spherical coordinate system
    protected double[][] rotation = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; //(rotation about x, y, z from reference frame)
    protected double[][] rotation_spherical = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; // pitch, heading, roll ( and rates and accel) langrange frame MUST HAVE SAME ORIGIN 
    protected double[][] rotation_matrix = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; // using rotation[0] orientation is based of rotation matrix
    protected double[][] orientation = {{0.0,0.0,0.0},{0.0,0.0,0.0},{0.0,0.0,0.0}}; // unit vector in euler frame (direction of x y z-axis) 
    //protected double[] lagrange_velocity; //current velocity of frame within reference frame
    
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
    
    public double[][] calcSphericalUnitVectors() {
        double ct = Math.cos(spherical[0][1]);
        double st = Math.sin(spherical[0][1]);
        double cp = Math.cos(spherical[0][2]);
        double sp = Math.sin(spherical[0][2]);
        spherical_unit_vectors[0][0] = ct*sp;
        spherical_unit_vectors[0][1] = st*sp;
        spherical_unit_vectors[0][2] = cp;
        spherical_unit_vectors[1][0] = -st;
        spherical_unit_vectors[1][1] = ct;
        spherical_unit_vectors[1][2] = 0;
        spherical_unit_vectors[2][0] = ct*cp;
        spherical_unit_vectors[2][1] = st*cp;
        spherical_unit_vectors[2][2] = -sp;
        return spherical_unit_vectors;
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
        return this.rotation[1];
    }
    
    public void setAngularVelocity(double[] axis, double rate) {
        this.rotation[1] = LA.multiply(axis, rate);
    }
    
    public void propagate() {
        
    }
    
    public double[][] getRotation(){
        return rotation;
    }
    
    public void setRotation(double[][] rotation) {
        this.rotation = rotation;
    }
    
    public double[][] getRotationSpherical(){
        return rotation;
    }
    
    public void setRotationSpherical(double[][] rotation) {
        this.rotation = rotation;
    }
    
    public double[] transform(double[] u) {
        return LA.cross(u,this.rotation[1]);
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
