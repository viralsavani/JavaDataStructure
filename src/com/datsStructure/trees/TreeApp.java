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


	/**
	 * Like any recursive function, there must be a base case: the 
	 * condition that causes the routine to return immediately, without 
	 * calling itself. In inOrder() this happens when the node passed 
	 * as an argument is null.
	 * It may not be obvious, but for each node, the routine traverses 
	 * the node's left subtree, visits the node, and traverses the right subtree.
	 */

	private void inOrderTraversal(Node localRoot){

		if(localRoot != null){
			inOrderTraversal(localRoot.leftChild);
			localRoot.displayNode();
			inOrderTraversal(localRoot.rightChild);
		}
	}

	private void preOrderTraversal(Node localRoot){

		if(localRoot != null){
			localRoot.displayNode();
			inOrderTraversal(localRoot.leftChild);
			inOrderTraversal(localRoot.rightChild);
		}
	}

	private void postOrderTraversal(Node localRoot){

		if(localRoot != null){
			inOrderTraversal(localRoot.leftChild);
			inOrderTraversal(localRoot.rightChild);
			localRoot.displayNode();
		}
	}

	public Node minimum(){
		Node current = root;
		Node minimumNode = null;
		while(current != null){
			minimumNode = current;
			current = current.leftChild;
		}
		return minimumNode;
	}

	public boolean delete(int key){
		Node current = root;
		Node parent = null;
		boolean isLeftChild = true;


		/**
		 * First step in deleting a node is to find the node which
		 * is shown below
		 */
		while(current.key != key){
			parent = current;

			if(key < current.key){
				isLeftChild = true;
				current = current.leftChild;
			}else{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null){
				return false;
			}
		}


		/**
		 * State where node to delete is leave node
		 */
		if(current.leftChild == null && current.rightChild == null){
			if(current == root){
				root = null;
			}else if(isLeftChild){
				parent.leftChild = null;
			}else{
				parent.rightChild = null;
			}
		}


		/**
		 * State when node to delete has only one child i.e.
		 * either leftChild or rightChild
		 */
		else if(current.leftChild == null){						// No left child
			if(current == root){
				root = current.rightChild;
			}else{
				if(isLeftChild){								// Is the node being deleted is leftChild of parent
					parent.leftChild = current.rightChild;		
				}else{
					parent.rightChild = current.rightChild;
				}
			}
		}
		else if(current.rightChild == null){					// No right child
			if(current == root){
				root = current.leftChild;
			}else{
				if(isLeftChild){
					parent.leftChild = current.leftChild;
				}else{
					parent.rightChild = current.leftChild;
				}
			}
		}
		/**
		 * Final case where node to be deleted has two childs.
		 */
		else{
			Node successor = getSuccessor(current);		// Get the successor node of the Node to delete
			if(current == root){	
				root = successor;
			}else if(isLeftChild){
				parent.leftChild = successor;
			}else{
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}
		return true;
	}

	private Node getSuccessor(Node deleteNode){
		Node successor = deleteNode;
		Node successorParent = deleteNode;
		Node current = deleteNode.rightChild;

		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if(successor != deleteNode.rightChild){
			successorParent.leftChild = successor.rightChild;		// replaces the successor with its right subtree
			successor.rightChild = deleteNode.leftChild;			// Link of to right sub tree
		}
		return successor;
	}

}

public class TreeApp {

	public static void main(String[] args) {


	}

}
