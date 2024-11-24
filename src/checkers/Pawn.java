package checkers;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board, int r, int c) {
        // TODO: come up with the list of moves a king piece can make
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }
    
}
