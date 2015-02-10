package com.datsStructure.trees;

/**
 * This is a class of node objects. These objects 
 * contain the data representing the objects being 
 * stored (employees in an employee database, for example) 
 * and also references to each of the node's two children.
 */

class Node{
	int key;			
	int data;			
	Node leftChild;
	Node rightChild;
	
	public void displayNode(){
		System.out.print('{');	
		System.out.print(key);	
		System.out.print(", ");	
		System.out.print(data);	
		System.out.print("} ");	
	}
}
	

/**
 * We'll also need a class from which to instantiate the tree itself; 
 * the object that holds all the nodes. We'll call this class Tree. 
 * It has only one field: a Node variable that holds the root. It doesn't 
 * need fields for the other nodes because they are all accessed from the root.
 */

class Tree
{
	private Node root; 		
	
	public Tree(){
		root = null;
	}
	
	
	/**
	 * Perform simple binary search to locate a key
	 */
	
	public Node find(int key){
		Node current  = root;
		
		while(current.key != key){
			if(key < current.key){
				current = current.leftChild;
			}else{
				current = current.rightChild;
			}
			
			if(current == null){
				return null;
			}
		}
		return current;
	}
	
	
	/**
	 * To insert first we need to find the right parent.
	 * For this we use the same logic used in find(), where
	 * instead to returning null when no element id found
	 * we add new Node at left or right side of that Node.
	 */
	
	public void insert(int key, int data){
		Node newNode = new Node();
		newNode.key = key;
		newNode.data = data;
		
		if(root == null){
			root = newNode;
		}else{
			Node current = root;
			Node parent;
			
			while(true){
				parent = current;
				if(key < current.key){
					current = current.leftChild;
					if(current.leftChild == null){
						parent.leftChild = newNode;
						return;
					}
				}else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	public void delete(int id){
	
	}
	
}

public class TreeApp {

	public static void main(String[] args) {
		

	}

}
