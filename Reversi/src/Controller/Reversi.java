package Controller;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import GUI.GUIBoard;
import Model.Board;
import Model.Node;

public class Reversi {
	
	private int myScore;
	private int computerScore;
	private Board reversiBoard;
	private ArrayList<Point> possibleNextMoves;
	private boolean myTurn;
	private boolean computerPlaying;
	private TreeBuilder computer;
	
	public Reversi(Board board, boolean computerPlaying) {
		reversiBoard = board;
		this.computer = new TreeBuilder(null, this);
		myScore = 2;
		computerScore = 2;
		myTurn = true;
		this.computerPlaying = computerPlaying;
		possibleNextMoves = findPossibleMoves(myTurn);
	}
	
	/**
	 * Copy constructor
	 */
	public Reversi(Reversi copy) {
		this.myScore = copy.myScore;
		this.computerScore = copy.computerScore;
		this.reversiBoard = new Board(copy.reversiBoard);
		this.possibleNextMoves = copy.possibleNextMoves;
		this.myTurn = copy.myTurn;
		this.computerPlaying = false;
	}
	
	public int getMyScore() {
		return myScore;
	}
	
	public int getOpponentsScore() {
		return computerScore;
	}
	
	public boolean isMyTurn() {
		return myTurn;
	}
	
	public ArrayList<Point> getPossibleMoves() {
		return possibleNextMoves;
	}
	
	public Color getColorOnSquare(int x, int y) {
		return reversiBoard.getPiece(x, y);
	}
	
	public void changeScore(int addedPieces, int flips) {
		if(myTurn) {
			myScore += addedPieces + flips;
			computerScore -= flips;
		} else {
			computerScore += addedPieces + flips;
			myScore -= flips;
		}
	}
	
	public void putPieceOnSquare(Point p, Color color, GUIBoard gui) {
		if(isPossibleMove(p)) {
			if(reversiBoard.putPieceOnSquare(p.x, p.y, color)) {
				changeScore(1, 0);
				flipPieces(p);
				if(gui != null)  {
					gui.updateUI();
				}
				switchTurns(gui);
			}			
		}
	}
	
	public void flipPieces(Point lastMove) {
		for(int x = -1; x<=1 ; x++) {
			for(int y = -1; y<=1 ; y++) {
				if(x!=0 || y!=0) {
					Point direction = new Point(x,y);
					ArrayList<Point> squaresToBeFlipped = existsPossibleLine(lastMove, direction, myTurn);
					if(squaresToBeFlipped != null) {
						changeScore(0,squaresToBeFlipped.size());
						for(Point square : squaresToBeFlipped) {
							reversiBoard.turnPieceOnSquare(square.x, square.y);
						}
					}
				}
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
		if(myTurn) {
			oppositeColor = Color.WHITE;
		} else {
			oppositeColor = Color.BLACK;
		}
		
		if(reversiBoard.squareIsOnBoard(checkSquare.x, checkSquare.y) && reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(oppositeColor)) {
			while(reversiBoard.squareIsOnBoard(checkSquare.x, checkSquare.y) && !reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(Color.GRAY)) {
				if(reversiBoard.getPiece(checkSquare.x, checkSquare.y).equals(oppositeColor)) {
					line.add(new Point(checkSquare.x, checkSquare.y));
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
	
	private void switchTurns(GUIBoard gui) {
		myTurn = !myTurn;
		possibleNextMoves = findPossibleMoves(myTurn);
		if(!myTurn && computerPlaying) {
			System.out.println("Computer moving...");
			Node rootNode = new Node();
			computer.buildTreeWithDepth(6, 0, this, rootNode, myScore, computerScore);
			Node bestNode = computer.depthFirstLimited(6, rootNode);
			Point bestMove = bestNode.getMove();
			putPieceOnSquare(bestMove, Color.WHITE, gui);
			System.out.println("Computer done!");
			System.out.println("My score: " + myScore + " Computer score: " + computerScore);
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
