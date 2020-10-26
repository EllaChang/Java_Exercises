package edu.smith.cs.csc212.p7;

import java.util.List;

/*
 * I looked up https://www.geeksforgeeks.org/insertion-sort/ for the basic idea.
 */
public class InsertionSort {
	
	public static void insertionSort(List<Integer> input) {
		
        int n = input.size(); 
        
        for (int i = 1; i < n; i++) {
        	
            int key = input.get(i);
            int j = i-1;
            
            // Move elements greater than key one index ahead.
            while (j >= 0 && input.get(j) > key) {
            	input.set(j+1,  input.get(j));
            	j--;
            }
            
            input.set(j+1, key);
            
        }
        
    }

}