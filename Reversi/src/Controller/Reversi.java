package Controller;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import GUI.GUIBoard;

public class Reversi {
	
	private int myScore;
	private int computerScore;
	private Board reversiBoard;
	private GUIBoard gui;
	private ArrayList<Point> possibleNextMoves;
	private boolean myTurn;
	
	public Reversi(Board board) {
		myScore = 0;
		computerScore = 0;
		myTurn = true;
		reversiBoard = board;
		possibleNextMoves = new ArrayList<Point>();
	}
	
	public void flipPieces(Point lastMove) {
		
	}
	
	public void putPieceOnSquare(Point p, Color color, GUIBoard gui) {
		if(myTurn) {
			if(reversiBoard.putPieceOnSquare(p.x, p.y, color)) {
				flipPieces(p);
				gui.updateUI();
//				switchTurns();
			}			
		}
		
	}
	
	public void possibleMoves() {
		
	}
	
	private void switchTurns() {
		myTurn = !myTurn;
	}
	
}
