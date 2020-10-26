package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;

/**
 * This is a data structure that has an array inside each node of a Linked List.
 * Therefore, we only make new nodes when they are full. Some remove operations
 * may be easier if you allow "chunks" to be partially filled.
 * 
 * @author jfoley
 * @param <T> - the type of item stored in the list.
 */
public class ChunkyLinkedList<T> implements P6List<T> {
	private int chunkSize;
	private SinglyLinkedList<FixedSizeList<T>> chunks;

	public ChunkyLinkedList(int chunkSize) {
		this.chunkSize = chunkSize;
		chunks = new SinglyLinkedList<>();
	}

	@Override
	public T removeFront() {
		// Check if the ChunkyLinkedList is empty.
		if (isEmpty())
			throw new EmptyListError();
		
		// Remove the first value of the first chunk.
		FixedSizeList<T> front = chunks.getFront();
		T removed = front.removeFront();
		
		// Remove the first chunk if it's empty.
		if (front.isEmpty())
			chunks.removeFront();
		
		return removed;
	}

	@Override
	public T removeBack() {
		// Check if the ChunkyLinkedList is empty.
		if (isEmpty())
			throw new EmptyListError();
		
		// Remove the last value of the last chunk.
		FixedSizeList<T> back = chunks.getBack();
		T removed = back.removeBack();
		
		// Remove the last chunk if it's empty.
		if (back.isEmpty())
			chunks.removeBack();
		
		return removed;
	}

	@Override
	public T removeIndex(int index) {
		// Check if the ChunkyLinkedList is empty.
		if (this.isEmpty())
			throw new EmptyListError();
		
		// A counter for the overall index of the current item.
		int start = 0;
		
		// A counter for the index of the current chunk.
		int chunkNumber = 0;
		
		for (FixedSizeList<T> chunk : this.chunks) {
			// Calculate the overall index boundary of the current chunk.
			int end = start + chunk.size();

			// Check if the intended index is in this chunk.
			if (start <= index && index < end) {
				
				// Calculate the "in-chunk" index and remove the corresponding item from the chunk.
				T remove = chunk.removeIndex(index - start);
				
				// Remove the chunk if it's empty.
				if (chunk.isEmpty())
					chunks.removeIndex(chunkNumber);
				
				return remove;
			}
			// Update the counters bofore moving on to the next chunk.
			start = end;
			chunkNumber++;
		}
		// If the return statement is never executed, it means the index is invalid.
		throw new BadIndexError();
	}

	@Override
	public void addFront(T item) {
		// Add an empty chunk if the ChunkyLinkedList is empty.
		if (this.chunks.isEmpty())
			this.chunks.addFront(new FixedSizeList<T>(chunkSize));
		
		// Find the first chunk.
		FixedSizeList<T> firstChunk = this.chunks.getFront();
		
		// If the first chunk is full.
		if (firstChunk.size() == chunkSize) {
			// Create and add a new chunk to the beginning and add the item to its front.
			FixedSizeList<T> newChunk = new FixedSizeList<T>(chunkSize);
			chunks.addFront(newChunk);
			newChunk.addFront(item);
		
		// If the first chunk is not full.
		} else
			// Add the item to the front of the first chunk.
			firstChunk.addFront(item);

	}

	@Override
	public void addBack(T item) {
		// Add an empty chunk if the ChunkyLinkedList is empty.
		if (this.chunks.isEmpty())
			this.chunks.addFront(new FixedSizeList<T>(chunkSize));
		
		// Find the last chunk.
		FixedSizeList<T> lastChunk = this.chunks.getBack();
		
		// If the last chunk is full.
		if (lastChunk.size() == chunkSize) {
			// Create and add a new chunk to the end and add the item to its back.
			FixedSizeList<T> newChunk = new FixedSizeList<T>(chunkSize);
			chunks.addBack(newChunk);
			newChunk.addBack(item);
		
		// If the last chunk is not full.
		} else
			// Add the item to the back of the last chunk.
			lastChunk.addBack(item);
		
	}

	@Override
	public void addIndex(T item, int index) {
		// Add the item to the front if the ChunkyLinkedList is empty.
		if (this.isEmpty())
			this.addFront(item);

		// A counter for the overall index of the current item.
		int start = 0;
		
		// A counter for the index of the current chunk.
		int chunkNum = 0;
		
		for (FixedSizeList<T> chunk : this.chunks) {
			// Calculate the overall index boundary of the current chunk.
			int end = start + chunk.size();
			
			// Check if the intended index is in this chunk.
			if (start <= index && index <= end) {
				
				// If the chunk is full.
				if (chunk.size() >= chunkSize) {
					// Create a new empty chunk and add it after the current chunk.
					FixedSizeList<T> nextChunk = new FixedSizeList<T>(chunkSize);
					chunks.addIndex(nextChunk, chunkNum + 1);
					
					// If the intended index happen to be right after the current chunk.
					if (index == end)
						// Add the item to the front of the new chunk.
						nextChunk.addFront(item);
					
					// If the intended index happen to be within the current chunk.
					else {
						// Remove the last item of the current chunk and add it to the front of the new chunk.
						nextChunk.addFront(chunk.removeBack());
						
						// Calculate the "in-chunk" index and add the item to the corresponding slot.
						chunk.addIndex(item, index - start);
					}
				// If the chunk is not full.
				} else
					// Calculate the "in-chunk" index and add the item to the corresponding slot.
					chunk.addIndex(item, index - start);
				
				return;
			}
			
			// Update the counters bofore moving on to the next chunk.
			start = end;
			chunkNum++;
		}
		// If the return statement is never executed, it means the index is invalid.
		throw new BadIndexError();
	}

	@Override
	public T getFront() {
		return this.chunks.getFront().getFront();
	}

	@Override
	public T getBack() {
		return this.chunks.getBack().getBack();
	}

	@Override
	public T getIndex(int index) {
		// Check if the ChunkyLinkedList is empty.
		if (this.isEmpty())
			throw new EmptyListError();
		
		// A counter for the overall index of the current item.
		int start = 0;
		
		for (FixedSizeList<T> chunk : this.chunks) {
			// Calculate the overall index boundary of the current chunk.
			int end = start + chunk.size();

			// Check if the intended index is in this chunk.
			if (start <= index && index < end)
				return chunk.getIndex(index - start);

			// Update the counter before moving on to the next chunk.
			start = end;
		}
		// If the return statement is never executed, it means the index is invalid.
		throw new BadIndexError();
	}

	@Override
	public int size() {
		int total = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			if (!chunk.isEmpty())
				total += chunk.size();
		}
		return total;
	}

	@Override
	public boolean isEmpty() {
		return this.chunks.isEmpty();
	}
}
