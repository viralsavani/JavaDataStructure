package com.Algorithms;

import java.util.Arrays;
import java.util.Random;


/**
 * Unlike a traditional sorting algorithm, which attempts to sort with 
 * the fewest comparisons possible, the goal is to sort the sequence in 
 * as few reversals as possible.
 * 
 * We one by one place maximum element at the end of array and reduce the 
 * size of current array by one. 
 */

public class PancakeSort {

	public static void main(String[] args) {
		//int[] array = {56,97,56,9,5,1,45,19,46,72,14,36,33,65,33};
		
		int[] array = new int[12];
		for(int i = 0; i < array.length; i++){
			array[i] = new Random().nextInt(100);
		}
		
		
		int maxIndex = 0;
		int length = array.length - 1;
		
		System.out.println(Arrays.toString(array));
		System.out.println();
		
		while(length > 1){
			maxIndex = getMaximumElementIndex(array, length);
			array = flipArray(array, maxIndex);
			array = flipArray(array, length);
			length--;
		}
		System.out.println(Arrays.toString(array));
	}
	
	private static int getMaximumElementIndex(int[] array, int endIndex){
		int max = 0;
		for(int i = 0; i <= endIndex; i++){
			if(array[i] > array[max]){
				max = i;
			}
		}
		return max;
	}
	
	private static int[] flipArray(int[] array, int endIndex){		
		int j = endIndex;
		int temp = 0;
		for(int i = 0; i <= endIndex / 2; i++){
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			j--;
		}
		return array;
	}

}
