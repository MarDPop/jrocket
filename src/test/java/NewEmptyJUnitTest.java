/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mpopescu
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void profileMe() {
        double tol = 0.0001;
        double sum = 1.2;
        long start = System.nanoTime();
        for(int i = 0; i < 1e5;i++){
            double delta  = Math.pow(tol/sum, 1/8);
        }
        long finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time");
        start = System.nanoTime();
        for(int i = 0; i < 1e5;i++){
            double delta  = Math.sqrt(tol/sum);
            delta = Math.sqrt(delta);
            delta = Math.sqrt(delta);
        }
        finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time");
    }
}
