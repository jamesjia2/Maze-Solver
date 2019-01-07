// Author: James Jia


package mazeworld;

import java.util.*;

import mazeworld.MazeProblem.MazeNode;


public class MazeDriver {
	public static void main(String args[]) {
	
		//Create and draw initial maze - from example given on assignment page
		Maze smallMaze = new Maze(7,7);
		smallMaze.board[0][1]=1;
		smallMaze.board[1][5]=1;
		smallMaze.board[2][5]=1;
		smallMaze.board[2][4]=1;
		smallMaze.board[2][2]=1;
		smallMaze.board[2][1]=1;
		smallMaze.board[3][4]=1;
		smallMaze.board[3][2]=1;
		smallMaze.board[3][1]=1;
		smallMaze.board[4][1]=1;
		smallMaze.board[4][0]=1;
		smallMaze.board[5][0]=1;
		System.out.println(smallMaze);

		MazeProblem problem = new MazeProblem(0, 0, 6, 6, smallMaze);
		List<SearchProblem.UUSearchNode> path;
		
		
		//bfs
		System.out.println("BFS Path:");
		path = problem.breadthFirstSearch();	
		Collections.reverse(path);
		
		
		System.out.println(path);
		System.out.println("Path length "+path.size());
		problem.printStats();
		System.out.println("\n");
		
		
		//astar
		System.out.println("Astar Path:");
		path = problem.aStarSearch();	
		Collections.reverse(path);
		System.out.println(path);
		System.out.println("Path length "+path.size());
		problem.printStats();
		
		
		//redraw maze with path marked
		for(int i=0; i<path.size();i++){
			MazeNode node = (MazeNode) path.get(i);
			int x = node.state[0];
			int y = node.state[1];
			
			smallMaze.board[x][y]=2;
		}
		
		System.out.println("\n"+"Path drawing:");
		System.out.println(smallMaze);
		

	
		
		
	}
}
