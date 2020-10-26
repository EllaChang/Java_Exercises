package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class RecursiveMergeSort {
	
	public static void recursiveMergeSort(List<Integer> data) {
		  sort(data, 0, data.size() - 1);
	}
	
	public static void sort(List<Integer> data, int low, int high) {
		  if (low == high)
			  return;
		  int mid = (low + high) / 2;
		  // Call itself again and again until the size is 0 or 1.
		  sort(data, low, mid);
		  sort(data, mid + 1, high);
		  // Merge the lists together.
		  merge(data, low, mid, high);
	}
	
	public static void merge(List<Integer> data, int low, int mid, int high) {
		  // Created "sorted", a list to record combined sorted data.
		  List<Integer> sorted = new ArrayList<Integer>(data.size());
		  for (int n = 0; n < data.size(); n++) {
			  sorted.add(data.get(n));
		  }
		  // Index indicator of the lower half.
		  int i = low;
		  // Index indicator of the upper half.
		  int j = mid + 1;
		  // Index indicator of the combined sorted list.
		  int k = low;
		  // Add the smaller value of the beginning elements of the two halves to "sorted"
		  while (i <= mid && j <= high) {
			  if (data.get(i) <= data.get(j)) {
				  sorted.set(k, data.get(i));
				  i++;
			  } else {
				  sorted.set(k, data.get(j));
				  j++;
			  }
			  k++;
		  }
		  // Add remaining elements of the lower half into "sorted".
		  if (i <= mid) {
			  while (k <= high) {
				  sorted.set(k, data.get(i));
				  i++;
				  k++;
			  }
		  }
		  // Add remaining elements of the upper half into "sorted".
		  if(j <= high){
			  while (k <= high) {
				  sorted.set(k, data.get(j));
				  j++;
				  k++;
			  }
		  }
		  
		  for (int m = low; m <= high; m++) {
			   data.set(m, sorted.get(m));
		  }

	}
	
}
