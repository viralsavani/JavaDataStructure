package com.DataStructure.LinkList;

class DoublyLink{
	public int dData;
	public DoublyLink next;
	public DoublyLink previous;
	
	public DoublyLink(int data) {
		dData = data;
	}
	
	public void displyLink(){
		System.out.print(dData + " ");
	}
}

class DoublyImplemntation{
	DoublyLink first;
	DoublyLink last;
	
	public DoublyImplemntation() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	public void insertFirst(int data){
		DoublyLink newLink = new DoublyLink(data);
		
		if(first == null){
			last = newLink;
		}else{
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}
	
	public void insertLast(int data){
		DoublyLink newLink = new DoublyLink(data);
		
		if(isEmpty()){
			first = newLink;
		}else{
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}
	
	public DoublyLink deleteFirst(){
		DoublyLink deleteLink = first;
		
		// If only one element
		if(deleteLink.next == null){
			first = null;
		}else{
			first = first.next;
			first.previous = null;
		}
		return deleteLink;
	}
	
	public DoublyLink deleteLast(){
		DoublyLink deleteLink = last;
		if(first.next == null){
			first = null;
		}else{
			last.previous.next = null;
		}
		last = last.previous;
		return deleteLink;
	}
	
	public boolean insertAfter (int key, int data){
		DoublyLink current = first;
		DoublyLink newLink = new DoublyLink(data);
		
		while(current.dData != key){
			current = current.next;
			if(current == null){
				return false;
			}
		}
		if(current == last){
			newLink.next = null;
			last = newLink;
		}else{
			current.next.previous = newLink;
			newLink.next = current.next;
		}
		current.next = newLink;
		newLink.previous = current;
		return true;
	}
	
	public DoublyLink deleteKey(int key){
		
		DoublyLink current = first;
		
		while(current.dData != key){
			current = current.next;
			if(current == null){
				return null;
			}
		}
		if(current == first){
			first = null;
		}else{
			
			current.previous.next = current.next;
		}
		
		if(current == last){
			last = current.previous;
		}else{
			current.next.previous = current.previous;
		}
		return current;
	}
	
	public void displayForward(){
		System.out.println("Displaying Forward -->");
		
		DoublyLink current = first;
		while(current != null){
			current.displyLink();
			current = current.next;
		}
		System.out.println("\n");
	}
	
	public void displayBackward(){
		System.out.println("Displaying Backward <--");
		
		DoublyLink current = last;
		
		while(current != null){
			current.displyLink();
			current = current.previous;
		}
		System.out.println("\n");
	}
}

public class DoublyLinkList {

	public static void main(String[] args) {
		DoublyImplemntation doublyLinkList = new DoublyImplemntation();
		
		doublyLinkList.insertFirst(1);
		doublyLinkList.insertFirst(2);
		doublyLinkList.insertFirst(3);
		
		doublyLinkList.displayForward();
		
		doublyLinkList.insertLast(4);
		doublyLinkList.insertLast(5);
		doublyLinkList.insertLast(6);
		
		doublyLinkList.displayForward();
		
		doublyLinkList.insertAfter(3, 3344);
		doublyLinkList.insertAfter(3344, 333444);
		
		doublyLinkList.displayForward();
		
		System.out.print("Delete First \t");
		doublyLinkList.deleteFirst().displyLink();
		System.out.println();
		
		System.out.print("Delete Last \t");
		doublyLinkList.deleteLast().displyLink();
		System.out.println();
		
		System.out.print("Delete 333444 \t");
		doublyLinkList.deleteKey(333444).displyLink();
		System.out.println();
		
		doublyLinkList.displayBackward();
	}
}
