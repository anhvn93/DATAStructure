package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test; 

public class CodingDNASequenceTest {
CodingDNASequence cdnaSeq ;

//Initiate an instance of class CodingDnaSequence to test
@Before
public void setUp(){
cdnaSeq = new CodingDNASequence(new char[]{'A','T','G','C','C','T','C','A','A','T','A','G'});
}


@Test
public void checkStartCodonTest(){
	assertTrue(cdnaSeq.checkStartCodon());
}
@Test
public void translate(){
	assertArrayEquals(new char[]{'M','P','Q'}, cdnaSeq.translate());
}
@Test(expected = IllegalArgumentException.class)
public void testException()
{
	new CodingDNASequence(new char[]{'B','a'});
}	


}
