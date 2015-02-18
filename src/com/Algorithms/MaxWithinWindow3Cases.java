package com.Algorithms;

import java.util.Random;

public class MaxWithinWindow3Cases {

	public static void main(String[] args) {

		int[] array = new int[15];
		int windowMax = -1;
		int windowMaxIndex = 0;
		int windowSize = 3; 
		windowSize--;

		for(int i = 0; i < array.length; i++){
			array[i] = new Random().nextInt(100);
		}
		
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
		for(int i = 0; i < windowSize; i++){
			if(array[i] > windowMax){
				windowMax = array[i];
				windowMaxIndex = i;
			}
		}

		System.out.println("Window\t0->"+(windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);

		for(int i = 1; i < array.length-windowSize; i++){
			if(windowMaxIndex >= i){
				if(array[windowMaxIndex] <= array[i+windowSize]){
					windowMax = array[i+windowSize];
					windowMaxIndex = i+windowSize;
					System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
				}else{
					System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
				}
				
			}else if(array[i+windowSize] >= array[windowMaxIndex]){
				windowMax = array[i+windowSize];
				windowMaxIndex = i+windowSize;
				System.out.println("Window\t"+i+"->"+(i+windowSize)+"\tMaxElement :: "+windowMax+"   MaxElemIndex :: "+windowMaxIndex);
			}else{
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
