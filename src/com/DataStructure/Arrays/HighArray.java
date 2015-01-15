package com.DataStructure.Arrays;

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
		ArrayImplementation arr = new ArrayImplementation(maxSize);
		
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		
		arr.display(); 
		
		int searchKey = 35; 
		
		if( arr.find(searchKey) )
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can't find " + searchKey);
		
		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();
	} 
} 