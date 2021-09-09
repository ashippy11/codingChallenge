import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SequenceOptmizer{
	
	/*
	Change Over Matrix is represented as a Hashmap of Hashmaps so that you can easily look up a Product to another Product and get the change over time
	For example if you want to find the change over from product A to product B you would use 'changeOverMatrix.get(A).get(B)'
	*/
	HashMap<String,HashMap<String,Integer>> changeOverMatrix = new HashMap<String,HashMap<String, Integer>>();
	
	//HashMap where the Product is the key and the time it takes to produce that product is the value
	HashMap<String, Integer> timeToProduce = new HashMap<String, Integer>();
	
	
	//Continue to making improving swaps greedily until no more improvements can be found
	public ArrayList<String> makeSwaps(ArrayList<String> sequence) {
		
		while(makeBestSwap(sequence)!=sequence) {
			sequence = makeBestSwap(sequence);
		}
		return sequence;
	}
	
	//Loop through all the possible adjacent swaps for a given sequence, updating the sequence when an order is found with a lower change-over time
	public ArrayList<String> makeBestSwap(ArrayList<String> sequence){
		int currentTime = getChangeOverTime(sequence);
		ArrayList<String> bestSequence = sequence;
		int bestTime = currentTime;
		for(int i = 0; i<=sequence.size()-2; i++) {
			ArrayList<String> sequenceCopy = new ArrayList<>(sequence);
			int newTime = updateChangeOverTime(currentTime, i, sequenceCopy);
			if(newTime<bestTime) {
				Collections.swap(sequenceCopy, i, i+1);
				bestTime = newTime;
				bestSequence = sequenceCopy;
			}
		}
		return bestSequence;
	}
	/* Method takes the current time the sequence passed to it takes to complete and removes the change-overs that would no longer be in needed
	 * after the swaps are made and adds the new change-overs that will be needed. If the swap was not made on the very first two elements
	 * or very last two elements swapping two adjacent products will result in 3 new change-overs
	 * By only updating the changeOverMatrix of the products whose changeOver times are being updated, we save looping through the entire
	 * product array and recalculating change overs that are not changing. 
	 */
	public int updateChangeOverTime(int current, int i, ArrayList<String> seq){
		
		int centerPair = changeOverMatrix.get(seq.get(i)).get(seq.get(i+1));
		int swappedCenterPair = changeOverMatrix.get(seq.get(i+1)).get(seq.get(i));
		current = current- centerPair + swappedCenterPair;
		//Check that first two products weren't swapped
		if(i>0) {
			int leftPair = changeOverMatrix.get(seq.get(i-1)).get(seq.get(i));
			int swappedleftPair = changeOverMatrix.get(seq.get(i-1)).get(seq.get(i+1));
			current = current- leftPair + swappedleftPair;
		}
		//Check the last two products weren't swapped
		if(i<seq.size()-2) {
			int rightPair= changeOverMatrix.get(seq.get(i+1)).get(seq.get(i+2));
			int swappedRightPair = changeOverMatrix.get(seq.get(i)).get(seq.get(i+2));
			current = current- rightPair + swappedRightPair;
		}
		
		return current;
	}

	//Calculates and returns the total change over time for all the products for a given sequence
	public int getChangeOverTime(List<String> sequence) {
		
		int totalChangeOverTime = 0;
		
		for(int i=0; i<=sequence.size()-2; i++) {
			int changeOverTime = changeOverMatrix.get(sequence.get(i)).get(sequence.get(i+1));
			totalChangeOverTime += changeOverTime;
		}
		
		return totalChangeOverTime;
	}
	
	//Calculate the total time a production sequence takes by adding the time for each product
	//to be made to the total change-over time of the sequence
	public int getTotalProductionTime(List<String> sequence) {
		int totalTime = getChangeOverTime(sequence);
		for(int i=0; i<=sequence.size()-1; i++) {
			totalTime+=timeToProduce.get(sequence.get(i));
		}
		return totalTime;
	}
	
	
	public void initializeTimeToProduceMatrix() {
		
		timeToProduce.put("A", 7);
		timeToProduce.put("B", 13);
		timeToProduce.put("C", 2);
		timeToProduce.put("D", 4);
		timeToProduce.put("E", 21);
		timeToProduce.put("F", 6);
		timeToProduce.put("G", 8);
		timeToProduce.put("H", 12);
		timeToProduce.put("I", 17);
		timeToProduce.put("J", 22);
	}
	
	public void initializeChangeOverMatrix() {
		
		HashMap<String,Integer> aHM = new HashMap<String, Integer>();
		HashMap<String,Integer> bHM = new HashMap<String, Integer>();
		HashMap<String,Integer> cHM = new HashMap<String, Integer>();
		HashMap<String,Integer> dHM = new HashMap<String, Integer>();
		HashMap<String,Integer> eHM = new HashMap<String, Integer>();
		HashMap<String,Integer> fHM = new HashMap<String, Integer>();
		HashMap<String,Integer> gHM = new HashMap<String, Integer>();
		HashMap<String,Integer> hHM = new HashMap<String, Integer>();
		HashMap<String,Integer> iHM = new HashMap<String, Integer>();
		HashMap<String,Integer> jHM = new HashMap<String, Integer>();
		
		aHM.put("B", 9);
		aHM.put("C", 13);
		aHM.put("D", 6);
		aHM.put("E", 7);
		aHM.put("F", 8);
		aHM.put("G", 3);
		aHM.put("H", 17);
		aHM.put("I", 7);
		aHM.put("J", 3);
		
		bHM.put("A", 3);
		bHM.put("C", 8);
		bHM.put("D", 7);
		bHM.put("E", 9);
		bHM.put("F", 4);
		bHM.put("G", 3);
		bHM.put("H", 16);
		bHM.put("I", 3);
		bHM.put("J", 8);
		
		cHM.put("A", 5);
		cHM.put("B", 0);
		cHM.put("D", 8);
		cHM.put("E", 7);
		cHM.put("F", 9);
		cHM.put("G", 4);
		cHM.put("H", 3);
		cHM.put("I", 16);
		cHM.put("J", 5);
		
		dHM.put("A", 7);
		dHM.put("B", 2);
		dHM.put("C", 5);
		dHM.put("E", 4);
		dHM.put("F", 2);
		dHM.put("G", 5);
		dHM.put("H", 7);
		dHM.put("I", 2);
		dHM.put("J", 5);
		
		eHM.put("A", 4);
		eHM.put("B", 3);
		eHM.put("C", 4);
		eHM.put("D", 7);
		eHM.put("F", 4);
		eHM.put("G", 3);
		eHM.put("H", 4);
		eHM.put("I", 7);
		eHM.put("J", 3);
		
		fHM.put("A", 2);
		fHM.put("B", 14);
		fHM.put("C", 7);
		fHM.put("D", 7);
		fHM.put("E", 5);
		fHM.put("G", 1);
		fHM.put("H", 4);
		fHM.put("I", 6);
		fHM.put("J", 4);
		
		gHM.put("A", 8);
		gHM.put("B", 7);
		gHM.put("C", 9);
		gHM.put("D", 4);
		gHM.put("E", 16);
		gHM.put("F", 3);
		gHM.put("H", 4);
		gHM.put("I", 9);
		gHM.put("J", 8);
		
		hHM.put("A", 11);
		hHM.put("B", 5);
		hHM.put("C", 2);
		hHM.put("D", 3);
		hHM.put("E", 2);
		hHM.put("F", 6);
		hHM.put("G", 4);
		hHM.put("I", 9);
		hHM.put("J", 7);
		
		iHM.put("A", 4);
		iHM.put("B", 2);
		iHM.put("C", 12);
		iHM.put("D", 9);
		iHM.put("E", 1);
		iHM.put("F", 5);
		iHM.put("G", 5);
		iHM.put("H", 6);
		iHM.put("J", 9);
		
		jHM.put("A", 9);
		jHM.put("B", 3);
		jHM.put("C", 6);
		jHM.put("D", 7);
		jHM.put("E", 8);
		jHM.put("F", 3);
		jHM.put("G", 17);
		jHM.put("H", 7);
		jHM.put("I", 3);
		
		changeOverMatrix.put("A", aHM);
		changeOverMatrix.put("B", bHM);
		changeOverMatrix.put("C", cHM);
		changeOverMatrix.put("D", dHM);
		changeOverMatrix.put("E", eHM);
		changeOverMatrix.put("F", fHM);
		changeOverMatrix.put("G", gHM);
		changeOverMatrix.put("H", hHM);
		changeOverMatrix.put("I", iHM);
		changeOverMatrix.put("J", jHM);
		
	}
}
