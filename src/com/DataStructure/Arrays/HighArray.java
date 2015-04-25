package com.DataStructure.Arrays;

import java.util.Arrays;

class ArrayImplementation
{
	private int[] a; 
	private int nElems; 


	public ArrayImplementation(int max){
		a = new int[max]; 
		nElems = 0; 
	}


	public boolean find(double searchKey){ 
		int j;
		for(j=0; j<nElems; j++){ 
			if(a[j] == searchKey){ 
				break; 
			}
		}
		if(j == nElems) 
			return false; 
		else
			return true; 
	} 


	public void insert(int value){
		a[nElems] = value; 

		nElems++; 
	}


	public boolean delete(int value){
		int j;
		for(j=0; j<nElems; j++) 
			if( value == a[j] )
				break;
		if(j==nElems) 
			return false;
		else 
		{
			for(int k=j; k<nElems; k++) 
				a[k] = a[k+1];
			nElems--; 
			return true;
		}
	} 

	public void bubbleSort(){
		for(int out = nElems-1; out > 1; out--){
			for(int inner = 0; inner < out; inner++){
				if(a[inner] > a[inner + 1]){
					int temp = a[inner];
					a[inner] = a[inner + 1];
					a[inner + 1] = temp;
				}
			}
		}
	}

	public void selectionSort(){
		int minPosition;
		for(int out = 0; out < nElems-1; out++){
			minPosition = out;
			for(int inner = out + 1; inner < nElems; inner++){
				if(a[inner] < a[minPosition]){
					minPosition = inner;
				}
			}
			int swapTemp = a[minPosition];
			a[minPosition] = a[out];
			a[out] = swapTemp;
		}
	}

	public void insertionSort(){
		for(int out = 1; out < nElems; out++){
			int temp = a[out];
			int in = out;

			while(in > 0 && a[in-1] >= temp){
				a[in] = a[in-1];
				--in;
			}
			a[in] = temp;
		}
	}
		
	public void shellSort(){
		int inner;
		int outer;
		int temp;
		
		int h = 1;
		while(h <= nElems / 3){
			h = h * 3 + 1;
		}
		
		while(h > 0){
			for(outer = h; outer < nElems; outer++){
				temp = a[outer];
				inner = outer;
				
				while(inner > h-1 && a[inner - h] > temp){
					a[inner] = a[inner - h];
					inner = inner - h;
				}
				a[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}

	
	public int binarySearch(int elementFind){
		int lowerBound = 0;
		int upperBound = nElems-1;
		int currentPosition;

		while(true){
			currentPosition = ((lowerBound + upperBound) / 2);
			if(a[currentPosition] == elementFind){
				return currentPosition;
			}else if(lowerBound >= upperBound){
				return nElems;
			}else{
				if(a[currentPosition] < elementFind){
					lowerBound = currentPosition + 1;
				}else{
					upperBound = currentPosition - 1;
				}
			}
		}
	}

	public void display(){

		for(int j=0; j<nElems; j++) 
			System.out.print(a[j] + " "); 
		System.out.println("");
	}	
} 

class HighArray
{
	public static void main(String[] args)
	{
		int maxSize = 100; 
		ArrayImplementation array = new ArrayImplementation(maxSize);

        int[] arry = new int[100];
        Arrays.sort(arry);


		array.insert(77);
		array.insert(99);
		array.insert(44);
		array.insert(55);
		array.insert(7);
		array.insert(73);
		array.insert(22);
		array.insert(88);
		array.insert(11);
		array.insert(00);
		array.insert(66);
		array.insert(33);

		array.display(); 

		int searchKey = 35; 

		if( array.find(searchKey) )
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);

		array.delete(00);
		array.delete(55);
		array.delete(99);
		array.display();

		System.out.println("BUBBLE SORT:: ");
		array.bubbleSort();
		array.display();

		System.out.println("SELECTION SORT:: ");
		array.selectionSort();
		array.display();
		
		System.out.println("INSERTION SORT:: ");
		array.insertionSort();
		array.display();
		
		System.out.println("Shell SORT:: ");
		array.shellSort();
		array.display();

		System.out.println("Binary Search 88 -> "+array.binarySearch(88));
	} 
} 