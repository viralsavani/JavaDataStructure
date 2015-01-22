package com.DataStructure.LinkList.Iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Link{
	public double dData;
	public Link next;


	Link(double dData){
		this.dData = dData;

	}

	public void displayLink(){
		System.out.print(dData+" ");
	}
}

class LinkList{
	private Link first;

	public LinkList() {
		first = null;
	}

	public Link getFirst(){
		return first;
	}

	public void setFirst(Link setLink){
		first = setLink;
	}

	public boolean isEmpty(){
		return (first == null);
	}

	public ListIterator getIterator(){
		return new ListIterator(this);
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

class ListIterator{
	private Link current;
	private Link previous;
	private LinkList currentLinkList;

	public ListIterator(LinkList currentLinkList) {
		this.currentLinkList = currentLinkList;
		resetIterator();
	}

	public void resetIterator(){
		current = currentLinkList.getFirst();
		previous = null;
	}

	public boolean isEnd(){
		return (current.next == null);
	}

	public void nextLink() {
		previous = current;
		current = current.next;
	}

	public Link getCurrent(){
		return current;	
	}

	public void insertAfter(double dData){
		Link newLink = new Link(dData);

		if(currentLinkList.isEmpty()){
			currentLinkList.setFirst(newLink);
			current = newLink;
		}else{
			newLink.next = current.next;
			current.next = newLink;
			nextLink();
		}
	}

	public void insertBefore(double dData){
		Link newLink = new Link(dData);

		if(previous == null){
			newLink.next = currentLinkList.getFirst();
			currentLinkList.setFirst(newLink);
			resetIterator();
		}else{
			newLink.next = current;
			previous.next = newLink;
			current = newLink;
		}
	}

	public double deleteCurrent(){
		Link deletedLink = current;

		if(previous == null){
			currentLinkList.setFirst(current.next);
			resetIterator();
		}else{
			previous.next = current.next;
			if(isEnd()){
				resetIterator();
			}else{
				current = current.next;
			}
		}

		return deletedLink.dData;
	}
}

public class LinkListIterator {

	public static void main(String[] args) throws IOException {

		LinkList theList = new LinkList(); // new list
		ListIterator iter1 = theList.getIterator(); // new iter
		double value;
		iter1.insertAfter(20); // insert items
		iter1.insertAfter(40);
		iter1.insertAfter(80);
		iter1.insertBefore(60);
		while(true)
		{
			System.out.print("Enter first letter of show, reset,");
					System.out.print("next, get, before, after, delete:");
							System.out.flush();
							int choice = getChar(); // get user's option
							switch(choice)
							{
							case 's': // show list
								if( !theList.isEmpty() )
									theList.displayList();
								
								else
									System.out.println("List is empty");
								break;
							case 'r': // reset (to first)
								iter1.resetIterator();
								break;
							case 'n': // advance to next
								
								if( !theList.isEmpty() && !iter1.isEnd() )
									iter1.nextLink();
								else
									System.out.println("Can't go to next link");
								break;
							case 'g': // get current item
								if( !theList.isEmpty() )
								{
									value = iter1.getCurrent().dData;
									System.out.println("Returned " + value);
								}
								else
									System.out.println("List is empty");
								break;
							case 'b': // insert before current
								System.out.print("Enter value to insert: ");
								System.out.flush();
								value = getInt();
								iter1.insertBefore(value);
								break;
							case 'a': // insert after current
								System.out.print("Enter value to insert: ");
								System.out.flush();
								value = getInt();
								iter1.insertAfter(value);
								break;
							case 'd': // delete current item
								if( !theList.isEmpty() )
								{
									value = iter1.deleteCurrent();
									System.out.println("Deleted " + value);
								}
								else
									System.out.println("Can't delete");
								break;
							default:
								System.out.println("Invalid entry");
							} // end switch
		} // end while

	}
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static int getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}

}
