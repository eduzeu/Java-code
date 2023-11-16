package Makeup_Assignment;
//Michael Lazieh
//I pledge on my honor to abide by the Stevens Honor System.

import java.util.Arrays;

public class CountingSort {
    public static void sort(int[] A) { 
    //A is the array of numbers that needs to be sorted 
        int n = A.length;
        int[] B = new int[n]; //B  is the array we will insert the final sorted values into.
        int[] L = A;
        Arrays.sort(L);
        int k = L[A.length-1];
        int[] C = new int[k+1]; //C is an auxillary array we will use for temporary storage.
        
        for (int i = 0; i < k; i++) {
            C[i] = 0;
        }
        
        for (int j = 1; j < n; j++) {
            C[A[j]] = C[A[j]] + 1;      // C[i] now contains the number of elements equal to i.
        }

        for (int i = 2; i < k; i++) {
            C[i] = C[i] + C[i-1];       // C[i] now contains the number of elements <= i.
        }

        for (int j = n-1; j >= 0; j--) {
            B[C[A[j]]] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }
    }

    public static void main(String[] args) {
        int[] A = {2,5,3,0,2,3,0,3};
        sort(A);
        for (int i=0; i<A.length; i++) {
            System.out.print(A[i]);
        }
    }
}