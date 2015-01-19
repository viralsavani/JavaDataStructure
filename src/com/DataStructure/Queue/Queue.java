package com.DataStructure.Queue;

class QueueImplementation{
	private int size;
	private int[] queue;
	private int front;
	private int rear;
	private int nItems;
	
	public QueueImplementation(int size) {
		this.size = size;
		queue = new int[size];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public void insert(int data) {
		
		if(!isFull()){
			if(rear == size - 1){
				rear = -1;
			}
			queue[++rear] = data;
			nItems++;
		}else{
			System.out.println("Queue is Full");
		}
	}
	
	public void priorityInsert(int data){
		int i;
		if(nItems == 0){
			queue[nItems++] = data;
		}else{
			for(i = nItems-1; i >=0 ; i--){
				if(data > queue[i]){
					queue[i+1] = queue[i];
				}else{
					break;
				}
			}
			queue[i+1] = data;
			nItems++;
		}
			
	}
	
	public int remove() {
		
		if(!isEmpty()){
			int temp = queue[front++];
			if(front == size){
				front = 0;
			}
			nItems--;
			return temp;
		}else{
			System.out.println("Queue is Empty");
			return -1;
		}
	}
	
	public int removePriority(){
		return queue[--nItems];
	}
	
	public int size() {
		return nItems;
	}
	
	public int peek() {
		return queue[front];
	}
	
	public boolean isEmpty() {
		return (nItems == 0);
	}
	
	public boolean isFull() {
		return (nItems == size);
	}
}

public class Queue {

	public static void main(String[] args) {
		
		QueueImplementation queue = new QueueImplementation(5);
		boolean priorityFlag = false;
		
		
		if(priorityFlag){

			queue.priorityInsert(12);
			queue.priorityInsert(21);
			queue.priorityInsert(112);
			queue.priorityInsert(1);
					
			while(!queue.isEmpty()){
				System.out.print(queue.removePriority()+"\t");
			}
			System.out.println();
			System.out.println("QUEUE FULL? "+queue.isFull());
			System.out.println("QUEUE EMPTY? "+queue.isEmpty());

		}else{
			
			queue.insert(12);
			queue.insert(9);
			queue.insert(99);
			queue.insert(3);
			queue.insert(1);
		
			queue.remove();
			
			queue.insert(99);
		
			while(!queue.isEmpty()){
				System.out.print(queue.remove()+"\t");
			}
			System.out.println();
			System.out.println("QUEUE FULL? "+queue.isFull());
			System.out.println("QUEUE EMPTY? "+queue.isEmpty());
		}
	}

}
