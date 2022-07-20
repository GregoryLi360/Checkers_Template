package chessLevel2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {

	public Knight(int turn, Image img) {
		super(turn, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) { //sets arraylist to all the 8 moves a knight can make
		ArrayList<int[]> moves=new ArrayList<int[]>(Arrays.asList(new int[][]{{r-2, c-1},{r-1, c-2},{r+1, c-2},{r+2, c-1},{r+2, c+1},{r+1, c+2},{r-1, c+2},{r-2, c+1}}));
		for (int i=moves.size()-1; i>=0; i--) //goes backward through the arraylist
			if (moves.get(i)[0]>=8||moves.get(i)[0]<0||moves.get(i)[1]>=8||moves.get(i)[1]<0||board.getBoard()[moves.get(i)[0]][moves.get(i)[1]].getTeam()==getTeam()) moves.remove(i);
		return moves; //removes the move if it is out of the board or has a friendly piece
	}
}
