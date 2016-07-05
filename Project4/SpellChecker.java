package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class SpellChecker {

	/**
	 * Displays usage information.
	 *
	 * There's no reason that you should need to modify this.
	 * 
	 * @author ANH NGUYEN
	 */
	private static void doUsage() {
		System.out.println("Usage: SpellChecker [-i] <dictionary> <document>\n"
				+ "                    -d <dictionary>\n" + "                    -h");
	}

	/**
	 * Displays detailed usage information and exits.
	 *
	 * There's no reason that you should need to modify this.
	 */
	private static void doHelp() {
		doUsage();
		System.out.println("\n" + "When passed a dictionary and a document, spell check the document.  Optionally,\n"
				+ "the switch -n toggles non-interactive mode; by default, the tool operates in\n"
				+ "interactive mode.  Interactive mode will write the corrected document to disk,\n"
				+ "backing up the uncorrected document by concatenating a tilde onto its name.\n\n"
				+ "The optional -d switch with a dictionary parameter enters dictionary edit mode.\n"
				+ "Dictionary edit mode allows the user to query and update a dictionary.  Upon\n"
				+ "completion, the updated dictionary is written to disk, while the original is\n"
				+ "backed up by concatenating a tilde onto its name.\n\n"
				+ "The switch -h displays this help and exits.");
		System.exit(0);
	}

	/**
	 * Runs the three modes of the SpellChecker based on the input arguments. DO
	 * NOT change this method in any way other than to set the name and sect
	 * variables.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			doUsage();
			System.exit(-1);
		}

		/*
		 * In order to be considered for the competition, set these variables.
		 */
		String name = "ANH NGUYEN"; // First and Last
		String sect = "A"; // "A" or "B"

		Timer timer = new Timer();

		timer.start();

		if (args[0].equals("-h"))
			doHelp();
		else if (args[0].equals("-n"))
			doNonInteractiveMode(args);
		else if (args[0].equals("-d"))
			doDictionaryEditMode(args);
		else
			doInteractiveMode(args);

		timer.stop();

		System.out.println("Student name:   " + name);
		System.out.println("Student sect:   " + sect);
		System.out.println("Execution time: " + timer.runtime() + " ms");
	}

	/**
	 * Carries out the Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doInteractiveMode(String[] args) throws FileNotFoundException {
		 Dictionary dictionary = new Dictionary(args[1]);
		File f = new File(args[2]);
		ArrayList<String> arrListString = new ArrayList<String>();
		Scanner scanner1 = new Scanner(f);
		Scanner scanner2 = new Scanner(System.in);
		int length = 0;
		String i = "";
		String hold = "";
		String line = "";
		LinkedList<Integer> linkedlist = new LinkedList<Integer>();
		while (scanner1.hasNextLine()) {
			line = scanner1.nextLine();
			if (line.equals("")) {
				arrListString.add(line);
				continue;
			}

			int j = 0;
			while (j < line.length()) {
				if (!Character.isWhitespace(line.charAt(j)))
					break;
				else
					i = i + line.charAt(j);

				j++;
			}
			String[] words = line.trim().split(" ");
			for (int index = 0; index < words.length; index++) {
				if (words[index].equals(" ") || words[index].equals("")) {
					i = i + " ";
					continue;
				}

				length = words[index].length();
				for (int j1 = 0; j1 < length; j1++) {
					char ch = words[index].charAt(j);
					if (!Character.isDigit(ch) && !Character.isAlphabetic(ch)) {
						hold = hold + " ";
						length--;
					}

					else
						break;
				}

				if (words[index].contains("-")||words[index].contains("'")){
				String x = words[index].replaceAll("[-']", " ");
				String [] subarr = x.split(" ");
				for (String sub : subarr){
					if (!dictionary.hasWord(sub.toLowerCase())){
						hold = hold + "^";
						linkedlist.add(index);
						break;
					}
				}
				
				}
			}
			scanner1.close();
			scanner2.close();
		}

	}

	/**
	 * Carries out the Non-Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doNonInteractiveMode(String[] args) {

	}

	/**
	 * Carries out the Dictionary Edit mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doDictionaryEditMode(String[] args) {

	}

	/**
	 * Timer class used for this project's competition. DO NOT modify this class
	 * in any way or you will be ineligible for Eternal Glory.
	 */
	private static class Timer {
		private long startTime;
		private long endTime;

		public void start() {
			startTime = System.nanoTime();
		}

		public void stop() {
			endTime = System.nanoTime();
		}

		public long runtime() {
			return endTime - startTime;
		}
	}
}
