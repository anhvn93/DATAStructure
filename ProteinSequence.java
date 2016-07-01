package edu.iastate.cs228.hw1;

/**
 * @author anhvn93
*/

public class ProteinSequence extends Sequence {
	/**
	 * the constructor saves a copy of the character array argument in the field
	 * of its superclass
	 * 
	 * @param psarr
	 *            array of character that the class takes in as parameter
	 */
	public ProteinSequence(char[] psarr) {
		super(psarr);
	}

	/**
	 * @return true if the character argument is equal to one of the 20 English
	 *         letters that are not in the set {B, b, J, j, O, o, U, u, X, x, Z,
	 *         z}. Otherwise, it returns false. This method overrides the one in
	 *         its superclass.
	 */

	@Override
	public boolean isValidLetter(char aa) {
		if (!super.isValidLetter(aa) || aa == 'B' || aa == 'b' || aa == 'J' || aa == 'j' || aa == 'O' || aa == 'o'
				|| aa == 'U' || aa == 'u' || aa == 'X' || aa == 'x' || aa == 'Z' || aa == 'z')
			return false;
		return true;
	}
}
