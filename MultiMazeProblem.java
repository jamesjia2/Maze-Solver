
// Author: James Jia
// Based off CannibalProblem structure, by Devin Balkcom

package mazeworld;

import java.util.*;


// for the first part of the assignment, you might not extend UUSearchProblem,
//  since UUSearchProblem is incomplete until you finish it.

public class MultiMazeProblem extends SearchProblem{

	public Maze maze;
	public int[] startX, startY, goalX, goalY;
	public int robotNumber;
	
	//construct possible moveset
	int[]north = {0,1};
	int[]east = {1,0};
	int[]south = {0, -1};
	int[]west = {-1,0};
	int[]wait = {0,0};
	
	//may need to include wait
	int[][]moveset = {north, east, south, west, wait};


	public MultiMazeProblem(int[] sx, int[] sy, int[] gx, int[] gy, int rn, Maze startMaze) {

		//create start node, initialize 4x4 maze
		maze = startMaze;
		startX = sx;
		startY = sy;
		goalX = gx;
		goalY = gy;
		robotNumber = rn;
		startNode = new MultiMazeNode(startX, startY, 0, 0);
	}
	
	// node class used by searches.  Searches themselves are implemented
	//  in UUSearchProblem.
	public class MultiMazeNode implements UUSearchNode {

		// uniform cost portion based on depth
		private int depth;  
		//whose turn it is
		private int turn;
		//current state
		private int[][]state = new int[robotNumber][2];

		public MultiMazeNode(int[] xList, int[] yList, int d, int t) {

			for(int i=0;i<robotNumber; i++){
				//set current robot's x/y to corresponding x/y in set of coordinates
				state[i][0] = xList[i];
				state[i][1] = yList[i];	
			}
			depth = d;
			turn = t;
		}

		public ArrayList<UUSearchNode> getSuccessors() {

			ArrayList<UUSearchNode> successorList = new ArrayList<UUSearchNode>();
			int[][] newstate = new int[robotNumber][2];

			//first copy old state
			for(int i=0; i<robotNumber; i++){
				for(int j=0; j<2; j++){
					newstate[i][j]=state[i][j];
				}
			}
			
			//for robot whose turn it is, check all possible moves, then check if overall state is safe
			for(int x=0;x<moveset.length; x++){
				newstate[turn][0]=state[turn][0]+moveset[x][0];
				newstate[turn][1]=state[turn][1]+moveset[x][1];
				
				//check if safe
				if(isSafe(newstate)){
					int[]xList = new int[robotNumber];
					int[]yList = new int[robotNumber];
					for(int a=0; a<robotNumber; a++){
						xList[a]=newstate[a][0];
						yList[a]=newstate[a][1];
					}
					
					//greedy algorithm - set distance to 0 to solely rely on heuristic
					//int distance = 0; 
					int distance=Math.abs(newstate[turn][0]-state[turn][0])+Math.abs(newstate[turn][1]-state[turn][1]);
					//wrap new state in a node and add it to the list
					MultiMazeNode successor = new MultiMazeNode(xList, yList, depth+distance, (turn+1)%robotNumber);
					successorList.add(successor);
				}
			}
			return successorList;
		}

		
		
		public boolean isSafe(int[][] newstate){

			//for each node, check if within bounds and not negative
			for (int i=0; i<robotNumber; i++){

					if (newstate[i][0]>=maze.width || newstate[i][0]<0){
						return false;
					}
					if (newstate[i][1]>=maze.height || newstate[i][1]<0){
						return false;
					}
					
					//check if wall
					if (maze.board[newstate[i][0]][newstate[i][1]] == 1){
						return false;
					}
			}
			
			//check if there are any collisions, i.e. robots can't be in same coordinate
			LinkedList<String> occupied = new LinkedList<String>();
			for(int j=0; j<robotNumber; j++){
				
				//convert coordinates to string to check for equality
				String state = "";
				state=Integer.toString(newstate[j][0])+Integer.toString(newstate[j][1]);
				if (occupied.contains(state)){
					return false;
				}
				occupied.add(state);
			}
			return true;
		}
		
		@Override
		public boolean goalTest() {
			for(int i=0; i<robotNumber; i++){
				if(state[i][0]!=goalX[i]){
					return false;
				}
				if(state[i][1]!=goalY[i]){
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean equals(Object other) {
			//have to check for all robots
			for (int i=0; i<robotNumber; i++){
				//check if x and y coords in state matches
				if (!Arrays.equals(state[i], ((MultiMazeNode) other).state[i])){
					return false;
				}
				//check if both on same turn
				if(turn!=((MultiMazeNode) other).turn){
					return false;
				}
			}
			return true;
		}
		
		//this method was pointed out to me by http://stackoverflow.com/questions/6718749/fast-hashing-of-2-dimensional-array
		@Override
		public int hashCode() {
			return Arrays.deepHashCode(state);
		}
		
		@Override
		public String toString() {
			String coord = new String();
			coord = "";
			for (int i = 0; i < robotNumber; i++) {
				coord += "("+state[i][0]+","+state[i][1]+")";
			}
			return coord;
		}
		
		//heuristic implementation - Manhattan distance
		@Override
		public int getManhattan(){	
			int mDistance = 0;
			for (int i=0; i<robotNumber; i++){
				mDistance+=(Math.abs(goalX[i]-state[i][0]))+Math.abs((goalY[i]-state[i][1]));
			}
			return mDistance;
		}
		
		@Override
		public int getDepth() {
			return depth;
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



}


