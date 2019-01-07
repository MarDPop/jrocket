/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meicompany.realtime;

import static com.meicompany.realtime.FragmentOde.norm;
import static com.meicompany.realtime.Helper.dot;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author mpopescu
 */
public class FragmentOdeSecondOrder extends FragmentOde {
    private double airspeed;
    private double windStrengthMultiplier;
    
    private OdeAtmosphere atm;
    
    final double[] r_ = new double[3];
    final double[] e_ = new double[3];
    final double[] n_ = new double[3];
    
    final double[] a0 = new double[3];
    final double[] x0 = new double[3];
    final double[] v0 = new double[3];
    final double[] aprev = new double[3];
    
    private double small_dt;
    
    private double minTol;
    
    public FragmentOdeSecondOrder(double[] x, double[] v, Fragment frag, double time) {
        super(x,v,frag,time);
        atm = new OdeAtmosphere("src/main/resources/altitudes2.csv");
        this.dt = 2;
        this.maxTimestep = 10;
        this.minTimestep = 1e-5;
        this.tol = 0.0005;
        this.minTol = tol*0.2;
        this.windStrengthMultiplier = 1;
    }
    
    public FragmentOdeSecondOrder(Fragment frag) {
        super(frag);
    }
    
    @Override
    public void setA(double[] a) {
        System.arraycopy(a, 0, this.aprev, 0, 3);
    }
    
    @Override 
    public double[] run() {
        for(int iter = 0; iter < ITER_MAX; iter++) {
            System.arraycopy(x, 0, xold, 0, 3);
            calcA();
            stepSize();
            for (int i = 0; i < 3; i++) {
                double da = (a[i]-aprev[i])*small_dt;
                x[i] = x0[i] + dt*(v0[i]+ a0[i]*small_dt + da/3);
                v[i] = v0[i] + a0[i]*dt + da;
            }
            if (stop()) {
                return groundImpact();
            } 
            System.arraycopy(a, 0, aprev, 0, 3);
            time += dt;
        }
        return new double[]{0, 0, 0, 0};
    }
    
    @Override
    public void calcA() {
        double R2 = dot(x,x);
        R = sqrt(R2);
        
        // Get unit vectors
        r_[0] = x[0]/R;
        r_[1] = x[1]/R;
        r_[2] = x[2]/R;
        h = atan2(x[1],x[0]);
        double cl = cos(h);
        double sl = sin(h);
        double b = Math.sqrt(1-r_[2]*r_[2]);
        e_[0] = -sl;
        e_[1] = cl;
        n_[0] = r_[2]*cl;
        n_[1] = r_[2]*sl;
        n_[2] = b;
        
        // Calculate Geopot Height
        b /= 6378137;
        h = r_[2]/6356752.3;
        h = 1/sqrt(b*b+h*h);
        
        h = (R-h)*h/R;
        
        // Temperature
        b = 280+tempOffset;
        
        // Atmospheric density and wind
        double rho;
        double[] wind;
        
        if (h > 1.2e5) {
            // rho is less than 1e-6
            double g = -EARTH_MU/R2;
            a[0] = g*r_[0];
            a[1] = g*r_[1];
            a[2] = g*r_[2];
            return;
        } else {
            if (h > 34080) {
                rho = 0.63*exp(-h*0.034167247386760/b); //already divided by 2 and 9.806/287
                wind = Helper.add(Helper.multiply(e_, 12.5), Helper.multiply(n_, 21.65063509461));
            } else {
                if (h < 1.022401e3) {
                    rho = 0.63*exp(-h*0.034167247386760/(280+b));
                    wind = Helper.add(Helper.multiply(e_, 3), Helper.multiply(n_, 4));
                } else {
                    h /= 340.8;
                    int i = (int) h;
                    double delta = h - i;
                    i--;
                    rho = atm.densities[i]+delta*(atm.densities[i+1]-atm.densities[i]);
                    b = atm.temperatures[i]+tempOffset;
                    rho *= (atm.temperatures[i]/b);//density is predivided!
                    delta = windStrengthMultiplier*atm.windStrengths[i];
                    wind = Helper.multiply( Helper.add( Helper.multiply(e_,cos(atm.windDirections[i])), Helper.multiply(n_,sin(atm.windDirections[i])) ), delta );
                }
            }
        }
        
        double[] v_free = new double[3];
        v_free[0] = -v[0] - x[1]*7.29211505392569e-05 + wind[0];
        v_free[1] = -v[1] + x[0]*7.29211505392569e-05 + wind[1];
        v_free[2] = -v[2] + wind[2];
        
        airspeed = norm(v_free);
        double mach = airspeed/sqrt(401.37*b);
        
        double drag = rho*airspeed/frag.bc(mach);
        double lift = drag*frag.l2d()*airspeed;
        lift -= EARTH_MU/R2;
        a[0] = drag*v_free[0]+lift*r_[0];
        a[1] = drag*v_free[1]+lift*r_[1];
        a[2] = drag*v_free[2]+lift*r_[2];
    }

    
    @Override
    public void stepSize() {
        short stepIteration = 0;
        // get initial state
        System.arraycopy(x, 0, x0, 0, 3);
        System.arraycopy(v, 0, v0, 0, 3);
        System.arraycopy(a, 0, a0, 0, 3);
        
        while(stepIteration++ < 12) {
            small_dt = dt/2;
            // full step on velocity first
            double[] v_test  = Helper.add(v0, Helper.multiply(a0, dt));
            // half step
            System.arraycopy(Helper.add(v0, Helper.multiply(a0, small_dt)), 0, v, 0, 3);
            System.arraycopy(Helper.add(x0, Helper.multiply(v0, small_dt)), 0, x, 0, 3);
            // recalc acceleration
            calcA();
            // half step
            double[] temp = Helper.multiply(a, small_dt);  
            v[0] += temp[0];
            v[1] += temp[1];
            v[2] += temp[2];
            // compare velocity
            if (airspeed == 0) {
                airspeed = norm(v0);
            }
            double err = (Math.abs(v_test[0]-v[0])+Math.abs(v_test[1]-v[1])+Math.abs(v_test[2]-v[2]))/airspeed;
            if (err < tol) {
                if (err < minTol) {
                    dt *= 1.4;
                    if (dt > maxTimestep) {
                        dt = maxTimestep;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                dt *= 0.75;
                if (dt < minTimestep) {
                    dt = minTimestep;
                    small_dt = dt/2;
                    break;
                }
            }

        }
        
        
    }
}
