package com.Algorithms;

public class MaxSubArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] inputArray = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		
//		int[] returnArray = maxSubArray(inputArray, 0, inputArray.length-1);
//		
//		for (int i = 0; i < returnArray.length; i++) {
//			int j = returnArray[i];
//			System.out.println(j);
//		}
		
		System.out.println(FindMaxSumSubArray(inputArray, 0, inputArray.length-1));
	}
	
	private static int[] maxCrossingSubArray(int[] array, int low, int mid, int high){		
		int leftSum = -999999;
		int maxLeft = 0;
		
		int maxRight = 0;
		int rightSum = -999999;
		
		int sum = 0;
		
		int[] returnArray = new int[3];
				
		for(int i = mid; i >= low; i--){
			sum = sum + array[i];
			if(sum > leftSum){
				leftSum = sum;
				maxLeft = i;
			}
		}
		
		sum = 0;
		
		for(int i = mid+1; i <= high; i++){
			sum = sum + array[i];
			if(sum > rightSum){
				rightSum = sum;
				maxRight = i;
			}
		}
		
		
		/**
		 * Return array consists of maxLeft, maxRight and
		 * maximum sum i.e. leftSum + rightSum at
		 * 0th, 1st and 2nd position respectively
		 */
		
		returnArray[0] = maxLeft;
		returnArray[1] = maxRight;
		returnArray[2] = leftSum+rightSum;
		
		return null;
	}
	
	private static int[] maxSubArray(int[] array, int low, int high){
		
		int mid = array.length / 2;
		
		if(array.length == 1){
			return array;
		}else{
			/** Array notations.
			 * int[] array = [array-low, array-high, array-sum]
			 * int[] leftArray = [leftArray-low, leftArray-high, leftArray-sum]
			 * Same for rest of the arrays.
			 */
			
			int[] leftArray = new int[20]; 
			leftArray = maxSubArray(array, 0, mid);
			
			int[] rightArray = new int[20]; 
			rightArray = maxSubArray(array, mid+1, array.length-1);
			
			int[] crossArray = new int[20]; 
			rightArray = maxCrossingSubArray(array, low, mid, high);
			
			/**
			 * if (left-sum >= right-sum && left-sum >= cross-sum)
			 * */
			if(leftArray[2] >= rightArray[2] && leftArray[2] >= crossArray[2]){
				return leftArray;
			}
			
			/**
			 * if (right-sum >= left-sum && right-sum >= cross-sum)
			 * */
			
			else if(rightArray[2] >= leftArray[2] && rightArray[2] >= crossArray[2]){
				return rightArray;
			}else{
				return crossArray;
			}
			
		}
	}
	public static int FindMaxSumSubArray(int[] array, int low, int high){

		/* No element in the array */
		if (low > high)  
			return 0;
		/* One element in the array */
		if (low == high) 
			return Math.max(0, array[low]);

		/* Middle element of the array */   
		int middle = (low + high) / 2;

		/* find maximum sum crossing to left */
		int leftMax = 0;
		int sum = 0;
		for (int i = middle; i >= low; i--) {
			sum += array[i];
			if (sum > leftMax)
				leftMax = sum;
		}

		/* find maximum sum crossing to right */
		int rightMax = 0;
		sum = 0;
		for (int i = middle+1; i <= high; i++) {
			sum += array[i];
			if (sum > rightMax)
				rightMax = sum;
		}

		/* Return the maximum of leftMax, rightMax and their sum */
		return Math.max(leftMax + rightMax, 
				Math.max(FindMaxSumSubArray(array, low, middle), FindMaxSumSubArray(array, middle+1, high)));
	}

}
