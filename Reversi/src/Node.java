import java.awt.Point;
import java.util.ArrayList;

public class Node {
	
	private int myValue;
	private int opponentsScore;
	private ArrayList<Node> branches;
	
	public Node(int value) {
		branches = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		branches.add(node);
	}
	
	
	
	
}
