package com.DataStructure.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

	@Override
	public String toString() {
        return "{"+key+", "+data+"} ";
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

	public void traverse(String traversalType){	
		switch(traversalType){	
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
		else if(current.rightChild == null){			// No right child
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
	 * Compares two trees recursively from root to leaf in breadth first style and 
	 * stops at not equal nodes.
	 * @args root1-root of tree 1, root2-root of tree 2
	 */
	public static boolean treeCompare(Node root1, Node root2){
		if(root1 == root2){
			return true;
		}
		if(root1 == null || root2 == null){
			return false;
		}
		return (	(root1.data == root2.data) && 
					(root1.key == root2.key) && 
					(treeCompare(root1.leftChild,root2.leftChild)) && 
					(treeCompare(root1.rightChild, root2.rightChild))
				);
	}
	

	/**
	 * Level traversal uses recursive approach which can be inefficient for large data.
	 * Queue can be used instead of stack which dramatically increases the efficiency by 
	 * removing recursion and using iterative approach.
	 */
	public void levelTraversal(){
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
	 * Width of level represents the number of nodes in that level.
	 * The method performs breadth first traversal and instead of printing
	 * the node in each level it counts and when level changes the count is
	 * printed.
	 */
	public void widthOfEachLevel(){
		int level = height(root)+1;
		int levelInverse = 1;
		
		while(levelInverse <= level){
			System.out.print("Width Of LEVEL :: "+levelInverse+"  ");
			System.out.print(widthOfGivenLevel(root, levelInverse));
			System.out.println();
			levelInverse++;
		}	
	}
	
	/**
	 * Helper function widthOfEachLevel
	 */
	private int widthOfGivenLevel(Node node, int level){
		int width = 0;
		if(node == null){
			width = 0;
		}else{
			if(level == 1){
				width = 1;
			}
			if(level > 1){
				width = widthOfGivenLevel(node.leftChild, level-1) + widthOfGivenLevel(node.rightChild, level-1);
			}
		}
		return width;
	}
	
	
	/**
	 * Performs spiral traversal where node is visited in spiral form i.e each
	 * level is printed in zig-zag manner.
	 */
	public void spiralTraversal(){
		boolean itr = false;
		int level = height(root)+1;
		int levelInverse = 1;
		while(levelInverse <= level){
			printGivenLevelSpiral(root, levelInverse,itr);
			itr = ! itr;
			levelInverse++;
		}
	}
	private void printGivenLevelSpiral(Node node, int level, boolean itr){
		if(node == null){
			return;
		}else{
			if(level == 1){
				node.displayNode();
			}
			if(level > 1){
				if(itr){
					printGivenLevelSpiral(node.leftChild, level-1, itr);
					printGivenLevelSpiral(node.rightChild, level-1, itr);
				}else{
					printGivenLevelSpiral(node.rightChild, level-1, itr);
					printGivenLevelSpiral(node.leftChild, level-1, itr);
				}
			}
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
	
	
	/**
	 * Diameter of a tree is the maximum distance between any two nodes. Which
	 * the diameter of T�s left subtree the diameter of T�s right subtree
	 * the longest path between leaves that goes through the root of T 
	 * (this can be computed from the heights of the subtrees of T)
	 */
	public int treeDiameter(){
		return treeDiameter(root);
	}
	
	private int treeDiameter(Node root){
		
		if(root == null){
			return 1;
		}else{
			int diameterLeftTree = treeDiameter(root.leftChild);
			int diameterRightTree = treeDiameter(root.rightChild);
			int diameterWithRoot = (height(root.leftChild)+1) + (height(root.rightChild)+1);
			return Math.max(Math.max(diameterRightTree, diameterLeftTree), diameterWithRoot);			
		}

	}

	public int size(){
		return size;
	}
	

	/**
	 * Prints all the paths from root to all leaves recursively.
	 */
	public void printAllPath(Node node,List<Node> nodelist) {
		if(node != null) {
			nodelist.add(node);
			if(node.leftChild != null){
				printAllPath(node.leftChild,nodelist);
			}
			if(node.rightChild != null){
				printAllPath(node.rightChild,nodelist);
			}
			else if(node.leftChild == null && node.rightChild == null){
				int localSum = 0;
                for (Node aNodelist : nodelist) {

                    aNodelist.displayNode();
                    localSum = localSum + aNodelist.data;
                    System.out.print("  ");
                }
				System.out.println("Sum of above Path :: "+localSum);
				System.out.println();
			}
			nodelist.remove(node);
		}
	}
	
	
	/**
	 * Checks if tree has any path from root to leaf which has sum equal to given
	 * in @args sum.
	 */
	public boolean hasPathSum(int sum) { 
		 return(hasPathSum(root, sum)); 
	}
	boolean hasPathSum(Node node, int sum) { 
		if (node == null) { 
			return(sum == 0); 
		} 
		else { 
			int intermediateSum = sum - node.data; 
			return(hasPathSum(node.leftChild, intermediateSum) || hasPathSum(node.rightChild, intermediateSum)); 
		} 
	} 

	/**
	 * Tests if a tree meets the conditions to be a binary search tree (BST). 
	 * Uses the efficient recursive helper. 
	 */
	public boolean isBinaryTree(){
		return isBinaryTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Efficient BST helper -- Given a node, and min and max values, recurs down the tree 
	 * to verify that it is a BST, and that all its nodes are within the min..max range. 
	 * Works in O(n) time -- visits each node only once.
	 */
	private boolean isBinaryTree(Node node, int min, int max){
		if(node == null){
			return true;
		}else{
			boolean leftStatus = isBinaryTree(node.leftChild, min, node.key);
			if(!leftStatus){
				return false;
			}
            return isBinaryTree(node.rightChild, node.key+1, max);
		}
	}
		
	public void displayTree(){
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);	
		int nBlanks = 50;	
		boolean isRowEmpty = false;	
		System.out.println("......................................................");

		while(!isRowEmpty){
			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;	

			for(int j=0; j<nBlanks; j++){	
				System.out.print(' ');	
			}

			while(!globalStack.isEmpty()){
				Node temp = globalStack.pop();
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
			while(!localStack.isEmpty()){
				globalStack.push(localStack.pop());
			}
		}
		System.out.println("......................................................");	
	}


	/**
	 * The lowest common ancestor between two nodes n1 and n2 is defined as the lowest 
	 * node in T that has both n1 and n2 as descendants.
	 * The solution presented is iterative which takes O(n) where n number of nodes visited.
	 */
	public Node lowestCommonAncestor( int key1, int key2){
		Node current = root;
		
		while(current != null){
			if(current.key > key1 && current.key > key2 ){
				current = current.leftChild;
			}else if(current.key < key1 && current.key < key2){
				current = current.rightChild;
			}else{
				return current;
			}
		}
		return null;
	}

	
	public void inOrderIterative(){
		inOrderIterative(root);
		System.out.println();
	}
	private void inOrderIterative(Node node){
		Stack<Node> nodeStack = new Stack<>();
		while(!(nodeStack.isEmpty()) || node != null){
			if(node != null){
				nodeStack.push(node);
				node = node.leftChild;
			}else{
				node = nodeStack.pop();
				node.displayNode();
				node = node.rightChild;
			}
		}
	}
	
	
	/**
	 * Breadth first traversal iterative approach using queue
	 */
	public void breadthFirstTraversal(){
		ArrayDeque<Node> nodeQueue = new ArrayDeque<>();
		nodeQueue.add(root);
		while(!nodeQueue.isEmpty()){
			Node node = nodeQueue.remove();
			if(node != null){
				node.displayNode();
				if(node.leftChild != null){
					nodeQueue.add(node.leftChild);
				}
				if(node.rightChild != null){
					nodeQueue.add(node.rightChild);
				}
			}
		}
	} 
	
	
	/**
	 * A tree where no leaf is much farther away from the root than any other leaf. 
	 * Different balancing schemes allow different definitions of �much farther� 
	 * and different amounts of work to keep them balanced.
	 */
	public boolean isBalanced() {
        return root == null || isBalanced(root);
    }
	
	private boolean isBalanced(Node node){
		return ((node == null) ||
				((isBalanced(node.leftChild) && 
				(isBalanced(node.rightChild)) && 
				(Math.abs(
						height(node.leftChild) - height(node.rightChild)
						)) <= 1
				)));
	}
	
	public LinkedList<Node> toSortedLinkList(){
		return toSortedList(root);
	}
	private LinkedList<Node> toSortedList(Node node){
		Stack<Node> nodeStack = new Stack<>();
		LinkedList<Node> nodeList = new LinkedList<>();
		
		while(!(nodeStack.isEmpty()) || node != null){
			if(node != null){
				nodeStack.push(node);
				node = node.leftChild;
			}else{
				node = nodeStack.pop();
				nodeList.add(node);
				node = node.rightChild;
			}
		}
		return nodeList;
	}

	public Node binarySearch(int key){
		Node toSearchNode = new Node();
		Node current = root;
		
		while(current != null){
			if(current.key > key){
				current = current.leftChild;
			}else if(current.key < key){
				current = current.rightChild;
			}else{
				toSearchNode = current;
				break;
			}	
		}
		return toSearchNode;
	}

	
	public ArrayList<Node> getAllLeaf(){
		ArrayList<Node> leafNodes = new ArrayList<>();
		Node node = root;
		Stack<Node> nodeStack = new Stack<>();
		
		while(!(nodeStack.isEmpty()) || node != null){
			if(node != null){
				nodeStack.push(node);
				node = node.leftChild;
			}else{
				node = nodeStack.pop();
				if(node.leftChild == null && node.rightChild == null){
					leafNodes.add(node);
				}
				node = node.rightChild;
			}
		}		
		return leafNodes;
	}
	
	
	/**
	 * For every node, data value must be equal to sum of data values in left and 
	 * right children. Consider data value as 0 for NULL children
	 */
	public boolean isSumProperty(Node node) {
        return node != null && node.leftChild.data + node.rightChild.data == node.data;
    }
	
	public boolean isSumProperty(){
		Stack<Node> nodeStack = new Stack<>();
		Node node = root;
		while(!(nodeStack.isEmpty()) || node != null){
			if(node != null){
				nodeStack.push(node);
				node = node.leftChild;
			}else{
				node = nodeStack.pop();
				if(node.leftChild != null && node.rightChild != null){
					if(node.leftChild.data + node.rightChild.data != node.data){
						return false;
					}
				}
				node = node.rightChild;
			}
		}
		return true;
	}

	/**
	 * Using Morris traversal we can traverse tree iteratively without a stack.
	 * The concept of threaded tree is used where each node stores information
	 * of it's inorder successor 
	 */
	public void morrisTraversal(){
		Node current = root;
		Node previous ;
		if(current ==  null){
			return;
		}

		while(current != null){
			if(current.leftChild == null){
				current.displayNode();
				current = current.rightChild;
			}else{
				/**
				 * Find the inorder predecessor of current
				 */
				previous = current.leftChild;
				while(previous.rightChild != null && previous.rightChild != current){
					previous = previous.rightChild;
				}
				
				/**
				 * Make current as right child of it's inorder predecessor
				 */
				if(previous.rightChild == null){
					previous.rightChild = current;
					current = current.leftChild;					
				}else{
					
					/**
					 * Undo the changes to restore the original structure of tree
					 */
					previous.rightChild = null;
					current.displayNode();
					current = current.rightChild;
				}
			}
		}
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
		theTree.insert(43, 17);	
		theTree.insert(30, 15);	
		theTree.insert(87, 17);	
		theTree.insert(93, 15);	
		theTree.insert(13, 4);
		theTree.insert(11, 11);
		theTree.insert(70, 70);
		theTree.insert(72, 72);
		theTree.insert(86,99);

		
		Node root1 = theTree.find(50);	
		theTree.displayTree();
		
		System.out.print("Morris Traversal :: ");
		theTree.morrisTraversal();
		System.out.println();
		
		System.out.println("Is tree balanced ? "+theTree.isBalanced());
		System.out.println();
		
		System.out.print("Breadth First Iterative :: ");
		theTree.breadthFirstTraversal();
		System.out.println();
		
		System.out.print("Spiral Traversal :: ");
		theTree.spiralTraversal();
		System.out.println();
		
		System.out.println("Diameter of tree :: "+theTree.treeDiameter());
		
		System.out.print("InOrder Iterative :: ");
		theTree.inOrderIterative();
		
		System.out.println("LinkList :: "+theTree.toSortedLinkList().toString());
		
		int keyToSearch = 72;
		System.out.print("Node with key "+keyToSearch+" :: ");
		theTree.binarySearch(72).displayNode();
		System.out.println();
		
		System.out.print("All Leaf Nodes :: ");
		System.out.println(theTree.getAllLeaf().toString());

		System.out.println("Does node 12 adheres to ChildSumProperty ? "+theTree.isSumProperty(theTree.find(12)));
		
		System.out.println("Does Tree adheres to CihldSumProperty ? "+ theTree.isSumProperty());
		
		System.out.print("Lowest Common Ancestor of 13 and 30 :: ");
		theTree.lowestCommonAncestor(13, 30).displayNode();
		System.out.println();
		
		System.out.println();
		while(true){
			putText("Enter command for ");	
			putText("show, insert, find, delete, traverse, widthOfEachLevel, isBinaryTree, printAllPath ,levelTravesal, hasSumPath ,heightOfTree, exit or size : ");
			String choice = getString();
			
			switch(choice){
                case "exit":
                    System.exit(0);
                    break;

                case "widthOfEachLevel":
                    theTree.widthOfEachLevel();
                    System.out.println();
                    break;
                case "isBinaryTree":
                    System.out.println("Is tree binary ? "+theTree.isBinaryTree());
                    break;
                case "printAllPath":
                    List<Node> nodeList = new ArrayList<>(theTree.size());
                    theTree.printAllPath(root1,nodeList);
                    break;
                case "hasSumPath":
                    putText("Enter Sum in digits ::");
                    value = getInt();
                    System.out.println("Sum contains in tree ? "+theTree.hasPathSum(value));
                    break;
                case "show":
                    theTree.displayTree();
                    break;
                case "insert":
                    putText("Enter Key to insert: ");
                    value = getInt();
                    putText("Enter Data to insert: ");
                    int data = getInt();
                    theTree.insert(value, data);
                    break;
                case "find":
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
                case "delete":
                    putText("Enter Key to delete: ");
                    value = getInt();
                    boolean didDelete = theTree.delete(value);
                    if(didDelete)
                        putText("Deleted " + value + '\n');
                    else
                        putText("Could not delete " + value + '\n');
                    break;
                case "traverse":
                    putText("Enter type inOrder, preOrder or postOrder : ");
                    input = getString();
                    theTree.traverse(input);
                    break;
                case "levelTravesal":
                    theTree.levelTraversal();
                    break;
                case "heightOfTree":
                    System.out.println("Height of tree :: "+theTree.heightOfTree());
                    break;
                case "size":
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
