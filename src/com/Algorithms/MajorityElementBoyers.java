package com.Algorithms;


/**
 * Split the array into pairs of 2 elements.
 * For each pair, if they are the same element, keep one of the elements 
 * to add to a new array; otherwise, throw away both elements in the pair. 
 * (If there is an “extra,” keep it to the next round.) The claim is that 
 * the majority element, should one exist, survives the culling 
 * (rejection) process and is therefore the remaining element after the 
 * process is complete.
 * Time Complexity O(2N)
 * But for some special arrangement of elements this algorithms detects false Majority
 * Element. i.e. "3,3,3,9,9,0,0,9,9,3,0,3,3" -> This algorithm detects 3 as majority 
 * but we can see its not the case.
 * This occurs as 3 is arranged in some special manner 
 */

public class MajorityElementBoyers {

	public static void main(String[] args) {
		int[] intArray = {8,8,8,7,7,5,5,7,7,7,5,7,7};
		int currentCandidate = 0;
		int counter = 0;
		
		
		/**
		 * currentElement and currentCandidate are 2 elements in pair.
		 * Increment the counter until the currentCandidate is same as 
		 * currentElement. If they are not same decrement the counter
		 * and when counter is zero, currentCandiate is changed to value
		 * of currentElement.
		 * The value of currentCandidate at end of loop is the Majority Element*/
		
		for (int i = 0; i < intArray.length; i++) {
			int currentElement = intArray[i];
			if(counter == 0){
				currentCandidate = currentElement;						
			}
			if(currentElement == currentCandidate){
				counter++;
			}else{
				counter--;
			}
		}
		System.out.println(currentCandidate);
	}
}
