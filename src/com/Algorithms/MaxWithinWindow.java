package com.Algorithms;

import java.util.Random;



/**
 * Problem Definition :: Given an array ar[] of length �n� and an integer �k� 
 * 						 such that k < n. You need to maintain a window of size 'k' 
 * 						 starting from i=0 to i=k and print the MAX of that segment. 
 * 						 The window moves forward by 1 element.
 * windowSize must be >=1
 * windowMax
 */


public class MaxWithinWindow {

	public static void main(String[] args) {
		
		int[] array = new int[22];
		int windowSize = 7;
		int windowMax = -1;
		
		for(int i = 0; i < array.length; i++){
			array[i] = new Random().nextInt(100);
		}
		
		for(int i = 0; i < array.length-windowSize+1; i++){
			windowMax = 0;			
			for(int j = i; j < i+windowSize; j++){
				System.out.print(array[j]+" ");
				if(array[j] > windowMax){
					windowMax = array[j];
				}
			}
			System.out.println("\t Max in Iteration "+i+" :: "+windowMax);
		}
	}
}
