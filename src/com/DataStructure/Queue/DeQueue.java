package com.DataStructure.Queue;

class DeQueueImplementation{
	int size;
	int[] deQueue;
	int front;
	int rear;
	int nItems;
	
	public DeQueueImplementation(int size) {
		this.size = size;
		deQueue = new int[size];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public void insertRight(int data) {
		if(!isFull()){
			if(rear == size - 1){
				rear = -1;
			}
			deQueue[++rear] = data;
			nItems++;
		}else{
			System.out.println("Queue is Full from Right");
		}
	}
	
	public void insertLeft(int data){
		if(!isFull()){
			if(front == size){
				front = 0;
			}
			deQueue[front++] = data;
			nItems++;
		}else{
			System.out.println("Queue is Full from Left");
		}
	}
	
	public int deleteLeft() {
		if(!isEmpty()){
			int temp = deQueue[front++];
			if(front == size){
				front = 0;
			}
			nItems--;
			System.out.println("Deleted from Left"+temp);
			return temp;
		}else{
			System.out.println("Queue is Empty");
			return -1;
		}
	}
	
	public int deleteRight() {
		if(!isEmpty()){
			int temp = deQueue[rear--];
//			if(rear == size-1){
//				rear = -1;
//			}
			nItems--;
			System.out.println("deleted from Right"+ temp);
			return temp;
		}else{
			System.out.println("Queue is Empty");
			return -1;
		}
	}
	
	public boolean isEmpty(){
		return (nItems == 0);
	}
	
	public boolean isFull() {
		return (nItems == size);
	}
}

public class DeQueue {

	public static void main(String[] args) {
		DeQueueImplementation deQueue = new DeQueueImplementation(5);
		
		deQueue.insertRight(1);
		deQueue.insertRight(12);
		deQueue.insertRight(13);
		deQueue.insertRight(15);
		deQueue.insertRight(19);
		
		deQueue.deleteLeft();
		deQueue.insertRight(99);
		deQueue.deleteRight();
		deQueue.insertLeft(91);
		
		
		

	}

}
