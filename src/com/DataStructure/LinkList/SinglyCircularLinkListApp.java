package com.DataStructure.LinkList;

class LinkDataItem{
	public int key;
	public int data;
	public LinkDataItem next;

	public LinkDataItem(int key, int data){
		this.key = key;
		this.data = data;
	}

	public void displayLink(){
		System.out.print("{ " + key + " , " + data + "}");
	}
}

class SinglyCircularLinkList{
	private LinkDataItem first;
	private LinkDataItem last;
	private int size;
	
	public SinglyCircularLinkList(){
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty(){
		return (first == null || last == null);
	}

	public void insertFirst(int key, int data){
		LinkDataItem newItem = new LinkDataItem(key, data);
		newItem.next = first;

		if(last == null){
			last = newItem;
		}else{
			last.next = newItem;
		}
		first = newItem;
		size++;
	}

	public LinkDataItem findLink(int key){
		if(first.key == key){
			return first;
		}else if(last.key == key){
			return last;
		}else{
			LinkDataItem current = first.next;

			while(current != last){
				if(current.key == key){
					return current;
				}else{
					current = current.next;
				}
			}
		}
		return null;
	}

	public LinkDataItem deleteFirst(){
		LinkDataItem deleteLink = first;
		
		if(last != first){
			last.next = first.next;
		}else{
			last = first.next;
		}
		first = first.next;

		size--;
		return deleteLink;
	}
	
	public LinkDataItem deleteLink(int key){
		
		if(first.key == key){
			return deleteFirst();
		}else{
			LinkDataItem current = first.next;
			LinkDataItem previous = first;	
			
			while(current.key != key){
				previous = current;
				current = current.next;
				if(current == first){
					return null;
				}
			}
			if(current == last){
				last = previous;
			}
			previous.next = current.next;
			size--;
			return current;
		}		
	}
	
	public void displayList(){
		System.out.println("List (first -> last):");
		LinkDataItem current = first;
		
		while(current.next != first){
			current.displayLink();
			current = current.next;
		}
		current.displayLink();
		System.out.print("");
	}
	
	public void displayCirculaly(){
		LinkDataItem current = first;
		int displayCounter = size*2;
		while(displayCounter >0){
			current.displayLink();
			current = current.next;
	
			displayCounter--;
			if(displayCounter == size){
				System.out.print("  Loop Begins  ");
			}
		}		
	}
}

public class SinglyCircularLinkListApp {

	public static void main(String[] args) {
		
		SinglyCircularLinkList linkList = new SinglyCircularLinkList();
		
		linkList.insertFirst(1, 1);
		linkList.insertFirst(2, 2);
		linkList.insertFirst(3, 3);
		linkList.insertFirst(4, 4);
		linkList.insertFirst(5, 5);
		
		linkList.displayList();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		linkList.displayCirculaly();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.print("3 exists at ::");
		linkList.findLink(3).displayLink();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		linkList.deleteFirst();
		linkList.displayCirculaly();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		linkList.deleteLink(1);
		linkList.displayCirculaly();

	}

}
