package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIBoard {
	
	public JFrame window;
	private int boardSize = 8;
	
	public GUIBoard() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Reversi");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout squares = new GridLayout(boardSize, boardSize);
		window.setLayout(squares);
		
		for(int i = 1; i <= boardSize; i++) {
			for(int j = 1; j <= boardSize; j++){
				Point point = new Point(i,j);
				GUISquare square = new GUISquare("Button", point, Color.BLACK);
				window.add(square);
				System.out.println("Button created!");
			}
		}
		window.pack();
		window.setVisible(true);
	}
}
