package edu.smith.cs.csc212.p8;

import org.junit.Assert;
import org.junit.Test;

public class testCountNodes {
	
	@Test
	public void testCountNodes() {
		
		// Create a new CharTrie.
		CharTrie trie = new CharTrie();
		trie.insert("tea");
		trie.insert("ted");
		trie.insert("ten");
		trie.insert("inn");
		int count = trie.countNodes();
		Assert.assertEquals(9, count);
		
		// Create a different CharTrie, just to make sure.
		CharTrie trie2 = new CharTrie();
		trie2.insert("tea");
		trie2.insert("ted");
		trie2.insert("ten");
		int count2 = trie2.countNodes();
		Assert.assertEquals(6, count2);
		
	}
}
