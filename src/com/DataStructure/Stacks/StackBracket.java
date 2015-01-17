package com.DataStructure.Stacks;

class BracketImplementation{
	
	String input;
	
	public BracketImplementation(String input) {
		this.input = input;
	}
	
	public boolean checkBracket() {
		
		StackImplementation stack = new StackImplementation(input.length());
		
		for(int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
			
			switch (c) {
			case '[':
			case '{':
			case '(':
				stack.push(c);
				break;
			
			case ']':
			case '}':
			case ')':
				if(!stack.isEmpty()){
					char cPop = (char) stack.pop();
					if((c == '}' && cPop != '{') || (c == ')' && cPop != '(') || (c == ']' && cPop != '[')){
						System.out.println("ERROR IN PARENTHISIS :: "+c);
					}
				}
				else{
					System.out.println("ERROR :: "+c+"\t"+i);
				}
				break;
			default:
				break;
			}
		}
		if(!stack.isEmpty()){
			System.out.println("ERROR: Missing Right Parathensis");
		}
		
		return true;
	}
	
}

public class StackBracket {

	public static void main(String[] args) {
		String sampleString = "[{2+(5*3)}+{8*(2/3)}]";
		
		BracketImplementation sample = new BracketImplementation(sampleString);
		
		sample.checkBracket();
		

	}

}
