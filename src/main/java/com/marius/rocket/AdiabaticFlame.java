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
import java.util.Arrays;

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
    private int nEl;
    private int nSp; 
    private int[][] coef;
    private double[] max;
    
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
                if(!elements.contains(el)) {
                    elements.add(el);
                }
            }
            enthalpy += reactant.getMoles()*reactant.getEnthalpy();
        }
        this.nEl = elements.size();
        this.nSp = species.size();
        coef = new int[nSp][nEl];
        elementSum = new double[nEl];
        for(int i = 0; i < nSp; i++) {
            for(HashMap.Entry<Atom, Integer> el : species.get(i).elList.entrySet())  {        
                for(int j = 0; j < nEl; j++) {
                    if(el.getKey().getClass() == elements.get(j)) {
                        coef[i][j] = el.getValue();
                        elementSum[j] += el.getValue()*species.get(i).getMoles();
                        break;
                    }
                }
            }
        }
        max = new double[nSp];
        for(int i = 0; i < nSp; i++) {
            max[i] = -1;
            for(int j = 0; j < nEl; j++) {
                if(coef[i][j] > 0) {
                    if(max[i]<0) {
                        max[i] = elementSum[j]/coef[i][j];
                    } else {
                        if(elementSum[j]/coef[i][j] < max[i]) {
                            max[i] = elementSum[j]/coef[i][j];
                        }
                    }
                }
            }
        }
        System.out.println(enthalpy);
        set = true;
    }
    
    /* NASA Method */
    public void calcNASAConstantPressure(double Pressure) { 
        //CONSTANT VOLUME NEEDS CV (internal neergy) NOT CP
        //PRESSURE MUST BE IN ATM
        //Solution variables
        double[] X = new double[nSp];
        double[] newX = new double[nSp];
        double[] h = new double[nSp];
        this.pressure = Pressure;
        double cons = Math.log(Pressure/101325); //MUST BE IN UNITLESS (ATM)
        for(int tempIter = 0; tempIter < 100; tempIter++) {
            // constants in temperature calc
            double RT = Fluid.R*temperature; 
            double sumX = 0;
            for(int i = 0; i < nSp; i++) {
                sumX += X[i] = species.get(i).getMoles();
                species.get(i).SetAndCalcAll(temperature, Pressure);
            }
            for(int iter = 0; iter < 30; iter++) {
                //Intermediate Unknowns
                double[] b_ = new double[nEl+1];
                double[][] A = new double[b_.length][b_.length];
                
                //Variable construction
                for(int i = 0; i < nSp; i++) {
                    if(X[i] > 0) {
                        b_[0] += h[i] = X[i]*(species.get(i).getStandardGibbs()/RT+cons+Math.log(X[i]/sumX));
                    } else {
                        h[i] = 0;
                    }
                }
                for(int j = 0; j < nEl; j++) {
                    b_[j+1] = elementSum[j];
                    double sum = 0;
                    for(int i = 0; i < nSp; i++) {
                        b_[j+1] += h[i]*coef[i][j]; 
                        sum += X[i]*coef[i][j]; //OPTIMIZE SECION   
                        for(int k = 0; k < nEl; k++) {
                            A[j+1][k] += coef[i][k]*coef[i][j]*X[i];
                        }
                    }
                    A[0][j] = sum;
                    A[j+1][nEl] = sum;
                }
                // Solution
                double[] x_ = LA.GEPP2(A, b_);
                for(int i = 0; i < nSp; i++) {
                    newX[i] = 0; 
                    for(int j = 0; j < nEl; j++) {
                        newX[i] += x_[j]*coef[i][j]; 
                    }
                    if(X[i] < 0.01*sumX) {
                        newX[i] = sumX*Math.exp(newX[i]-cons-species.get(i).getStandardGibbs()/RT);
                    } else {
                        newX[i] = -h[i] + X[i]*(x_[nEl]+newX[i]);
                    }
                }
                //SOR
                for(int i = 0; i < nSp; i++) {
                    double dX = newX[i] - X[i];
                    newX[i] = X[i]+0.9*dX;
                    if (newX[i] > max[i]) {
                        if(max[i] > 0) {
                            newX[i] = max[i]*(0.9+i/300.0);
                        } else {
                            newX[i] = 0;
                        }
                    }
                    if(newX[i] < 0) {
                        newX[i] = X[i]/4;
                    }
                }
                //Stop condition
                if(LA.rSquared(X,newX) < 1e-9) {
                    //System.out.println("step "+tempIter+" in "+iter+ "steps ");
                    sumX = LA.sum(newX);
                    break;
                } else {
                    System.arraycopy( newX, 0, X, 0, X.length );
                    sumX = LA.sum(X);
                }
            }
            // temperature loop calc
            double enthalpy_calc = 0;
            double avgCP = 0;
            
            for(int i = 0; i < nSp; i++) {
                Molecule product = species.get(i);
                product.setMoles(newX[i]);
                enthalpy_calc += newX[i]*product.getEnthalpy();
                avgCP += newX[i]*product.getCP();
            }
            //System.out.println(enthalpy_calc+"J");
            avgCP /= sumX;
            double dT = (enthalpy_calc-enthalpy)/avgCP;
            this.temperature -= 0.1*dT;
            
            this.volume = sumX*RT/Pressure;
            if (Math.abs(dT/this.temperature) < 0.0005) {
                System.out.println("Temperature");
                System.out.println(temperature+"K");
                System.out.println("Volume");
                System.out.println(volume+"M^3");
                System.out.println("Species");
                for(int i = 0; i < nSp; i++) {
                    System.out.println(species.get(i).getClass().getSimpleName());
                    System.out.println(species.get(i).getMoles());
                }
                break;
            }
        }
    }
    
    public void getEquilibrium(double T, double P) {
        double[] X = new double[nSp];
        double[] newX = new double[nSp];
        double[] h = new double[nSp];
        this.pressure = P;
        this.temperature = T;
        double cons = Math.log(P/101325); //MUST BE IN UNITLESS (ATM)
        double RT = Fluid.R*temperature; 
        double sumX = 0;
        for(int i = 0; i < nSp; i++) {
            sumX += X[i] = species.get(i).getMoles();
            species.get(i).SetAndCalcAll(T, P);
        }
        for(int iter = 0; iter < 30; iter++) {
                //Intermediate Unknowns
                double[] b_ = new double[nEl+1];
                double[][] A = new double[b_.length][b_.length];
                
                //Variable construction
                for(int i = 0; i < nSp; i++) {
                    if(X[i] > 0) {
                        b_[0] += h[i] = X[i]*(species.get(i).getStandardGibbs()/RT+cons+Math.log(X[i]/sumX));
                    } else {
                        h[i] = 0;
                    }
                }
                for(int j = 0; j < nEl; j++) {
                    b_[j+1] = elementSum[j];
                    double sum = 0;
                    for(int i = 0; i < nSp; i++) {
                        b_[j+1] += h[i]*coef[i][j]; 
                        sum += X[i]*coef[i][j]; //OPTIMIZE SECION   
                        for(int k = 0; k < nEl; k++) {
                            A[j+1][k] += coef[i][k]*coef[i][j]*X[i];
                        }
                    }
                    A[0][j] = sum;
                    A[j+1][nEl] = sum;
                }
                // Solution
                double[] x_ = LA.GEPP2(A, b_);
                for(int i = 0; i < nSp; i++) {
                    newX[i] = 0; 
                    for(int j = 0; j < nEl; j++) {
                        newX[i] += x_[j]*coef[i][j]; 
                    }
                    if(X[i] < 0.01*sumX) {
                        newX[i] = sumX*Math.exp(newX[i]-cons-species.get(i).getStandardGibbs()/RT);
                    } else {
                        newX[i] = -h[i] + X[i]*(x_[nEl]+newX[i]);
                    }
                }
                //SOR
                for(int i = 0; i < nSp; i++) {
                    double dX = newX[i] - X[i];
                    newX[i] = X[i]+0.9*dX;
                    if (newX[i] > max[i]) {
                        if(max[i] > 0) {
                            newX[i] = max[i]*(0.9+i/300.0);
                        } else {
                            newX[i] = 0;
                        }
                    }
                    if(newX[i] < 0) {
                        newX[i] = X[i]/4;
                    }
                }
                //Stop condition
                if(LA.rSquared(X,newX) < 1e-9) {
                    //System.out.println("step "+tempIter+" in "+iter+ "steps ");
                    sumX = LA.sum(newX);
                    for(int i = 0; i < nSp; i++) {
                        species.get(i).setMoles(newX[i]);
                    }
                    break;
                } else {
                    System.arraycopy( newX, 0, X, 0, X.length );
                    sumX = LA.sum(X);
                }
            }
            
    }
    
    public void calcConstantPressure(double Pressure) { 
        //CONSTANT VOLUME NEEDS CV (internal neergy) NOT CP
        //PRESSURE MUST BE IN ATM
        //Solution variables
        double[] X = new double[nSp];
        double[] newX = new double[nSp];
        double[] h = new double[nSp];
        this.pressure = Pressure;
        double cons = Math.log(Pressure/101325); //MUST BE IN UNITLESS (ATM)
        for(int tempIter = 0; tempIter < 100; tempIter++) {
            // constants in temperature calc
            double RT = Fluid.R*temperature; 
            double sumX = 0;
            for(int i = 0; i < nSp; i++) {
                sumX += X[i] = species.get(i).getMoles();
            }
            for(int iter = 0; iter < 30; iter++) {
                for(int i = 0; i < nSp; i++) {
                    
                }
                
                //Stop condition
                if(LA.rSquared(X,newX) < 0.000001) {
                    break;
                } else {
                    System.arraycopy( newX, 0, X, 0, X.length );
                    sumX = LA.sum(X);
                }
            }
            sumX = 0;
            for(int i = 0; i < nSp; i++) {
                sumX += newX[i];
            }
            this.volume = sumX*RT/Pressure;
            
            double enthalpy_calc = 0;
            double avgCP = 0;
            System.out.println("step "+tempIter+": "+temperature);
            for(int i = 0; i < nSp; i++) {
                Molecule product = species.get(i);
                product.setMoles(newX[i]);
                product.SetAndCalcAll(temperature, Pressure);
                enthalpy_calc += newX[i]*product.getEnthalpy();
                avgCP += newX[i]*product.getCP();
            }
            System.out.println(enthalpy_calc+"J");
            avgCP /= sumX;
            double dT = (enthalpy_calc-enthalpy)/avgCP;
            this.temperature -= 0.5*dT;
            if (Math.abs(dT/this.temperature) < 0.0005) {
                System.out.println("Temperature");
                System.out.println(temperature+"K");
                System.out.println("Volume");
                System.out.println(volume+"M^3");
                System.out.println("Species");
                for(int i = 0; i < nSp; i++) {
                    System.out.println(species.get(i).getClass().getSimpleName());
                    System.out.println(species.get(i).getMoles());
                }
                break;
            }
        }
    }
    
}
