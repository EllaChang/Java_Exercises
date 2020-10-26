package edu.smith.cs.csc212.p8;

import org.junit.Assert;
import org.junit.Test;

public class testHash {
	
	@Test
	public void testHash() {
		
		// Create a LLHash.
		LLHash set = new LLHash(5);
		set.add("one");
		set.add("two");
		set.add("three");
		set.add("four");
		set.add("five");
		set.add("six");
		set.add("seven");
		set.add("eight");
		set.add("nine");
		set.add("ten");
		set.add("eleven");
		set.add("twelve");
		set.add("thirteen");
		set.add("fourteen");
		int count = set.countCollisions();
		int count1 = set.countUsedBuckets();
		System.out.println(count);
		System.out.println(count1);
	}
	
}
