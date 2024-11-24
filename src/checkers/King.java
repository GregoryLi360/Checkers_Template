package checkers;

import java.awt.Graphics;
import java.util.ArrayList;

public class King extends Piece {
    public King(Team team) {
        super(team);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
		super.draw(g, x, y);
        // TODO: draw something on top of the piece to distinguish that it is a king
	}

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        // TODO: come up with the list of moves a king piece can make
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
