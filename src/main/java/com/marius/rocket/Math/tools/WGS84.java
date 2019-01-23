/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.tools;

/**
 *
 * @author mpopescu
 */
public class WGS84 {
    
    public static final double EQUATORIAL_RADIUS = 6378137.0;
    public static final double POLAR_RADIUS =  6356752.314245;
    public static final double FLATTENING = 298.257223563;
    public static final double ECCENTRICITY_SQ = 6.6943799901378e-3;

    public static final double a1 = 4.2697672707157535e+4;  //a1 = a*e2
    public static final double a2 = 1.8230912546075455e+9;  //a2 = a1*a1
    public static final double a3 = 1.4291722289812413e+2;  //a3 = a1*e2/2
    public static final double a4 = 4.5577281365188637e+9;  //a4 = 2.5*a2
    public static final double a5 = 4.2840589930055659e+4;  //a5 = a1+a3
    public static final double a6 = 9.9330562000986220e-1;  //a6 = 1-e2
    
    public static double[] ecefToGeodesy( double[] ecef ){
        
        double zp,w2,w,r2,r,s2,c2,s,c,ss;
        double g,rg,rf,u,v,m,f,p,x,y,z;
        double[] geo = new double[3];   //Results go here (Lat, Lon, Altitude)
        x = ecef[0];
        y = ecef[1];
        z = ecef[2];
        zp = Math.abs( z );
        w2 = x*x + y*y;
        w = Math.sqrt( w2 );
        r2 = w2 + z*z;
        r = Math.sqrt( r2 );
        geo[1] = Math.atan2( y, x );       //Lon (final)
        s2 = z*z/r2;
        c2 = w2/r2;
        u = a2/r;
        v = a3 - a4/r;
        if( c2 > 0.3 ){
            s = ( zp/r )*( 1.0 + c2*( a1 + u + s2*v )/r );
            geo[0] = Math.asin( s );      //Lat
            ss = s*s;
            c = Math.sqrt( 1.0 - ss );
        }
        else{
            c = ( w/r )*( 1.0 - s2*( a5 - u - c2*v )/r );
            geo[0] = Math.acos( c );      //Lat
            ss = 1.0 - c*c;
            s = Math.sqrt( ss );
        }
        g = 1.0 - ECCENTRICITY_SQ*ss;
        rg = EQUATORIAL_RADIUS/Math.sqrt( g );
        rf = a6*rg;
        u = w - rg*c;
        v = zp - rf*s;
        f = c*u + s*v;
        m = c*v - s*u;
        p = m/( rf/g + f );
        geo[0] = geo[0] + p;      //Lat
        geo[2] = f + m*p/2.0;     //Altitude
        if( z < 0.0 ){
            geo[0] *= -1.0;     //Lat
        }
        return geo;    //Return Lat, Lon, Altitude in that order
    }
    
    public static double[] geodetic2ecef(double latitude, double longitude, double height) {
        double N = primeVerticalRadiusCurvature(latitude);
        double[] x = new double[3];
        x[0] = (N+height)*Math.cos(latitude)*Math.cos(longitude);
        x[1] = (N+height)*Math.cos(latitude)*Math.sin(longitude);
        x[2] = ((1-ECCENTRICITY_SQ)*N+height)*Math.sin(latitude);
        return x;
    }
    
    public static double primeVerticalRadiusCurvature(double phi){
        return EQUATORIAL_RADIUS/Math.sqrt(1-ECCENTRICITY_SQ*Math.sin(phi)*Math.sin(phi));
    }
}
