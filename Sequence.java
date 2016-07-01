package edu.iastate.cs228.hw1;

/**
 * The Sequence is a superclass of every other class. The class has a field
 * named seqarr, whichis a character array with protected access.
 * 
 * @author Anh
 *
 */

public class Sequence {

	/**
	 * @param seqarr
	 *            an array of character that is treated as the only field of the class
	 */
	protected char[] seqarr;

	/**
	 * The constructor first uses the isValidLetter() method to check if every
	 * character in the array sarr is valid. If so, it makes and keeps a copy of
	 * the array sarr in the field seqarr of type char[] with protected access.
	 * Otherwise, it throws an IllegalArgumentException with the message
	 * “Invalid sequence letter for class X” where X denotes
	 * ‘edu.iastate.cs228.hw1.Sequence’ or the name of a subclass of which an
	 * object is created.
	 * 
	 * @param sarr
	 *            the length of the field seqarr is equal to the length of the
	 *            array sarr
	 */

	public Sequence(char[] sarr) {
		for (int i = 0; i < sarr.length; i++) {
			if (this.isValidLetter(sarr[i]) == false)
				throw new IllegalArgumentException("Invalid sequence letter for " + this.getClass());
		}
		seqarr = new char[sarr.length];
		for (int i = 0; i < seqarr.length; i++) {
			seqarr[i] = sarr[i];
		}
	}

	/**
	 * 
	 * @return the length of the character array seqarr
	 */

	public int seqLength() {
		return seqarr.length;
	}

	/**
	 * 
	 * @return makes and returns a copy of the char array seqarr
	 */

	public char[] getSeq() {
		char[] copy_seqarr = new char[seqarr.length];
		for (int i = 0; i < seqarr.length; i++) {
			copy_seqarr[i] = seqarr[i];
		}
		return copy_seqarr;
	}

	/**
	 * @return the string representation of the char array seqarr.
	 */
	public String toString() {
		String representation = new String(seqarr);
		return representation;
	}

	/**
	 * @param obj
	 *            Object passed in to compare
	 * @return true if the argument obj is not null and is of the same type as
	 *         this object such that both objects represent the identical
	 *         sequence of characters in a case insensitive mode (”ACgt” is
	 *         identical to ”AcGt”).
	 */
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Sequence test = (Sequence) obj;
		return ((this.toString()).equalsIgnoreCase(test.toString()));
	}

	/**
	 * 
	 * @param let
	 *            character passed in
	 * @return true if the character let is an uppercase or lowercase
	 */
	public boolean isValidLetter(char let) {
		if (Character.isUpperCase(let) == true || Character.isLowerCase(let) == true)
			return true;
		return false;
	}

}
