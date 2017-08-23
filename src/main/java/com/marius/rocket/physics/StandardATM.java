/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.physics;

/**
 *
 * @author n5823a
 */
public class StandardATM extends Atmosphere{
    
    @Override
    void calc() {
        if(this.altitude > 600000) {
            this.temp = 298;
            this.pres = 0;
            this.dens = 0;
            return;
        }
        
        double[] altitudes = {11000,20000,32000,47000,53000,79000,90000};
        double[] base_temps = {288.16,216.66,216.66,282.66,282.66,165.66,165.66};
        double[] base_pres = {101325,22632,5474.9,868.02,110.91,66.939,165.66};
        double[] base_dens = {};
        
    }
    
}
