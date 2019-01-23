/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marius.rocket.Math.tools;

import java.util.ArrayList;

/**
 *
 * @author mpopescu
 */
public class Numbers {
    
    public static int factorial(int n) {
        if (n < 2) {
            return 1;
        }
        int sum = n;
        while(--n > 0) {
            sum*=n;
        }
        return sum;
    }
    
    public static int permutation(int n,int k) {
        int i = n;
        while(--i > (n-k)) {
            n*=i;
        }
        return n;
    }
    
    public static int combination(int n, int k) {
        int i = n;
        while(i-- > (n-k)) {
            n*=i;
        }
        return n/factorial(k);
    }
    
    public static ArrayList<Integer> primeFactors(int number) {
        int k = number;
        ArrayList<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= k / i; i++) {
            while (k % i == 0) {
                factors.add(i);
                k /= i;
            }
        }
        if (k > 1) {
            factors.add(k);
        }
        factors.add(number);
        return factors;
    }
}
