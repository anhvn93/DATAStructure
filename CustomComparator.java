package edu.iastate.cs228.hw2;
/**
 * @author Anh
 */
import java.util.Arrays;
import java.util.Comparator;

public class CustomComparator implements Comparator<String> {
        /***
         * Lookup table mapping characters in lexicographical order to
         * to their input order.
         */
	private CharacterValue[] characterOrdering;

        /***
         * Creates an array of CharacterValue from characterOrdering.  Sorts
         * it using Arrays.sort().
         * @param characterOrdering character order from configuration file
         */
	public CustomComparator(char[] characterOrdering) {
		this.characterOrdering = new CharacterValue [characterOrdering.length];
		for(int i = 0; i<characterOrdering.length; i++){
			this.characterOrdering[i]= new CharacterValue(i,characterOrdering[i]);
		}
		Comparator<CharacterValue> comp = new Comparator<CharacterValue>() {
			  @Override
			  public int compare(CharacterValue a, CharacterValue b) {
				  if (a.character<b.character)
					  return -1;
				  else if (a.character>b.character)
					  return 1;
				  else
					  return 0;
			  }  
		};
		
		Arrays.sort(this.characterOrdering, comp);
	}

        /***
         * Compares two words based on the configuration
         * @param a first word
         * @param b second word
         * @return negative if a<b, 0 if equal, postive if a>b
         */
	@Override
	public int compare(String a, String b) {
		for(int i = 0; i<Math.min(a.length(), b.length());i++){
			if (getCharacterOrdering(a.charAt(i))<getCharacterOrdering(b.charAt(i)))
				return -1;
			else if (getCharacterOrdering(a.charAt(i))>getCharacterOrdering(b.charAt(i)))
				return 1;
		}
		  return a.length()-b.length();
			
		
	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
          	   return binarySearch(characterOrdering, new CharacterValue(-1,key));
	}

	/**
	 * Searches characterOrdering for key via binary search
	 * @param characterOrdering the specified sort order
         * @param key the search term
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
        private int binarySearch(CharacterValue[] characterOrdering, CharacterValue key) {

        	int first =0;
        	int last = characterOrdering.length-1;

        	while(first<=last){
            	int mid = (last+first) /2;

        		if (key.character<characterOrdering[mid].character){
        		last = mid-1;
        	}
        	else if (key.character>characterOrdering[mid].character){
        		first = mid+1;
        	}
        	else
            	return characterOrdering[mid].value;
        	}

        	return -1;
        }
	
	private static class CharacterValue {
          /* This class is complete.  Do not modify it.  When you read 
           * the configuration file, which defines an alphabetical order for
           * our input alphabet.  The alphabet that Java uses internally has
           * a different order.  In order to efficiently search for letters in
           * the input alphabet, we need to sort them into "Java order", but 
           * once they're sorted, we need to know their input order.  This 
           * class keeps track of that.  Consider the following input alphabet:
           *
           *                          e d c a b
           *
           * Their indices in input order are e:0, d:1, c:2, a:3, and b:4.  How
           * do we find if c is less than b in input order?  We could do a 
           * linear search.  We'll find c before we find b, and we'll conclude
           * that it is, but that takes O(n) time.  If we sort the input 
           * alphabet in "Java order" and keep track of the input order indices,
           * then we can use binary search and get O(lg n) performance, so we 
           * end up with an array of character value that looks like:
           *
           *              character = a b c d e
           *                  value = 3 4 2 1 0
           *
           * To check if c is less than b, binary search for c and return its
           * input index.  Do the same for b.  Compare the indices.
           */
		public int value;
		public char character;
		
		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}
		
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}
}
