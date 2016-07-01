package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test; 

public class GenomicDNASequenceTest {
GenomicDNASequence gdnaSeq;
int[] exonposition;

//Initiate an instance of class GenomicDNASequence to test
@Before
public void SetUpTest(){
	gdnaSeq = new GenomicDNASequence(new String ("AATGCCAGTCAGCATAGCG").toCharArray());
	exonposition = new int[] {1, 5, 8, 10, 13, 16};
}
@Test 
public void extractExonsTest(){
	assertArrayEquals(new char[]{'A','T','G','C','C','T','C','A','A','T','A','G'}, gdnaSeq.extractExons(exonposition));
	
}
@Test(expected = IllegalArgumentException.class)
	public void CheckException()
	{
		new GenomicDNASequence(new char[]{'H','a'});
	}	
}
