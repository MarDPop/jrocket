/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import com.marius.rocket.Math.LA;
import com.marius.rocket.chemistry.Atoms.Atom;
import com.marius.rocket.chemistry.Molecules.Molecule;
import com.marius.rocket.physics.Fluid;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author n5823a
 */
public class AdiabaticFlame {
    public ArrayList<Molecule> species  = new ArrayList<>();
    private final ArrayList<Class> elements = new ArrayList<>();
    private double[] elementSum;
    private double enthalpy;
    private double temperature;
    private double volume;
    private double pressure;
    private boolean set = false;
    public boolean useNASA = true;
    int[][] coef;
    
    public double getTemperature() {
        return this.temperature;
    }
    
    public double getPressure() {
        return this.pressure;
    }
    
    public double getEnthalpy() {
        return this.enthalpy;
    }
    
    public void init(double temperature) {
        this.temperature = temperature;
        enthalpy = 0;
        for(Molecule reactant : species) {
            reactant.calc();
            for(Atom atom : reactant.elList.keySet())  {
                Class el = atom.getClass();
                System.out.println(el);
                if(!elements.contains(el)) {
                    elements.add(el);
                }
            }
            enthalpy += reactant.getEnthalpy();
        }
        coef = new int[elements.size()][species.size()];
        elementSum = new double[elements.size()];
        for(int i = 0; i < species.size(); i++) {
            for(HashMap.Entry<Atom, Integer> el : species.get(i).elList.entrySet())  {        
                for(int j = 0; j < elements.size(); j++) {
                    if(el.getKey().getClass() == elements.get(i)) {
                        coef[i][j] = el.getValue();
                        elementSum[i] += el.getValue()*species.get(i).getConcentration();
                        break;
                    }
                }
            }
        }
        set = true;
    }
    
    public void calcNASAConstantVolume(double Volume) {
        /* NASA Method */
        // numbers
        int nEl = elements.size();
        int nSp = species.size();

        //Solution variables
        double[] X = new double[nSp];
        double[] newX = new double[nSp];
        // double[] dX = new double[nSp];
        double[] h = new double[nSp];

        for(int tempIter = 0; tempIter < 30; tempIter++) {
            // constants in temperature calc
            double RT = Fluid.R*temperature;
            double cons = Math.log(RT/Volume);

            //Intermediate Unknowns
            double[] x_ = new double[nEl+1];
            double[][] A = new double[x_.length][x_.length];
            double[] b_ = new double[x_.length];
            double sumX = 0;
            for(int iter = 0; iter < 30; iter++) {
                sumX = 0;
                for(int i = 0; i < nSp; i++) {
                    sumX += X[i] = species.get(i).getMoles();
                }
                for(int i = 0; i < nSp; i++) {
                    b_[0] += h[i] = X[i]*(species.get(i).chemical_potential/RT+cons+Math.log(X[i]/sumX));
                }
                for(int j = 0; j < nEl; j++) {
                    b_[j] = elementSum[j];
                    for(int i = 0; i < nSp; i++) {
                        b_[j] += h[i]*coef[i][j];
                        A[0][j] = X[i]*coef[i][j];
                        A[j][nEl] += A[0][j];      
                        for(int k = 0; k < nEl; k++) {
                            A[j][k] += coef[i][k]*A[0][j];
                        }
                    }
                }
                x_ = LA.GEPP(A, b_);
                for(int i = 0; i < nSp; i++) {
                    newX[i] = 0; 
                    for(int j = 0; j < nEl; j++) {
                            newX[i] += x_[j]*coef[i][j]; 
                    }
                    newX[i] = -h[i] * X[i]*(x_[nEl]+newX[i]);
                }
                if(LA.rSquared(X,newX) < 0.0001) {
                    sumX = 0;
                    for(int i = 0; i < nSp; i++) {
                        sumX += newX[i];
                    }
                    this.pressure = sumX*RT/Volume;
                    break;
                } else {
                    X = newX;
                }
            }
            double enthalpy_calc = 0;
            double avgCP = 0;
            for(int i = 0; i < nSp; i++) {
                Molecule blah = species.get(i);
                blah.SetAndCalcAll(temperature, pressure);
                blah.setMoles(newX[i]);
                enthalpy_calc += newX[i]*blah.getEnthalpy();
                avgCP += newX[i]*blah.getCP();
            }
            avgCP /= sumX;
            temperature -= 0.8*(enthalpy_calc-enthalpy)/avgCP;
        }
    }
    
}
