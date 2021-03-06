package com.DataStructure.LinkList;

import java.util.HashSet;

import com.Recursion.MergeSort;
import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;

class LinkListNode{
	
	public int iData;
	public double dData;
	public LinkListNode next;
	
	public LinkListNode(int iData, double dData){
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
	private LinkListNode first;
	
	public LinkList(){
		first = null;
	}
	
	/**
	 * Returns the head of LinkList
	 */
	public LinkListNode head() {
		if (first == null) {
			return null;
		}
		return first;
	}
	
	public boolean isEmpty(){
		return (first == null);
	}

	/**
	 * 	The insertFirst() method of LinkList inserts a new link at the beginning of the list.
	 * 	This is the easiest place to insert a link, because first already points to the first link.
	 *  To insert the new link, we need only set the next field in the newly created link to
	 *  point to the old first link, and then change first so it points to the newly created link.
	 */
	public void insertFirst(int iData, double dData){
		LinkListNode newLink = new LinkListNode(iData, dData);
		newLink.next = first;		//newLink --> oldFirst
		first = newLink;			//first -->newLink
	}
	
	public LinkListNode findLink(int key){
		LinkListNode current = first;
		while(current.iData != key){		//Check until key is not found
			if(current.next == null){		//Check if end of list
				return null;				//If yes return null
			}else{
				current = current.next;		//Else keep traversing
			}
		}
		return current;						//Key found
	}
	
	public LinkListNode deleteFirst(){
		LinkListNode temp = first;
		first = first.next;
		return temp;
	}

	public LinkListNode deleteLink(int key){
		LinkListNode current = first;
		LinkListNode previous = first;
		
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

        LinkListNode link1 = findLink(key1);
        LinkListNode link1Previous = previousLink(key1);

        LinkListNode link2 = findLink(key2);
        LinkListNode link2Previous = previousLink(key2);

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

        LinkListNode swapTemp = link2.next;
        link2.next = link1.next;
        link1.next = swapTemp;


        return true;
    }

    /**
     * @param key key of the node.
     * @return previousLink of the node with given key
     */
    private LinkListNode previousLink(int key){
        LinkListNode current = first;
        LinkListNode previous = null;
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
    public LinkListNode getMiddle(){
        return getMiddle(first);
    }

    /**
     * Deletes nth node from end
     * @param n number of node from end
     * @return Link which is deleted
     */
    public LinkListNode nthNodeFromEnd(int n){
        LinkListNode current = first;
        LinkListNode nthAway = first;

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
        LinkListNode current = first;
        LinkListNode previous = null;
        LinkListNode next;

        while (current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        first = previous;
    }

    public static LinkListNode mergeSortedListIterative(LinkList list1, LinkList list2){
      	
    	LinkListNode node1 = list1.head();
    	LinkListNode node2 = list2.head();
    	LinkListNode resultHead = null;
    	LinkListNode resultNode = null;
    	
    	while (node1 != null && node2 != null){
    		if (node1.iData < node2.iData){
    			if (resultHead == null){
    				resultHead = node1;
    				resultNode = resultHead;
    			}else{
    				resultNode.next = node1;
    				resultNode = resultNode.next;
    			}
				node1 = node1.next;
    		}else{
    			if (resultHead == null){
    				resultHead = node2;
    				resultNode = resultHead;
    			}else{
    				resultNode.next = node2;
    				resultNode = resultNode.next;
    			}
    			node2 = node2.next;
    		}
    		resultNode.next = null;
    	}
    	
		while (node1 != null){
			resultNode.next = node1;
			resultNode = resultNode.next;
			node1 = node1.next;
		}
    	    	
		while (node2 != null){
			resultNode.next = node2;
			resultNode = resultNode.next;
			node2 = node2.next;
		}
    	
    	return resultHead;
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
    private LinkListNode mergeSortedList(LinkListNode head){
        if (head == null || head.next == null){
            return head;
        }

        LinkListNode firstList1 = head;
        LinkListNode newMiddle = getMiddle(head);
        LinkListNode firstList2 = newMiddle.next;
        newMiddle.next = null;

        LinkListNode firstAfterSortedList1 = mergeSortedList(firstList1);
        LinkListNode firstAfterSortedList2 = mergeSortedList(firstList2);

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
    private LinkListNode sortedMerge(LinkListNode link1, LinkListNode link2){
        LinkListNode result;

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
    private LinkListNode getMiddle(LinkListNode head){
        LinkListNode fastLink = head;
        LinkListNode slowLink = head;
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

        if (size() == 1){
            return;
        }

        LinkListNode current = first;
        int lastIndex = size() - k;

        while (current.next != null){
            current = current.next;
        }

        current.next = first;

        LinkListNode tempLink = first;
        while (lastIndex > 0){
            tempLink = tempLink.next;
            lastIndex--;
        }
        first = tempLink.next;
        tempLink.next = null;
    }

    public int size(){
        LinkListNode current = first;
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
        LinkListNode beforePivot = null;
        LinkListNode beforeTracker = null;

        LinkListNode afterPivot = null;
        LinkListNode afterTracker = null;

        LinkListNode current = first;
        while (current != null){

            LinkListNode currentTemp = current.next;
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
    public LinkListNode detectLoop(){
        HashSet<LinkListNode> visitedNodeSet =  new HashSet<>(size());

        LinkListNode current = first;
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

        LinkListNode current = first;
        LinkListNode previous = first;

        while (current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        current.next = first;
        first = current;
    }

    /**
     * Swap the Kth node from start with Kth node from end
     */
    public void swapKthNode(int k){
        if (k >= size() || k < 0){
            throw new IllegalArgumentException("K should be less than size or greater than 0\t"+"Found :: "+k);
        }

        /* Check if both nodes to swap are same */
        if(k == 0 || 2*(k-1) == size()){
            return;
        }

        LinkListNode kthLinkFromStart = first;
        LinkListNode kthLinkFromStartPrevious = null;
        for (int i = 1; i < k; i++) {
            kthLinkFromStartPrevious = kthLinkFromStart;
            kthLinkFromStart = kthLinkFromStart.next;
        }

        LinkListNode kthLinkFromEnd = first;
        LinkListNode kthLinkFromEndPrevious = null;
        for (int i = 1; i < size()-k+1; i++) {
            kthLinkFromEndPrevious = kthLinkFromEnd;
            kthLinkFromEnd = kthLinkFromEnd.next;
        }

        if (kthLinkFromStartPrevious != null){
            kthLinkFromStartPrevious.next = kthLinkFromEnd;
        }
        if (kthLinkFromEndPrevious != null){
            kthLinkFromEndPrevious.next = kthLinkFromStart;
        }

        LinkListNode temp = kthLinkFromStart.next;
        kthLinkFromStart.next = kthLinkFromEnd.next;
        kthLinkFromEnd.next = temp;

        if (k == 1){
            first = kthLinkFromEnd;
        }
        if (k == size()){
            first = kthLinkFromStart;
        }
    }
   
	public void displayList(){
//		System.out.println("List (first -> last):");
		LinkListNode current = first;
		
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

public class SimpleLinkList {

	public static void main(String[] args) {
		LinkList linkList1 = new LinkList();
		linkList1.insertFirst(20, 20);
		linkList1.insertFirst(15, 15);
		linkList1.insertFirst(10, 10);
        linkList1.insertFirst(5, 5);
        linkList1.insertFirst(1, 1);
        
		linkList1.displayList();
	}

}
