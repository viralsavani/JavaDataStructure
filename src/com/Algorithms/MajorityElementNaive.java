package com.Algorithms;

import java.util.Random;


/**
 * This is naive method to find Majority element where time complexity 
 * is O(N^2).
 * Only problem is that not every objects are comparable and sortable.
 */


public class MajorityElementNaive {

	public static void main(String[] args) {
		int[] intArray = new int[100001];
		int maxElement = 0;

		/**
		 * Creating an array with 9 as majority element
		 * i.e. placing 9 in every alternate position
		 */

		for(int i = 0; i < intArray.length; i++){
			if(i%2 == 0){
				intArray[i] = 9;
			}else{
				intArray[i] = new Random().nextInt(3);
			}
		}


		/**
		 * Printing a array
		 */

//		for (int i : intArray) {
//			System.out.print(i+" ");
//		}
//		System.out.println();

		/**
		 * counter variable stores occurrence of each "holder" with 
		 * "tally". We should only look up to half of the array
		 * Thus if no majority is found in first half there is no
		 * way we could find it in second half as majority element
		 * need to appear half+1 times.
		 */

		
		for (int i = 0; i < intArray.length/2+1; i++) {
			int counter = 0;
			int holder = intArray[i];
			for (int j = i; j < intArray.length; j++) {
				int tally = intArray[j];
				if(holder == tally){
					counter++;
					if(counter > intArray.length/2){
						maxElement = intArray[i];
						break;
					}
				}
			}
		}
		System.out.println("Max Element :: " + maxElement);
		
//		Max Element :: 9
//		Time taken in ms :: 7599
//		Input Size: 100001
	}

}
