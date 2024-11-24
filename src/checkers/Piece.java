package checkers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Piece {
	public enum Team {
		RED,
		BLACK,
		NONE;

		public Team toggle() {
			switch (this) {
				case RED: { return BLACK; }
				case BLACK: { return RED; }
				default: { throw new IllegalStateException(); }
			}
		}
	}

	private Team team; 
	
	public Piece(Team turn) {
		team = turn;
	}
	
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Given this piece's location, returns a list of int[2] (coordinates) 
	 * containing all the possible legal squares this piece can move to
	 */ 
	public abstract ArrayList<int[]> getMoves(Board board, int r, int c);
	
	public void draw(Graphics g, int x, int y) {
		int diameter = Checkers.SQUARE_WIDTH;
		Color previousColor = g.getColor();
		g.setColor(team == Team.RED ? Color.RED : Color.BLACK);
		g.fillOval(x + 1, y + 1, diameter - 2, diameter - 2);
		g.setColor(previousColor);
	}
	
	/**
	 * @return whether this is an empty square (is always false except for in {@code class Empty})
	 */ 
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * @return whether this is a King piece (is always false except for in {@code class King})
	 */ 
	public boolean isKing() {
		return false;
	}
}
