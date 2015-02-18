package com.Algorithms;

import java.util.Random;

public class MaxWithinWindow3Cases {

	public static void main(String[] args) {

		int[] array = new int[15];
		int windowMax = -1;
		int windowMaxIndex = 0;
		int windowSize = 3; 
		windowSize--;

		/**
		 * Inserting random elements in array who's values in not more than
		 * 100
		 */

		for(int i = 0; i < array.length; i++){
			array[i] = new Random().nextInt(100);
		}

		/**
		 * Pretty printing array for accessibility.
		 */
		for(int i = 0; i < array.length; i++){
			System.out.print(i+"\t");
		}
		System.out.println();
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i]+"\t");
		}
		System.out.println();
		System.out.println();

		System.out.println("Window Size :: "+(windowSize+1));


		/**
		 * For first case when i=0 use brute force
		 */
		for(int i = 0; i < windowSize; i++){
			if(array[i] > windowMax){
				windowMax = array[i];
				windowMaxIndex = i;
			}
		}
		System.out.println("Window\t0->"+(windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);



		for(int i = 1; i < array.length-windowSize; i++){

			/**
			 * Check if the current maximum element is still in the window.
			 */
			if(windowMaxIndex >= i){

				/**
				 * If Current maximum element is still in the window. Then check if
				 * the new values i.e. new window's last element is greater than current
				 * maximum value.
				 * If yes then swap values and change assign max element index as per.
				 */
				if(array[windowMaxIndex] <= array[i+windowSize]){
					windowMax = array[i+windowSize];
					windowMaxIndex = i+windowSize;
					System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
				}else{
					System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
				}

			}else if(array[i+windowSize] >= array[windowMaxIndex]){

				/**
				 * If Current maximum element has just left window then check if new windows
				 * last element is greater than current maximum element.
				 * If yes than that values is implicitly larger in current window
				 */
				windowMax = array[i+windowSize];
				windowMaxIndex = i+windowSize;
				System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
			}else{

				/**
				 * If current maximum values in not new window and the last element of new 
				 * window is not larger than current maximum element, perform brute force
				 * and compare every element sequentially to find maximum
				 */
				windowMax = -1;
				windowMaxIndex = -1;
				for(int j = i; j <= i+windowSize; j++){
					if(array[j] > windowMax){
						windowMax = array[j];
						windowMaxIndex = j;
					}
				}
				System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
			}

		}
	}
}
