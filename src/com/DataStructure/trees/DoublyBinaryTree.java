package com.DataStructure.trees;

import java.util.Stack;

/**
 * Created by VIRAL on 3/24/2015.
 */

/**
 * Only difference between regular binary tree and doubly binary tree is that,
 * in doubly binary tree each node has reference to its parent in addition
 * to its left and right child.
 */
class DoublyNode{
    int key;
    int data;
    DoublyNode leftChild;
    DoublyNode rightChild;
    DoublyNode parent;

    public DoublyNode() {
    }

    public DoublyNode(int key, int data){
        this.key = key;
        this.data = data;
    }

    public void displayDoublyNode(){
        System.out.print('{');
        System.out.print(key);
        System.out.print(", ");
        System.out.print(data);
        System.out.print("} ");
    }

    @Override
    public String toString() {
        return "{"+key+","+data+"}";
    }
}

class DoublyTreeImplementation{
    private DoublyNode root;

    public DoublyTreeImplementation(){
        root = null;
    }

    public DoublyTreeImplementation(DoublyNode root){
        this.root = root;
        root.parent = root;
    }

    public void insert(int key, int data) {
        DoublyNode insertNode = new DoublyNode(key, data);

        if (root == null) {
            root = insertNode;
            root.parent = root;
        } else {
            DoublyNode current = root;
            DoublyNode parent;
            while (true) {
                parent = current;
                if (current.key > key) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = insertNode;
                        parent.leftChild.parent = parent;
                        return;
                    }
                } else if (current.key < key) {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = insertNode;
                        parent.rightChild.parent = parent;
                        return;
                    }
                }
            }
        }
    }

    public DoublyNode find(int key){
        DoublyNode current = root;

        while (current.key != key){
            if (current.key > key){
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

    public DoublyNode getParent(int key){
        DoublyNode doublyNode = find(key);
        if (doublyNode == null){
            return null;
        }else{
            return doublyNode.parent;
        }
    }

    public void displayTree(){
        Stack<DoublyNode> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 50;
        boolean isRowEmpty = false;
        System.out.println("......................................................");

        while(!isRowEmpty){
            Stack<DoublyNode> localStack = new Stack<>();
            isRowEmpty = true;

            for(int j=0; j<nBlanks; j++){
                System.out.print(' ');
            }

            while(!globalStack.isEmpty()){
                DoublyNode temp = globalStack.pop();
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
}

public class DoublyBinaryTree {
    public static void main(String[] args) {
        DoublyTreeImplementation doublyTree = new DoublyTreeImplementation(new DoublyNode(50,50));

        doublyTree.insert(45,45);
        doublyTree.insert(60,60);
        doublyTree.insert(32,32);
        doublyTree.insert(47,47);
        doublyTree.insert(55,55);

        doublyTree.displayTree();
    }
}
