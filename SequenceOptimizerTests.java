

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SequenceOptimizerTests {
	
	SequenceOptmizer optimizer = new SequenceOptmizer();
	ArrayList<String> testSequence1 = new ArrayList<String>();
	ArrayList<String> testSequence2 = new ArrayList<String>();
	
	@Before
	public void createInputSequence() { 
		optimizer.initializeChangeOverMatrix();
		optimizer.initializeTimeToProduceMatrix();
		
		testSequence1.add("H");
		testSequence1.add("F");
		testSequence1.add("G");
		testSequence1.add("I");
		testSequence1.add("C");
		testSequence1.add("A");
		testSequence1.add("B");
		testSequence1.add("D");
		testSequence1.add("E");
		testSequence1.add("J");
		
		testSequence2.add("B");
		testSequence2.add("G");
		testSequence2.add("E");
		testSequence2.add("A");
		testSequence2.add("C");
		testSequence2.add("I");
		testSequence2.add("J");
		testSequence2.add("D");
		testSequence2.add("H");
		testSequence2.add("F");
    }
  

	@Test
	public void makeSwaps() {

		ArrayList<String> bestSequence = new ArrayList<String>();
		bestSequence.add("H");
		bestSequence.add("G");
		bestSequence.add("F");
		bestSequence.add("I");
		bestSequence.add("C");
		bestSequence.add("B");
		bestSequence.add("A");
		bestSequence.add("D");
		bestSequence.add("E");
		bestSequence.add("J");
		
		assertEquals(bestSequence, optimizer.makeSwaps(testSequence1));
	}
	
	
	@Test
	public void makeBestSwapsTest() throws Exception {

		ArrayList<String> bestSequence = new ArrayList<String>();
		bestSequence.add("B");
		bestSequence.add("G");
		bestSequence.add("E");
		bestSequence.add("C");
		bestSequence.add("A");
		bestSequence.add("I");
		bestSequence.add("J");
		bestSequence.add("D");
		bestSequence.add("H");
		bestSequence.add("F");
		
		assertEquals(bestSequence, optimizer.makeBestSwap(testSequence2));		
	}
	
	@Test
	public void getTotalProductionTime() throws Exception {

		assertEquals(168, optimizer.getTotalProductionTime(testSequence1));		
	}
	
}
