package com.DataStructure.QuickSort;

class ArraysImpl{
	private int[] theArray; 
	private int nElems;
	
	public ArraysImpl(int max){
		theArray = new int[max];
		nElems = 0;
	}

	public void insert(int value){
		theArray[nElems] = value;
		nElems++;
	}
	
	private void swapInArray(int left, int right){
		int temp = theArray[left];
		theArray[left] = theArray[right];
		theArray[right] = temp;
	}
	
	private void manualSort(int left, int right){
		int size = right - left +1;
		
		if(size == 1){
			swapInArray(left, right);
			return;
		}else if(size == 2){
			if(theArray[left] > theArray[right]){
				swapInArray(left, right);
			}
			return;
		}else{
			int center = right-1;
			if(theArray[left] > theArray[center]){
				swapInArray(left, center);
			}
			if(theArray[left] > theArray[right]){
				swapInArray(left, right);
			}
			if(theArray[center] > theArray[right]){
				swapInArray(center, right);
			}
		}
	}
	
	private int partitionIt(int left, int right, int pivot){
		int leftPtr = left;
		int rightPtr = right-1;		//Left of pivot
		
		while(true){
			while(theArray[++leftPtr] < pivot){
				;
			}
			while(theArray[--rightPtr] > pivot){
				;
			}
			
			if(leftPtr >= rightPtr){
				break;
			}else{
				swapInArray(leftPtr, rightPtr);
			}
		}
		swapInArray(leftPtr, right-1);
		return leftPtr;
	}
	
	private int medianOfThree(int left, int right){
		int center = (left + right) / 2;
		
		if(theArray[left] > theArray[center]){
			swapInArray(left, center);
		}
		if(theArray[left] > theArray[right]){
			swapInArray(left, right);
		}
		if(theArray[center] > theArray[right]){
			swapInArray(center, right);
		}
		
		swapInArray(center, right - 1);
		
		return theArray[right-1];
	}
	
	private void recursiveQuickSort(int left, int right){
		int size = right - left + 1;
		
		if(size <= 3){
			manualSort(left, right);
		}else{
			int medianAsPivot = medianOfThree(left, right);
			int partition = partitionIt(left, right, medianAsPivot);
			
			recursiveQuickSort(left, partition - 1);
			recursiveQuickSort(partition + 1, right);
		}
	}
	
	public void quickSortMedian(){
		recursiveQuickSort(0, nElems - 1);
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


public class QuickSortMedianOf3 {

	public static void main(String[] args) {
		int maxSize = 20;
		ArraysImpl array = new ArraysImpl(maxSize);
		for(int j=0; j<maxSize; j++){
			int n = (int)(java.lang.Math.random()*99);
			array.insert(n);
		}
		array.display();
		array.quickSortMedian();
		System.out.println();
		array.display();
	}

}
