package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Comparator;

public abstract class SorterWithStatistics implements Sorter {
	private Stopwatch timer = new Stopwatch();
	private int numberWords_lastSort;
	private long time_lastSort;
	private int totalWords_sorted;
	private long total_timespent;

	/***
	 * Default constructor
	 */
	public SorterWithStatistics() {
	}

	/***
	 * Public interface to sortHelper that keeps track of performance
	 * statistics, including counting words sorted and timing sort instances.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	public void sort(String[] words, Comparator<String> comp) {
		timer.start();
		sortHelper(words, comp);
		timer.stop();
		time_lastSort= timer.getElapsedTime();
		total_timespent+= time_lastSort;
		numberWords_lastSort = words.length;
		totalWords_sorted += numberWords_lastSort;
	}

	/**
	 * Sorts the array words.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	protected abstract void sortHelper(String[] words, Comparator<String> comp);

	/**
	 * Returns number of words sorted in last sort. Throws IllegalStateException
	 * if nothing has been sorted.
	 * 
	 * @return number of words sorted in last sort.
	 */
	public int getWordsSorted() {
		if (numberWords_lastSort == 0)
			throw new IllegalStateException();
		return numberWords_lastSort;

	}

	/**
	 * Returns time the last sort took. Throws IllegalStateException if nothing
	 * has been sorted.
	 * 
	 * @return time last sort took.
	 */
	public long getTimeToSortWords() {
		if (time_lastSort == 0)
			throw new IllegalStateException();
		return time_lastSort;
	}

	/**
	 * Returns total words sorted by this instance.
	 * 
	 * @return total number of words sorted.
	 */
	public int getTotalWordsSorted() {

		return totalWords_sorted;
	}

	/**
	 * Returns the total amount of time spent sorting by this instance.
	 * 
	 * @return total time spent sorting.
	 */
	public long getTotalTimeToSortWords() {
		return total_timespent;
	}
}
