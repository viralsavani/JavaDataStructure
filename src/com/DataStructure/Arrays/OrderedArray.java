package com.DataStructure.Arrays;

class OrderedarrayayImplementation{
	
	private int[] array;
	private int nElems;
	
	public OrderedarrayayImplementation(int size){
		array = new int[size];
		nElems = 0;
	}
	
	public int size(){
		return nElems;
	}
	
	public int findBinarySearch(int elementFind){
		int lowerBound = 0;
		int upperBound = nElems-1;
		int currentPosition;
		
		while(true){
			currentPosition = ((lowerBound + upperBound) / 2);
			if(array[currentPosition] == elementFind){
				return currentPosition;
			}else if(lowerBound >= upperBound){
				return nElems;
			}else{
				if(array[currentPosition] < elementFind){
					lowerBound = currentPosition + 1;
				}else{
					upperBound = currentPosition - 1;
				}
			}
		}
	}
	
	public void insert(int element){
		
		//Linear search to find where element should be inserted
		int i;
		for(i = 0; i < nElems; i++){
			if(array[i] > element){
				break;
			}
		}
		
		//Push down all the elements greater than "element" one step down
		for(int j = nElems; j > i; j--){
			array[j] = array[j-1];
		}
		array[i] = element;
		nElems++;
	}
	
	public boolean delete(int value)
	{
		int j = findBinarySearch(value);
		if(j==nElems)
			return false;
		else 
		{
			for(int k=j; k<nElems; k++) 
				array[k] = array[k+1];
			nElems--; 
			return true;
		}
	}
	
	public void display()
	{
		for(int j=0; j<nElems; j++) 
			System.out.print(array[j] + " ");
		System.out.println("");
	}
	
}


public class OrderedArray {

	public static void main(String[] args) {
		int maxSize = 100;
		
		OrderedarrayayImplementation array = new OrderedarrayayImplementation(maxSize);
		
		array.insert(77); 
		array.insert(99);
		array.insert(44);
		array.insert(55);
		array.insert(22);
		array.insert(88);
		array.insert(11);
		array.insert(00);
		array.insert(66);
		array.insert(33);
		
		int searchKey = 55;
		
		if( array.findBinarySearch(searchKey) != array.size()){
			System.out.println("Found " + searchKey);
		}else{
			System.out.println("Can't find " + searchKey);
		}
		
		array.display();
		
		array.delete(00); 
		array.delete(55);
		array.delete(99);
		
		array.display();
	}

}
