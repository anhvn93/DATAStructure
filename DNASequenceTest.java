package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 

public class DNASequenceTest {
DNASequence dnaseq ;


//Initiate an instance of class DNASequence to test
@Before
public void instantiated(){
	dnaseq = new DNASequence (new char[]{'a','g','C','T','A','T','C'});
	}

@Test
public void isValidLetterTest(){
	assertTrue(dnaseq.isValidLetter('a'));
	}

@Test(expected = IllegalArgumentException.class)
public void TestingException()
{
	new DNASequence(new char[]{'H','a'});
}	 
}
