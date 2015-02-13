package com.DataStructure.QuickSort;

import java.util.ArrayList;

/**
 * Here worst case only if all the elements in array
 * are inversely sorted or the pivot is chosen in such
 * a way that lead to partition of size N and N-1.
 * To remove such inefficiency read QuickSortMedianOf3.java
 */

class ArrayImp{
	private int[] theArray; 
	private int nElems;

	public ArrayImp(int max){
		theArray = new int[max];
		nElems = 0;
	}

	public void insert(int value){
		theArray[nElems] = value;
		nElems++;
	}

	private int partitionIt(int left, int right, int pivot){
		int leftPtr = left-1;
		int rightPtr = right;
		int temp;

		while(true){
			while(leftPtr < right && theArray[++leftPtr] < pivot){
				; //No Operation
			}
			while(rightPtr > left && theArray[--rightPtr] > pivot){
				; //No Operation
			}

			if(leftPtr >= rightPtr){
				break;
			}else{
				// Swap Elements
				temp = theArray[rightPtr];
				theArray[rightPtr] = theArray[leftPtr];
				theArray[leftPtr] = temp;
			}
		}

		/**
		 * At the end of each scan, the leftScan variable ends up 
		 * pointing to the partition—that is, the left element of 
		 * the right SubArray. The pivot is then swapped with the 
		 * partition to put the pivot in its proper place
		 */

		temp = theArray[right];
		theArray[right] = theArray[leftPtr];
		theArray[leftPtr] = temp;

		return leftPtr;
	}

	private void recursiveQuickSort(int left, int right){
		if(right - left < 0){
			return;
		}else{
			int pivot = theArray[right];

			/**
			 * algorithm partitions the array into two parts, 
			 * then sorts each of these parts by partitioning 
			 * it into two parts, and so on, creating smaller 
			 * and smaller sub arrays
			 */
			
			int partition = partitionIt(left, right, pivot);
			recursiveQuickSort(left, partition-1);		//Sort Left SubArray
			recursiveQuickSort(partition+1, right);		//Sort Right SubArray
		}
	}

	public void quickSort(){
		recursiveQuickSort(0, nElems-1);
	}

	public int size(){ 
		return nElems; 
	}

	public void display(){
		System.out.print("Array -> ");
		for(int i=0; i<nElems; i++){
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}
}


class QuickSortFinal {
	public static void main(String[] args) {
		int maxSize = 20;
		ArrayImp array = new ArrayImp(maxSize);
		for(int j=0; j<maxSize; j++){
			int n = (int)(java.lang.Math.random()*99);
			array.insert(n);
		}
		array.display();
		array.quickSort();
		array.display();		
	}
}
