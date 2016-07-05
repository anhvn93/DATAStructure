package edu.iastate.cs228.hw1;

/**
 * @author anhvn93
*/

public class DNASequence extends Sequence {
	/**
	 * the constructor saves a copy of the character array argument in the field
	 * of its superclass
	 * 
	 * @param dnaarr
	 *            array of character that the class takes in as parameter
	 */
	public DNASequence(char[] dnaarr) {
		super(dnaarr);
	}

	/**
	 * @return true if the character argument is equal to one of the eight
	 *         characters ’a’, ’A’, ’c’, ’C’, ’g’, ’G’, ’t’ and ’T’. Otherwise,
	 *         it returns false. This method overrides the one in its
	 *         superclass.
	 */
	@Override
	public boolean isValidLetter(char let) {
		char[] eightChar = { 'a', 'A', 'c', 'C', 'g', 'G', 't', 'T' };
		for (int i = 0; i < 8; i++) {
			if (eightChar[i] == let)
				return true;
		}
		return false;
	}

}
