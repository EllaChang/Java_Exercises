package edu.smith.cs.csc212.p8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class CheckSpelling {
	/**
	 * Read all lines from the UNIX dictionary.
	 * @return a list of words!
	 */
	public static List<String> loadDictionary() {
		long start = System.nanoTime();
		List<String> words;
		try {
			words = Files.readAllLines(new File("src/main/resources/words").toPath());
		} catch (IOException e) {
			throw new RuntimeException("Couldn't find dictionary.", e);
		}
		long end = System.nanoTime();
		double time = (end - start) / 1e9;
		System.out.println("Loaded " + words.size() + " entries in " + time +" seconds.");
		return words;
	}
	
	/**
	 * Read all the lines from the book, Dracula.
	 * @return a list of words from the book
	 */
	public static List<String> loadBook() {
		List<String> book;
		List<String> bookWords = new ArrayList<String>();
		try {
			book = Files.readAllLines(new File("src/main/resources/dracula").toPath());
		} catch (IOException e) {
			throw new RuntimeException("Couldn't find book.", e);
		}
		for (String x : book) {
			List<String> lineWords = WordSplitter.splitTextToWords(x);
			for (String y : lineWords) {
				bookWords.add(y);
			}
		}
		System.out.println("Successfully loaded the book.");
		return bookWords;
	}
	
	/**
	 * This method looks for all the words in a dictionary.
	 * @param words - the "queries"
	 * @param dictionary - the data structure
	 */
	public static void timeLookup(List<String> words, Collection<String> dictionary) {
		long startLookup = System.nanoTime();
		
		int found = 0;
		for (String w : words) {
			if (dictionary.contains(w)) {
				found++;
			}
		}
		
		long endLookup = System.nanoTime();
		double fractionFound = found / (double) words.size();
		double timeSpentPerItem = (endLookup - startLookup) / ((double) words.size());
		int nsPerItem = (int) timeSpentPerItem;
		System.out.println(dictionary.getClass().getSimpleName()+": Lookup of items found="+fractionFound+" time="+nsPerItem+" ns/item");
	}
	
	/**
	 * This method finds the words in a list that are not in the dictionary.
	 * @param words - the "queries"
	 * @param dictionary - the data structure
	 * @return a list of "mis-spelled" words
	 */
	public static List<String> missedWords(List<String> words, Collection<String> dictionary) {
		List<String> missed = new ArrayList<String>();
		for (String w : words) {
			if (!dictionary.contains(w)) {
				missed.add(w);
			}
		}
		System.out.println("The 'mis-spelled' words are: " + missed);
		return missed;
	}
	
	/**
	 * This method create a list of valid and mis-spelled words.
	 * @param words - the list of all valid words
	 * @param num - the number of strings in the final output list
	 * @param frac - the percentage of valid words to be added to the output list
	 * @return a list of mixed words
	 */
	public static List<String> createMixedDataset(List<String> words, int num, double frac) {
		Random rand = new Random();
		List<String> mixed = new ArrayList<String>();
		int mid = (int)(frac * num);
		for (int a = 0; a < mid; a++) {
			mixed.add(words.get(rand.nextInt(words.size())));
		}
		for (int b = mid; b < num; b++) {
			mixed.add(words.get(rand.nextInt(words.size()))+"zzz");
		}
		return mixed;
	}
	
	public static void main(String[] args) {
		
		// Load the dictionary.
		List<String> listOfWords = loadDictionary();
		
		// Load the book.
		List<String> dracula = loadBook();
		
		// Time taken to fill a TreeSet.
		System.out.println("\n *** TreeSet ***");
		long startTree0 = System.nanoTime();
		TreeSet<String> treeOfWords = new TreeSet<>(listOfWords);
		long startTree = System.nanoTime();
		TreeSet<String> treeOfWords1 = new TreeSet<>();
		for (String w : listOfWords) {
			treeOfWords1.add(w);
		}
		long endTree = System.nanoTime();
		double treeConsSec = (startTree - startTree0) / 1e9;
		double treeInsertSec = (endTree - startTree) / 1e9;
		double treePerInsertSec = treeInsertSec / treeOfWords.size() * 1e9;
		double treeConsPerInsertSec = treeConsSec / treeOfWords.size() * 1e9;
		System.out.println("It takes " + treeConsSec +" seconds to fill a TreeSet with a constructor.");
		System.out.println("It takes " + treeInsertSec +" seconds to fill a TreeSet with a for loop.");
		System.out.println("It takes " + treeConsPerInsertSec +" nanoseconds to insert one TreeSet element by using a constructor.");
		System.out.println("It takes " + treePerInsertSec +" nanoseconds to insert one TreeSet element by using a for loop.");
		
		// Time taken to fill a HashSet.
		System.out.println("\n *** HashSet ***");
		long startHash0 = System.nanoTime();
		HashSet<String> hashOfWords = new HashSet<>(listOfWords);
		long startHash = System.nanoTime();
		HashSet<String> hashOfWords1 = new HashSet<>();
		for (String w : listOfWords) {
			hashOfWords1.add(w);
		}
		long endHash = System.nanoTime();
		double hashConsSec = (startHash - startHash0) / 1e9;
		double hashInsertSec = (endHash - startHash) / 1e9;
		double hashPerInsertSec = hashInsertSec / hashOfWords.size() * 1e9;
		double hashConsPerInsertSec = hashConsSec / hashOfWords.size() * 1e9;
		System.out.println("It takes " + hashConsSec +" seconds to fill a HashSet with a constructor.");
		System.out.println("It takes " + hashInsertSec +" seconds to fill a HashSet with a for loop.");
		System.out.println("It takes " + hashConsPerInsertSec +" nanoseconds to insert one HashSet element by using a constructor.");
		System.out.println("It takes " + hashPerInsertSec +" nanoseconds to insert one HashSet element by using a for loop.");
		
		// Time taken to fill a SortedStringListSet.
		System.out.println("\n *** SortedStringListSet ***");
		long startList = System.nanoTime();
		SortedStringListSet bsl = new SortedStringListSet(listOfWords);
		long endList = System.nanoTime();
		double listInsertSec = (endList - startList) / 1e9;
		double listPerInsertSec = listInsertSec / bsl.size() * 1e9;
		System.out.println("It takes " + listInsertSec +" seconds to fill a SortedStringListSet with a constructor.");
		System.out.println("It takes " + listPerInsertSec +" nanoseconds to insert one SortedStringListSet element by using a constructor.");
		
		// Time taken to fill a CharTrie.
		System.out.println("\n *** CharTrie ***");
		CharTrie trie = new CharTrie();
		long startTrie = System.nanoTime();
		for (String w : listOfWords) {
			trie.insert(w);
		}
		long endTrie = System.nanoTime();
		double trieInsertSec = (endTrie - startTrie) / 1e9;
		double triePerInsertSec = trieInsertSec / trie.size() * 1e9;
		System.out.println("It takes " + trieInsertSec +" seconds to fill a CharTrie with a for loop.");
		System.out.println("It takes " + triePerInsertSec +" nanoseconds to insert one CharTrie element by using a for loop.");
		
		// Time taken to fill a LLSet.
		System.out.println("\n *** LLSet ***");
		LLHash hm100k = new LLHash(100000);
		long startLL = System.nanoTime();
		for (String w : listOfWords) {
			hm100k.add(w);
		}
		long endLL = System.nanoTime();
		double llInsertSec = (endLL - startLL) / 1e9;
		double llPerInsertSec = llInsertSec / hm100k.size() * 1e9;
		System.out.println("It takes " + llInsertSec +" seconds to fill a LLHash with a for loop.");
		System.out.println("It takes " + llPerInsertSec +" nanoseconds to insert one LLHash element by using a for loop.");
		
		// Analyze the words of the book.
		System.out.println("\n *** Analyzation of the book, Dracula ***");
		timeLookup(dracula, treeOfWords);
	    timeLookup(dracula, hashOfWords);
	    timeLookup(dracula, bsl);
	    timeLookup(dracula, trie);
	    timeLookup(dracula, hm100k);
	    
	    // This is a test to see which extra words CharTrie contains.
	    System.out.println("\n *** This is a test to see which extra words CharTrie contains ***");
	    List<String> test = new ArrayList<String>();
	    test.add("alpha");
	    test.add("beta");
	    test.add("edward");
	    test.add("123");
	    test.add("-");
	    test.add("dracula");
	    test.add("eddie");
	    missedWords(test, trie);
	    missedWords(test, hashOfWords);
	    System.out.println(listOfWords.contains("edward"));
	    System.out.println(listOfWords.contains("eddie"));
	    
		// Make sure that every word in the dictionary is in the dictionary:
	    System.out.println("\n *** If every dictionary word is in the dictionary ***");
		timeLookup(listOfWords, treeOfWords);
		timeLookup(listOfWords, hashOfWords);
		timeLookup(listOfWords, bsl);
		timeLookup(listOfWords, trie);
		timeLookup(listOfWords, hm100k);
		
		// Create a dataset of mixed hits and misses:
		System.out.println("\n *** Create a dataset of mixed hits and misses ***");
		List<String> hitsAndMisses = new ArrayList<String>();
		for (int i=0; i<11; i++) {
		    double fraction = i / 10.0;
		    System.out.println(" * for fraction " + fraction + " * ");
		    hitsAndMisses = createMixedDataset(listOfWords, 10000, fraction);
		    timeLookup(listOfWords, hitsAndMisses);
		    timeLookup(hitsAndMisses, treeOfWords);
		    timeLookup(hitsAndMisses, hashOfWords);
		    timeLookup(hitsAndMisses, bsl);
		    timeLookup(hitsAndMisses, trie);
		    timeLookup(hitsAndMisses, hm100k);
		}
		
		// linear list timing:
		// Looking up in a list is so slow, we need to sample:
		System.out.println("\nStart of list: ");
		timeLookup(listOfWords.subList(0, 1000), listOfWords);
		System.out.println("End of list: ");
		timeLookup(listOfWords.subList(listOfWords.size()-100, listOfWords.size()), listOfWords);
		
	
		// Print statistics about the data structures:
		System.out.println("Count-Nodes: "+trie.countNodes());
		System.out.println("Count-Items: "+hm100k.size());

		System.out.println("Count-Collisions[100k]: "+hm100k.countCollisions());
		System.out.println("Count-Used-Buckets[100k]: "+hm100k.countUsedBuckets());
		System.out.println("Load-Factor[100k]: "+hm100k.countUsedBuckets() / 100000.0);

		
		System.out.println("log_2 of listOfWords.size(): "+listOfWords.size());
		
		System.out.println("Done!");
		
	}
	
}