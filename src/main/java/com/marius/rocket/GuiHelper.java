/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket;

import com.marius.rocket.physics.Objects.solarsystem.Sun;
import com.marius.rocket.physics.Objects.solarsystem.Saturn;
import com.marius.rocket.physics.Objects.solarsystem.Venus;
import com.marius.rocket.physics.Objects.solarsystem.Luna;
import com.marius.rocket.physics.Objects.solarsystem.Mercury;
import com.marius.rocket.physics.Objects.solarsystem.Pluto;
import com.marius.rocket.physics.Objects.solarsystem.SolarsystemBody;
import com.marius.rocket.physics.Objects.solarsystem.Neptune;
import com.marius.rocket.physics.Objects.solarsystem.Ceres;
import com.marius.rocket.physics.Objects.solarsystem.Mars;
import com.marius.rocket.physics.Objects.solarsystem.Jupiter;
import com.marius.rocket.physics.Objects.solarsystem.Uranus;
import com.marius.rocket.physics.Objects.solarsystem.Earth;
import com.marius.rocket.physics.Objects.Body;
import java.util.HashMap;

/**
 *
 * @author mpopescu
 */
public class GuiHelper {
    
    public final static HashMap<String,SolarsystemBody> BODIES = new HashMap<String,SolarsystemBody>();
    public final static HashMap<Integer,String> BODY_MAP = new HashMap<Integer,String>();
    
    public GuiHelper() {
        BODIES.put("Sun",new Sun());
        BODIES.put("Mercury",new Mercury());
        BODIES.put("Venus",new Venus());
        BODIES.put("Earth",new Earth());
        BODIES.put("Moon",new Luna());
        BODIES.put("Mars",new Mars());
        BODIES.put("Jupiter",new Jupiter());
        BODIES.put("Saturn",new Saturn());
        BODIES.put("Uranus",new Uranus());
        BODIES.put("Neptune",new Neptune());
        BODIES.put("Pluto",new Pluto());
        BODIES.put("Ceres",new Ceres());
        
        BODY_MAP.put(0,"Sun");
        BODY_MAP.put(101,"Mercury");
        BODY_MAP.put(201,"Venus");
        BODY_MAP.put(301,"Earth");
        BODY_MAP.put(302,"Moon");
        BODY_MAP.put(401,"Mars");
        BODY_MAP.put(501,"Jupiter");
        BODY_MAP.put(601,"Saturn");
        BODY_MAP.put(701,"Uranus");
        BODY_MAP.put(801,"Neptune");
        BODY_MAP.put(1001,"Pluto");
        BODY_MAP.put(2001,"Ceres");
    }
    
}
