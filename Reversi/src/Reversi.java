import java.awt.Point;
import java.util.ArrayList;

public class Reversi {
	
	private int myScore;
	private int computerScore;
	private Board reversiBoard;
	private ArrayList<Point> possibleNextMoves;
	private boolean myTurn;
	
	public Reversi() {
		myScore = 0;
		computerScore = 0;
		myTurn = true;
		reversiBoard = new Board();
		possibleNextMoves = new ArrayList<Point>();
	}
	
	public void flipPieces(Point lastMove) {
		
	}
	
}
