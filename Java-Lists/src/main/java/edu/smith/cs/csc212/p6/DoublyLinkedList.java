package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.BadIndexError;

public class DoublyLinkedList<T> implements P6List<T> {

	private Node<T> start;
	private Node<T> end;

	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}

	@Override
	// O(1)
	public T removeFront() {
		checkNotEmpty();
		
		T front = start.value;
		this.start = start.after;
		
		// If the list is empty now, update the end as null as well.
		if (this.start == null)
			this.end = null;
		
		// If there are still more nodes, make sure the new start is not preceded by any value.
		else
			this.start.before = null;
		
		return front;
	}

	@Override
	// O(1)
	public T removeBack() {
		checkNotEmpty();
		
		T back = end.value;
		this.end = end.before;
		
		// If the list is empty now, update the start as null as well.
		if (this.end == null)
			this.start = null;
		
		// If there are still more nodes, make sure the new end is not followed by any value.
		else
			this.end.after = null;
		
		return back;
	}

	@Override
	// O(n)
	public T removeIndex(int index) {
		checkNotEmpty();
		
		// If we want to add an item to the front.
		if (index == 0)
			return this.removeFront();
		
		// A counter for the index of the current item.
		int count = 0;
		
		// 
		for (Node<T> current = start; current != null; current = current.after) {
			// Remove the item by using the one before it.
			if (count == index - 1) {
				T remove = current.after.value;
				current.after = current.after.after;
				return remove;
			}
			count++;
		}
		// If the return statement is never executed, it means the index is invalid.
		throw new BadIndexError();
	}

	@Override
	// O(1)
	public void addFront(T item) {
		Node<T> newStart = new Node<T>(item);
		newStart.before = null;
		
		// If the list is empty, add a new item to the list.
		if (start == null) {
			start = newStart;
			end = newStart;
			newStart.after = null;
		
		// If the list is not empty, simply set the item as the start.
		} else {
			newStart.after = start;
			start.before = newStart;
			start = newStart;
		}
	}

	@Override
	// O(1)
	public void addBack(T item) {
		Node<T> newEnd = new Node<T>(item);
		
		// If the list is empty, call addFront(item).
		if (start == null) {
			addFront(item);
		
		// If the list is not empty, simply set the item as the end.
		} else {
			newEnd.before = end;
			newEnd.after = null;
			end.after = newEnd;
			end = newEnd;
		}
	}

	@Override
	// O(n)
	public void addIndex(T item, int index) {
		// Check if the index is valid.
		if (index < 0 || index > this.size())
			throw new BadIndexError();
		
		Node<T> insert = new Node<T>(item);
		
		// If we want to add an item to the front.
		if (index == 0) {
			addFront(item);
		
		// If we want to add an item to the end.
		} else if (index == size()) {
			addBack(item);
		
		// If we want to add an item to the middle.
		} else {
			// A counter for the index of the current item.
			int count = 0;
			for (Node<T> current = start; current != null; current = current.after) {
				// Add the item by using the one before it.
				if (count == index - 1) {
					insert.before = current;
					insert.after = current.after;
					current.after = insert;
					current.after.before = insert;
				}
				count++;
			}
		}
	}

	@Override
	// O(1)
	public T getFront() {
		if (this.isEmpty())
			throw new EmptyListError();
		return start.value;
	}

	@Override
	// O(1)
	public T getBack() {
		if (this.isEmpty())
			throw new EmptyListError();
		return end.value;
	}

	@Override
	// O(n)
	public T getIndex(int index) {
		// Check is the list is empty.
		if (this.isEmpty()) {
			throw new EmptyListError();
		
		} else {
			// A counter for the index of the current item.
			int count = 0;
			for (Node<T> current = start; current != null; current = current.after) {
				if (count == index) {
					return current.value;
				}
				count++;
			}
		}
		
		// If the return statement is never executed, it means the index is invalid.
		throw new BadIndexError();
	}

	@Override
	// O(n)
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	// O(1)
	public boolean isEmpty() {
		return size() == 0;
	}

	// O(1)
	private void checkNotEmpty() {
		if (this.start == null)
			throw new EmptyListError();
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of DoublyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}