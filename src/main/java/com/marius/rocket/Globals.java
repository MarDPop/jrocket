/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author n5823a
 */
public class Globals {
    public static Date time;
      
    public static double Deg2Rad(double deg, double min, double sec) {
        return Math.signum(deg)*(Math.abs(deg) + min/60 + sec/3600)*Math.PI/180;
    }
}
