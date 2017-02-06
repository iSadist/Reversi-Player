package Controller;

import GUI.GUIBoard;
import Model.Board;

public class Main {
	
	public static void main(String[] args) {
		Board board = new Board();
		Reversi reversi = new Reversi(board, true);
		GUIBoard game = new GUIBoard(board, reversi);
		game.updateUI();
	}
}
