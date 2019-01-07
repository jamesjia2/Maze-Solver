
// Author: James Jia
// Based off UUSearchProblem structure, by Devin Balkcom

package mazeworld;

import java.util.*;


public abstract class SearchProblem {
	
	// used to store performance information about search runs.
	//  these should be updated during the process of searches

	// see methods later in this class to update these values
	protected int nodesExplored;
	protected int maxMemory;

	protected UUSearchNode startNode;
	
	protected interface UUSearchNode extends Comparable<UUSearchNode>{
		public ArrayList<UUSearchNode> getSuccessors();
		public int getManhattan();
		public boolean goalTest();
		public int compareTo(UUSearchNode node);
		public int getDepth();
		public int getCost();
	}

	
	
	//Search with uniform cost elements as well as heuristic elements
	//cost of move is sum of node depth and manhattan distance
	public List<UUSearchNode> aStarSearch(){
		
		resetStats();
		//Priority Queue
		PriorityQueue<UUSearchNode> exploreQueue = new PriorityQueue<UUSearchNode>();
		
		//visited for backchain, costMap to keep track of costs
		HashMap<UUSearchNode, UUSearchNode> visited = new HashMap<>();
		HashMap<UUSearchNode, Integer> costMap = new HashMap<>();
		
		List<UUSearchNode> successors;
		List<UUSearchNode> finalPath;
		UUSearchNode node = startNode;
		
		//put startnode into the queue and the visited set and the cost map
		exploreQueue.add(node);
		visited.put(node, null);
		costMap.put(node, node.getCost());
		
		boolean empty = exploreQueue.isEmpty();
		
		//until there are no more nodes to explore in the queue
		while (!empty){

			//remove the first value in the queue and store it in currentNode
			incrementNodeCount();
			UUSearchNode currentNode = exploreQueue.poll();
			successors = currentNode.getSuccessors();
			updateMemory(exploreQueue.size() + visited.size() + costMap.size());
			
			if(currentNode.goalTest()){
				//call backchain to return path from end to startnode when goal is reached
				finalPath = backchain(currentNode, visited);
				return finalPath;
			}
			
			//if not goal, then look at successors
			for (int j=0; j<successors.size(); j++){	
				UUSearchNode successor = successors.get(j);
				
				//if successor hasn't been visited or is currently mapped to higher cost, put it in visited and put it in the queue
				if (!costMap.containsKey(successor)|| successor.getCost()<costMap.get(successor)){
					visited.put(successor, currentNode);
					costMap.put(successor, successor.getCost());
					exploreQueue.add(successor);
				}	
			}	
		}
		//if goal isn't found, return null;
		return null;
	}
	
	
	// breadthFirstSearch:  return a list of connecting Nodes, or null
	// no parameters, since start and goal descriptions are problem-dependent.
	//  therefore, constructor of specific problems should set up start
	//  and goal conditions, etc.
	public List<UUSearchNode> breadthFirstSearch(){

		resetStats();
		
		//Use linked list as queue
		LinkedList<UUSearchNode> exploreQueue = new LinkedList<UUSearchNode>();
		HashMap<UUSearchNode, UUSearchNode> visited = new HashMap<>();
		List<UUSearchNode> successors;
		List<UUSearchNode> finalPath;
		UUSearchNode node = startNode;
		
		//put startnode into the queue and the visited set
		exploreQueue.add(node);
		visited.put(node, null);
		incrementNodeCount();
		
		boolean empty = exploreQueue.isEmpty();
		
		//until there are no more nodes to explore in the queue
		while (!empty){
			
			incrementNodeCount();
			//System.out.println(exploreQueue);
			//remove the first value in the queue and store it in currentNode
			UUSearchNode currentNode = exploreQueue.removeFirst();
			successors = currentNode.getSuccessors();
			
			if(currentNode.goalTest()){
				updateMemory(visited.size()+exploreQueue.size());
				//call backchain to return path from end to startnode when goal is reached
				finalPath = backchain(currentNode, visited);
				return finalPath;
			}
			
			//if not goal, then look at successors
			for (int j=0; j<successors.size(); j++){	
				UUSearchNode successor = successors.get(j);
				
				//if successor hasn't been visited, put it in visited and put it in the queue
				if (!visited.containsKey(successor)){
					visited.put(successor, currentNode);
					exploreQueue.addLast(successor);
				}	
			}	
		}
		//if goal isn't found, return null;
		return null;
	}
	
	
	// backchain should only be used by bfs, not the recursive dfs
	private List<UUSearchNode> backchain(UUSearchNode node,
			HashMap<UUSearchNode, UUSearchNode> visited) {
		
		List<UUSearchNode> finalPath = new ArrayList<UUSearchNode>();
		finalPath.add(node);
		
		//Add node to path until reaching startnode key, which maps to null
		while (visited.get(node)!=null){
			finalPath.add(visited.get(node));
			node=visited.get(node);
		}
		return finalPath;
	}

	protected void resetStats() {
		nodesExplored = 0;
		maxMemory = 0;
	}
	
	protected void printStats() {
		System.out.println("Nodes explored during last search:  " + nodesExplored);
		System.out.println("Maximum memory usage during last search " + maxMemory);
	}
	
	protected void updateMemory(int currentMemory) {
		maxMemory = Math.max(currentMemory, maxMemory);
	}
	
	protected void incrementNodeCount() {
		nodesExplored++;
	}

}
