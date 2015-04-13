package com.Algorithms;

import java.util.Arrays;
import java.util.Random;

//
public class FindingKthLargest {

	/**
	 * Stats while using sorting.
	 * Total Time for array of 100000000 :: 38057 milliseconds
	 */
	
	public static int kthLargestSorting(int[] array, int kthPosition){
		Arrays.sort(array);
		return array[array.length-1-kthPosition];
	}

	public static void displayArray(int[] array){
		System.out.println(Arrays.toString(array));
	}
	
	public static void main(String[] args) {		
		int[] intArray = new int[10];
		int kthPosition = 8;
		int kthLargestElement = 0;
		
		for(int i = 0; i < intArray.length; i++){
			intArray[i] = new Random().nextInt(100);
		}
		displayArray(intArray);
		kthLargestElement = kthLargestSorting(intArray, kthPosition);
		System.out.println(kthPosition+"th Largest Element :: "+kthLargestElement);
	}

}
