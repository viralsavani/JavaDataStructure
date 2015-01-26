package com.Recursion;

class IntArray{
	private int[] array;
	private int nElems;
	
	public IntArray(int size) {
		array = new int[size];
		nElems = 0;
	}
	
	public void insert(int data){
		array[nElems++] = data;
	}
	
	public void display(){
		for(int i = 0; i < nElems; i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	public void mergerSort(){
		int[] workspace = new int[nElems];
		mergeSortImplementation(workspace, 0, nElems-1);
	}
	
	private void mergeSortImplementation(int[] workspace, int lowerBound, int upperBound){
		if(lowerBound == upperBound){
			return;
		}else{
			int mid = (lowerBound + upperBound) / 2;
			
			mergeSortImplementation(workspace, lowerBound, mid);
			
			mergeSortImplementation(workspace, mid+1, upperBound);
			
			merge(workspace, lowerBound, mid+1, upperBound);
		}
		
	}
	
	private void merge(int[] workspace, int lowPtr, int highPrt, int upperBound){
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPrt - 1;
		int n = upperBound - lowerBound +1;
		
		while(lowPtr <= mid && highPrt <= upperBound){
			if(array[lowPtr] < array[highPrt]){
				workspace[j++] = array[lowPtr++];
			}else{
				workspace[j++] = array[highPrt++];
			}
		}
		
		while(lowPtr <= mid){
			workspace[j++] = array[lowPtr++];
		}
		
		while(highPrt <= upperBound){
			workspace[j++] = array[highPrt++];
		}
		
		for(j = 0; j < n; j++){
			array[lowerBound + j] = workspace[j];
		}
	}
}

public class MergeSort {

	public static void main(String[] args) {
		int size = 20;
		
		IntArray array = new IntArray(size);
		
		array.insert(64);
		array.insert(21);
		array.insert(33);
		array.insert(70);
		array.insert(12);
		array.insert(85);
		array.insert(44);
		array.insert(3);
		array.insert(99);
		array.insert(0);
		array.insert(108);
		array.insert(36);
		
		array.display();
		
		array.mergerSort();
		
		array.display();
	}

}
