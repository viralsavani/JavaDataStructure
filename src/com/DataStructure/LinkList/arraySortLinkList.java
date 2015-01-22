package com.DataStructure.LinkList;



class NewLink{
	public double dData;
	public NewLink next; 

	public NewLink(double dd){
		dData = dd; 
	}
}

class SortedList{

	private NewLink first;

	public SortedList(){ 
		first = null; 
	}

	public SortedList(NewLink[] linkArr){
		first = null;
		for(int j=0; j<linkArr.length; j++)
			insert( linkArr[j] );
	}

	public void insert(NewLink k) 
	{
		NewLink previous = null; 
		NewLink current = first;

		while(current != null && k.dData > current.dData){ 
			previous = current;
			current = current.next;
		}
		if(previous==null)
			first = k; 
		else 
			previous.next = k; 
		k.next = current; 
	} 

	public NewLink remove(){
		NewLink temp = first; 
		first = first.next; 
		return temp;
	}
}

public class arraySortLinkList {

	public static void main(String[] args) {
		int size = 10;
		NewLink[] linkArray = new NewLink[size];

		for(int j=0; j<size; j++){ 
			int n = (int)(java.lang.Math.random()*99);
			NewLink newLink = new NewLink(n);
			linkArray[j] = newLink; 
		}

		System.out.print("Unsorted array: ");
		for(int j=0; j<size; j++){
			System.out.print( linkArray[j].dData + " " );
		}
		System.out.println("");

		SortedList theSortedList = new SortedList(linkArray);
		for(int j=0; j<size; j++){ 
			linkArray[j] = theSortedList.remove();
		}

		System.out.print("Sorted Array: ");
		for(int j=0; j<size; j++){
			System.out.print(linkArray[j].dData + " ");
		}
		System.out.println("");
	}
}
