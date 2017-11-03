package com.jaketrefethen.eightpuzzle;

public class Solution {
	
	private Node[] nodes;
	
	public Solution(Node n) {
		nodes = new Node[n.g()+1];
		Node cur = n;
		for (int i = n.g(); i >= 0; i--) {
			nodes[i] = cur;
			cur = cur.parent;
		}
	}
	
	public void print() {
		System.out.println("=========");
		System.out.println("Solved difficulty [" + Start.difficulty + "] in [" + nodes.length + "] moves!");
		System.out.println("=========");
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].print();
		}
		System.out.println("=========");
	}

}
