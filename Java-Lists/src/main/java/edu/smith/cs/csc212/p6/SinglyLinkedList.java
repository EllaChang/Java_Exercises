package edu.smith.cs.csc212.p6;

import java.util.Iterator;
import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class SinglyLinkedList<T> implements P6List<T>, Iterable<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	//O(1)
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.next;
		return before;
	}

	@Override
	//O(1)
	public T removeBack() {
		checkNotEmpty();
		if (start.next == null) {
			T last = start.value;
			start = null;
			return last;
		} else {
			Node<T> current = start;
			for (current = start; current.next.next != null; current = current.next) {
				continue;
			}
			T last = current.next.value;
			current.next = null;
			return last;
		}
	}

	@Override
	//O(n)
	public T removeIndex(int index) {
		checkNotEmpty();
		
		// If we want to add an item to the front.
		if (index == 0)
			return this.removeFront();
		
		// Check if the index is valid.
		else if (index < 0 || index >= size())
			throw new BadIndexError();
		
		else {
			// Find the node before the intended index.
			Node<T> before = start;
			for (int i = 0; i < index - 1; i++) {
				before = before.next;
			}
			
			// Find the node after the intended index.
			Node<T> after = before.next.next;
			
			// Record the removed value and remove the intended node.
			T remove = before.next.value;
			before.next = after;
			return remove;
		}
	}
	
	@Override
	//O(1)
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	//O(n)
	public void addBack(T item) {
		Node<T> newNode = new Node<T> (item, null);
		
		// If the list is empty, set the node as start.
		if (start == null)
			start = newNode;
		
		// If the list is not empty, add the new node to the end.
		else {
			Node<T> last = start;
			while (last.next != null) {
				last = last.next;
			}
			last.next = newNode;
		}
	}

	@Override
	
	//O(n)
	public void addIndex(T item, int index) {
		// Check if the index is valid.
		if (index < 0 || index > this.size())
			throw new BadIndexError();
		
		// If we want to add an item to the front.
		if (index == 0)
			addFront(item);
		
		// A counter for the index of the current item.
		int count = 0;
		
		// Add the item to the correct slot.
		for (Node<T> current = start; current != null; current = current.next) {
			if (count == index - 1) {
				current.next = new Node<T>(item, current.next);
			}
			count++;
		}
	}

	@Override
	//O(1)
	public T getFront() {
		// Check if the list is empty.
		checkNotEmpty();
		
		return start.value;
	}

	@Override
	//O(n)
	public T getBack() {
		// Check if the list is empty.
		checkNotEmpty();
		
		T back = null;
		
		// If the loop has reached the end, record the value as the last value.
		for (Node<T> current = start; current != null; current = current.next) {
			if (current.next == null) {
				back = current.value;
			}
		}
		return back;
	}

	@Override
	//O(N)
	public T getIndex(int index) {
		// Check if teh list is empty.
		checkNotEmpty();
		
		// Check if the index is valid.
		if (index < 0 || index > this.size() - 1)
			throw new BadIndexError();
		
		// A counter for the index of the current item.
		int count = 0;
		
		T value = null;
		
		// If the loop has reached the desired index, record the value.
		for (Node<T> current = start; current != null; current = current.next) {
			if (count == index) {
				value = current.value;
			}
			count++;
		}
		return value;
	}

	@Override
	//O(n)
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	//O(1)
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * Helper method to throw the right error for an empty state.
	 */
	//O(1)
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * I'm providing this class so that SinglyLinkedList can be used in a for loop
	 * for {@linkplain ChunkyLinkedList}. This Iterator type is what java uses for
	 * {@code for (T x : list) { }} lops.
	 * 
	 * @author jfoley
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(SinglyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.next;
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
}
