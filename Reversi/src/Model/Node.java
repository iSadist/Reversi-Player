package Model;
import java.awt.Point;
import java.util.ArrayList;

public class Node {
	
	private int value;
	private Point nextMove;
	private ArrayList<Node> branches;
	
	public Node() {
		value = Integer.MIN_VALUE;
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
	}
	
	public Point getMove() {
		return nextMove;
	}
	
	public void setMove(Point move) {
		nextMove = move;
	}
	
}
