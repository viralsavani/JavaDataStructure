package com.DataStructure.Stacks;


class StringReverser{
	String input;
	String output = "";
	
	public StringReverser(String input) {
		this.input = input;
	}
	
	public String doReverse(){
		
		StackImplementation stack = new StackImplementation(input.length());
		
		for(int i = 0; i < input.length(); i++){
			char character = input.charAt(i);
			stack.push(character);
		}
		while(! stack.isEmpty()){
			output = output + (char)stack.pop();
		}
		
		return output;
	}
}

public class StackStringReverse {

	public static void main(String[] args) {
		
		String sample1 = "ABCDEFGHIJK";
		StringReverser reverse = new StringReverser(sample1);
		System.out.println(reverse.doReverse());
	}
}