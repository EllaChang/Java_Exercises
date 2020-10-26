package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterativeMergeSort {

	public static List<Integer> iterativeMergeSort(List<Integer> input) {
		
		// Create "singles", a list of lists to store every single element of the input.
		List<List<Integer>> singles = new ArrayList<List<Integer>>();
		
		// Store each element of the input as a singleton list into the list "singles".
		for (int i = 0; i < input.size(); i++) {
			List<Integer> single = new ArrayList<Integer>(Arrays.asList(input.get(i)));
			singles.add(single);
		}
		
		while (singles.size() > 1) {
			// Add the merged list of the first two lists in "singles" to the back of "singles".
			singles.add(MergeSort.mergeSort(singles.get(0), singles.get(1)));
			// Remove the first two lists used to make the merged list from "singles".
			singles.remove(0);
			singles.remove(0);
		}
		
		// The first list in "singles" would be the complete sorted list.
		return singles.get(0);
		
	}
	
	public static DoublyLinkedList<Integer> doublyIterative(DoublyLinkedList<Integer> input) {
		
		// Create "singles", a list of lists to store every single element of the input.
		DoublyLinkedList<DoublyLinkedList<Integer>> singles = new DoublyLinkedList<DoublyLinkedList<Integer>>();
		
		// Create an iterator to traverse the doubly linked list.
		Iterator<Integer> iter = input.iterator();
		
		// Store each element of the input as a singleton list into the list "singles".
		while (iter.hasNext()) {
			DoublyLinkedList<Integer> single = new DoublyLinkedList<Integer>();
			single.addBack(iter.next());
			singles.addBack(single);
		}
		
		while (singles.size() > 1) {
			// Add the merged list of the first two doubly linked lists in "singles" to the back of "singles".
			singles.addBack(MergeSort.doublyMergeSort(singles.getIndex(0), singles.getIndex(1)));
			// Remove the first two doubly linked lists used to make the merged list from "singles".
			singles.removeFront();
			singles.removeFront();
		}
		
		// The first doubly linked list in "singles" would be the complete sorted list.
		return singles.getFront();
		
	}
	
}