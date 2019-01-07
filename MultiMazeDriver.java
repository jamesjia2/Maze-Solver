// Author: James Jia


package mazeworld;

import java.util.*;


public class MultiMazeDriver {
	public static void main(String args[]) {
	
		//MAZE 1
		System.out.println("First Maze");
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
		
		//initialize starting positions
		int[]sx = {0,6};
		int[]sy = {0,6};
		int[]gx = {6,0};
		int[]gy = {6,0};
		
		//get path
		MultiMazeProblem problem = new MultiMazeProblem(sx, sy, gx, gy, 2, smallMaze);
		List<SearchProblem.UUSearchNode> path;
		path = problem.aStarSearch();	
		Collections.reverse(path);
		System.out.println("First Maze Path: ");
		System.out.println(path+"\n");

		
		//MAZE2
		System.out.println("Second Maze");
		Maze smallMaze1 = new Maze(7,7);
		smallMaze1.board[0][1]=1;
		smallMaze1.board[1][5]=1;
		smallMaze1.board[2][5]=1;
		smallMaze1.board[2][4]=1;
		smallMaze1.board[2][2]=1;
		smallMaze1.board[2][1]=1;
		smallMaze1.board[3][4]=1;
		smallMaze1.board[3][2]=1;
		smallMaze1.board[3][1]=1;
		smallMaze1.board[4][1]=1;
		smallMaze1.board[4][0]=1;
		smallMaze1.board[5][0]=1;
		System.out.println(smallMaze1);
		
		
		//initialize starting positions
		int[]sx1 = {0,6,2};
		int[]sy1 = {0,6,3};
		int[]gx1 = {6,0,5};
		int[]gy1 = {6,0,1};
		
		//get path
		MultiMazeProblem problem1 = new MultiMazeProblem(sx1, sy1, gx1, gy1, 3, smallMaze1);
		path = problem1.aStarSearch();	
		Collections.reverse(path);
		System.out.println("Second Maze Path: ");
		System.out.println(path+"\n");
		
		//MAZE3
		System.out.println("Third Maze");
		Maze mediumMaze1 = new Maze(10,10);
		mediumMaze1.board[1][3]=1;
		mediumMaze1.board[2][3]=1;
		mediumMaze1.board[3][3]=1;
		mediumMaze1.board[3][4]=1;
		mediumMaze1.board[3][5]=1;
		mediumMaze1.board[3][6]=1;
		mediumMaze1.board[3][7]=1;
		mediumMaze1.board[2][7]=1;
		mediumMaze1.board[1][7]=1;
		
		mediumMaze1.board[8][3]=1;
		mediumMaze1.board[7][3]=1;
		mediumMaze1.board[6][3]=1;
		mediumMaze1.board[6][4]=1;
		mediumMaze1.board[6][5]=1;
		mediumMaze1.board[6][6]=1;
		mediumMaze1.board[6][7]=1;
		mediumMaze1.board[7][7]=1;
		mediumMaze1.board[8][7]=1;
		System.out.println(mediumMaze1);

		//initialize starting positions
		int[]sx2 = {2,7};
		int[]sy2 = {4,4};
		int[]gx2 = {9,0};
		int[]gy2 = {4,4};
		
		//get path
		MultiMazeProblem problem2 = new MultiMazeProblem(sx2, sy2, gx2, gy2, 2, mediumMaze1);
		path = problem2.aStarSearch();	
		Collections.reverse(path);
		System.out.println("Third Maze Path: ");
		System.out.println(path+"\n");
		
		//MAZE4
		System.out.println("Fourth Maze");
		Maze mediumMaze2 = new Maze(20,20);
		mediumMaze2.board[1][3]=1;
		mediumMaze2.board[2][3]=1;
		mediumMaze2.board[3][3]=1;
		mediumMaze2.board[3][4]=1;
		mediumMaze2.board[3][5]=1;
		mediumMaze2.board[3][6]=1;
		mediumMaze2.board[3][7]=1;
		mediumMaze2.board[2][7]=1;
		mediumMaze2.board[1][7]=1;
		mediumMaze2.board[18][3]=1;
		mediumMaze2.board[17][3]=1;
		mediumMaze2.board[16][3]=1;
		mediumMaze2.board[16][4]=1;
		mediumMaze2.board[16][5]=1;
		mediumMaze2.board[16][6]=1;
		mediumMaze2.board[16][7]=1;
		mediumMaze2.board[17][7]=1;
		mediumMaze2.board[18][7]=1;
		
		System.out.println(mediumMaze2);

		//initialize starting positions
		int[]sx3 = {2,7};
		int[]sy3 = {4,4};
		int[]gx3 = {19,0};
		int[]gy3 = {4,4};
		
		//get path
		MultiMazeProblem problem3 = new MultiMazeProblem(sx3, sy3, gx3, gy3, 2, mediumMaze2);
		path = problem3.aStarSearch();	
		Collections.reverse(path);
		System.out.println("Fourth Maze Path: ");
		System.out.println(path+"\n");
		
		
		//MAZE5
		System.out.println("Fifth Maze");
		Maze largeMaze = new Maze(40,40);
		
		
		//horizontal wall across center
		for(int i=2; i<38; i++){
			largeMaze.board[20][i]=1;
		}
		
		
		
		for(int i=2; i<38; i++){
			largeMaze.board[i][20]=1;
		}
		
		System.out.println(largeMaze);

		//initialize starting positions
		int[]sx4 = {0,39,10};
		int[]sy4 = {0,0,0};
		int[]gx4 = {10,15,0};
		int[]gy4 = {10,15,0};
		
		//get path
		MultiMazeProblem problem4 = new MultiMazeProblem(sx4, sy4, gx4, gy4, 3, largeMaze);
		path = problem4.aStarSearch();	
		Collections.reverse(path);
		System.out.println("Fifth Maze Path: ");
		System.out.println(path);
		
		
	}
}
