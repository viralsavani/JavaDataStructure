package com.Recursion;

class Params{
	public int n;
	public int codePart;
	
	public Params(int nn, int ra){
		n = nn;
		codePart = ra;
	}
}

class StackX{
	private int maxSize;
	private Params[] stackArray;
	private int top;
	
	public StackX(int maxSize){
		this.maxSize = maxSize;
		stackArray = new Params[maxSize];
		top = -1;
	}
	
	public void push(Params p){
		stackArray[++top] = p;
	}
	
	public Params pop(){
		return stackArray[top--];
	}
	
	public Params peek(){
		return stackArray[top];
	}
}

public class StackTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
