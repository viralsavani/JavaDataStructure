package com.DataStructure.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Time needed to carry out the common tree operations is proportional to the
 * base-2 log of N. In Big-O notation we say such operations take O(logN) time.
 * Except for tree traversal.
 */


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

class Tree {
	private Node root; 		
	private int size = 0;


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
			size++;
		}else{
			Node current = root;
			Node parent;

			while(true){
				parent = current;
				if(key < current.key){
					current = current.leftChild;
					if(current == null){
						parent.leftChild = newNode;
						size++;
						return;
					}
				}else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
						size++;
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
	 * 
	 * Traversing is not as fast as the other operations. However, traversals 
	 * are probably not very commonly carried out in a typical large database. 
	 * They're more appropriate when a tree is used as an aid to parsing algebraic 
	 * or similar expressions, which are probably not too long anyway.
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
			preOrderTraversal(localRoot.leftChild);
			preOrderTraversal(localRoot.rightChild);
		}
	}

	private void postOrderTraversal(Node localRoot){

		if(localRoot != null){
			postOrderTraversal(localRoot.leftChild);
			postOrderTraversal(localRoot.rightChild);
			localRoot.displayNode();
		}
	}

	public void traverse(String traversalType)	
	{	
		switch(traversalType)	
		{	
		case "preOrder": System.out.print("\nPreorder traversal: ");	
		preOrderTraversal(root);	
		break;	
		case "inOrder": System.out.print("\nInorder traversal:  ");	
		inOrderTraversal(root);	
		break;	
		case "postOrder": System.out.print("\nPostorder traversal: ");	
		postOrderTraversal(root);	
		break;	
		}	
		System.out.println();	
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
		Node parent = root;
		boolean isLeftChild = true;


		/**
		 * First step in deleting a node is to find the node which
		 * is shown below.
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
		 * State where node to delete is leaf node.
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
			}else if(isLeftChild){								// Is the node being deleted is leftChild of parent
				parent.leftChild = current.rightChild;		
			}else{
				parent.rightChild = current.rightChild;
			}

		}
		else if(current.rightChild == null){					// No right child
			if(current == root){
				root = current.leftChild;
			}else if(isLeftChild){
				parent.leftChild = current.leftChild;
			}else{
				parent.rightChild = current.leftChild;
			}
		}
		/**
		 * Final case where node to be deleted has two children.
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
		size--;
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
			successor.rightChild = deleteNode.rightChild;			// Link of to right sub tree
		}
		return successor;
	}


	/**
	 * Prints Node (Key,value) at given level.
	 * @args root node, level to print
	 */
	public void printGivenLevel(Node root,int level){
		if(root == null){
			return;
		}else{
			if(level == 1){
				root.displayNode();
				System.out.print("\t");
			}
			if(level > 1){
				printGivenLevel(root.leftChild, level-1);
				printGivenLevel(root.rightChild, level-1);
			}
		}
	}


	/**
	 * Breadth First traversal uses recursive approach which can be inefficient for large data.
	 * Queue can be used instead of stack which dramatically increases the efficiency by 
	 * removing recursion and using iterative approach.
	 */
	public void breadthFirstTraversal(){
		int level = height(root)+1;
		int levelInverse = 1;
		while(levelInverse <= level){
			System.out.print("LEVEL :: "+levelInverse+"  ");
			printGivenLevel(root, levelInverse);
			System.out.println();
			levelInverse++;
		}		
	}


	/**
	 * Return height i.e. number of edges on the longest downward path between the root and a leaf
	 * of the tree.
	 */
	public int heightOfTree(){
		return height(root);
	}

	private int height(Node node){
		if(node == null){
			return -1;
		}else{
			return Math.max(height(node.leftChild), height(node.rightChild))+1;
		}
	}

	public int size(){
		return size;
	}

	public void displayTree(){

		Stack<Node> globalStack = new Stack<Node>();	
		globalStack.push(root);	
		int nBlanks = 40;	
		boolean isRowEmpty = false;	
		System.out.println("......................................................");

		while(isRowEmpty == false){

			Stack<Node> localStack = new Stack<Node>();	
			isRowEmpty = true;	

			for(int j=0; j<nBlanks; j++){	
				System.out.print(' ');	
			}

			while(globalStack.isEmpty() == false){

				Node temp = (Node)globalStack.pop();	
				if(temp != null){

					System.out.print(temp.key);	
					localStack.push(temp.leftChild);	
					localStack.push(temp.rightChild);	
					if(temp.leftChild != null || temp.rightChild != null){	
						isRowEmpty = false;	
					}
				}	
				else{	
					System.out.print("--");	
					localStack.push(null);	
					localStack.push(null);	
				}	
				for(int j=0; j<nBlanks*2-2; j++){
					System.out.print(' ');	
				}
			}
			System.out.println();	
			nBlanks /= 2;	
			while(localStack.isEmpty() == false){
				globalStack.push(localStack.pop());
			}
		}
		System.out.println("......................................................");	
	}
}

public class TreeApp {

	public static void main(String[] args) throws Exception {

		int value;	
		String input;
		Tree theTree = new Tree();	
		theTree.insert(50, 15);	
		theTree.insert(25, 12);	
		theTree.insert(75, 17);	
		theTree.insert(12, 15);	
		theTree.insert(37, 12);	
		theTree.insert(43, 17);	
		theTree.insert(30, 15);	
		theTree.insert(33, 12);	
		theTree.insert(87, 17);	
		theTree.insert(93, 15);	
		theTree.insert(97, 15);	

		while(true){

			putText("Enter first letter of ");	
			putText("show, insert, find, delete, traverse, breadthFirstTravesal, heightOfTree or Size : ");	
			int choice = getChar();	
			switch(choice)	
			{	
			case 's':	
				theTree.displayTree();	
				break;	
			case 'i':	
				putText("Enter Key to insert: ");	
				value = getInt();	
				putText("Enter Data to insert: ");
				int data = getInt();
				theTree.insert(value, data);	
				break;	
			case 'f':	
				putText("Enter Key to find: ");	
				value = getInt();	
				Node found = theTree.find(value);	
				if(found != null)	
				{	
					putText("Found: ");	
					found.displayNode();	
					putText("\n");	
				}	
				else	
					putText("Could not find " + value + '\n');	
				break;	
			case 'd':	
				putText("Enter Key to delete: ");	
				value = getInt();	
				boolean didDelete = theTree.delete(value);	
				if(didDelete)	
					putText("Deleted " + value + '\n');	
				else	
					putText("Could not delete " + value + '\n');	
				break;	
			case 't':	
				putText("Enter type inOrder, preOrder or postOrder : ");	
				input = getString();	
				theTree.traverse(input);	
				break;
			case 'b':			
				theTree.breadthFirstTraversal();	
				break;
			case 'h':			
				System.out.println("Height of tree :: "+theTree.heightOfTree());	
				break;
			case 'S':
				System.out.println("Size of tree :: "+theTree.size());
				break;
			default:	
				putText("Invalid entry\n");	
			}	
		}
	}

	public static void putText(String s){	
		System.out.print(s);	
		System.out.flush();	
	}	

	public static String getString() throws IOException{	
		InputStreamReader isr = new InputStreamReader(System.in);	
		BufferedReader br = new BufferedReader(isr);	
		String s = br.readLine();	
		return s;	
	}

	public static char getChar() throws IOException {	
		String s = getString();	
		return s.charAt(0);	
	}

	public static int getInt() throws IOException {	
		String s = getString();	
		return Integer.parseInt(s);	
	}
}
