package com.DataStructure.trees;

import java.util.*;

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
	
	
	public void levelTraversalIterative(){
		Queue<Node> queue = new ArrayDeque<>(size());
		queue.add(root);
		Node node;
		
		while(queue.size() > 0){
			node = queue.poll();
			node.displayNode();
			
			if (node.leftChild != null){
				queue.add(node.leftChild);
			}
			if (node.rightChild != null){
				queue.add(node.rightChild);
			}
		}
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
	 * to verify that it is a BST, and that all its nodes are within the min...max range.
	 * Works in O(n) time -- visits each node only once.
	 */
	private boolean isBinaryTree(Node node, int min, int max){
		if(node == null){
            return true;
		}else{
            if(node.key <= min || node.key > max){
                return false;
            }else {
                if(isBinaryTree(node.leftChild,min,node.key) &&
                        (isBinaryTree(node.rightChild, node.key, max))){
                    return true;
                }else {
                    return false;
                }
            }
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


    public void preOrderIterative(){
        preOrderIterative(root);
        System.out.println();
    }

    private void preOrderIterative(Node node){
        Stack<Node> nodeStack = new Stack<>();
        Node poppedNode;
        nodeStack.push(node);
        while (!nodeStack.isEmpty() || node != null){

            if(nodeStack.size() <= 0){
                break;
            }
            poppedNode = nodeStack.pop();
            poppedNode.displayNode();
            if(poppedNode.rightChild != null){
                nodeStack.push(poppedNode.rightChild);
            }
            if(poppedNode.leftChild != null){
                nodeStack.push(poppedNode.leftChild);
            }
        }
    }

    public void postOrderIterative(){
        postOrderIterative(root);
    }

    private void postOrderIterative(Node node){
        Stack<Node> nodeStack = new Stack<>();

        while (true){

            while (node != null){
                if (node.rightChild != null){
                    nodeStack.push(node.rightChild);
                }
                nodeStack.push(node);
                node = node.leftChild;
            }

            node = nodeStack.pop();

            if (!nodeStack.isEmpty()){
                if (node.rightChild == nodeStack.peek()){
                    Node tempNode = node;
                    node = nodeStack.pop();
                    nodeStack.push(tempNode);
                }else{
                    node.displayNode();
                    node = null;
                }
            }else {
                node.displayNode();
                break;
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
        return checkHeight(root) != -1;
    }
	/**
     * Return the height of the tree. Better recursive approach
     */
    private int checkHeight(Node node){
        if (node == null){
            return 0;
        }

        int leftHeight = checkHeight(node.leftChild);
        if (leftHeight == -1){
            return -1;
        }

        int rightHeight = checkHeight(node.rightChild);
        if (rightHeight == -1){
            return -1;
        }

        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1){
            return -1;
        }else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
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


    /**
     * Modifies the tree such that each of the nodes in left sub tree is replace by node
     * in right sub tree. The positions of nodes are exactly mirror imaged
     */
    public Tree mirrorTree(){
        Tree mirrorTree = new Tree();
        mirrorTree.root = root;
        mirrorTree.mirrorTree(mirrorTree.root);
        return mirrorTree;
    }

    /**
     * Helper function for mirrorTree()
     */
    private void mirrorTree(Node node){
        if(node !=  null){
            Node temp;
            mirrorTree(node.leftChild);
            mirrorTree(node.rightChild);

            temp = node.leftChild;
            node.leftChild = node.rightChild;
            node.rightChild = temp;
        }
    }


    /**
     * Creates the new tree with root equal to leftChild of "this" tree
     */
    public Tree leftSubTree(){
        Tree leftTree = new Tree();
        leftTree.root = root.leftChild;
        return leftTree;
    }

    /**
     * Creates the new tree with root equal to rightChild of "this" tree
     */
    public Tree rightSubTree(){
        Tree rightTree = new Tree();
        rightTree.root = root.rightChild;
        return rightTree;
    }

    /**
     * Merge tree as name signifies is used to combine two binary trees and returns
     * a merged binary tree.
     *
     * Approach of converting tree to linked list and then inserting might seem
     * little bit tedious. But method is predefined so I used this approach.
     *
     * Efficient way is to traverse tree in any fashion and perform insertion
     * during the traversal itself which way reduce the space used by linked list.
     *
     * @param tree  a Tree which is to be merged with tree in context
     * @return Tree a Tree which is result of the merging.
     */
    public Tree mergeTree(Tree tree){
        Tree resultantTree = tree;
        LinkedList<Node> nodeList =  toSortedLinkList();
        for (Node aNodeList : nodeList) {
            resultantTree.insert(aNodeList.key, aNodeList.data);
        }
        return resultantTree;
    }


    /**
     * Print nodes at given level of tree.
     * Uses iterative approach.
     */

    public void printGivenLevelIterative(int level){
        printGivenLevelIterative(root, level);
        System.out.println();
    }

    private void printGivenLevelIterative(Node node, int level){
        Queue<Node> nodeQueue = new LinkedList<>();
        Node removedNode ;
        int currentLevel = 0;

        nodeQueue.add(node);
        nodeQueue.add(null);

        while (!nodeQueue.isEmpty()){

            if(currentLevel == level){
                while (nodeQueue.peek() != null){
                    removedNode = nodeQueue.remove();
                    removedNode.displayNode();
                }
            }

            removedNode = nodeQueue.remove();

            if(removedNode == null){
                if(!nodeQueue.isEmpty()){
                    nodeQueue.add(null);
                }
                currentLevel++;
            }else {
                if(removedNode.leftChild != null){
                    nodeQueue.add(removedNode.leftChild);
                }
                if (removedNode.rightChild != null){
                    nodeQueue.add(removedNode.rightChild);
                }
            }
        }
    }

    public Node kthSmallestNode(int k){
        Node current = root;
        Stack<Node> nodeStack = new Stack<>();

        while (current != null){
            nodeStack.push(current);
            current = current.leftChild;
        }

        while ((current = nodeStack.pop()) != null){
            if(--k == 1){
                break;
            }

            if(current.rightChild != null){
                current = current.rightChild;

                while (current != null){
                    nodeStack.push(current);
                    current = current.leftChild;
                }
            }
        }
        return current;
    }

    /**
     * @return arrayList containing LinkedList of each node in respected level
     */
    public ArrayList<LinkedList<Node>> createLevelLinkedList(){
        ArrayList<LinkedList<Node>> result = new ArrayList<>();
        LinkedList<Node> current = new LinkedList<>();

        if (root != null){
            current.add(root);
        }

        while (current.size() > 0){
            result.add(current);

            /**
             * Save the reference to previous level and
             * add their childrens in new current.
             */
            LinkedList<Node> parents = current;
            current = new LinkedList<>();
            for (Node parent : parents){
                if (parent.leftChild != null){
                    current.add(parent.leftChild);
                }
                if (parent.rightChild != null){
                    current.add(parent.rightChild);
                }
            }
        }
        return result;
    }

    /**
     * @param node A Node to check if is a descendant of root
     * @return boolean TRUE or FALSE
     */
    public boolean isDescendant(Node node){
        return isDescendant(root, node);
    }
    /**
     * Helper function if isDescendant() and commonAncestor()
     */
    private boolean isDescendant(Node root, Node child){
        if (root == null){
            return false;
        }
        if (root == child){
            return true;
        }
        return isDescendant(root.leftChild, child) || isDescendant(root.rightChild, child);
    }

    /**
     * Returns the commonAncestor of the nodes. Works even if the tree is not
     * Binary Search Tree and no parent link.
     */
    public Node commonAncestor(Node node1, Node node2){
        /* Check if both nodes are in the tree */
        if (!isDescendant(root, node1) || !isDescendant(root, node2)){
            return null;
        }
        return commonAncestorHelper(root, node1, node2);
    }

    /**
     * Helper function for commonAncestor()
     */
    private Node commonAncestorHelper(Node root, Node node1, Node node2){
        if (root == null){
            return null;
        }
        if (root == node1 || root == node2){
            return root;
        }
        boolean is_node1_onLeftSide = isDescendant(root.leftChild, node1);
        boolean is_node2_onLeftSide = isDescendant(root.leftChild, node2);
        if (is_node1_onLeftSide != is_node2_onLeftSide){
            return root;
        }
        Node newRoot = is_node1_onLeftSide ? root.leftChild : root.rightChild;
        return commonAncestorHelper(newRoot, node1, node2);
    }
    
    public int shortestPath(int key1, int key2) {
    	Node lca = lowestCommonAncestor(key1, key2);
    	if (lca == null){
    		return -1;
    	}
    	
    	Node current = lca;
    	int distance = 0;
    	while(true){
    		if (current.key < key1){
    			current = current.leftChild;
    		}else if (current.key > key2){
    			current = current.rightChild;
    		}else{
    			break;
    		}
    		distance++;
    	}
    	
    	current = lca;
    	while(true){
    		if (current.key < key1){
    			current = current.leftChild;
    		}else if (current.key > key2){
    			current = current.rightChild;
    		}else{
    			break;
    		}
    		distance++;
    	}  	
    	return distance;
	}
}

public class TreeApp {

	public static void main(String[] args) throws Exception {

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

		theTree.displayTree();
		
		theTree.inOrderIterative();
	}
}
