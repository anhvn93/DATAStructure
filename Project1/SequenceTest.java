package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 

public class SequenceTest {
Sequence seq;
Sequence seq2;
char let;

//Initiate an instance called seq of class Sequence to test
// seq2 is another object to test equal
	@Before	
	public void initizialized() {
		 seq = new Sequence(new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G'});
		 seq2 = new Sequence (new char[]{'T','A','A'});
		 let = 'a';
	}
	
	@Test 
		public void seqLengthTest(){
			assertEquals(19,seq.seqLength());
		}
	@Test
		public void getSeqTest(){
		assertArrayEquals(new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G'},seq.getSeq());
	}
	@Test
		public void toStringTest(){
		assertEquals(new String("AATGCCAGTCAGCATAGCG"),seq.toString());
	}
	@Test
	public void equalsTest(){
		assertFalse(seq.equals(seq2));
	}
	@Test
	public void isValidLetterCheck(){
		assertTrue(seq.isValidLetter(let));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Testingexception()
	{
		 new Sequence(new char[]{'$','a'});
	}	
	

}
