package com.Algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * This algorithm runs at most efficiently O(nlogn) which
 * is way more less than O(N^2).
 */

public class MajorityElementOptimal {

	public static void main(String[] args) {
		//int[] intArray = {1,6,2,6,3,6,4,6,5,6,6,7,8,6,9,6,6,6};
		int[] intArray = new int[100001];

		for(int i = 0; i < intArray.length; i++){
			if(i%2 == 0){
				intArray[i] = 7;
			}else{
				intArray[i] = new Random().nextInt(3);
			}
		}

		Arrays.sort(intArray);

		int currentElement = intArray[0];
		int elementCounter = 0;

		for (int i = 0; i < intArray.length/2+1; i++) {
			if(currentElement == intArray[i]){
				elementCounter++;
				if(elementCounter > intArray.length/2){
					break;
				}
			}else{
				currentElement = intArray[i];
				elementCounter = 0;
			}
		}
		System.out.println("Maximum is :: "+currentElement);
		
//		Maximum is :: 7
//		Time taken in ms :: 16
//		Input Size: 100001
	}

}
