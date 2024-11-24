package checkers;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import checkers.Piece.Team;

/******** DON'T EDIT THIS CLASS ********/
public class Checkers {
	// constants that are predefined and won't change (me when I lie)
	public static int width = 600;
	public static int SQUARE_WIDTH = width/8;
	public static int xadjust = 0, yadjust = 0; // center adjustment
	
	private Board board;
	
	// the most recently clicked-on piece and its location
	private Piece last_clicked = null;
	private int lastx = 0,lasty = 0;
	
	private Team turn = Team.RED;
	
	// game status
	private boolean game_over = false;
	
	// the window
	private JFrame frame;
	
	// very simple main method to get the game going
	public static void main(String[] args) {
		new Checkers(); 
	}
 
	// initialize the graphics and listeners
	public Checkers() {
		board = new Board();
		frame = new JFrame();
		frame.setSize(width+2, width+24);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				width = frame.getWidth() < frame.getHeight() ? frame.getWidth() : frame.getHeight() - 24; //sets new width when resized
				SQUARE_WIDTH = width / 8; 
				
				// adjustment value
				xadjust = Math.abs(frame.getWidth()-width)/2; 
				yadjust = Math.abs(frame.getHeight() - width - 24) / 2;

				board.draw(g, last_clicked);
			}
		};
		
		canvas.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!game_over) {
					// catches if mouse is out of grid
					int y = e.getY() - yadjust, x = e.getX() - xadjust;
					int r = y / SQUARE_WIDTH, c = x / SQUARE_WIDTH;
					Piece clicked;
					if (r >= 8 || r < 0 || c >= 8 || c < 0) {
						clicked = new Empty();
					} else {
						clicked = board.getBoard()[r][c];
					}

					if (clicked.getTeam() == turn) {
						lastx = (e.getX() - xadjust) / SQUARE_WIDTH;
						lasty = (e.getY() - yadjust) / SQUARE_WIDTH;
						last_clicked = clicked;
					}
					
					else if (last_clicked != null) {
						if (is_in((e.getY() - yadjust) / SQUARE_WIDTH, (e.getX() - xadjust) / SQUARE_WIDTH, last_clicked.getMoves(board, lasty, lastx))) {	
							int result = board.move(lasty, lastx, (e.getY() - yadjust) / SQUARE_WIDTH, (e.getX() - xadjust) / SQUARE_WIDTH);
							
							if (result == 2) {
								game_over = true;
								frame.getContentPane().repaint();
								JOptionPane.showMessageDialog(null, "Game over. " + turn + " wins!");
								return;
							}

							last_clicked = null;
							turn = turn.toggle();
						}
					}
				}
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
			public void keyTyped(KeyEvent e) {}

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
			public void keyReleased(KeyEvent e) {}
			
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
			if (x == pair[0] && y == pair[1]) { return true; }
		}
		return false;
	}
}


