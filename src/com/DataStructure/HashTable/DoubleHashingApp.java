package com.DataStructure.HashTable;

class HashDataItemDoubleHashing{
	public int key;
	public int data;
	
	public HashDataItemDoubleHashing(int key, int data){
		this.key = key;
		this.data = data;
	}
}

class HashTableDoubleHashing{
	private HashDataItemDoubleHashing[] hashArray;
	int arraySize;
	private HashDataItemDoubleHashing nonItem;
	
	public HashTableDoubleHashing(int arraySize) {
		this.arraySize = arraySize;
		hashArray = new HashDataItemDoubleHashing[arraySize];
		nonItem = new HashDataItemDoubleHashing(-1,-1);
	}
	
	public void displayTable(){
		System.out.print("Table: ");
		
		for(int i=0; i<arraySize; i++){
			
			if(hashArray[i] != null)
				System.out.print(hashArray[i].key+ "->"+hashArray[i].data+" ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}
	
	public int hashFunction(int key){
		return key % arraySize; 
	}
	
	public int doubleHashFunction(int key){
		// Array size must be relatively prime to 5, 4, 3 and 2
		return 5 - key % 5;
	}
	
	public void insert (int key, HashDataItemDoubleHashing newItem){
		int hashValue = hashFunction(key);
		int stepSize = doubleHashFunction(key);
		
		while(hashArray[hashValue] != null && hashArray[hashValue].key != -1){
			hashValue = hashValue + stepSize;
			hashValue = hashValue % arraySize;
		}
		hashArray[hashValue] = newItem;
	}
	
	public HashDataItemDoubleHashing delete(int key){
		int hashvalue = hashFunction(key);
		int stepSize = hashFunction(key);
		HashDataItemDoubleHashing deletedItem;
		
		while(hashArray[hashvalue] != null){
			if(hashArray[hashvalue].key == key){
				deletedItem = hashArray[hashvalue];
				hashArray[hashvalue] = nonItem;
				return deletedItem;
			}
			hashvalue = hashvalue + stepSize;
			hashvalue = hashvalue % arraySize;
		}
		return null;
	}
}

public class DoubleHashingApp {

}
