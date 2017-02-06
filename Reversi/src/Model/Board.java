package Model;

import java.awt.Color;

public class Board {
	
	public final int boardSize = 8;
	private Color[][] squares;
	
	public Board() {
		squares = new Color[boardSize][boardSize];
		setup();
	}
	
	public boolean putPieceOnSquare(int x, int y, Color piece) {
		if(!squareIsOnBoard(x, y)) {
			return false;
		}
		if(squareIsTaken(x, y)) {
			return false;
		}
		squares[x][y] = piece;
		return true;
	}
	
	public boolean turnPieceOnSquare(int x, int y) {
		if(!squareIsTaken(x, y)) {
			return false;
		} else if(squares[x][y] == Color.BLACK) {
			System.out.println("Turn to white");
			setPiece(x, y, Color.WHITE);
		} else if(squares[x][y] == Color.WHITE) {
			System.out.println("Turn to black");
			setPiece(x, y, Color.BLACK);
		}
		return true;
	}
	
	public void setPiece(int x, int y, Color piece) {
		if(!squareIsOnBoard(x, y)) {
			return;
		}
		squares[x][y] = piece;
	}
	
	public Color getPiece(int x, int y) {
		if(!squareIsOnBoard(x, y)) {
			return null;
		}
		return squares[x][y];			
	}
	
	public boolean squareIsOnBoard(int x, int y) {
		return !(x<0 || x>boardSize-1 || y<0 || y>boardSize-1);
	}
	
	public boolean squareIsTaken(int x, int y) {
		return squares[x][y] == Color.BLACK || squares[x][y] == Color.WHITE;
	}
	
	private void setup() {
		for(int x = 0; x < boardSize; x++) {
			for(int y = 0; y < boardSize; y++) {
				putPieceOnSquare(x, y, Color.GRAY);
			}
		}
		putPieceOnSquare(3, 3, Color.BLACK);
		putPieceOnSquare(3, 4, Color.WHITE);
		putPieceOnSquare(4, 3, Color.WHITE);
		putPieceOnSquare(4, 4, Color.BLACK);
	}
	
	public void printBoard() {
		for(int x = 0; x < boardSize; x++) {
			for(int y = 0; y < boardSize; y++) {
				System.out.println(squares[x][y]);
			}
		}
	}

}
