package chessLevel2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece {

	public Rook(int turn, Image img) {
		super(turn, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		ArrayList<int[]> moves=new ArrayList<int[]>();
		boolean[] lines=new boolean[4];
		for (int i=1; i<8; i++) //max possible moves from rook
			for (int j=0; j<4; j++) { //4 directions from rook
				if (lines[j]) continue; //skips the direction if it has already been blocked
				lines[j]=(j%2==0?r:r+i*(j%3==0?-1:1))>=8||(j%2==0?r:r+i*(j%3==0?-1:1))<0||(j%2==0?c+i*(j%3==0?-1:1):c)>=8||(j%2==0?c+i*(j%3==0?-1:1):c)<0||!board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].isEmpty();
				if ((j%2==0?r:r+i*(j%3==0?-1:1))<8&&(j%2==0?r:r+i*(j%3==0?-1:1))>=0&&(j%2==0?c+i*(j%3==0?-1:1):c)<8&&(j%2==0?c+i*(j%3==0?-1:1):c)>=0&&(board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].isEmpty()||board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].getTeam()!=getTeam()))
					moves.add(new int[] {j%2==0?r:r+i*(j%3==0?-1:1), j%2==1?c:c+i*(j%3==0?-1:1)});
			} //2 lines of legendary code that will make your brain hurt while reading it but it works magically
		return moves; //(adds move if the square is empty or an enemy piece and the direction is valid and makes the direction no longer valid if it is off the board or not empty)
	}
}