package com.DataStructure.QuickSort;


/**
 * This is a very basic partitioning scheme that will be
 * a part of Quick Sort. Here we are selecting a pivot
 * element statically and is really not much effective
 * But in Quick Sort clever pivot selection is needed.
 */

class ArrayImplementation{
	private double[] theArray; 
	private int nElems;
	
	public ArrayImplementation(int max){
		theArray = new double[max];
		nElems = 0;
	}
	
	public void insert(double value){
		theArray[nElems] = value;
		nElems++;
	}
	
	public int size(){ 
		return nElems; 
	}
	
	public void display(){
		System.out.print("A -> ");
		for(int j=0; j<nElems; j++){
			System.out.print((int)theArray[j] + " ");
		}
		System.out.println("");
	}
	
	public int partitionIt(int left, int right, double pivot){
		
		int leftPtr = left-1;
		int rightPtr = right + 1;
		
		/**
		 * It is tempting to replace No Operation inside while with the
		 * increment operation but it will result in increment only if the
		 * condition is matched. We are needed to move the left and 
		 * right pointer no matter what is the case.
		 */
		
		while(true){
			while(leftPtr < right && theArray[++leftPtr] < pivot){
				;	// No Operation
			}
			
			while(rightPtr > left && theArray[--rightPtr] > pivot){
				;	// No Operation
			}
			
			if(leftPtr >= rightPtr){
				break;
			}else{
				// Swap Positions
				double temp = theArray[leftPtr];
				theArray[leftPtr] = theArray[rightPtr];
				theArray[rightPtr] = temp;
			}	
		}		
		return leftPtr;
	}
}

class ArrayPartitioning {

	public static void main(String[] args) {
		int maxSize = 10; // array size
		ArrayImplementation array = new ArrayImplementation(maxSize);
		
		for(int j=0; j<maxSize; j++){ 
			double n = (int)(java.lang.Math.random()*99);
			array.insert(n);
		}
		
		array.display();
		
		int pivot = 50;
		System.out.print("Pivot is " + pivot);
		
		int size = array.size();
		
		int partDex = array.partitionIt(0, size-1, pivot);
		System.out.println(", Partition is at index " + partDex);
		array.display();
	}

}
