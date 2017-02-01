import java.awt.Point;
import java.util.ArrayList;

public class Node {
	
	private int myValue;
	private int opponentsScore;
	private Point position;
	private ArrayList<Node> branches;
	
	public Node(int value) {
		position = new Point();
		branches = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		branches.add(node);
	}
	
	
}
