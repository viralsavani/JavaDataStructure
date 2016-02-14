package com.DataStructure.lruCache;

import java.util.HashMap;

class DoublyNode<T,V>{
	final T key;
	private V data;
	DoublyNode<T, V> next;
	DoublyNode<T, V> previous;
	
	public DoublyNode(T key, V data){
		if (key == null || data == null){
			throw new IllegalArgumentException("Key or data cannot be null");
		}
		
		this.key = key;
		this.data = data;
	}
	
	public void setData(V data){
		if (data == null){
			throw new IllegalArgumentException("Data cannot be null");
		}
		this.data = data;
	}
	
	public V getData(){
		return data; 
	}
		
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(").
		append(key.toString()).
		append("->").
		append(getData().toString()).
		append(")");
		
		return builder.toString();
	}
}


class DoublyLinkList<T,V>{	
	private DoublyNode<T,V> head;
	private DoublyNode<T,V> tail;
	private int size = 0;
	
	public boolean addToHead(DoublyNode<T, V> node){
		if (node == null){
			return false;
		}
		
		if (head == null){
			head = node;
			head.next = null;
			tail = node;
			tail.next = null;
		}else {
			head.previous = node;
			node.next = node;
			head = node;
		}
		size++;		
		return true;
	}	
	
	public boolean moveToFirst(DoublyNode<T,V> node){
		if (node == null){
			return false;
		}
		
		if (head == node){
			return true;
		}
		// Remove the node from list and connect it's next and previous
		node.previous.next = node.next;
		node.next.previous = node.previous;
		
		// Add node to head
		node.next = head;
		head.previous = node;
		head = node;
		return true;
	}
	
	public boolean removeTail(){
		return remove(tail);
	}
	
	public boolean remove(DoublyNode<T, V> node){
		if (head == null || node == null){
			return false;
		}
		
		if (head == tail && node == head){
			head = null;
			tail = null;
			size = 0;
			return true;
		}
		
		if (tail == node){
			tail.previous.next = null;
			tail = tail.previous;
			return true;
		}
		
		node.previous.next = node.next;
		node.next.previous = node.previous;
		node = null;
		return true;
		
	}
	
	public DoublyNode<T, V> getHead(){
		return head;
	}
	
	public DoublyNode<T,V> getTail(){
		return tail;
	}
	
	public void displayList(){
		if (head == null){
			return;
		}
		if (size == 1){
			System.out.println(head.toString());
			return;
		}
		
		DoublyNode<T, V> current = head;
		while (current != null){
			System.out.print(current.toString());
			current = current.next;
		}
	}
	
}

public class LRUCache<T, V> {
	private final int INIT_CAPACITY = 16;
	private int thresold = INIT_CAPACITY - 4;
	private HashMap<T, DoublyNode<T,V>> map;
	private DoublyLinkList<T, V> list;
	private int cacheSize;
	
	public LRUCache(int init_size) {
		if (init_size > INIT_CAPACITY){
			map = new HashMap<>(init_size);
			thresold = init_size - 4;
		}else{
			map = new HashMap<>(INIT_CAPACITY);
		}
		list = new DoublyLinkList<>();
		cacheSize = 0;
	}
	
	public void setInCache(T key, V data){
		DoublyNode<T, V> node;
		if (!map.containsKey(key)){
			node = new DoublyNode<T, V>(key, data);
			map.put(key, node);
			list.addToHead(node);
			cacheSize++;
			if (cacheSize > thresold){
				evict();
			}
		}else{
			node = map.get(key);
			node.setData(data);
			list.moveToFirst(node);
		}
	}
	
	public V getFromCache(T key){
		DoublyNode<T, V> node;
		if (map.containsKey(key)){
			node = map.get(key);
			list.moveToFirst(node);
			return node.getData();
		}
		return null;
	}
	
	private boolean evict(){
		int evicts = cacheSize - thresold;
		while (evicts >= 0){
			list.removeTail();
			evicts--;
		}
		return true;
	}	
}
