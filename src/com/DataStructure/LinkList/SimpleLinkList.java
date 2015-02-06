package com.DataStructure.LinkList;

class Link{
	
	public int iData;
	public double dData;
	public Link next;
	
	public Link(int iData, double dData){
		//next is automatically set to null
		this.iData = iData;
		this.dData = dData;
	}
	
	public void displayLink(){
		System.out.print("{ " + iData + " , " + dData + "}");
	}
}

class LinkList{
	
	// Reference to the first link on the list.
	private Link first;
	
	public LinkList(){
		first = null;
	}
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	
	
	/*
	 * 	The insertFirst() method of LinkList inserts a new link at the beginning of the list.
	 * 	This is the easiest place to insert a link, because first already points to the first link.
	 *  To insert the new link, we need only set the next field in the newly created link to
	 *  point to the old first link, and then change first so it points to the newly created link.
	 */
	public void insertFirst(int iData, double dData){
		Link newLink = new Link(iData, dData);
		newLink.next = first;		//newLink --> oldFirst
		first = newLink;			//first -->newLink
	}
	
	public Link findLink(int key){
		Link current = first;
		while(current.iData != key){		//Check until key is not found
			if(current.next == null){		//Check if end of list
				return null;				//If yes return null
			}else{
				current = current.next;		//Else keep traversing
			}
		}
		return current;						//Key found
	}
	
	public Link deleteFirst(){
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	public Link deleteLink(int key){
		Link current = first;
		Link previous = first;
		
		while(current.iData != key){			// Same as find key.
			if(current.next == null){			
				return null;
			}else{
				previous = current;				// Keep track of previous Link
				current = current.next;
			}
		}
		if(current == first){					// If the first is to be deleted: same as deleteFirst()
			first = first.next;
		}else{
			previous.next = current.next;		// previous->current->future  CHANGES TO  previous->future.
		}
		return current;
	}
	
	public void displayList(){
		System.out.println("List (first -> last):");
		Link current = first;
		
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.print("");
	}
}

public class SimpleLinkList {

	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		
		linkList.insertFirst(1, 1);
		linkList.insertFirst(2, 2);
		linkList.insertFirst(3, 3);
		linkList.insertFirst(4, 4);
		linkList.insertFirst(5, 5);
		linkList.insertFirst(6, 6);
		
		linkList.displayList();
		

		System.out.println("\n4 exists at ::");
		linkList.findLink(4).displayLink();
		System.out.println("\n\n4 Deleted ::" );
		linkList.deleteLink(4).displayLink();
		System.out.println("\n\n4 exists at ::" + linkList.findLink(4));
		
		while( !linkList.isEmpty()){
			Link deletedLink = linkList.deleteFirst();
			System.out.println("\nDeleted Link");
			deletedLink.displayLink();
			System.out.println();
		}

	}

}
