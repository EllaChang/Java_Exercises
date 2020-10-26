package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.p7.errors.EmptyListError;
import edu.smith.cs.csc212.p7.errors.BadIndexError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoublyLinkedList<T> implements P7List<T> {
	
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
	//O(1)
	public T removeFront() {
		checkNotEmpty();
		Node<T> remove = start;
		this.start = remove.after;
		return remove.value;
	}
	
	@Override
	//O(1)
	public T removeBack() {
		checkNotEmpty();
		if (end.before != null) {
			T last = end.value;
			end = end.before;
			end.after = null;
			return last;
		}
		T last = start.value;
		start = null;
		end = null;
		return last;
	}
	
	@Override
	//O(n)
	public T removeIndex(int index) {
		checkNotEmpty();
		if (index == 0) {
			removeFront();
		} else if (index == size() - 1) { 
			removeBack();
		} else if (index >= size() || index < 0) {
			throw new BadIndexError();
		}
		Node<T> remove = null;
		int count = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			if (count == index) {
				remove = current;
			}
			count++;
		}
		T removeValue = remove.value;
		remove.before.after = remove.after;
		remove.after.before = remove.before;
		return removeValue;
	}

	@Override
	//O(1)
	public void addFront(T item) {
		Node<T> newStart = new Node<T>(item);
		if (start == null) {
			start = newStart;
			end = newStart;
			newStart.before = null;
			newStart.after = null;
		} else {
			newStart.before = null;
			newStart.after = start;
			start.before = newStart;
			start = newStart;
		}
	}

	@Override
	//O(1)
	public void addBack(T item) {
		Node<T> newEnd = new Node<T>(item);
		if (end == null) {
			addFront(item);
		} else {
			newEnd.before = end;
			newEnd.after = null;
			end.after = newEnd;
			end = newEnd;
		}
	}

	@Override
	//O(n)
	public void addIndex(T item, int index) {
		Node<T> insert = new Node<T> (item);
		if (index == 0) {
			addFront(item);
		} else if (index == size()) {
			addBack(item);
		} else if (index > size()) {
			throw new BadIndexError();
		}
		int count = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			if (count == index - 1) {
				insert.after = current.after;
				insert.before = current;
				current.after.before = insert;
				current.after = insert;
			}
			count++;
		}
	}

	@Override
	//O(1)
	public T getFront() {
		return start.value;
	}

	@Override
	//O(1)
	public T getBack() {
		return end.value;
	}
	
	@Override
	//O(n)
	public T getIndex(int index) {
		checkNotEmpty();
		int count = 0;
		T value = null;
		for (Node<T> current = start; current != null; current = current.after) {
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
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	//O(1)
	public boolean isEmpty() {
		return size() == 0;
	}
	
	//O(1)
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
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
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
	
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(DoublyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.after;
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code DoublyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
	
	// For unit tests, List<T> supports equals, but P7List<T> does not.
    public List<T> copyToList() {
            ArrayList<T> output = new ArrayList<T>();
            for (Node<T> n = this.start; n != null; n = n.after) {
                    output.add(n.value);
            }
            return output;
    }
    
    // If you treat your DoublyLinkedList<T> like a queue with pop() as removeFront(), you are destroying the lists.
    // This may also be helpful for unit-testing.
    public DoublyLinkedList<T> copy() {
            DoublyLinkedList<T> output = new DoublyLinkedList<T>();
            for (Node<T> n = this.start; n != null; n = n.after) {
                    output.addBack(n.value);
            }
            return output;
    }
	
}