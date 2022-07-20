package chessLevel2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chess {
	
	
	/******** DON'T EDIT THIS CLASS ********/
	
		
	// constants that are predefined and won't change
	public static int width = 600;
	public static int SQUARE_WIDTH = width/8;
	public static int IMG_WIDTH = SQUARE_WIDTH;
	public static int xadjust=0, yadjust=0; //center adjustment
	
	// the board
	private Board board;
//	private ArrayList<Board> prev=new ArrayList<Board>();
	
	// the most recently clicked-on piece and its location
	private Piece last_clicked = null;
	private int lastx = 0,lasty = 0;
	
	// current turn, alternates between 0 and 1
	private int turn = 0;
	
	// game status
	private boolean game_over = false;
	
	// the window
	private JFrame frame;
	
	// very simple main method to get the game going
	public static void main(String[] args) {
		new Chess(); 
	}
 
	// initialize the graphics and listeners
	public Chess() {
		board = new Board();
		frame = new JFrame();
		frame.setSize(width+2, width+24);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				width = frame.getWidth()<frame.getHeight()?frame.getWidth():frame.getHeight()-24; //sets new width when resized
				SQUARE_WIDTH = width/8; IMG_WIDTH = SQUARE_WIDTH; //sets all variables to follow
				xadjust=Math.abs(frame.getWidth()-width)/2; yadjust=Math.abs(frame.getHeight()-width-24)/2; //gets adjustment value
				board.draw(g, last_clicked);
			}
		};
		canvas.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!game_over) {
					Piece clicked=((e.getY()-yadjust)/SQUARE_WIDTH>=8||(e.getY()-yadjust)/SQUARE_WIDTH<0||(e.getX()-xadjust)/SQUARE_WIDTH>=8||(e.getX()-xadjust)/SQUARE_WIDTH<0)?clicked=new Empty():board.getBoard()[(e.getY()-yadjust)/SQUARE_WIDTH][(e.getX()-xadjust)/SQUARE_WIDTH];;
					//catches if mouse is out of grid
					if (clicked.getTeam() == turn) {
						lastx = (e.getX()-xadjust)/SQUARE_WIDTH;
						lasty = (e.getY()-yadjust)/SQUARE_WIDTH;
						last_clicked = clicked;
					}
					
					else if (last_clicked != null)		{	

						if (is_in((e.getY()-yadjust)/SQUARE_WIDTH, (e.getX()-xadjust)/SQUARE_WIDTH, last_clicked.getMoves(board, lasty, lastx))) {
								
							int result = board.move(lasty, lastx, (e.getY()-yadjust)/SQUARE_WIDTH, (e.getX()-xadjust)/SQUARE_WIDTH);
							
							if (result == 2) {
								game_over = true;
								frame.getContentPane().repaint();
								JOptionPane.showMessageDialog(null, "Checkmate. " + (turn == 0 ? "White" : "Black") + " wins!");
								return;
							}
							else if (result == 1) {
								frame.getContentPane().repaint();
								JOptionPane.showMessageDialog(null, "Check");
							}

							
							last_clicked = null;
					
							turn = (turn + 1)%2;
//							prev.add(board);
						}
					}
				}
//				for (Piece[] z:board.getBoard()) System.out.println(Arrays.toString(z));
				frame.getContentPane().repaint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		canvas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

			    if (key == KeyEvent.VK_LEFT) {
			    }

			    if (key == KeyEvent.VK_RIGHT) {
			    }

			    if (key == KeyEvent.VK_UP) {
			    }

			    if (key == KeyEvent.VK_DOWN) {
			    }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		canvas.setFocusable(true);
		
		frame.add(canvas);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().repaint();
	}
	
	public boolean is_in(int x, int y, ArrayList<int[]> moves) {
		for (int[] pair : moves) {
			if (x == pair[0] && y == pair[1])
				return true;
		}
		return false;
	}
}


