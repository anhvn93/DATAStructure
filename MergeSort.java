package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Comparator;
import java.util.Arrays;


public class MergeSort extends SorterWithStatistics {
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO -- implement merge sort
		// You'll probably need a helper method here, i.e., merge()
		if (words.length >= 2) {
			String[] left = new String[words.length / 2];
			String[] right = new String[words.length - words.length / 2];

			for (int i = 0; i < left.length; i++) {
				left[i] = words[i];
			}
			for (int i = 0; i < right.length; i++) {
				right[i] = words[i + words.length / 2];
			}

			sortHelper(left, comp);
			sortHelper(right,comp);

			merge(words, left, right,comp);
		}
	}

	private static void merge(String[] result, String[] left, String[] right,Comparator<String> comp) {
		int p1 = 0;
		int p2 = 0;
		for (int i = 0; i < result.length; i++) {
			if (p2 >= right.length || (p1 < left.length && comp.compare(left[p1],right[p2]) < 0)) {
				result[i] = left[p1];
				p1++;
			} else {
				result[i] = right[p2];
				p2++;
			}
		}
	}

}
