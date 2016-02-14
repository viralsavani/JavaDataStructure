package com.Algorithms;

import java.util.ArrayList;

class Node{
	private final String packageName;
	private ArrayList<Node> dependencyList;
	
	public Node(String packageName){
		this.packageName = packageName;
		dependencyList = new ArrayList<>();
	}
	
	public boolean addDependency(Node node){
		return dependencyList.add(node);
	}
	
	public Node[] getDependencies(){
		Node[] dependencies = new Node[dependencyList.size()];
		dependencyList.toArray(dependencies);
		return dependencies;
	}
	
	@Override
	public String toString() {
		return packageName;
	}
}

public class DependencyResolver {
	
	public static ArrayList<Node> dependencyResolve(
			Node node, 
			ArrayList<Node> resolved,
			ArrayList<Node> unResolved) throws Exception{
		
		unResolved.add(node);
		for (Node node1 : node.getDependencies()){
			// Resolve nodes which are not visited
			if (!resolved.contains(node1)){
				// Check for circular reference (cycle)
				if (unResolved.contains(node1)){
					throw new Exception("Circular reference found:" + node.toString() + " and " + node1.toString());
				}
				dependencyResolve(node1, resolved, unResolved);
			}
		}
		resolved.add(node);
		unResolved.remove(node);
		return resolved;
		
	}
}
