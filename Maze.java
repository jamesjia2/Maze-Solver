// Author: James Jia

package mazeworld;

public class Maze {

	public int width;
	public int height;
	
	public int[][] board;

	public Maze (int w, int h) {
		
		board = new int[w][h];
		width = w;
		height = h;
		
		for (int y=height-1; y>=0; y--){
			for (int x=0; x<width; x++){
				board[y][x] = 0;
			}
		}
		
	}
	
	public String toString() {
		String maze = "";
		for (int y=height-1; y>=0; y--) {
			for (int x=0; x<width; x++) {
				
				//mark spaces with periods
				if(board[x][y]==0){
					maze+=".";
				}
				//mark walls with pound signs
				if(board[x][y]==1){
					maze+="#";
				}
				//mark path with Os
				if(board[x][y]==2){
					maze+="o";
				}
			}
			maze += "\n";
		}
		return maze;
	}

	public static void main(String args[]) {
		//test maze printout
		Maze m = new Maze(20,20);
		System.out.println(m);
	}

}