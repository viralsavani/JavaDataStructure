package com.DataStructure.LinkList;

class DoubleEndedImplementation{
	private Link first;
	private Link last;

	public DoubleEndedImplementation(){
		first = null;		
		last = null;
	}

	public boolean isEmpty(){
		return first==null; 
	}

	public void insertFirst(int iData, double dData){
		Link  newLink = new Link(iData, dData);

		if(isEmpty()){
			last = newLink;
		}
		newLink.next = first;
		first = newLink;		
	}

	public void insertLast(int iData, double dData){
		Link newLink = new Link(iData, dData);

		if(isEmpty()){
			first = newLink;
		}else{
			last.next = newLink;
		}
		last = newLink;
	}

	public Link deleteFirst(){
		Link deleteLink = first;
		if(first.next == null){
			last = null;
		}
		first = first.next;
		return deleteLink;
	}

	
	/** Deleting the last require to 
	 * traverse entire list which is not efficient.
	 * A doubly link list would to the trick. 
	 */
	public Link deleteLast(){
		Link current = first;
		Link previous = first;
		
		while(current.next != null){
			previous = current;
			current = current.next;
		}
		if(current == first){
			last = null;
			first = null;
		}else{
			last = previous;
			last.next = null;

		}
		return current;
	}

	public void displayList(){
		Link current = first;
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

public class DoubleEndedLinkList {

	public static void main(String[] args) {
		DoubleEndedImplementation doubleLinkList = new DoubleEndedImplementation();

		doubleLinkList.insertFirst(1, 1);
		doubleLinkList.insertFirst(2, 2);
		doubleLinkList.insertFirst(3, 3);
		doubleLinkList.insertFirst(4, 4);

		doubleLinkList.displayList();

		doubleLinkList.insertLast(91, 91);
		doubleLinkList.insertLast(92, 92);

		doubleLinkList.displayList();

		doubleLinkList.deleteFirst();
		doubleLinkList.deleteFirst();

		doubleLinkList.displayList();

		doubleLinkList.deleteLast();
		doubleLinkList.deleteLast();
		doubleLinkList.deleteLast();
		doubleLinkList.deleteLast();
		
		doubleLinkList.displayList();
		
		doubleLinkList.insertLast(91, 91);
		doubleLinkList.insertFirst(4, 4);
		doubleLinkList.insertLast(92, 92);

		doubleLinkList.displayList();

	}

}
