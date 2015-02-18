package com.DataStructure.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

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

	public HashDataItemDoubleHashing find(int key){
		int hashValue = hashFunction(key);
		int stepSize = doubleHashFunction(key);

		while(hashArray[hashValue] != null){
			if(hashArray[hashValue].key == key){
				return hashArray[hashValue];
			}
			hashValue = hashValue + stepSize;
			hashValue = hashValue % arraySize;
		}
		return null;
	}
}

public class DoubleHashingApp {

	public static void main(String[] args)throws Exception{
		int key;
		int data;
		HashDataItemDoubleHashing dataItem;
		int size;
		int n;

		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();

		HashTableDoubleHashing theHashTable = new HashTableDoubleHashing(size);

		for(int j=0; j<n; j++) {
			key = (int)(java.lang.Math.random() * 2 * size);
			data = new Random().nextInt(100);
			dataItem = new HashDataItemDoubleHashing(key,data);
			theHashTable.insert(key, dataItem);
		}

		while(true)
		{
			putText("Enter first letter of ");
			putText("show, insert, delete, or find: ");
			char choice = getChar();
			switch(choice)
			{
			case 's':
				theHashTable.displayTable();
				break;

			case 'i':
				putText("Enter key value to insert: ");
				key = getInt();
				putText("Enter data value to insert: ");
				data = getInt();
				dataItem = new HashDataItemDoubleHashing(key, data);
				theHashTable.insert(key,dataItem);
				break;

			case 'd':
				putText("Enter key value to delete: ");
				key = getInt();
				dataItem = theHashTable.delete(key);
				if(dataItem != null){
					System.out.println("Deleted " + key);
				}
				else{
					System.out.println("Invalid key for deletion " + key);
				}
				break;

			case 'f':
				putText("Enter key value to find: ");
				key = getInt();
				dataItem = theHashTable.find(key);
				if(dataItem != null){
					System.out.println("Found " + key);
				}
				else{
					System.out.println("Could not find " + key);
				}
				break;

			default:
				putText("Invalid entry\n");
			}
		}	
	}


	private static void putText(String s){
		System.out.println(s);
		System.out.flush();
	}

	private static String getString() throws IOException{
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputStreamReader);
		return br.readLine();
	}

	private static char getChar() throws IOException{
		String s = getString();
		return s.charAt(0);
	}

	private static int getInt() throws IOException{
		String s = getString();
		return Integer.parseInt(s);
	}

}
