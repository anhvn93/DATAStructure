package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Comparator;

/* Nothing to be done here. */

public interface Sorter {
	/**
	 * Sorts words according to the Comparator comp.
	 * @param words words to be sorted.
	 * @param comp Comparator used to sort words.
	 */
	public void sort(String[] words, Comparator<String> comp);
}
