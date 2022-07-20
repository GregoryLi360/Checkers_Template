package chessLevel2;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece{
	
	// represents a pawn. 
	// getMoves is trickier than other classes because pawns can move 2 squares
	// if still in their starting position.
	// don't edit this class
	
	public Pawn(int turn, Image img) {
		super(turn, img);
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		ArrayList<int[]> moves = new ArrayList<int[]>();
		int i;
		if (getTeam() == 0)
			i = -1;
		else
			i = 1;
		if (r+i < 8 && r+i >= 0 && board.getBoard()[r+i][c].getTeam() == -1) {
			int[] move = {r+i,c};
			moves.add(move);
		}
		if (getTeam() == 1 && r == 1 && board.getBoard()[r+2][c].getTeam() == -1 && board.getBoard()[r+1][c].getTeam() == -1) {
			int[] move = {r+2,c};
			moves.add(move);
		}
		if (getTeam() == 0 && r == 6 && board.getBoard()[r-2][c].getTeam() == -1 && board.getBoard()[r-1][c].getTeam() == -1) {
			int[] move = {r-2,c};
			moves.add(move);
		}
		if (c+1 < 8 && r+i < 8 && r+i >= 0)
			if (board.getBoard()[r + i][c+1].getTeam() == (getTeam()+1)%2) {
				int[] move = {r+i,c+1};
				moves.add(move);
			}
		if (c-1 >= 0 && r+i < 8 && r+i >= 0)
			if (board.getBoard()[r+i][c-1].getTeam() == (getTeam()+1)%2) {
				int[] move = {r+i,c-1};
				moves.add(move);
			}
		return moves;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean check(int kingr, int kingc, int r, int c, Board board) {
		if (getTeam() == 0) {
			if (kingr - r == -1) { 
				if (Math.abs(kingc - c) == 1)
					return true;
			}
		}
		if (getTeam() == 1) {
			if (kingr - r == 1) {
				if (Math.abs(kingc - c) == 1)
					return true;
			}
		}
		return false;
	}


}
