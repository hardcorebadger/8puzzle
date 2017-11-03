package com.jaketrefethen.eightpuzzle;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Start {
	
	public static boolean useHeuristic = false;
	public static boolean useCost = true;
	// 0 = misplaced tiles, 1 = 
	public static int heuristic = 1;
	public static int difficulty = 20;
	
	public static Comparator<Node> comp = new NodeComparator();
	public static PriorityQueue<Node> frontier = new PriorityQueue<Node>(100000,comp);
	
	public static void main(String[] args) {
		if (args.length < 4) {
			System.out.println("Incorrect Format, Need 4 Arguments: [difficulty] [heuristic] [useHeuristic] [useCost]");
			System.out.println("[difficulty] (integer)");
			System.out.println("[heuristic] 0 = misplaced tiles, 1 = manhattan distance sum, 2 = custom");
			System.out.println("[useHeuristic] n = no, y = yes");
			System.out.println("[useCost] n = no, y = yes");
			System.out.println("Instructions For A*: set [useHeuristic] and [useCost] to yes");
			System.out.println("Instructions For BFS: set [useHeuristic] to no and [useCost] to yes");
			System.out.println("Instructions For DFS: set [useHeuristic] and [useCost] to no");
			System.out.println("Instructions For GBFS: set [useHeuristic] to yes and [useCost] to no");
			System.exit(-1);
		}
		difficulty = Integer.parseInt(args[0]);
		heuristic = Integer.parseInt(args[1]);
		if (args[2].toLowerCase().startsWith("y"))
			useHeuristic = true;
		else
			useHeuristic = false;
		if (args[3].toLowerCase().startsWith("y"))
			useCost = true;
		else
			useCost = false;
		Node n = new Node();
		Solution s = search(n);
		s.print();
	}
	
	public static Solution search(Node initial) {
		frontier.add(initial);
		while (!frontier.isEmpty()) {
			Node n = frontier.poll();
			if (n.goaltest())
				return new Solution(n);
			for (Node n1 : n.expand())
				frontier.add(n1);
		}
		return null;
	}

}
