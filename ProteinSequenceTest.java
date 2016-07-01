package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test; 

public class ProteinSequenceTest {
	ProteinSequence proteinSeq ;
	
	//Initiate an instance of class ProteinSequence to test
	@Before
	public void constructObj (){
		proteinSeq = new ProteinSequence(new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G'});
	}
	
	@Test
	public void isValidLettersTest(){
		assertTrue(proteinSeq.isValidLetter('a'));
	}
	@Test
	public void isValidLettersTest2(){
		assertFalse(proteinSeq.isValidLetter('b'));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testingExceptions()
	{
		 new Sequence(new char[]{'$','a'});
	}	
}


