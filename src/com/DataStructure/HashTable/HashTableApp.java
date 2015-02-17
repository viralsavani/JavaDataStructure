package com.DataStructure.HashTable;

class HashDataItemLinearProbe{
	public int key;
	public int data;
	
	public HashDataItemLinearProbe(int key, int data){
		this.key = key;
		this.data = data;
	}
}

class HashTableLinearProbe{
	HashDataItemLinearProbe[] hashArray;		// array holds hash table
	int arraySize;
	HashDataItemLinearProbe nonItem;			// Deleted item stored here
	
	public HashTableLinearProbe(int arraySize){
		this.arraySize = arraySize;
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
	
	public void insert(HashDataItemLinearProbe item){
		int newKey = item.key;
		int hashVal = hashFunction(newKey);
		
		while(hashArray[hashVal] != null && hashArray[hashVal].key != -1){
			++hashVal;
			hashVal = hashVal % arraySize;
		}
		hashArray[hashVal] = item;
	}
	
	
	
}

public class HashTableApp {

	public static void main(String[] args) {


	}

}
