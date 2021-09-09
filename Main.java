import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	public static void main(String [] args) {
		SequenceOptmizer optimizer = new SequenceOptmizer();
		
		optimizer.initializeChangeOverMatrix();
		optimizer.initializeTimeToProduceMatrix();
		ArrayList<String> sequence = new ArrayList<String>();
		
		sequence.add("H");
		sequence.add("F");
		sequence.add("G");
		sequence.add("I");
		sequence.add("C");
		sequence.add("A");
		sequence.add("B");
		sequence.add("D");
		sequence.add("E");
		sequence.add("J");
		
		ArrayList<String> optimizedSequence = optimizer.makeSwaps(sequence);
		System.out.println("The optimized list order is: ");
		for(String i : optimizedSequence) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println("Production time of optimized sequence is: "+optimizer.getTotalProductionTime(optimizedSequence));
	}
} 
