package Controller;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import GUI.GUIBoard;
import Model.Board;

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
	
	public void checkFlip(Point lastMove) {
		int x = lastMove.x;
		int y = lastMove.y;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 && j != 0 && x + i < 9 && y + j < 9) {
					checkAround(lastMove, new Point(x + i, y + j));
				}
			}
		}
	}
	
	public boolean checkAround(Point lastMove,Point checkPoint) {
		if(checkPoint.x<9 && checkPoint.y<9) {
			if(reversiBoard.getPiece(lastMove.x, lastMove.y)!=reversiBoard.getPiece(checkPoint.x, checkPoint.y)
					&& reversiBoard.getPiece(checkPoint.x, checkPoint.y) != Color.GRAY) {
				if(checkAround(lastMove,new Point(2*checkPoint.x-lastMove.x,2*checkPoint.y-lastMove.y))) {
					return reversiBoard.turnPieceOnSquare(checkPoint.x,checkPoint.y);					
				}
				else return false;
			}
			else if(reversiBoard.getPiece(lastMove.x, lastMove.y)==reversiBoard.getPiece(checkPoint.x, checkPoint.y)) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
}
