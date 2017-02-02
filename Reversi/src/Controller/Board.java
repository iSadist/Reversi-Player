package Controller;
public class Board {
	
	public static final int boardSize = 8;
	private ReversiPiece[][] squares;
	
	public Board() {
		squares = new ReversiPiece[boardSize][boardSize];
		setup();
	}
	
	public boolean putPieceOnSquare(int x, int y, ReversiPiece piece) {
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
		} else if(squares[x][y] == ReversiPiece.BLACK) {
			setPiece(x, y, ReversiPiece.WHITE);
		} else if(squares[x][y] == ReversiPiece.WHITE) {
			setPiece(x, y, ReversiPiece.BLACK);
		}
		return true;
	}
	
	public void setPiece(int x, int y, ReversiPiece piece) {
		if(squareIsOnBoard(x, y)) {
			return;
		}
		squares[x][y] = piece;
	}
	
	public ReversiPiece getPiece(int x, int y) {
		if(squareIsOnBoard(x, y)) {
			return squares[x][y];			
		}
		return null;
	}
	
	private boolean squareIsOnBoard(int x, int y) {
		return x<1 || x>boardSize || y<1 || y>boardSize;
	}
	
	private void setup() {
		putPieceOnSquare(4, 4, ReversiPiece.BLACK);
		putPieceOnSquare(4, 5, ReversiPiece.WHITE);
		putPieceOnSquare(5, 4, ReversiPiece.WHITE);
		putPieceOnSquare(5, 5, ReversiPiece.BLACK);
	}

}
