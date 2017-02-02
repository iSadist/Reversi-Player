package Controller;

import java.awt.Color;

public class Board {
	
	public static final int boardSize = 8;
	private Color[][] squares;
	
	public Board() {
		squares = new Color[boardSize][boardSize];
		setup();
	}
	
	public boolean putPieceOnSquare(int x, int y, Color piece) {
		if(squareIsOnBoard(x, y)) {
			System.out.println("You are trying to put a piece outside of the board");
			return false;
		}
		if(squares[x][y] == null) {
			System.out.println("The square is already taken");
			return false;
		}
		squares[x-1][y-1] = piece;
		return true;
	}
	
	public boolean turnPieceOnSquare(int x, int y) {
		if(squares[x][y] == null) {
			return false;
		} else if(squares[x][y] == Color.BLACK) {
			setPiece(x, y, Color.WHITE);
		} else if(squares[x][y] == Color.WHITE) {
			setPiece(x, y, Color.BLACK);
		}
		return true;
	}
	
	public void setPiece(int x, int y, Color piece) {
		if(squareIsOnBoard(x, y)) {
			return;
		}
		squares[x][y] = piece;
	}
	
	public Color getPiece(int x, int y) {
		if(squareIsOnBoard(x, y)) {
			return squares[x][y];			
		}
		return null;
	}
	
	private boolean squareIsOnBoard(int x, int y) {
		return x<1 || x>boardSize || y<1 || y>boardSize;
	}
	
	private void setup() {
		putPieceOnSquare(4, 4, Color.BLACK);
		putPieceOnSquare(4, 5, Color.WHITE);
		putPieceOnSquare(5, 4, Color.WHITE);
		putPieceOnSquare(5, 5, Color.BLACK);
	}

}
