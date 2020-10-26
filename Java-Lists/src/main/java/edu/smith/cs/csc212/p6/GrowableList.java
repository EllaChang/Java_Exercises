package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}
	
	@Override
	//O(1)
	public T removeFront() {
		return removeIndex(0);
	}
	
	@Override
	//O(1)
	public T removeBack() {
		// Check if the list is empty.
		if (this.size() == 0)
			throw new EmptyListError();
		
		// Get the last value.
		T value = this.getIndex(fill-1);
		this.array[fill-1] = null;
		fill--;
		return value;
	}
	
	@Override
	//O(n)
	public T removeIndex(int index) {
		// Check if the list is empty.
		if (this.size() == 0)
			throw new EmptyListError();
		
		T removed = this.getIndex(index);
		fill--;
		
		// Remove the item and shift the items after it by one.
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}
	
	@Override
	//O(1)
	public void addFront(T item) {
		addIndex(item, 0);
	}
	
	@Override
	//O(n)
	public void addBack(T item) {
		if (fill >= this.array.length) {
			// Double the limit of the list if it becomes full.
			int newSize = fill * 2;
			
			// Put all items into a new, larger array.
			Object[] newArray = new Object[newSize];
			for (int i=0; i<array.length; i++) {
				newArray[i] = array[i];
			}
			newArray[fill] = item;
			this.array = newArray;
		}
		this.array[fill++] = item;
	}
	
	@Override
	//O(2n)
	public void addIndex(T item, int index) {
		// Check if the index is valid.
		if (index < 0 || index > this.size())
			throw new BadIndexError();
		
		if (fill >= this.array.length) {
			// Double the limit of the list if it becomes full.
			int newSize = fill * 2;
			
			// Put all items into a new, larger array.
			Object[] newArray = new Object[newSize];
			for (int i=0; i<fill; i++) {
				newArray[i] = array[i];
			}
			this.array = newArray;
		}
		
		// Push all items after the intended index down by one.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		
		// Add the item.
		array[index] = item;
		fill++;
	}
	
	@Override
	//O(1)
	public T getFront() {
		// Check if the list is empty.
		if (this.isEmpty())
			throw new EmptyListError();
		
		return this.getIndex(0);
	}
	
	@Override
	//O(1)
	public T getBack() {
		// Check if the list is empty.
		if (this.isEmpty())
			throw new EmptyListError();
		
		return this.getIndex(this.fill-1);
	}
	
	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	//O(1)
	public T getIndex(int index) {
		if (index < 0 || index > this.size() - 1)
			throw new BadIndexError();
		return (T) this.array[index];
	}
	
	@Override
	//O(1)
	public int size() {
		return fill;
	}
	
	@Override
	//O(1)
	public boolean isEmpty() {
		return fill == 0;
	}
	
}