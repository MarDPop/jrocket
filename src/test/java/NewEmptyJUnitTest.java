/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;
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

        double sum;
        int number = 100000000;
        Random rand = new Random();
        
        long start = System.nanoTime();
        sum = 0;
        for(int i = 0; i < number;i++){
            for(int j = 2; j >= 0;j--){
                sum += j*i;
            }
        }
        long finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time " +sum);
        
        start = System.nanoTime();
        sum = 0;
        for(int i = 0; i < number;i++){
            for(int j = 0; j < 3;j++){
                sum += j*i;
            }
        }
        finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time "+sum);
        
        start = System.nanoTime();
        sum = 0;
        for(int i = 0; i < number;i++){
            sum = Math.random();
        }
        finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time "+sum);
        
        start = System.nanoTime();
        sum = 0;
        for(int i = 0; i < number;i++){
            sum = rand.nextFloat();
        }
        finish = System.nanoTime();
        System.out.println((finish-start)/1e6+" ms run time "+sum);
        
    }
}
