/*
 * Simple math package
 * MANY of the functions here will not throw errors if improperly used
 */
package com.marius.rocket.Math;

import static java.lang.Math.*;
import java.util.Arrays;
/**
 *
 * @author n5823a
 */
public class LA {
    
    public static double[][] copy(double[][] A) {
        double[][] out = new double[A.length][A[0].length];
        for(int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, out[i], 0, A[0].length);
        return out;
    }
    
    public static double[][] randSquare(int n, double max) {
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                A[i][j] = random()*max;
        return A;
    }
    
    public static double[][] eye(int n) {
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) 
            A[i][i] = 1; 
        return A;
    }
    
    public static double[][] diag(double a, int n){
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) 
            A[i][i] = a; 
        return A;
    }
    
    public static double[][] diag(double[] v){
        int n = v.length;
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) 
            A[i][i] = v[i]; 
        return A;
    }
    
    public static double[] randVec(int n, double max) {
        double[] v = new double[n];
        for (int i = 0; i < n; i++) 
            v[i] = random()*max;
        return v;
    }
    
        public static int[] linspace(int start,int n) {
        int[] x = new int[n];
        for (int i = 0; i < n; i++) 
            x[i]=start+i;
        return x;
    }
    
    public static double[] linspace(double start,double end,int n) {
        double[] x = new double[n];
        double dx = (end-start)/(n-1);
        for (int i = 0; i < n; i++) 
            x[i]=start+i*dx;
        return x;
    }
    
    public static double[] linfill(double start,double dx,int n) {
        double[] x = new double[n];
        for (int i = 0; i < n; i++) 
            x[i]=start+i*dx;
        return x;
    }
    
    public static double[] fill(double val,int n) {
        double[] x = new double[n];
        for (int i = 0; i < n; i++) 
            x[i]=val;
        return x;
    }
    
    public static double[][] transpose(double[][] A) {
        double[][] B = new double[A[0].length][A.length];
        for (int i = 0; i < A[0].length; i++) 
            for (int j = 0; j < A.length; j++) 
                B[i][j] = A[j][i];
        return B;        
    }
    
    public static double[] bubbleSort(double[] u) {
        for (int i = 0; i < u.length; i++) {
            for (int j = 0; j < u.length - i - 1; j++) {
                if (u[j] > u[j + 1]) {
                    double temp = u[j];
                    u[j] = u[j + 1];
                    u[j + 1] = temp;
                }
            }
        }
        return u;
    }
    
    public static double[] mergeSort(double[] u) {
        int n = u.length;
        if(n <= 1) {
            return u;
        }
        double[] a = new double[n / 2];
        double[] b = new double[n - a.length];
        System.arraycopy(u, 0, a, 0, a.length);
        System.arraycopy(u, a.length, b, 0, b.length);      
        mergeSort(a);
        mergeSort(b);       
        return merge(a,b,u);   
    }
    
    private static double[] merge(double[] a, double[] b, double[] out) {
        int m = a.length;
        int n = b.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < out.length){
            if(i >= m) {
                out[k] = b[j];
                j++;
            } else if(j >= n) {
                out[k] = a[i];
                i++;
            } else {
                if(a[i] > b[j]) {
                    out[k] = b[j];
                    j++;
                } else {
                    out[k] = a[i];
                    i++;
                }
            }
            k++;
        }
        return out;
    }
    // these funcitons overwrite!!
    public static double[] add(double[] u, double[] v) {
        for (int i = 0; i < u.length; i++) 
            u[i] += v[i];
        return u;
    }
    
    public static double[][] add(double[][] A, double[][] B) {
        for (int i = 0; i < A.length; i++) 
            for (int j = 0; j < A[0].length; j++) 
                A[i][j] += B[i][j];
        return A;
    }
    
    public static double[] subtract(double[] u, double[] v) {
        for (int i = 0; i < u.length; i++) 
            u[i] -= v[i];
        return u;
    }
    
    public static double[] multiply(double[] u, double a) {
        for (int i = 0; i < u.length; i++)
            u[i] *= a;
        return u;
    }
    
    public static double[][] multiply(double[] u, double[] v) {
        // opposite orientation of dot, make sure to use dot if scalar is desired
        double[][] C = new double[u.length][v.length];
        for (int i = 0; i < u.length; i++)
            for (int j = 0; j < v.length; j++) 
                C[i][j] = u[i]*v[j];
        return C;
    }
    
    public static double[][] multiply(double[][] A, double a) {
        for (double[] A1 : A) {
            multiply(A1, a);
        }
        return A;
    }
    
    public static double[] multiply(double[][] A, double[] u) {
        double[] x = new double[A.length];
        for (int i = 0; i < u.length; i++)
            x[i] = dot(A[i],u);
        return x;
    }
    
    public static double[][] multiply(double[] u, double[][] A) {
        double[][] C = new double[u.length][A[0].length];
        for (int i = 0; i < u.length; i++) 
            for (int j = 0; j < A[0].length; j++) 
                C[i][j] = u[i]*A[j][i];
        return C;
    }
    
    public static double[][] multiply(double[][] A, double[][] B ) {
        double[][] C = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                double s = 0;
                    for (int k = 0; k < B.length; k++) 
                        s += A[i][k]*B[k][j];
                C[i][j] = s;
            }
        }
        return C;
    }
    
    public static double dot(double[] u, double[] v) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) {
            sum += u[i]*v[i];
        }
        return sum;
    }  
    
    public static double[] cross(double[] u, double[] v) {
        double[] x = {0,0,0};
        x[0] = u[1]*v[2] - v[2]*u[1];
        x[1] = u[2]*v[0] - v[0]*u[2];
        x[2] = u[0]*v[1] - v[1]*u[0];
        return x;
    }
    
    public static double mag(double[] u) {
        double sum = 0;
        for (int i = 0; i < u.length; i++)
            sum += u[i]*u[i];
        return sqrt(sum);
    }
    
    public static double angle(double[] a, double[] b) {
        return Math.acos(dot(a,b)/Math.sqrt(dot(a,a)*dot(b,b)));
    }
    
    public static double project(double[] a, double[] b) {
        return dot(a,b)/mag(b);
    }
    
    public static double norm(double[] u, double p) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) 
            sum += pow(u[i],p);
        return pow(sum,1/p);
    }
    
    public static double max(double[] u) {
        double src = 0;
        for (int i = 0; i < u.length; i++) 
            if(u[i] > src) src = u[i];
        return src;
    }
    
    public static int largest(double[] u) {
        int src = 0;
        for (int i = 0; i < u.length; i++) 
            if(abs(u[i]) > src) src = i;
        return src;
    }
    
    public static double det (double [][] a) {
        int n = a.length - 1;
        if (n < 0) return 0;
        double M [][][] = new double [n+1][][];
        M[n] = a; // init first, largest, M to a
        // create working arrays
        for (int i = 0; i < n; i++)
            M[i] = new double [i+1][i+1];
        return getDecDet (M, n);
    } 
 
    private static double getDecDet (double [][][] M, int m) {
        if (m == 0) return M[0][0][0];
        int e = 1;
        for (int i = 0; i < m; i++)
            System.arraycopy(M[m][i], 0, M[m-1][i], 0, m);
        double sum = M[m][m][m] * getDecDet (M, m-1);
        for (int i = m-1; i >= 0; i--) {
            System.arraycopy(M[m][i+1], 0, M[m-1][i], 0, m);
            e = -e;
            sum += e * M[m][i][m] * getDecDet (M, m-1);
        }
        return sum;
    }
    
    public static double rayleigh(double[][] A, double[] x) {
        double num = dot(x,multiply(A,x));
        double den = dot(x,x);
        return num/den;
    }
    
    public static double[] GENP(double[][] A, double[] b) {
        //https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
        int n = A[0].length; //must be nxn
        for(int k = 0; k < (n-1); k++) { 
            for(int i = (k+1); i < n; i++) {
                A[i][k] /= A[k][k];
                for(int j = (k+1); j < n; j++) {
                    A[i][j] -= A[i][k]*A[k][j];
                }
            }
        } 
        for(int k = 0; k < (n-1); k++) { 
            for(int i = k+1; i < n; i++) { 
                b[i] -= A[i][k]*b[k];
            }
        }
        for(int i = (n-1); i > -1; i--) { 
            double s = b[i];
            for(int j = i+1; j < n; j++) { 
                s -= A[i][j]*b[j];
            }
            b[i] = s/A[i][i];
        }
        return b;
    }
    
    public static double[] GEPP(double[][] A, double[] b) {
        //http://rosettacode.org/wiki/Gaussian_elimination
        int n = A[0].length; //must be nxn
        
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i]=i;
        }
        double s;
        int temp;
        for(int k = 0; k < (n-1); k++) { 
            int max_i = k;
            double max = abs(A[p[k]][k]);
            for(int i = (k+1); i < n; i++) {
                s = abs(A[p[i]][k]);
                if(s > max) {
                    max = s;
                    max_i = i;
                }
            }
            if(max_i != k){
                temp = p[k];
                p[k] = p[max_i];
                p[max_i] = temp;
            }
            for(int i = (k+1); i < n; i++) {
                A[p[i]][k] /= A[p[k]][k];
                for(int j = (k+1); j < n; j++) {
                    A[p[i]][j] -= A[p[i]][k]*A[p[k]][j];
                }
            }
        } 
        for(int k = 0; k < (n-1); k++) { 
            for(int i = k+1; i < n; i++) { 
                b[p[i]] -= A[p[i]][k]*b[p[k]];
            }
        }
        double[] x = new double[n];
        for(int i = (n-1); i > -1; i--) { 
            s = b[p[i]];
            for(int j = i+1; j < n; j++) { 
                s -= A[p[i]][j]*b[p[j]];
            }
            b[p[i]] = s/A[p[i]][i];
        }
        for(int i = 0; i < n; i++){
            x[i] = b[p[i]];
        }
        return x;
    }
    
    public static double[] GEPP2(double[][] C, double[] b) {
        int n = C.length;
        double[][] A = new double[n][n+1];
        for(int i=0; i<n; i++) {
            System.arraycopy(C[i], 0, A[i], 0, n);
            A[i][n] = b[i];
        }
        for (int i=0; i<n; i++) {
            // Search for maximum in this column
            double maxEl = abs(A[i][i]);
            int maxRow = i;
            for (int k=i+1; k<n; k++) {
                if (abs(A[k][i]) > maxEl) {
                    maxEl = abs(A[k][i]);
                    maxRow = k;
                }
            }
            // Swap maximum row with current row (column by column)
            for (int k=i; k<n+1;k++) {
                double tmp = A[maxRow][k];
                A[maxRow][k] = A[i][k];
                A[i][k] = tmp;
            }
            // Make all rows below this one 0 in current column
            for (int k=i+1; k<n; k++) {
                double c = -A[k][i]/A[i][i];
                for (int j=i; j<n+1; j++) {
                    if (i==j) {
                        A[k][j] = 0;
                    } else {
                        A[k][j] += c * A[i][j];
                    }
                }
            }
        }
        // Solve equation Ax=b for an upper triangular matrix A
        double[] x = new double[n];
        for (int i=n-1; i>=0; i--) {
            x[i] = A[i][n]/A[i][i];
            for (int k=i-1;k>=0; k--) {
                A[k][n] -= A[k][i] * x[i];
            }
        }
        return x;
    }
    
    public static double[] triDiag(double a_, double b_, double c_, double[] d){
        // a solver for 3 diagonals constants a,b,c 
        int n = d.length;
        double[] c = new double[n];
        Arrays.fill(c, c_);
        c[0] = c[0]/b_;
        d[0] = d[0]/b_;
        for(int i = 1; i < n; i++) {
            double m = b_-a_*c[i-1];
            c[i] -= c[i]/m;
            d[i] -= (d[i]-a_*d[i-1])/m;
        }
        for(int i = n-2; i > -1; i--) {
            d[i] = (d[i]-c[i]*d[i+1]);
        }
        return d;
    }
    
    public static double[][] triDiagVectors(double[] a, double[] b, double[] c, double[] d){
        // a solver for 3 diagonals constants a,b,c 
        int n = d.length;
        c[0] = c[0]/b[0];
        d[0] = d[0]/b[0];
        for(int i = 1; i < n; i++) {
            double m = b[i]-a[i]*c[i-1];
            c[i] -= c[i]/m;
            d[i] -= (d[i]-a[i]*d[i-1])/m;
        }
        double[][] out = new double[2][n];
        out[0] = c;
        out[1] = d;
        return out;
    }
    
    public static double[] triDiagSolve(double[] c, double[] d) {
        int n = d.length; 
        for(int i = n-2; i > -1; i--) {
            d[i] = (d[i]-c[i]*d[i+1]);
        }
        return d;
    }
    // COULD MAKE A FASTER TRIDIAG SOLVE ONCE VECTORS ARE KNOWN

    public static double[] LTS(double[][] L, double[] b){
        //lower triangular solver by forward substitution
        for(int i = 0; i< b.length;i++){
            for(int j = 0; j < i;j++){
                b[i]-=L[i][j]*b[j];
            }
            b[i] /= L[i][i];
        }
        return b;
    }
    
    public static double[] UTS(double[][] U, double[] b){
        //lower triangular solver by backward substitution
        for(int i = (b.length-1); i > -1;i--){
            for(int j = i+1; j < b.length;j++){
                b[i]-=U[i][j]*b[j];
            }
            b[i] /= U[i][i];
        }
        return b;
    }
    
    public static double[][][] LU(double[][] A){
        // returns L and U
        int n = A.length;
        double[][][] out = new double[2][n][n];
        out[0] = eye(n);
        for(int i = 0; i < n;i++)
            System.arraycopy(A[i], 0, out[1][i], 0, n);
        for(int k = 0; k < n-1;k++){
            for(int i = k+1; i < n;i++){
                out[0][i][k] = out[1][i][k]/out[1][k][k];
            }
            for(int i = k+1; i < n;i++){
                for(int j = k+1; j < n;j++){
                    out[1][i][j] -= out[0][i][k]*out[1][k][j];
                }
            }
        }
        return out;
    }
    
    public static Object[] LUPP(double[][] U, double[] b){
        //lower triangular solver by forward substitution
        int n = b.length;
        double[][] L = eye(n);
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i]=i;
        }
        for(int k = 0; k > n-1;k++){
            for(int j = k+1; j < n;j++){
                L[j][k] = U[j][k]/U[k][k];
            }
            for(int i = k+1; i < n;i++){
                for(int j = k+1; j < n;j++){
                    U[i][j] -= L[i][k]*U[k][j];
                }
            }
        }
        return new Object[]{L,U,p};
    }
    
    public static double[] LUPsolve(double[][] U, double[] b){
        //lower triangular solver by forward substitution
        int n = b.length;
        double[][] L = eye(n);
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i]=i;
        }
        for(int k = 0; k > n-1;k++){
            for(int j = k+1; j < n;j++){
                L[j][k] = U[j][k]/U[k][k];
            }
            for(int i = k+1; i < n;i++){
                for(int j = k+1; j < n;j++){
                    U[i][j] -= L[i][k]*U[k][j];
                }
            }
        }
        return new double[n];
    }
    
    public static double[] LUsolve(double[][] L, double[][] U, double[] b){
        double[] y = LTS(L,b);
        return UTS(U,y);
    }
    
    public static double[][] Chol(double[][] A){
        //lower triangular solver by forward substitution
        int n = A.length;
        double[][] R = A;
        for(int k = 1; k < n; k++) {
            double d = R[k][k];
            for(int j = k+1; k < n; j++){
                for(int i =j; i < n; i++){
                    R[j][i] -= R[k][i]*R[k][j]/d;
                }
            }
            d = sqrt(d);
            for(int i =k; i < n; i++){
                R[k][i] /= d;
            }
        }        
        return R;
    }
    
    public static double[][] Householder(double[][] A ){
        return A;
    }
    
    public static double[][] HouseholderRv(double[][] A){
        int m = A.length;
        int n = A[0].length;
        double[][] vs = new double[m][];
        for(int k = 0; k < n; k++) {
            double[] v = new double[m-k];
            for(int i = k; i < m; i++) {
                v[i] = A[i][k];
            }
            v[0] += Math.signum(v[0])*mag(v);
            vs[k] = multiply(v,(1/mag(v)));
            for(int i = k; i < m; i++) { 
                for(int j = k; j < n; j++) {
                    double s = 0;
                    for(int l = k; l < m; l++) {
                        s+= vs[k][l-k]*A[l][j];
                    }
                    A[i][j] -= vs[k][i-k]*s;
                }
            }
        }
        return vs;
    }
    
    public static double[] HouseholderSolve(double[][] A, double[] b){
        int m = A.length;
        int n = A[0].length;
        double[][] vs = new double[m][];
        for(int k = 0; k < n; k++) {
            double[] v = new double[m-k+1];
            for(int i = k; i < m; i++) {
                v[i-k] = A[i][k];
            }
            v[0] += Math.signum(v[0])*mag(v);
            vs[k] = multiply(v,(1/mag(v)));
            for(int i = k; i < m; i++) { 
                for(int j = k; j < n; j++) {
                    double s = 0;
                    for(int l = k; l < m; l++) {
                        s+= vs[k][l-k]*A[l][j];
                    }
                    A[i][j] -= 2*vs[k][i-k]*s;
                }
            }
        }
        for(int k = 0; k < n; k++) {
            for(int j=k;j<m;j++) {
                double s = 0;
                for(int i=k;i<m;i++) {
                    s+= vs[k][i-k]*b[i];
                }
                b[j] -= 2*vs[k][j-k]*s;
            }
        }
        return UTS(A,b);
    }
    
    public static double[] HouseholderQR2(double[][] A){
        int m = A.length;
        int n = A[0].length;
        double[][] vs = new double[m][];
        for(int k = 0; k < n; k++) {
            double[] v = new double[m-k+1];
            for(int i = k; i < m; i++) {
                v[i-k] = A[i][k];
            }
            v[0] += Math.signum(v[0])*mag(v);
            vs[k] = multiply(v,(1/mag(v)));
            double[][] x = new double[m-k][n-k];
            for(int i = k; i < m; i++)
                for(int j = k; j < n; j++)
                    x[i-k][j-k] = A[i][j]; 
            x = multiply(vs[k],multiply(vs[k],x));
            for(int i = k; i < m; i++)
                for(int j = k; j < n; j++)
                     A[i][j] -= 2*x[i-k][j-k];
        }
        return new double[n];
    }
    
    public static double[][][] MGS(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] Q = new double[m][n];
        double[][] R = new double[n][n];
        for(int k = 0; k < n; k++) {
            double s = 0;
            for(int j = 0; j < m; j++) {
                s += A[j][k]*A[j][k];
            }
            R[k][k] = sqrt(s);
            for(int j = 0; j < m; j++) {
                Q[j][k] = A[j][k]/R[k][k];
            }
            for(int i = k+1; i < m; i++) {
                s = 0;
                for(int j = 0; j < m; j++) {
                    s+=A[j][i]*Q[j][k];
                }
                R[k][i] = s;
                for(int j = 0; j < m; j++) {
                    A[j][i] -= R[k][i]*Q[j][k];
                }
            }
        }
        double[][][] out = new double[2][][];
        out[0] = Q;
        out[1] = R;
        return out;
    }
    
    
    public static double powereig(double[][] A) {
        int n = A.length;
        double[] v = new double[n];
        double[] oldv = new double[n];
        v[0] = 1;
        for(int k = 0; k < 40; k++) {
            System.arraycopy(v, 0, oldv , 0, n);
            v = multiply(A,v);
            multiply(v,1/mag(v));
            subtract(oldv,v);
            if(abs(max(oldv)) < 0.0001) {
                break;
            }
        }
        return rayleigh(A,v);
    }
    
    public static double inveig(double[][] A, double u) {
        int n = A.length;
        double[] v = fill(1/sqrt(n),n);
        double[] oldv = new double[n];
        double[][] C = diag(-u, n);
        add(C,A);
        double[][][] dummy = LU(C);
        for(int k = 0; k < 40; k++) {
            System.arraycopy(v, 0, oldv , 0, n);
            v = LUsolve(dummy[0],dummy[1],v);
            multiply(v,1/mag(v));
            subtract(oldv,v);
            if(mag(oldv) < 0.0001) {
                break;
            }
        }
        return rayleigh(A,v);
    }
    
    public static double rayeig(double[][] A) {
        int n = A.length;
        double[] v = fill(1/sqrt(n),n);
        double[] oldv = new double[n];
        double[][] C = new double[n][n];
        for(int i = 0; i< n; i++)
            System.arraycopy(A[i], 0, C[i], 0, n);
        double eig = dot(v,multiply(A,v));
        for(int k = 0; k < 30; k++) {
            System.arraycopy(v, 0, oldv , 0, n);
            for(int i = 0; i< n; i++)
               C[i][i] =  A[i][i] - eig;
            v = GEPP2(C,v);
            multiply(v,1/mag(v));
            eig = dot(v,multiply(A,v));
            subtract(oldv,v);
            if(abs(max(oldv)) < 0.0001) {
                break;
            }
        }
        return eig; 
    }
    
    public static double[][] QReig(double[][] A) {
        for(int k = 0; k < 50; k++) {
            double[][][] out = MGS(A);
            A = multiply(out[1],out[0]);
        }
        return A;
    }
    
    public static double[] Jacobi(double[][] A, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        double[] xnew = new double[n];
        double err = 0.00001*mag(b);
        for(int t = 0; t < 60; t++) {
            System.arraycopy(xnew, 0, x , 0, n);
            for(int i = 0; i < n; i++) {
                double s = 0;
                for(int j = 0; j < i; j++) {
                    s += A[i][j]*x[j];
                }
                for(int j = i+1; j < n; j++) {
                    s += A[i][j]*x[j];
                }
                xnew[i] = (b[i]-s)/A[i][i];
            }
            subtract(x,xnew); 
            if(mag(x) < err) {
                break;
            }
        }
        return xnew;
    }
    
    public static double[] SOR(double[][] A, double[] b, double w) {
        int n = b.length;
        double[] x = new double[n];
        double[] xnew = new double[n];
        double err = 0.000001*mag(b);
        double w1 = 1-w;
        for(int t = 0; t < 100; t++) {
            System.arraycopy(xnew, 0, x , 0, n);
            for(int i = 0; i < n; i++) {
                double s = 0;
                for(int j = 0; j < i; j++) {
                    s += A[i][j]*xnew[j];
                }
                for(int j = i+1; j < n; j++) {
                    s += A[i][j]*x[j];
                }
                xnew[i] = w1*x[i]+ w*(b[i]-s)/A[i][i];
            }
            subtract(x,xnew);
            if(mag(x) < err){
                break;
            }
        }
        return xnew;
    }
    
    public static double[] RotateX(double[] v, double angle) {
        v[1] = cos(angle)*v[1]-sin(angle)*v[2];
        v[2] = cos(angle)*v[2]+sin(angle)*v[1];
        return v;
    }
    
    public static double[] RotateY(double[] v, double angle) {
        v[0] = cos(angle)*v[0]+sin(angle)*v[2];
        v[2] = cos(angle)*v[2]-sin(angle)*v[0];
        return v;
    }
    
    public static double[] RotateZ(double[] v, double angle) {
        v[0] = cos(angle)*v[0]-sin(angle)*v[1];
        v[1] = cos(angle)*v[1]+sin(angle)*v[0];
        return v;
    }
    
    public static double[] RotateAxis(double[] v, double[] u, double angle) {
        //u  = unit vector of rotation axis
        double c = cos(angle);
        double c1 = 1-c;
        double s = sin(angle);
        v[0] = (c + u[0]*u[0]*c1)*v[0] + (u[0]*u[1]*c1-u[2]*s)*v[1] + (u[0]*u[2]*c1+u[1]*s)*v[2];
        v[1] = (c + u[1]*u[1]*c1)*v[1] + (u[0]*u[1]*c1+u[2]*s)*v[0] + (u[1]*u[2]*c1-u[0]*s)*v[2];
        v[2] = (c + u[2]*u[2]*c1)*v[2] + (u[0]*u[2]*c1-u[1]*s)*v[0] + (u[1]*u[2]*c1+u[0]*s)*v[1];
        return v;
    }
    
    
}
