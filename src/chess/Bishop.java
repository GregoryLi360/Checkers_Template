package chessLevel2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {

	public Bishop(int turn, Image img) {
		super(turn, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		ArrayList<int[]> moves=new ArrayList<int[]>();
		boolean[] lines=new boolean[4];
		for (int i=1; i<8; i++) 
			for (int j=0; j<4; j++) {
				if (lines[j]) continue; //skips direction if it's invalid
				lines[j]=(r+i*(j%3==0?1:-1))>=8||(r+i*(j%3==0?1:-1))<0||(c+i*(j%2==0?1:-1))>=8||(c+i*(j%2==0?1:-1))<0||!board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].isEmpty();
				if ((r+i*(j%3==0?1:-1))<8&&(r+i*(j%3==0?1:-1))>=0&&(c+i*(j%2==0?1:-1))<8&&(c+i*(j%2==0?1:-1))>=0&&(board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].isEmpty()||board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].getTeam()!=getTeam()))
					moves.add(new int[] {r+i*(j%3==0?1:-1), c+i*(j%2==0?1:-1)}); //add move if spot is empty or enemy piece
			} //same logic as rook but checks for the diagonals in the order ++ -- +- -+
		return moves;
	}
}
