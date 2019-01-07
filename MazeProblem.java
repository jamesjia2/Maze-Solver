
// Author: James Jia
// Based off CannibalProblem structure, by Devin Balkcom

package mazeworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


// for the first part of the assignment, you might not extend UUSearchProblem,
//  since UUSearchProblem is incomplete until you finish it.

public class MazeProblem extends SearchProblem{

	// the following are the only instance variables you should need.
	//  (some others might be inherited from UUSearchProblem, but worry
	//  about that later.)

	public Maze maze;
	public int startX, startY, goalX, goalY;
	
	//moveset
	int[]north = {0,1};
	int[]east = {1,0};
	int[]south = {0, -1};
	int[]west = {-1,0};

	public MazeProblem(int sx, int sy, int gx, int gy, Maze startMaze) {

		//create start node, initialize 4x4 maze
		startNode = new MazeNode(sx, sy, 0);
		maze = new Maze(10,10);
		startX = sx;
		startY = sy;
		goalX = gx;
		goalY = gy;
	}
	
	// node class used by searches.  Searches themselves are implemented
	//  in UUSearchProblem.
	public class MazeNode implements UUSearchNode {

		// possible extension: bigger moves
		private final static int MOVE_SIZE = 2;
		private int depth;  
	
		public int[] state; 

		public MazeNode(int x, int y, int d) {
			state = new int[2];
			this.state[0] = x;
			this.state[1] = y;
			depth = d;
		}

		public ArrayList<UUSearchNode> getSuccessors() {
			
			ArrayList<UUSearchNode> successorList = new ArrayList<UUSearchNode>();
			int[] newstate = new int[2];
			
			//try north
			newstate[0] = state[0]+north[0];
			newstate[1] = state[1]+north[1];
			if (isSafe(newstate)){
				MazeNode successorNode = new MazeNode(newstate[0],newstate[1],depth+1);
				successorList.add(successorNode);
			}
			//try east
			newstate[0] = state[0]+east[0];
			newstate[1] = state[1]+east[1];
			if (isSafe(newstate)){
				MazeNode successorNode = new MazeNode(newstate[0],newstate[1],depth+1);
				successorList.add(successorNode);
			}
			//try south
			newstate[0] = state[0]+south[0];
			newstate[1] = state[1]+south[1];
			if (isSafe(newstate)){
				MazeNode successorNode = new MazeNode(newstate[0],newstate[1],depth+1);
				successorList.add(successorNode);
			}
			//try west
			newstate[0] = state[0]+west[0];
			newstate[1] = state[1]+west[1];
			if (isSafe(newstate)){
				MazeNode successorNode = new MazeNode(newstate[0],newstate[1],depth+1);
				successorList.add(successorNode);
			}
			return successorList;
		}
		
		public boolean isSafe(int[] newstate){
			int newX = newstate[0];
			int newY = newstate[1];
			
			//test if within bounds and then if wall
			if (newstate[0]<maze.width && newstate[0]>=0){
				if (newstate[1]<maze.height&& newstate[1]>=0){
					return (maze.board[newX][newY] == 0);
				}
			}
			return false;
		}
		
		@Override
		public boolean goalTest() {
			return (state[0]==goalX && state[1]==goalY);
		}

		// an equality test is required so that visited lists in searches
		// can check for containment of states
		@Override
		public boolean equals(Object other) {
			return Arrays.equals(state, ((MazeNode) other).state);
		}

		@Override
		public int hashCode() {
			return state[0] * 100 + state[1];
		}

		@Override
		public String toString() {
			return "("+state[0]+state[1]+")";
		}

		@Override
		public int getDepth() {
			return depth;
		}
		
		@Override
		public int getManhattan(){	
			return Math.abs(goalX-state[0])+Math.abs(goalY-state[1]);
		}
		
		public int getCost(){
			return getManhattan()+getDepth();
		}
		
		public int compareTo(UUSearchNode other){
			
			if (getCost()>other.getCost()){
				return 1;
			}
			if (getCost()==other.getCost()){
				return 0;
			}
			else {
				return -1;
			}
		}	
		
	}
	public static void main(String args[]) {
		/*
		Maze smallMaze = new Maze(6,6);
		MazeProblem problem = new MazeProblem(2,1,3,3);
		MazeNode startNode = (MazeNode) problem.startNode;

		
		//System.out.println(startNode.getCost());
		
		ArrayList<UUSearchNode> successorList = new ArrayList<UUSearchNode>();
		successorList = startNode.getSuccessors();
		
		PriorityQueue<UUSearchNode> pq = new PriorityQueue<UUSearchNode>();
		
		for (int i = 0; i<successorList.size(); i++){
			//System.out.println(successorList.get(i));
			//System.out.println(((MazeNode) successorList.get(i)).getCost());
			pq.add(successorList.get(i));
		}
		
		//test
		while (!pq.isEmpty()){
			System.out.println(pq.remove());
		}
		*/
		
	}


}


