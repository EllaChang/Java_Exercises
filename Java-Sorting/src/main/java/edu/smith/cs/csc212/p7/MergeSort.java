package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergeSort {
	
	/*
	 * A merge method for lists.
	 */
	public static List<Integer> mergeSort(List<Integer> list1, List<Integer> list2) {
		
		// Create a list to store sorted elements.
		List<Integer> sorted = new ArrayList<>();
		
		// Compare the first elements of the two lists while neither of them is empty.
		while (list1.isEmpty() == false && list2.isEmpty() == false) {
			if (list1.get(0) <= list2.get(0)) {
				sorted.add(list1.get(0));
				list1.remove(0);
			} else if (list2.get(0) < list1.get(0)) {
				sorted.add(list2.get(0));
				list2.remove(0);
			}
		}
		
		// Add the rest of the element of one list to "sorted" if
		// the other list is already empty.
		if (list1.isEmpty() && list2.isEmpty() == false) {
			for (int ele : list2)
				sorted.add(ele);
		} else if (list2.isEmpty() && list1.isEmpty() == false) {
			for (int ele : list1)
				sorted.add(ele);
		}
		
		return sorted;
	}
	
    /*
     * A merge method for doubly linked lists.
     */
	public static DoublyLinkedList<Integer> doublyMergeSort(DoublyLinkedList<Integer> list1, DoublyLinkedList<Integer> list2) {
		
		// Create a doubly linked list to store sorted elements.
		DoublyLinkedList<Integer> sorted = new DoublyLinkedList<>();
		
		// Compare the first elements of the two input doubly linked lists while neither of them is empty.
		while (list1.isEmpty() == false && list2.isEmpty() == false) {
			if (list1.getFront() <= list2.getFront()) {
				sorted.addBack(list1.getFront());
				list1.removeFront();
			} else if (list2.getFront() < list1.getFront()) {
				sorted.addBack(list2.getFront());
				list2.removeFront();
			}
		}
		
		// Add the rest of the element of one doubly linked list to "sorted" if
		// the other one is already empty.
		if (list1.isEmpty() && list2.isEmpty() == false) {
			Iterator<Integer> iter1 = list2.iterator();
			while (iter1.hasNext())
				sorted.addBack(iter1.next());
		} else if (list2.isEmpty() && list1.isEmpty() == false) {
			Iterator<Integer> iter2 = list1.iterator();
			while (iter2.hasNext())
				sorted.addBack(iter2.next());
		}
		
		return sorted;
	}
	
}
