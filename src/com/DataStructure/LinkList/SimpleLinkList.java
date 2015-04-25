package com.DataStructure.LinkList;

import java.util.HashSet;

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

    /**
     * @param key key of the node.
     * @return previousLink of the node with given key
     */
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

    /**
     * Traverse linked list using two pointers. Move one pointer by one and
     * other pointer by two. When the fast pointer reaches end slow pointer
     * will reach middle of the linked list.
     * A tie-breaker needs to be used with even number of nodes such as:
     * return both or return the larger node or vice-versa
     * In case of even numbers a counter needs to used to check if linked list
     * is even-numbered or odd-numbered
     */
    public Link getMiddle(){
        return getMiddle(first);
    }

    /**
     * Deletes nth node from end
     * @param n number of node from end
     * @return Link which is deleted
     */
    public Link nthNodeFromEnd(int n){
        Link current = first;
        Link nthAway = first;

        int count = 0;
        while (count < n){
            current = current.next;
            count++;
            if (current == null){
                return null;
            }
        }

        while (current != null){
            current = current.next;
            nthAway = nthAway.next;
        }
        return nthAway;
    }

    /**
     * Reverse entire linked list
     */
    public void reverse(){
        Link current = first;
        Link previous = null;
        Link next;

        while (current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        first = previous;
    }

    /**
     * Performs merge sort on LinkedList.
     * XXXXXX HIGHLY UNSTABLE MAY RESULT IN STACK OVERFLOW WITH VERY SMALL LINKED LIST. XXXXXX
     * Not preferable for realistic scenario, implemented only to understand the
     * mergeSort from recursion point of view.
     */
    public void mergeSortedList(){
        first = mergeSortedList(first);
    }

    /**
     * Helper function for mergeSortList()
     *
     * Performs recursive merge sort on linked list
     * @param head
     * @return Link head of the new linkedList which is sorted
     */
    private Link mergeSortedList(Link head){
        if (head == null || head.next == null){
            return head;
        }

        Link firstList1 = head;
        Link newMiddle = getMiddle(head);
        Link firstList2 = newMiddle.next;
        newMiddle.next = null;

        Link firstAfterSortedList1 = mergeSortedList(firstList1);
        Link firstAfterSortedList2 = mergeSortedList(firstList2);

        return sortedMerge(firstAfterSortedList1, firstAfterSortedList2);
    }


    /**
     * Helper function for mergeSortedList
     * Performs a merging of two linkedList in increasing order recursively.
     *
     * @param link1 first/head of first half linkedList
     * @param link2 first/head of second half linkedList
     * @return Link first/head of final linkedList created by merging.
     */
    private Link sortedMerge(Link link1, Link link2){
        Link result;

        if(link1 == null){
            return link2;
        }else if(link2 == null){
            return link1;
        }else if (link2.dData < link1.dData){
            result = link2;
            result.next = sortedMerge(link1, link2.next);
        }else {
            result = link1;
            result.next = sortedMerge(link1.next, link2);
        }

        return result;
    }

    /** Helper function for  mergeSortedList(Link head) and getMiddle()
     *
     * @param head head of the linkedList
     * @return Link node which is in the middle
     */
    private Link getMiddle(Link head){
        Link fastLink = head;
        Link slowLink = head;
        while (fastLink != null && fastLink.next != null){
            fastLink = fastLink.next.next;
            slowLink = slowLink.next;
        }
        return slowLink;
    }

    /**
     * Rotates the linkedList
     * @param k number of rotations must be*/
    public void rotate(int k){
        if (k < 0){
            throw new IllegalArgumentException("Number of rotation must be positive number\t"+"found -> "+k);
        }

        Link current = first;
        int lastIndex = size() - k;

        while (current.next != null){
            current = current.next;
        }

        current.next = first;

        Link tempLink = first;
        while (lastIndex > 0){
            tempLink = tempLink.next;
            lastIndex--;
        }
        first = tempLink.next;
        tempLink.next = null;
    }

    public int size(){
        Link current = first;
        int count = 0;
        while (current != null){
            current = current.next;
            count++;
        }
        return count;
    }

    /**
     * Partitions entire linked list with left partition containing values
     * less than pivot and right side containing values greater than
     * pivot
     */
    public void partition(int pivot){
        Link beforePivot = null;
        Link beforeTracker = null;

        Link afterPivot = null;
        Link afterTracker = null;

        Link current = first;
        while (current != null){

            Link currentTemp = current.next;
            current.next = null;

            if (current.iData < pivot){
                if (beforePivot == null){
                    beforePivot = current;
                    beforeTracker = beforePivot;
                }else {
                    beforeTracker.next = current;
                    beforeTracker = current;
                }
            }else{
                if(afterPivot == null){
                    afterPivot = current;
                    afterTracker = afterPivot;
                }else {
                    afterTracker.next = current;
                    afterTracker = current;
                }
            }
            current =  currentTemp;
        }
        beforeTracker.next = afterPivot;

        first = beforePivot;
    }

    /**
     * Uses HashSet to detect loops in linked list.
     */
    public Link detectLoop(){
        HashSet<Link> visitedNodeSet =  new HashSet<>(size());

        Link current = first;
        while (current != null){
            current.displayLink();
            if (visitedNodeSet.contains(current)){
                return current;
            }else {
                visitedNodeSet.add(current);
                current = current.next;
            }
        }
        return null;
    }

    /**
     * Moves the last node to the head of the list.
     */
    public void moveLastToFirst(){
        if (first == null || first.next == null){
            return;
        }

        Link current = first;
        Link previous = first;

        while (current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        current.next = first;
        first = current;
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

		linkList.insertFirst(34, 34);
		linkList.insertFirst(12, 12);
		linkList.insertFirst(56, 56);
        linkList.insertFirst(22, 22);
        linkList.insertFirst(45, 45);
        linkList.insertFirst(31, 31);
        linkList.insertFirst(65, 65);

		linkList.displayList();

	}

}
