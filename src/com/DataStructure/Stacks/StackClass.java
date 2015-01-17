package com.DataStructure.Stacks;

class StackImplementation{
	int maxSize;
	int [] stack;
	int top;
	
	/* Replacing int with char we can use
	 * stack implementation to reverse a word
	 * */
	
	public StackImplementation(int size) {
		maxSize = size;
		stack = new int[size];
		top = -1;
	}
	
	public void push(int data) {
		if(! isFull()){
			stack[++top] = data;
		}else{
			System.out.println("Cannot push data Stack is Full");
		}
	}
	
	public int pop() {
		if(!isEmpty()){
			int data = stack[top--];
			return data;
		}else{
			return -1;
		}
	}
	
	public int peek() {
		return stack[top];
	}
	
	public boolean isFull() {
		if(top == maxSize-1)
			return true;
		return false;
	}
	
	public boolean isEmpty() {
		if(top == -1)
			return true;
		return false;
	}
}


public class StackClass {

	public static void main(String[] args) {
		
		StackImplementation stack = new StackImplementation(7);
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		//stack.push(8); Error as size == full
		
		System.out.println(stack.peek());
		
		
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+"\t");
		}
		System.out.println();
	}
}

