/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.integrators;

/**
 *
 * @author mpopescu
 */
public class IntegratorExeception extends Exception {

    public IntegratorExeception() {
        super();
    }
    
    public IntegratorExeception(String message){
        super(message);
    }
    
}
