package com.Recursion;

public class Anagrams {

	static int size;
	static int count;
	static char[] charArray;
	public static void main(String[] args) {

		String input = "cats";
		size = input.length();
		count = 0;
		charArray = new char[size];

		for(int i = 0 ; i < size; i++){
			charArray[i] = input.charAt(i);
		}
		doAnagram(size);
	}

	public static void doAnagram(int size) {
		if(size == 1)
			return;
		for(int i = 0; i < size; i++){
				doAnagram(size-1);
				if(size == 2)
					displayWord();
				rotate(size);
			}
	}

	public static void displayWord(){
		if(count < 99)
			System.out.print(" ");
		
		if(count < 9)
			System.out.print(" ");
		
		System.out.print(++count + "");
		
		for(int j=0; j<size; j++)
			System.out.print( charArray[j] );
		
		System.out.print("  ");
		
		if(count%6 == 0)
			System.out.println("");
	}
	
	public static void rotate(int newSize){
		int j;
		int position = size - newSize;
		char firstChar = charArray[position];
		
		for(j = position +1; j < size; j++)
			charArray[j-1] = charArray[j];
		charArray[j-1] = firstChar;
	}
}