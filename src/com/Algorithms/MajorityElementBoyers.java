package com.Algorithms;

public class MajorityElementBoyers {

	public static void main(String[] args) {
		int[] intArray = {8,8,8,7,7,5,5,7,7,7,5,7,7};
		int currentCandidate = 0;
		int counter = 0;
		
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
