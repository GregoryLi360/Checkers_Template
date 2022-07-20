package chessLevel2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.HashMap;

public class Board {
	
	// keeps track of the position of each king. First row is the position of the white king, 
	// second row is the black king.
	private int[][] kingPositions = new int[2][2];

	// represents the entire board - make sure to initialize!.
	private Piece[][] board=new Piece[8][8];
	private boolean gameOver;
	private HashMap<String, Image> images;
	
	public Board() {
		
		// loads the images into a map
		images = loadImages();
		
		// sets black and white back row 
		board[0]=new Piece[]{new Rook(1, images.get("BlackRook")), new Knight(1, images.get("BlackKnight")), new Bishop(1, images.get("BlackBishop")), new Queen(1, images.get("BlackQueen")), new King(1, images.get("BlackKing")), new Bishop(1, images.get("BlackBishop")), new Knight(1, images.get("BlackKnight")), new Rook(1, images.get("BlackRook"))};
		board[7]=new Piece[]{new Rook(0, images.get("WhiteRook")), new Knight(0, images.get("WhiteKnight")), new Bishop(0, images.get("WhiteBishop")), new Queen(0, images.get("WhiteQueen")), new King(0, images.get("WhiteKing")), new Bishop(0, images.get("WhiteBishop")), new Knight(0, images.get("WhiteKnight")), new Rook(0, images.get("WhiteRook"))};
		kingPositions[0] = new int[]{7, 4}; //fill king pos
		kingPositions[1] = new int[] {0, 4};
			
		//sets pawns and rest to be empty
		for (int i=0; i<board.length; i++) {
			board[6][i]=new Pawn(0, images.get("WhitePawn"));
			board[1][i]=new Pawn(1, images.get("BlackPawn"));
			if (i>=2&&i<board.length-2) Arrays.fill(board[i], new Empty());
		}
	}
	
	public boolean testBoard(int[][] move, int team) {
		Piece[][] temp = new Piece[board.length][];
		for(int i = 0; i < board.length; i++)
		    temp[i] = board[i].clone();
		temp[move[1][0]][move[1][1]]=temp[move[0][0]][move[0][1]];
		temp[move[0][0]][move[0][1]]=new Empty();
		for (int i=0; i<temp.length; i++) 
			for (int j=0; j<temp[i].length; j++) //checks every piece on the temp if they are checking the opposite team's king
				if (temp[i][j].getTeam()!=team&&temp[i][j].check(kingPositions[team][0], kingPositions[team][1], i, j, this)) return true;
		return false; 
	}
	
	// draws the board. There should be a grid of 8x8 squares, and each piece in their location. 
	// the last clicked piece (curr) should be drawn on a yellow background.
	public void draw(Graphics g, Piece curr) {
		int x=-1, y=-1, sw = Chess.SQUARE_WIDTH, xadjust=Chess.xadjust, yadjust=Chess.yadjust;	// the width of each square on the board and indexes and adjustments for resizing
		for (int i=0; i<board.length; i++) 
			for (int j=0; j<board[i].length; j++) { 
				g.setColor(Color.black);
				g.drawRect(j*sw+xadjust, i*sw+yadjust, sw, sw); //draws squares for grid
				g.setColor(i%2!=j%2?new Color(0,100,0):Color.white); //sets checkerboard color
				g.fillRect(j*sw+1+xadjust, i*sw+1+yadjust, sw-2, sw-2); //fills squares for grid
				if (board[i][j].equals(curr)) { //if the the piece is the selected one
					x=i; y=j; //index it
					g.setColor(Color.yellow);
					g.fillRect(y*sw+1+xadjust, x*sw+1+yadjust, sw-2, sw-2); //highlight the box
				}
				board[i][j].draw(g, j*sw+xadjust, i*sw+yadjust); //draws pieces
			}
		if (curr==null||gameOver||x==-1||y==-1) return; //don't draw moves if curr is null or the game is already over and catches promotion bug
		g.setColor(new Color(255, 0, 0));
		for (int k=0; k<curr.getMoves(this, x, y).size(); k++) //draws red dots for the possible moves of the selected piece on top of everything else
			g.fillOval(curr.getMoves(this, x, y).get(k)[1]*sw+sw/3+xadjust, curr.getMoves(this, x, y).get(k)[0]*sw+sw/3+yadjust, sw/3, sw/3);
	}
	
	// moves the piece currently at (r, c) to (newR, newC), filling 
	// in the vacated space with an empty square.
	// returns 0 for a normal move, 1 for a check move, 2 for a checkmate move
	public int move(int r, int c, int newR, int newC) {
		if (board[r][c].getClass()==King.class) {
			board[r][c].castling(true, ""); //remove both castling
			kingPositions[board[r][c].getTeam()]=new int[] {newR, newC}; //if piece moved is king, change the king position in the 2d array
			if (Math.abs(newC-c)==2) { //checks if king move was a castle
				board[r][newC<c?c-1:c+1]=board[r][newC<c?0:7]; //moves rook
				board[r][newC<c?0:7]=new Empty();
			}
		}
		else if ((r==0&&c==0||r==0&&c==7||r==7&&c==0||r==7&&c==7)&&board[r][c].getClass()==Rook.class&&board[board[r][c].getTeam()==0?7:0][4].getClass()==King.class) 
			board[board[r][c].getTeam()==0?7:0][4].castling(true, c==0?"rookl":"rookr"); //sends if either side castling revoked due to rook move
		else if (board[r][c].getClass()==Pawn.class&&newR==(board[r][c].getTeam()==0?0:7)) //if pawn reaches enemy side's back rank
			board[r][c]=new Queen(board[r][c].getTeam(), images.get(board[r][c].getTeam()==0?"WhiteQueen":"BlackQueen")); //promotes to queen
		try {
			gameOver=board[newR][newC].getClass()==King.class; //game is over if piece captures king
			if (gameOver) return 2; //returns 2 then runs finally
		}
		finally { //always moves the piece to the new square
			board[newR][newC]=board[r][c];
			board[r][c]=new Empty();
		}
		return check()?1:0; //returns 1 or 0 if not 2 depending on if check() is true or false if game is not over
	}
	
	// determines if the non-current team is in check. 
	public boolean check() {
		for (int i=0; i<board.length; i++) 
			for (int j=0; j<board[i].length; j++) //checks every piece on the board if they are checking the opposite team's king
				if (board[i][j].check(kingPositions[(board[i][j].getTeam()+1)%2][0], kingPositions[(board[i][j].getTeam()+1)%2][1], i, j, this)) return true;
		return false; 
	}
	
	/******** DON'T TOUCH THE BELOW CODE ****************/
	
	// loads all necessary images into a map. Key = piece name, value = corresponding image
	public HashMap<String, Image> loadImages() {
		String[] pieces = {"King", "Queen", "Rook", "Bishop", "Knight", "Pawn"};
		
		HashMap<String, Image> images = new HashMap<String, Image>();
		
		for (String p : pieces) {
			for (String color : new String[] {"Black", "White"}) {
				Image img = Toolkit.getDefaultToolkit().getImage("Images/" + color + p + ".png");	
				images.put(color + p, img.getScaledInstance(Chess.IMG_WIDTH, Chess.IMG_WIDTH, Image.SCALE_SMOOTH));
			}
		}
		return images;
	}
	
	public Piece[][] getBoard() {
		return board;
	}
}
