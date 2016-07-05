package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Arrays;
import java.util.Comparator;


public class SelectionSort extends SorterWithStatistics {
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO -- implement selection sort
		if (words == null || words.length == 0)
			throw new IllegalArgumentException("Null pointer or zero size");
		for (int i = 0; i < words.length - 1; i++) {
			int pos = i;
			for (int j = i + 1; j < words.length; j++)
				if (comp.compare(words[j], words[pos]) < 0)
					pos = j;
			if (i != pos) {
				String t = words[i];
				words[i] = words[pos];
				words[pos] = t;
			}
		}
	}
}

