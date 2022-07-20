package chessLevel2;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {
	
	// generic class to represent any chess piece
	// don't edit this class
	
	private int team; // either 0 or 1
	private Image img; 
	
	public Piece(int turn, Image img) {
		team = turn;
		this.img = img;
	}
	
	public int getTeam() {
		return team;
	}
	
	// given this piece's location, returns a list of 2-element arrays (coordinates) 
	// containing all the possible legal squares this piece can move to.
	public abstract ArrayList<int[]> getMoves(Board board, int r, int c);
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(img, x, y, Chess.IMG_WIDTH, Chess.IMG_WIDTH, null);
	}
	
	// returns whether this is an empty square (is always false except empty)
	public boolean isEmpty() {
		return false;
	}
	
	//never used but is always false except in king class
	public boolean isKing() {
		return false;
	}
	
	// given the location of the opponent's king and this piece's location,
	// determines if this piece has put the king in check.
	public boolean check(int kingr, int kingc, int r, int c, Board board) {
		for (int[] temp:getMoves(board, r, c)) //for every piece other than the pawn, if the king is in the moves it can make on the next turn, it is a check
			if (Arrays.equals(temp, new int[] {kingr, kingc})) return true;
		return false;
	}

	public boolean[] castling(boolean b, String s) {return new boolean[] {false, false};}

}
