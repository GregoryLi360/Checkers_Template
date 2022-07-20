package chessLevel2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {
	private boolean[] castle;
	
	public King(int turn, Image img) {
		super(turn, img);
		castle=new boolean[2];
		// TODO Auto-generated constructor stub
	}

	public boolean[] castling(boolean moved, String p) {
		if (p.equalsIgnoreCase("rookl")) castle[0]=true;
		else if (p.equalsIgnoreCase("rookr")) castle[1]=true;
		else Arrays.fill(castle, true);
		return castle;
	}
	
	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		ArrayList<int[]> moves=new ArrayList<int[]>();
		for (int i=-1; i<=1; i++)
			for (int j=-1; j<=1; j++) //checks 8 spaces around the king
				if (r+i>=8||r+i<0||c+j>=8||c+j<0||board.getBoard()[r+i][c+j].getTeam()==getTeam()) continue; //dont add if it's off the board or has a friendly piece in that square
				else moves.add(new int[] {r+i, c+j}); //else add move
		for (int i=-1; i<=1; i+=2)
			if (!castle[(i+1)%3/2]&&((i+1)%3/2!=0||board.getBoard()[r][c+i*3].isEmpty())&&board.getBoard()[r][c+i].isEmpty()&&board.getBoard()[r][c+i*2].isEmpty()&&board.getBoard()[r][(i==1?7:0)].getClass()==Rook.class)
				moves.add(new int[] {r, c+i*2}); //if castling is valid (king hasn't moved, rook hasn't moved) add the option to 
		return moves;
	}
}
