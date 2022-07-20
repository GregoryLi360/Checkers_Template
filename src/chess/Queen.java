package chessLevel2;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece{

	public Queen(int turn, Image img) {
		super(turn, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) { //just combines the rook and bishop getMoves method
		ArrayList<int[]> moves=new ArrayList<int[]>();
		boolean[] lines=new boolean[8];
		for (int i=1; i<8; i++) 
			for (int j=0; j<4; j++) //runs in 8 directions, 4 diagonal, 4 straight
				for (int k=0; k<2; k++)
					if (lines[j*2+k]) continue;
					else if (k==0) {
						lines[j*2+k]=(r+i*(j%3==0?1:-1))>=8||(r+i*(j%3==0?1:-1))<0||(c+i*(j%2==0?1:-1))>=8||(c+i*(j%2==0?1:-1))<0||!board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].isEmpty();
						if ((r+i*(j%3==0?1:-1))<8&&(r+i*(j%3==0?1:-1))>=0&&(c+i*(j%2==0?1:-1))<8&&(c+i*(j%2==0?1:-1))>=0&&(board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].isEmpty()||board.getBoard()[r+i*(j%3==0?1:-1)][c+i*(j%2==0?1:-1)].getTeam()!=getTeam()))
							moves.add(new int[] {r+i*(j%3==0?1:-1), c+i*(j%2==0?1:-1)}); //add move if spot is empty or enemy piece
					} //bishop moves
					else {
						lines[j*2+k]=(j%2==0?r:r+i*(j%3==0?-1:1))>=8||(j%2==0?r:r+i*(j%3==0?-1:1))<0||(j%2==0?c+i*(j%3==0?-1:1):c)>=8||(j%2==0?c+i*(j%3==0?-1:1):c)<0||!board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].isEmpty();
						if ((j%2==0?r:r+i*(j%3==0?-1:1))<8&&(j%2==0?r:r+i*(j%3==0?-1:1))>=0&&(j%2==0?c+i*(j%3==0?-1:1):c)<8&&(j%2==0?c+i*(j%3==0?-1:1):c)>=0&&(board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].isEmpty()||board.getBoard()[j%2==0?r:r+i*(j%3==0?-1:1)][j%2==0?c+i*(j%3==0?-1:1):c].getTeam()!=getTeam()))
							moves.add(new int[] {j%2==0?r:r+i*(j%3==0?-1:1), j%2==1?c:c+i*(j%3==0?-1:1)});
					} //rook moves
//		System.out.println(getTeam()+" "+moves);
//		for (int[] z:moves) System.out.print(Arrays.toString(z)+" ");
//		System.out.println();
//		System.out.println();
//		for (int i=moves.size()-1; i>=0; i--) {
//			if (board.testBoard(new int[][]{new int[] {r,c}, moves.get(i)}, getTeam())) {
//				moves.remove(i);
//			}
//		}
		return moves;
	}
}
