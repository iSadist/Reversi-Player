package Controller;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.GUIBoard;

public class Main {
	
	public static void main(String[] args) {
		Board board = new Board();
		Reversi reversi = new Reversi(board);
		GUIBoard game = new GUIBoard(board, reversi);
		game.updateUI();
	}
}
