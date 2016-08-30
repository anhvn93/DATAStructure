package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) throws FileNotFoundException, FileConfigurationException {
		// TODO
		// You may do anything you like here in order to get the behavior
		// described in the PDF.
		// A recommended structure is to first check the arguments, then
		// read the configuration (character ordering). With the configuration
		// in hand, you'll be able to read the word list and check that the
		// words are valid. Next, initilize your sorters. Since these all
		// inherit from SorterWithStatistics, you can put them in an array.
		// Run the sorts on the word list and process and display the
		// statistics.

		char[] arr = readCharacterOrdering("10.alphabet.txt");
		CustomComparator comp = new CustomComparator(arr);
		String[] sarr = readWordsFile("10.wordlist.txt", comp);
		MergeSort ms = new MergeSort();
		QuickSort qs = new QuickSort();
		SelectionSort ss = new SelectionSort();
		for (int i = 0; i<100000; i++){
			String [] tmp = Arrays.copyOf(sarr, sarr.length);
			ss.sort(tmp, comp);
			tmp = Arrays.copyOf(sarr, sarr.length);
			qs.sort(tmp, comp);
			tmp = Arrays.copyOf(sarr, sarr.length);
			ms.sort(tmp, comp);
	}
		System.out.println("For 10");
		System.out.println();
		System.out.println("For SelectionSort");
		System.out.println("length of the wordlist: " + ss.getWordsSorted());
		System.out.println("total number of words sorted: "+ ss.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ss.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ss.getTotalTimeToSortWords()/100000 );
		System.out.println("Words sorted per second: "+ ss.getTotalWordsSorted()/(ss.getTotalTimeToSortWords()/1000000000.0));
		
		System.out.println("For MergeSort");
		System.out.println("length of the wordlist: " + ms.getWordsSorted());
		System.out.println("total number of words sorted: "+ ms.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ms.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ms.getTotalTimeToSortWords()/100000 );
		System.out.println("Words sorted per second: "+ ms.getTotalWordsSorted()/(ms.getTotalTimeToSortWords()/1000000000.0));
		
		System.out.println("For QuickSort");
		System.out.println("length of the wordlist: " + qs.getWordsSorted());
		System.out.println("total number of words sorted: "+ qs.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+qs.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +qs.getTotalTimeToSortWords()/100000 );
		System.out.println("Words sorted per second: "+ qs.getTotalWordsSorted()/(qs.getTotalTimeToSortWords()/1000000000.0));
		System.out.println();
		
		CustomComparator comp2 = new CustomComparator(readCharacterOrdering("100.alphabet.txt"));
		String[] sarr2 = readWordsFile("100.wordlist.txt", comp2);
		MergeSort ms2 = new MergeSort();
		QuickSort qs2 = new QuickSort();
		SelectionSort ss2 = new SelectionSort();
		for (int i = 0; i<10000; i++){
			String [] tmp = Arrays.copyOf(sarr2, sarr2.length);
			ss2.sort(tmp, comp2);
			tmp = Arrays.copyOf(sarr2, sarr2.length);
			qs2.sort(tmp, comp2);
			tmp = Arrays.copyOf(sarr2, sarr2.length);
			ms2.sort(tmp, comp2);
		}
		System.out.println("For 100");
		System.out.println();
		System.out.println("For SelectionSort");
		System.out.println("length of the wordlist: " + ss2.getWordsSorted());
		System.out.println("total number of words sorted: "+ ss2.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ss2.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ss2.getTotalTimeToSortWords()/10000 );
		System.out.println("Words sorted per second: "+ (ss2.getTotalWordsSorted()/(ss2.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For MergeSort");
		System.out.println("length of the wordlist: " + ms2.getWordsSorted());
		System.out.println("total number of words sorted: "+ ms2.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ms2.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ms2.getTotalTimeToSortWords()/10000 );
		System.out.println("Words sorted per second: "+ (ms2.getTotalWordsSorted()/(ms2.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For QuickSort");
		System.out.println("length of the wordlist: " + qs2.getWordsSorted());
		System.out.println("total number of words sorted: "+ qs2.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+qs2.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +qs2.getTotalTimeToSortWords()/10000 );
		System.out.println("Words sorted per second: "+ qs2.getTotalWordsSorted()/(qs2.getTotalTimeToSortWords()/1000000000.0));
		System.out.println();
		
		CustomComparator comp3 = new CustomComparator(readCharacterOrdering("1000.alphabet.txt"));
		String[] sarr3 = readWordsFile("1000.wordlist.txt", comp3);
		MergeSort ms3 = new MergeSort();
		QuickSort qs3 = new QuickSort();
		SelectionSort ss3 = new SelectionSort();
		for (int i = 0; i<1000; i++){
			String [] tmp = Arrays.copyOf(sarr3, sarr3.length);
			ss3.sort(tmp, comp3);
			tmp = Arrays.copyOf(sarr3, sarr3.length);
			qs3.sort(tmp, comp3);
			tmp = Arrays.copyOf(sarr3, sarr3.length);
			ms3.sort(tmp, comp3);
	}
		System.out.println("For 1000");
		System.out.println();
		System.out.println("For SelectionSort");
		System.out.println("length of the wordlist: " + ss3.getWordsSorted());
		System.out.println("total number of words sorted: "+ ss3.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ss3.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ss3.getTotalTimeToSortWords()/1000 );
		System.out.println("Words sorted per second: "+ (ss3.getTotalWordsSorted()/(ss3.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For MergeSort");
		System.out.println("length of the wordlist: " + ms3.getWordsSorted());
		System.out.println("total number of words sorted: "+ ms3.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ms3.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ms3.getTotalTimeToSortWords()/1000 );
		System.out.println("Words sorted per second: "+ (ms3.getTotalWordsSorted()/(ms3.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For QuickSort");
		System.out.println("length of the wordlist: " + qs3.getWordsSorted());
		System.out.println("total number of words sorted: "+ qs3.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+qs3.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +qs3.getTotalTimeToSortWords()/1000 );
		System.out.println("Words sorted per second: "+ qs3.getTotalWordsSorted()/(qs3.getTotalTimeToSortWords()/1000000000.0));
		System.out.println();
		
		CustomComparator comp4 = new CustomComparator(readCharacterOrdering("10000.alphabet.txt"));
		String[] sarr4 = readWordsFile("10000.wordlist.txt", comp4);
		MergeSort ms4 = new MergeSort();
		QuickSort qs4 = new QuickSort();
		SelectionSort ss4 = new SelectionSort();
		for (int i = 0; i<100; i++){
			String [] tmp = Arrays.copyOf(sarr4, sarr4.length);
			ss4.sort(tmp, comp4);
			tmp = Arrays.copyOf(sarr4, sarr4.length);
			qs4.sort(tmp, comp4);
			tmp = Arrays.copyOf(sarr4, sarr4.length);
			ms4.sort(tmp, comp4);
	}
		System.out.println("For 10000");
		System.out.println();
		System.out.println("For SelectionSort");
		System.out.println("length of the wordlist: " + ss4.getWordsSorted());
		System.out.println("total number of words sorted: "+ ss4.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ss4.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ss4.getTotalTimeToSortWords()/100 );
		System.out.println("Words sorted per second: "+ (ss4.getTotalWordsSorted()/(ss4.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For MergeSort");
		System.out.println("length of the wordlist: " + ms4.getWordsSorted());
		System.out.println("total number of words sorted: "+ ms4.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ms4.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ms4.getTotalTimeToSortWords()/100 );
		System.out.println("Words sorted per second: "+ (ms4.getTotalWordsSorted()/(ms4.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For QuickSort");
		System.out.println("length of the wordlist: " + qs4.getWordsSorted());
		System.out.println("total number of words sorted: "+ qs4.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+qs4.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +qs4.getTotalTimeToSortWords()/100 );
		System.out.println("Words sorted per second: "+ qs4.getTotalWordsSorted()/(qs4.getTotalTimeToSortWords()/1000000000.0));
		System.out.println();
		
		CustomComparator comp5 = new CustomComparator(readCharacterOrdering("100000.alphabet.txt"));
		String[] sarr5 = readWordsFile("100000.wordlist.txt", comp5);
		MergeSort ms5 = new MergeSort();
		QuickSort qs5 = new QuickSort();
		SelectionSort ss5 = new SelectionSort();
		for (int i = 0; i<10; i++){
			String [] tmp = Arrays.copyOf(sarr5, sarr5.length);
			ss5.sort(tmp, comp5);
			tmp = Arrays.copyOf(sarr5, sarr5.length);
			qs5.sort(tmp, comp5);
			tmp = Arrays.copyOf(sarr5, sarr5.length);
			ms5.sort(tmp, comp5);
	}
		System.out.println("For 100000");
		System.out.println();
		System.out.println("For SelectionSort");
		System.out.println("length of the wordlist: " + ss5.getWordsSorted());
		System.out.println("total number of words sorted: "+ ss5.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ss5.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ss5.getTotalTimeToSortWords()/10 );
		System.out.println("Words sorted per second: "+ (ss5.getTotalWordsSorted()/(ss5.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For MergeSort");
		System.out.println("length of the wordlist: " + ms5.getWordsSorted());
		System.out.println("total number of words sorted: "+ ms5.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+ms5.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +ms5.getTotalTimeToSortWords()/10 );
		System.out.println("Words sorted per second: "+ (ms5.getTotalWordsSorted()/(ms5.getTotalTimeToSortWords()/1000000000.0)));
		
		System.out.println("For QuickSort");
		System.out.println("length of the wordlist: " + qs5.getWordsSorted());
		System.out.println("total number of words sorted: "+ qs5.getTotalWordsSorted());
		System.out.println("total time spent sorting: "+qs5.getTotalTimeToSortWords());
		System.out.println("average time required to sort the word list: " +qs5.getTotalTimeToSortWords()/10 );
		System.out.println("Words sorted per second: "+ qs5.getTotalWordsSorted()/(qs5.getTotalTimeToSortWords()/1000000000.0));
		System.out.println();
	}

	/**
	 * Reads the characters contained in filename (the input alphabet) and
	 * returns them as a character array.
	 * 
	 * @param filename
	 *            the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used
	 *          or null if there is an exception. FileNotFoundException is
	 *          thrown when filename is does not exist.
	 *          FileConfigurationException is thrown when any line of the input
	 *          file is repeated or when any line contains other than exactly 1
	 *          byte before the terminating newline.
	 */
	public static char[] readCharacterOrdering(String filename)
			throws FileNotFoundException, FileConfigurationException {
		String t = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			sc.useDelimiter("\n");
			while (sc.hasNext()) {
				String add = sc.next();
				for (int i = 0; i < t.length(); i++) {
					if ((add.length() > 1) || (add.charAt(0) == (t.toCharArray()[i])))
						throw new FileConfigurationException();
				}
				t += add;
			
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			return null;
		}

		catch (FileConfigurationException e2) {
			return null;
		}
		return t.toCharArray();
	}

	/**
	 * Reads the words from the file and returns them as a String array.
	 * 
	 * @param filename
	 *            file containing words
	 * @return the words contained in the file or null if there was an
	 *         exception. FileNotFoundException is thrown when filename is does
	 *         not exist. FileConfigurationException is thrown when the file
	 *         contains any characters that didn't first appear in the input
	 *         alphabet.
	 */
	public static String[] readWordsFile(String filename, CustomComparator comp)
			throws FileNotFoundException, FileConfigurationException {
		try {
			int count = 0;
			Scanner sc = new Scanner(new File(filename));
			sc.useDelimiter("\n");
			while (sc.hasNext()) {
				count++;
				sc.next();
			}
			sc.close();

			Scanner sc2 = new Scanner(new File(filename));
			sc2.useDelimiter("\n");

			String[] str = new String[count];
				for (int i = 0; i < count; i++) {
					str[i] = sc2.next();
					if (isValid(str[i], comp) == false)
						throw new FileConfigurationException();
				}
			return str;
		} 
		catch (FileNotFoundException e1) {
			return null;
		}
		catch (FileConfigurationException e2){
			return null;
		}
	}
	

	/**
	 * Returns whether or not word is valid.
	 * 
	 * @param word
	 *            word to be checked.
	 * @param comparator
	 *            comparator used to check if characters are valid.
	 * @return true if every character in the word is in the input alphabet,
	 *         else false.
	 */
	public static boolean isValid(String word, CustomComparator comparator) {
		char[] arr = word.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (comparator.getCharacterOrdering(arr[i]) == -1)
				return false;
		}
		return true;
	}

	private static class FileConfigurationException extends Exception {
	}
}
