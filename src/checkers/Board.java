package checkers;

import java.awt.Color;
import java.awt.Graphics;

import checkers.Piece.Team;

public class Board {
	// represents the entire board - make sure to initialize!.
	private Piece[][] board = new Piece[8][8];
	private boolean gameOver;
	
	/** 
	 * intializes the board so that it looks like the start of a checkers game
	 */
	public Board() {
		for (int i = 0; i < 8; i++) {
			Team team = i <= 2 ? Team.BLACK : i >= 5 ? Team.RED : Team.NONE;

			switch (team) {
				case BLACK, RED: {
					board[i] = getStartingRow(team, i % 2 == 0);
					break;
				}

				case NONE: {
					for (int j = 0; j < 8; j++) {
						board[i][j] = new Empty();
					}
					break;
				}
			}
		}
	}

	private Piece[] getStartingRow(Team team, boolean isOffset) {
		if (!isOffset) {
			return new Piece[] {
				new Pawn(team), new Empty(), new Pawn(team), new Empty(), new Pawn(team), new Empty(), new Pawn(team), new Empty()
			};
		} 

		return new Piece[] {
			new Empty(), new Pawn(team), new Empty(), new Pawn(team), new Empty(), new Pawn(team), new Empty(), new Pawn(team)
		};
	}
	
	// draws the board. There should be a grid of 8x8 squares, and each piece in their location. 
	// the last clicked piece (curr) should be drawn on a yellow background.
	public void draw(Graphics g, Piece curr) {
		int r = -1, c = -1, sideLen = Checkers.SQUARE_WIDTH, xadjust = Checkers.xadjust, yadjust = Checkers.yadjust;

		Color GREEN = new Color(0,100,0);
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				// draw square border
				g.setColor(Color.black);
				g.drawRect(j * sideLen + xadjust, i * sideLen + yadjust, sideLen, sideLen); 

				// fill square color
				g.setColor(i % 2 != j % 2 ? GREEN : Color.white);
				g.fillRect(j * sideLen + 1 + xadjust, i * sideLen + 1 + yadjust, sideLen - 2, sideLen - 2);

				// if the the piece is the selected one
				if (board[i][j].equals(curr)) { 
					// keep track of its coordinate
					r = i;
					c = j;

					// highlight the square with yellow
					g.setColor(Color.yellow);
					g.fillRect(c * sideLen + 1 + xadjust, r * sideLen + 1 + yadjust, sideLen - 2, sideLen - 2);
				}

				// draw the pieces
				board[i][j].draw(g, j * sideLen + xadjust, i * sideLen + yadjust); 
			}
		
		// don't draw possible moves if curr is null or the game is already over
		if (curr == null || gameOver || r == -1 || c == -1) { return; }

		// draws dots for the possible moves of the selected piece
		g.setColor(new Color(255, 0, 150));
		var possibleMoves = curr.getMoves(this, r, c);
		for (int k = 0; k < possibleMoves.size(); k++) {
			int x = possibleMoves.get(k)[1];
			int y = possibleMoves.get(k)[0];
			g.fillOval(
				x * sideLen + sideLen / 3 + xadjust, 
				y * sideLen + sideLen / 3 + yadjust, 
				sideLen / 3,
				sideLen / 3
			);
		}
	}
	
	/** 
	 * Moves the piece currently at (r, c) to (newR, newC), 
	 * filling in the vacated space with an empty square,
	 * and capturing any pieces along the way.
	 * 
	 * If a pawn piece reaches the other side of the board, it should become a king piece.
	 * 
	 * @param r current row (y-coordinate) of piece
	 * @param c current column (x-coordinate) of piece
	 * @param newR new row (y-coordinate) of piece
	 * @param newC new column (x-coordinate) of piece
	 * 
	 * @return 0 for a normal move, 1 for a capturing move, 2 for a winning move (meaning that the opponent has no more pieces)
	 */
	public int move(int r, int c, int newR, int newC) {
		// TODO: move the piece to the new square and account for any captured pieces or promotion
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}
	
	public Piece[][] getBoard() {
		return board;
	}
}
