package edu.smith.cs.csc212.p7;

import java.util.List;

/*
 * I looked up https://www.geeksforgeeks.org/selection-sort/ for the basic idea.
 */
public class SelectionSort {
	
	public static void selectionSort(List<Integer> input) {
		
		int n = input.size();
		
		for (int i = 0; i < n-1; i++) {
			
            // Find the minimum element in unsorted list.
            int min = i;
            
            for (int j = i+1; j < n; j++) {
                if (input.get(j) < input.get(min))
                    min = j;
            }
            
            // Swap the found minimum element with the first element.
            int temp = input.get(min);
            input.set(min, input.get(i));
            input.set(i, temp);
        
        }
		
	}
	
}