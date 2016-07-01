package edu.iastate.cs228.hw1;

/**
 * @author anhvn93
 */

public class GenomicDNASequence extends DNASequence {
	/**
	 * the constructor saves a copy of the character array argument in the field
	 * of its superclass
	 * 
	 * @param gdnaarr
	 *            array of character that the class takes in as parameter
	 */
	public GenomicDNASequence(char[] gdnaarr) {
		super(gdnaarr);
	}

	/**
	 * 
	 * @param exonpos
	 *            an array of all the start and end positions of every coding
	 *            exon
	 * @return the method takes all the coding exons specified by the array
	 *         exonpos, concatenates them in order, and returns the resulting
	 *         sequence in a new character array
	 */

	public char[] extractExons(int[] exonpos) {

		if (exonpos == null || exonpos.length == 0 || exonpos.length % 2 != 0)
			throw new IllegalArgumentException("Empty array or odd number of array elements");

		for (int i = 0; i < exonpos.length; i++) {
			if (exonpos[i] < 0 || exonpos[i] >= super.seqLength())
				throw new IllegalArgumentException("Exon position is out if bound");
		}
		for (int i = 0; i < exonpos.length - 1; i++) {
			if (exonpos[i] > exonpos[i + 1])
				throw new IllegalArgumentException("Exon positions are not in order");
		}
		int extracted_exonarrLength = 0;
		for (int i = 0; i < exonpos.length; i += 2) {
			extracted_exonarrLength += (exonpos[i + 1] - exonpos[i] + 1);
		}

		char[] extracted_exonarr = new char[extracted_exonarrLength];
		int k = 0;
		int m = 0;
		for (int j = 0; j < extracted_exonarrLength; j++) {
			if ((m + exonpos[k]) > exonpos[k + 1]) {
				k = k + 2;
				m = 0;
			}
			extracted_exonarr[j] = super.getSeq()[m + exonpos[k]];
			m++;
		}
		return extracted_exonarr;
	}
}