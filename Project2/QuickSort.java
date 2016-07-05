package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Arrays;
import java.util.Comparator;


public class QuickSort extends SorterWithStatistics {
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO -- implement quicksort
		// You will need a helper method, i.e., partition()

		if (words == null || words.length == 0)
			throw new RuntimeException("Null pointer or zero size");
		quickSortRec(words, 0, words.length - 1, comp);
	}

	private static void quickSortRec(String[] arr, int first, int last, Comparator<String> comp) {
		if (first >= last)
			return;
		int mid = partition(arr, first, last,comp); // performs a partition
		quickSortRec(arr, first, mid,comp); // recursive calls
		quickSortRec(arr, mid + 1, last,comp);
	} // quickSortRec

	// Note that the code needs to be revised if a different method is used to
	// select the pivot.
	// Otherwise, the code may not work correctly.

	private static int partition(String[] arr, int first, int last,Comparator<String> comp) {
		String pivot = arr[first]; // A simple way to select the pivot.
		int left = first;
		int right = last;

		while (true) {
			while (comp.compare(arr[left], pivot)< 0)
				left++;
			while (comp.compare(arr[right], pivot) > 0)
				right--;
			if (left < right) {

				String t = arr[left];
				arr[left++] = arr[right];
				arr[right--] = t;
			} else
				break;
		}
		return right;
	}

}