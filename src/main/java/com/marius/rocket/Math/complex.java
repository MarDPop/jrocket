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
public class complex {
    public final double real;
    public final double imag;
    
    public complex(double real) {
        this.real = real;
        this.imag = 0;
    }
    
    public complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public complex add(complex c) {
        return new complex(this.real + c.real, this.imag + c.imag);
    }
    
    public complex sub(complex c) {
        return new complex(this.real - c.real, this.imag - c.imag);
    }
    
    public complex multi(complex c) {
        return new complex((this.real*c.real-this.imag*c.imag), (this.real*c.imag+this.imag*c.real));
    }
    
    public complex div(complex c) {
        double den = c.real*c.real+c.imag*c.imag;
        return new complex((this.real*c.real+this.imag*c.imag)/den, (this.imag*c.real-this.real*c.imag)/den);
    }
    
    public complex base(double x){
        double xa = Math.pow(x, this.real);
        double fact = this.imag*Math.log(x);
        return new complex(xa*Math.cos(fact),xa*Math.sin(fact));
    }
    
}
