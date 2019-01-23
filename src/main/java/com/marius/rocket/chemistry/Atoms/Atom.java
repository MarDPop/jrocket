/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.chemistry.Atoms;

import com.marius.rocket.physics.Physics;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 *
 * @author n5823a
 */
public abstract class Atom {
    //public final static double EV2KJ = 1312.0/13.59844;
    public final String symbol;
    public final int protons;
    public int neutrons;
    public double atomic_mass; //in dalton
    protected int ionization;
    // PLEASE NOTE, the following data is STANDARD, things like molecular behavior may affect electron affinity, ionization energy etc and is for reference only
    public double[] ionization_energy; //in eV
    public double[] electron_affinity; //in eV
    public double atomic_radius; //pm
    public double electronegativy; //paulin scale
    Document doc;
    
    public Atom (String symbol,int protons, int neutrons) {
        this.symbol = symbol;
        this.protons = protons;
        this.neutrons = neutrons;
        try {
            File fXmlFile = new File(protons+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Atom(String symbol,int protons, int neutrons, double atomic_mass, double[] ionization_energy, double[] electron_affinity, double atomic_radius, double electronegativy) {
        this.symbol = symbol;
        this.protons = protons;
        this.neutrons = neutrons;
        this.atomic_mass = atomic_mass;
        this.ionization_energy = ionization_energy;
        this.electron_affinity = electron_affinity;
        this.electronegativy = electronegativy;
        this.atomic_radius = atomic_radius;
    }
    
    public final int getIonization() {
        return this.ionization;
    }
    
    public final void setIonization(int ionization) {
        this.ionization = ionization;
    }
    
    public final double kgMass() {
        return this.atomic_mass*Physics.ATOMIC_MASS_UNIT;
    }
    
    
}
