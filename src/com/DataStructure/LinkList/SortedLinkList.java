package com.DataStructure.LinkList;

class SortedLinkListImplementation{

	private Link first;
	private Link last;

	public SortedLinkListImplementation(){
		first = null;		
		last = null;
	}

	public boolean isEmpty(){
		return first==null; 
	}

	public void insertSorted(int iData, double dData){
		Link newLink = new Link(iData, dData);
		Link current = first;
		Link previous = null;
		
		// Key is iData, actual data is in dData
		while(current != null && iData > current.iData){
			previous = current;
			current = current.next;
		}
		if(previous == null){
			first = newLink;
		}else{
			previous.next = newLink;
		}
		newLink.next = current;
	}
	
	public Link removeFirst(){
		Link deleteLink = first;
		first = first.next;
		return deleteLink;
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

public class SortedLinkList {

	public static void main(String[] args) {
		SortedLinkListImplementation newLinkList = new SortedLinkListImplementation();
		
		newLinkList.insertSorted(3, 33);
		newLinkList.insertSorted(1, 11);
		newLinkList.insertSorted(4, 44);
		newLinkList.insertSorted(2, 22);
		
		newLinkList.displayList();
		
		newLinkList.removeFirst();
		newLinkList.removeFirst();
		
		newLinkList.displayList();

	}

}
