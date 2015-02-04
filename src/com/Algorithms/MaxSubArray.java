package com.Algorithms;

public class MaxSubArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static int[] maxCrossingSubArray(int[] array){
		
		int mid = array.length / 2;
		
		int leftSum = -999999;
		int maxLeft = 0;
		
		int maxRight = 0;
		int rightSum = -999999;
		
		int sum = 0;
		
		int[] returnArray = new int[3];
				
		for(int i = mid; i >= 0; i--){
			sum = sum + array[i];
			if(sum > leftSum){
				leftSum = sum;
				maxLeft = i;
			}
		}
		
		sum = 0;
		
		for(int i = mid+1; i < array.length; i++){
			sum = sum + array[i];
			if(sum > rightSum){
				rightSum = sum;
				maxRight = i;
			}
		}
		
		returnArray[0] = maxLeft;
		returnArray[1] = maxRight;
		returnArray[3] = leftSum+rightSum;
		
		return null;
	}
	
	

}
