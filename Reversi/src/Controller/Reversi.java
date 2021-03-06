package Controller;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import GUI.GUIBoard;
import Model.Board;
import Model.Node;

public class Reversi {
	
	private final int searchTreeDepth = 10;
	
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
	
	/**
	 * Return an array with the possible next moves
	 * @param turn
	 * @return
	 */
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
	
	/**
	 * A method for calculating if there is a line of the opposite color next to the square that ends with a piece of the same color.
	 * @param square
	 * @return
	 */
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
	
	/**
	 * @param square
	 * @param direction
	 * @param myTurn
	 * @return A list of the squares in the line, otherwise null
	 */
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
	
	/**
	 * Switch the turns and let the computer think if it is its turn
	 * @param gui
	 */
	private void switchTurns(GUIBoard gui) {
		myTurn = !myTurn;
		possibleNextMoves = findPossibleMoves(myTurn);
		if(!myTurn && computerPlaying) {
			long startTime = System.currentTimeMillis();
			System.out.println("Computer is thinking...");
			Node rootNode = new Node();
			computer.buildTreeWithDepth(searchTreeDepth, 0, this, rootNode, myScore, computerScore, startTime);
			System.out.println("Tree was built...");
			Node bestNode = computer.depthFirstLimited(searchTreeDepth, rootNode);
			Point bestMove = bestNode.getMove();
			putPieceOnSquare(bestMove, Color.WHITE, gui);
			System.out.println("Computer done!");
			System.out.println("My score: " + myScore + " Computer score: " + computerScore);
		}
	}
}
