package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Board;
import Controller.Reversi;

public class GUIBoard {
	
	private JFrame window;
	private int boardSize = 8;
	private Board board;
	private GUISquare[][] squares;
	
	public GUIBoard(Board board, Reversi reversi) {
		this.board = board;
		squares = new GUISquare[boardSize][boardSize];
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Reversi");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout squareLayout = new GridLayout(boardSize, boardSize);
		window.setLayout(squareLayout);
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++){
				Point point = new Point(i,j);
				GUISquare square = new GUISquare("Button", point, null);
				ClickEventListener eventListener = new ClickEventListener(square, reversi, this);
				square.addMouseListener(eventListener);
				squares[i][j] = square;
				window.add(square);
			}
		}
		window.setSize(800, 800);
		window.pack();
		window.setVisible(true);
	}
	
	public void updateUI() {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				squares[i][j].setBackground(board.getPiece(i, j));
				System.out.println(board.getPiece(i, j));
			}
		}
		System.out.println("Updated!");
		
	}
}
