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
public class Earth extends Planet {

    public Earth(){
        super(3.986004418e14, 6371000);
        setAtm(new StandardATM());
    }
    
}
