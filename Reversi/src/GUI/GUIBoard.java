package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Reversi;
import Model.Board;

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
				Point point = new Point(j,i);
				GUISquare square = new GUISquare("Button", point, null);
				ClickEventListener eventListener = new ClickEventListener(square, reversi, this);
				square.addMouseListener(eventListener);
				squares[j][i] = square;
				window.add(square);
			}
		}
		Dimension size = new Dimension(600, 600);
		window.setSize(size);
		window.setPreferredSize(size);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
	public void updateUI() {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				squares[j][i].setBackground(board.getPiece(j, i));
			}
		}
		System.out.println("Updated!");
		
	}
}
