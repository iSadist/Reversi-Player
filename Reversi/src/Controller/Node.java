package Controller;
import java.awt.Point;
import java.util.ArrayList;

public class Node {
	
	private int myValue;
	private int opponentsScore;
	private int value;
	private boolean valueIsSet;
	private boolean myTurn;
	private Point nextMove;
	private ArrayList<Node> branches;
	
	public Node(int myScore, int computerScore) {
		value = 0;
		valueIsSet = false;
		branches = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		branches.add(node);
	}
	
	public Node getNode(int place) {
		return branches.get(place);
	}
	
	public int children() {
		return branches.size();
	}
	
	public ArrayList<Node> getChildren() {
		return branches;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		valueIsSet = true;
	}
	
	public boolean hasValue() {
		return valueIsSet;
	}
	
	public Point getMove() {
		return nextMove;
	}
	
}
