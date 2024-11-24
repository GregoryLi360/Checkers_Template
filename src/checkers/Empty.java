package checkers;

import java.awt.Graphics;
import java.util.ArrayList;

// represents an empty square on the board. 
// don't edit this class. 
public class Empty extends Piece {
	public Empty() {
		super(Team.NONE);
	}

	@Override
	public ArrayList<int[]> getMoves(Board board, int r, int c) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		return;
	}
}
