package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Board;

public class GUIBoard {
	
	public JFrame window;
	private int boardSize = 8;
	private Board board;
	private GUISquare[][] squares;
	
	public GUIBoard(Board board) {
		this.board = board;
		squares = new GUISquare[boardSize][boardSize];
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Reversi");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout squareLayout = new GridLayout(boardSize, boardSize);
		window.setLayout(squareLayout);
		
		for(int i = 1; i <= boardSize; i++) {
			for(int j = 1; j <= boardSize; j++){
				Point point = new Point(i,j);
				GUISquare square = new GUISquare("Button", point, Color.GRAY);
				squares[i-1][j-1] = square;
				window.add(square);
				System.out.println("Button created!");
			}
		}
		window.setSize(800, 800);
		window.pack();
		window.setVisible(true);
	}
	
	public void updateUI() {
		//TODO
		for(int i = 1; i <= boardSize; i++) {
			for(int j = 1; j <= boardSize; j++) {
				squares[i-1][j-1].setBackground(board.getPiece(i-1, j-1));
			}
		}
		
	}
}
