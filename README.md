# 8puzzle

## Simple A* on the 8Puzzle in Java

Implements a tree search without pruning on valid representations of the 8 Puzzle Problem. Randomly generates a start state by scrambling an amount of times defined by the difficulty parameter.

## Required Arguments: 
difficulty (int) heuristic (int from below) useHeuristic (y/n) useCost (y/n)

## Heuristics Options
0: Misplaced Tiles
1: Manhattan Distance Sum
2: Custom

## Various Algorithms
Instructions For A*: set useHeuristic and useCost to yes
Instructions For BFS: set useHeuristic to no and useCost to yes
Instructions For DFS: set useHeuristic and useCost to no
Instructions For GBFS: set useHeuristic to yes and useCost to no
