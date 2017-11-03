package com.jaketrefethen.eightpuzzle;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	
	private int[] state;
	public Node parent;
	private int pathCost;
	
	public Node() {
		state = scramble();
		parent = null;
		pathCost = 0;
	}
	
	public Node(int[] p) {
		state = p;
		parent = null;
		pathCost = 0;
	}
	
	public Node(int[] p, Node pa, int g) {
		state = p;
		parent = pa;
		pathCost = g;
	}
	
	public int[] getPuzzle() {
		return state;
	}
	
	public int f() {
		int f = 0;
		if (Start.useHeuristic)
			f += h();
		if (Start.useCost)
			f += g();
		return f;
	}
	
	public int g() {
		return pathCost;
	}
	
	public int h() {
		switch (Start.heuristic) {
		case 0:
			return misplacedTiles();
		case 1:
			return manhattanSum();
		case 2:
			return 0;
		default:
			return 0;
		}
	}
	
	public boolean goaltest() {
		return misplacedTiles() == 0;
	}
	
	public ArrayList<Node> expand() {
		ArrayList<Node> nodes = new ArrayList<Node>();
		int blank = findBlank();
		if (blank != 0 && blank != 3 && blank != 6)
			nodes.add(swap(-1));
		if (blank != 2 && blank != 5 && blank != 8)
			nodes.add(swap(1));
		if (blank != 0 && blank != 1 && blank != 2)
			nodes.add(swap(-3));
		if (blank != 6 && blank != 7 && blank != 8)
			nodes.add(swap(3));
		return nodes;
	}
	
	public void print() {
		System.out.println("---------");
		System.out.println("["+f()+"] g:"+g()+" h:"+h());
		System.out.println("---------");
		for (int r = 0; r < 9; r+=3) {
			System.out.print("| ");
			for (int c = 0; c < 3; c++) {
				if (state[r+c] == 0)
					System.out.print("_ ");
				else
					System.out.print(state[r+c] + " ");
			}
			System.out.println("|");
		}
		System.out.println("---------");
	}
	
	private int findBlank() {
		int b = -1;
		for (int i = 0; i < 9; i++) {
			if (state[i] == 0)
				b = i;
		}
		return b;
	}
	
	private Node swap(int relativeIndex) {
		int blank = findBlank();
		int[] i = state.clone();
		i[blank] = i[blank+relativeIndex];
		i[blank+relativeIndex] = 0;
		return new Node(i,this,pathCost+1);
	}
	
	private int[] scramble() {
		Node n = new Node(new int[]{1,2,3,4,5,6,7,8,0});
		Random r = new Random();
		for (int i = 0; i < Start.difficulty; i++) {
			ArrayList<Node> nodes = n.expand();
			n = nodes.get(r.nextInt(nodes.size()));
		}
		return n.getPuzzle();
	}
	
	public int misplacedTiles() {
		int m = 0;
		for (int i = 0; i < 9; i++) {
			if (state[i] == 0) {
				if (i != 8)
					m++;
			} else {
				if (state[i] != i+1)
					m++;
			}
		}
		return m;
	}
	
	public int manhattanSum() {
		int m = 0;
		for (int i = 0; i < 9; i++) {
			if (state[i] == 0) {
				if (i != 8) {
					m+=manhattanDistance(i,8);
				}
			} else {
				if (state[i] != i+1) {
					m+=manhattanDistance(i,state[i]-1);
				}
			}
		}
		return m;
	}
	
	private int manhattanDistance(int index, int goalIndex) {
		int cur = index;
		int m = 0;
		while (cur/3 < goalIndex/3) {
			m++;
			cur+= 3;
		}
		while (cur < goalIndex) {
			m++;
			cur++;
		}
		while (cur > goalIndex) {
			m++;
			cur--;
		}
		return m;
	}

}
