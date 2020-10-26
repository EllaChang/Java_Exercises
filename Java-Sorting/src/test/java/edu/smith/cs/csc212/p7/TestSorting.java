package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i=0; i<items.size()-1; i++) {
			if (items.get(i) > items.get(i+1)) {
				System.err.println("items out of order: "+items.get(i)+", "+items.get(i+1) + " at index="+i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Here's some data!
	 */
	private static int[] data = {9,8,7,6,5,4,3,2,1};
	private static int[] a = {1,2,4,7,9,12};
	private static int[] b = {3,5,6,8,10};
	
	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testSelectionSort() {
		// See if the data can be reversed:
		List<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testInsertionSort() {
		// See if the data can be reversed:
		List<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void testMergeSort() {
		// See if the data can be reversed:
		List<Integer> sorted1 = new ArrayList<>();
		for (int x : a) {
			sorted1.add(x);
		}
		List<Integer> sorted2 = new ArrayList<>();
		for (int y : b) {
			sorted2.add(y);
		}
		List<Integer> sorted = MergeSort.mergeSort(sorted1, sorted2);
		Assert.assertTrue(checkSorted(sorted));
	}
	
	@Test
	public void testIterativeMergeSort() {
		List<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		List<Integer> sorted1 = IterativeMergeSort.iterativeMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sorted1));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		InsertionSort.insertionSort(sortMe);
		List<Integer> sorted2 = IterativeMergeSort.iterativeMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sorted2));
	}
	
	@Test
	 public void testRecursiveMergeSort() {
		List<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		RecursiveMergeSort.recursiveMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		RecursiveMergeSort.recursiveMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	 }
	
	@Test
	public void testDoublyMergeSort() {
		// See if the data can be reversed:
		DoublyLinkedList<Integer> sorted1 = new DoublyLinkedList<>();
		for (int x : a) {
			sorted1.addBack(x);
		}
		DoublyLinkedList<Integer> sorted2 = new DoublyLinkedList<>();
		for (int y : b) {
			sorted2.addBack(y);
		}
		DoublyLinkedList<Integer> sorted = MergeSort.doublyMergeSort(sorted1, sorted2);
		Assert.assertTrue(checkSorted(sorted.copyToList()));
	}
	
	@Test
	 public void testDoublyIterative() {
		DoublyLinkedList<Integer> sortMe = new DoublyLinkedList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		DoublyLinkedList<Integer> sorted = IterativeMergeSort.doublyIterative(sortMe);
		
		Assert.assertTrue(checkSorted(sorted.copyToList()));
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe.copyToList());
		DoublyLinkedList<Integer> sorted2 = IterativeMergeSort.doublyIterative(sortMe);
		Assert.assertTrue(checkSorted(sorted2.copyToList()));
		
	 }
	 
}
