/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math;

/**
 *
 * @author n5823a
 */
public class Complex {
    public final double real;
    public final double imag;
    
    public Complex(double real) {
        this.real = real;
        this.imag = 0;
    }
    
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public Complex add(Complex c) {
        return new Complex(this.real + c.real, this.imag + c.imag);
    }
    
    public Complex sub(Complex c) {
        return new Complex(this.real - c.real, this.imag - c.imag);
    }
    
    public Complex multi(Complex c) {
        return new Complex((this.real*c.real-this.imag*c.imag), (this.real*c.imag+this.imag*c.real));
    }
    
    public Complex div(Complex c) {
        double den = c.real*c.real+c.imag*c.imag;
        return new Complex((this.real*c.real+this.imag*c.imag)/den, (this.imag*c.real-this.real*c.imag)/den);
    }
    
    public Complex base(double x){
        double xa = Math.pow(x, this.real);
        double fact = this.imag*Math.log(x);
        return new Complex(xa*Math.cos(fact),xa*Math.sin(fact));
    }
    
}
