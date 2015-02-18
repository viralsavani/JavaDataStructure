package com.DataStructure.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class HashDataItemLinearProbe{
	public int key;
	public int data;
	
	public HashDataItemLinearProbe(int key, int data){
		this.key = key;
		this.data = data;
	}
}

/**
 * Linear probing approach to solve problem of same hash value for different key suffers
 * from the problem of clustering, as the size of cluster grows the efficiency of Hash Table
 * decreases dramatically.
 * This problem of clustering can be overcome with help of double hashing.
 */
class HashTableLinearProbe{
	private HashDataItemLinearProbe[] hashArray;		// array holds hash table
	private int arraySize;
	private HashDataItemLinearProbe nonItem;			// Deleted item
	
	public HashTableLinearProbe(int arraySize){
		this.arraySize = arraySize;
		hashArray = new HashDataItemLinearProbe[arraySize];
		nonItem = new HashDataItemLinearProbe(-1, -1);
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
	
	private int hashFunction(int key){
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
	
	public HashDataItemLinearProbe delete(int key){
		int hashVal = hashFunction(key);
		HashDataItemLinearProbe deletedDataItem;
		
		while(hashArray[hashVal] != null){
		
			if(hashArray[hashVal].key == key){
				deletedDataItem = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return deletedDataItem;
			}
			++hashVal;
			hashVal = hashVal % arraySize;
		}		
		return null;
	}
	
	public HashDataItemLinearProbe find(int key){
		int hashVal = hashFunction(key);
		
		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].key == key){
				return hashArray[hashVal];
			}
			++hashVal;
			hashVal = hashVal % arraySize;
		}
		return null;
	}
	
	
}

public class HashTableApp {

	public static void main(String[] args)throws Exception {
		HashDataItemLinearProbe aDataItem;
		int aKey;
		int aData;
		int size;
		int n;
		int keysPerCell;
		
		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();
		
		keysPerCell = 10;
		
		HashTableLinearProbe theHashTable = new HashTableLinearProbe(size);
		
		for(int i = 0; i < n; i++) {
			aKey = (int)(java.lang.Math.random() * keysPerCell * size);
			aData = new Random().nextInt(100);
			aDataItem = new HashDataItemLinearProbe(aKey, aData);
			theHashTable.insert(aDataItem);
		}
		
		while(true) // interact with user
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
				aKey = getInt();
				putText("Enter data value to insert: ");
				aData = getInt();
				aDataItem = new HashDataItemLinearProbe(aKey, aData);
				theHashTable.insert(aDataItem);
				break;
				
			case 'd':
				putText("Enter key value to delete: ");
				aKey = getInt();
				theHashTable.delete(aKey);
				break;
				
			case 'f':
				putText("Enter key value to find: ");
				aKey = getInt();
				aDataItem = theHashTable.find(aKey);
				if(aDataItem != null){
					System.out.println("Found " + aKey);
				}
				else{
					System.out.println("Could not find " + aKey);
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
