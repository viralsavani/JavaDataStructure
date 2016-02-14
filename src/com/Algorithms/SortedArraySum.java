package com.Algorithms;

import java.util.Arrays;

public class SortedArraySum {

	public static boolean isSum(int[] array, int sum){
		if (array.length == 0 || array == null){
			return false;
		}
		Arrays.sort(array);
		int startPointer = 0;
		int endPointer = array.length-1;
		
		while(startPointer <= endPointer){
			int tempSum = array[startPointer] + array[endPointer];
			if (tempSum == sum){
				System.out.println("Found: "+array[startPointer]+" + "+array[endPointer]);
				return true;
			}else if (tempSum < sum){
				startPointer++;
			}else{
				endPointer--;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] array = {7,13,18,29,42,45};
		System.out.println(isSum(array, 29));
	}

}
