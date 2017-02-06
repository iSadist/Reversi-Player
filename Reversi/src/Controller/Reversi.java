package Controller;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.management.openmbean.OpenMBeanParameterInfoSupport;

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
		possibleNextMoves = findPossibleMoves(myTurn);
		System.out.println(possibleNextMoves);
	}
	
	public void flipPieces(Point lastMove) {
		
	}
	
	public void putPieceOnSquare(Point p, Color color, GUIBoard gui) {
		if(myTurn && isPossibleMove(p)) {
			if(reversiBoard.putPieceOnSquare(p.x, p.y, color)) {
				checkFlip(p);
				gui.updateUI();
//				switchTurns();
			}			
		}
	}
	
	public ArrayList<Point> findPossibleMoves(boolean turn) {
		ArrayList<Point> posMoves = new ArrayList<Point>();
		for(int i = 0; i< reversiBoard.boardSize; i++) {
			for(int j = 0; j < reversiBoard.boardSize; j++) {
				Point square = new Point(j,i);
				if (isSquareAPossibleMove(square)) {
					posMoves.add(square);
				}
			}
		}
		return posMoves;
	}
	
	private boolean isSquareAPossibleMove(Point square) {
		if(reversiBoard.squareIsTaken(square.x, square.y)) return false;
		return lineOfOppositeColorCloseTo(square);
	}
	
	private boolean lineOfOppositeColorCloseTo(Point square) {
		for(int x = -1; x<=1 ; x++) {
			for(int y = -1; y<=1 ; y++) {
				if(x!=0 || y!=0) {
					Point direction = new Point(x,y);
					if(existsPossibleLine(square, direction, myTurn) != null) return true;
				}
			}
		}
		return false;
	}
	
	private ArrayList<Point> existsPossibleLine(Point square, Point direction, boolean myTurn) {
		ArrayList<Point> line = new ArrayList<Point>();
		Point checkSquare = new Point(square.x + direction.x, square.y + direction.y);
		Color oppositeColor;
		Color thisColor;
		if(myTurn) {
			oppositeColor = Color.WHITE;
			thisColor = Color.BLACK;
		} else {
			oppositeColor = Color.BLACK;
			thisColor = Color.WHITE;
		}
		
		if(reversiBoard.squareIsOnBoard(checkSquare.x, checkSquare.y) && reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(oppositeColor)) {
			while(reversiBoard.squareIsOnBoard(checkSquare.x, checkSquare.y) && !reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(Color.GRAY)) {
				if(reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(oppositeColor)) {
					line.add(checkSquare);
				} else {
					return line;
				}
				checkSquare.move(checkSquare.x + direction.x, checkSquare.y + direction.y);
			}			
		}
		return null;
	}
	
	private boolean isPossibleMove(Point move) {
		return possibleNextMoves.contains(move);
	}
	
	private void switchTurns() {
		possibleNextMoves = findPossibleMoves(myTurn);
		System.out.println(possibleNextMoves);
		myTurn = !myTurn;
		if(!myTurn) {
			//Make computer start to think
			System.out.println("Computer moves...");
			switchTurns();
		}
	}
	
	private void checkFlip(Point lastMove) {
		int xO = lastMove.x;
		int yO = lastMove.y;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int x = xO + i-1;
				int y = yO + j-1;
				if ((i-1 != 0 || j-1 != 0)) {
					checkAround(lastMove, new Point(x, y),reversiBoard.getPiece(xO,yO));
				}
			}
		}
	}
	
	private boolean checkAround(Point lastMove,Point checkPoint, Color moveColor) {
		if(checkPoint.x<9 && checkPoint.y<9) {
			if(moveColor!=reversiBoard.getPiece(checkPoint.x, checkPoint.y)
					&& reversiBoard.getPiece(checkPoint.x, checkPoint.y) != Color.GRAY) {
				if(checkAround(lastMove,new Point(checkPoint.x+(int)Math.signum(checkPoint.x-lastMove.x),
						checkPoint.y+(int)Math.signum(checkPoint.y-lastMove.y)),moveColor)) {
					return reversiBoard.turnPieceOnSquare(checkPoint.x,checkPoint.y);					
				}
				else return false;
			}
			else if(moveColor==reversiBoard.getPiece(checkPoint.x, checkPoint.y)) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
}
