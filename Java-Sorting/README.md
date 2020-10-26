# CSC212P7

Sorting! In this assignment, you have little starter code. I have provided an implementation of BubbleSort (from class) and isSorted in a single test file. You do not need separate test files for each sorting algorithm, although you can do so.

Although we discussed Comparable and Comparator in class, your sorting algorithms here need only work on ``List<Integer>``, like the provided bubbleSort. No signatures are provided. You will have to decide whether your sorting algorithm modifies a list (easy in BubbleSort) or returns a new list (much easier for MergeSort). Therefore, copying the BubbleSort test directly will not help you with MergeSort -- you'll have to deal with the fact that your mergesort will be returning a new ``List<Integer>`` to you.

## About P7

We're going to focus on the mechanics of sorting here.
Some algorithm descriptions are derived from [N. Howe's assignment on Sorting](http://www.cs.smith.edu/~nhowe/teaching/csc212/Assignments/sorting.php). So you have these textual descriptions in addition to the slides.

## Rubric (100)

This assignment is a little smaller than usual and the maximum is only 100 points.

### (30) Reflection, Code Compiles and Tests Run.

It is very important your code compiles and you do not change the directory structure. If you have issues importing the code into Eclipse, let me know and we can fix it together. I plan to grade these as automatically as possible.


### (20) Simple Sorts

These are sorts that have quite short descriptions.

#### (10) Selection Sort

Selection Sort works by finding the minimum value in the list and bringing it to the front, over and over. This is one of the simplest sorting algorithms.

> Until unsorted is empty, scan it for the smallest remaining element. Remove that element from unsorted and add it to the tail of sorted. (One way to do this: Loop through all the nodes, keeping the index of the smallest element seen so far as you continue to scan through the list, then remove that element by index. Another way: avoid using an index by actually pulling out the smallest element seen so far, and then swapping it back in if and when you encounter a smaller one. Yet another: use two iterators, one to traverse the loop element by element and the other to hold the place of the smallest element seen so far, so you get a stable sort.)

#### (10) Insertion Sort

Insertion Sort works by building up a list one at a time, and inserting it into the right point. Some bonus points may be available for using binary search to insert, but it can be done with linear search as well. 

> Until unsorted is empty, take the first element off it and find the point where it should go in sorted (the point where all previous elements are smaller, and all following elements are equal to or greater than). Insert it into sorted at this point.


### (30) Merge Sort

Merge sort is an important sorting algorithm. We will explore the recursive and iterative solutions in this assignment.

#### (10) Merge Sorted Lists into a Longer, Sorted List

To merge two sorted lists into a single sorted list, look at the first element in each list. Take the smaller of the two off the front of its old list and put it at the end of the new (merged) list. Repeat this until both one of the old lists is empty, at which point you can append the remainder of the other original list to the new list. If the original lists were sorted, and you always take the smallest element available, then the resulting list will also be sorted. (You might want to convince yourself of this fact before continuing.)

#### (10) Recursive MergeSort

Use recursion to break down the list into pieces and build it back up. Split the list in half until there are only tiny lists that remain.

#### (10) Iterative MergeSort

Begin by placing each element of the original list into a new singleton list. You may store all the singleton lists in a list of lists.

While more than one list remains, take the first two lists from your work queue and merge them, preserving the sorted order. Put the result back at the end of the work queue.

### (20) MergeSort with P6 Doubly-Linked List.

Take your implementation of the Doubly-Linked List from P6 and implement MergeSort using that interface. Partial credit will be given for a solution that uses an alternate list from P6. Partial credit will be given for only using it for the partial parts of MergeSort. Think about how you will split lists in half (for the recursive method) -- a new functionality for DoublyLinkedList for this assignment. The iterative method does not have this requirement.

Suggested edits to DoublyLinkedList from P6 for unit-testing:

```java
       // For unit tests, List<T> supports equals, but P6List<T> does not.
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
```
