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

    /**
     * Swap two link in a link list. Four cases to be handled
     *      1) x and y may or may not be adjacent.
     *      2) Either x or y may be a head node.
     *      3) Either x or y may be last node.
     *      4) x and/or y may not be present in linked list.
     *      5) x and y are the same nodes
     */
    public boolean swapNodes(int key1, int key2){
        if (key1 == key2){
            return false;
        }

        Link link1 = findLink(key1);
        Link link1Previous = previousLink(key1);

        Link link2 = findLink(key2);
        Link link2Previous = previousLink(key2);

        if(link1 == null || link2 == null){
            return false;
        }

        if (link1Previous != null){
            link1Previous.next = link2;
        }else {
            first = link2;
        }

        if (link2Previous != null){
            link2Previous.next = link1;
        }else {
            first = link1;
        }

        Link swapTemp = link2.next;
        link2.next = link1.next;
        link1.next = swapTemp;


        return true;
    }

    private Link previousLink(int key){
        Link current = first;
        Link previous = null;
        while (current.iData != key){
            previous = current;
            current = current.next;
            if (current == null){
                return null;
            }
        }
        return previous;
    }
	
	public void displayList(){
//		System.out.println("List (first -> last):");
		Link current = first;
		
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

public class SimpleLinkList {

	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		
		linkList.insertFirst(12, 1);
		linkList.insertFirst(24, 2);
		linkList.insertFirst(13, 3);
		linkList.insertFirst(46, 4);
		linkList.insertFirst(35, 5);
		linkList.insertFirst(16, 6);
		
		linkList.displayList();

        linkList.swapNodes(12,35);

        linkList.displayList();

		System.out.println("\n4 exists at ::");
		linkList.findLink(24).displayLink();
		System.out.println("\n\n4 Deleted ::" );
		linkList.deleteLink(24).displayLink();
		System.out.println("\n\n4 exists at ::" + linkList.findLink(24));

		while( !linkList.isEmpty()){
			Link deletedLink = linkList.deleteFirst();
			System.out.println("\nDeleted Link");
			deletedLink.displayLink();
			System.out.println();
		}

	}

}
