import java.util.ArrayList;

public class Node {
	
	private int value;
	private ArrayList<Node> branches;
	
	public Node(int value) {
		this.value = value;
		branches = new ArrayList<Node>();
	}
	
	public void addNode(Node node) {
		branches.add(node);
	}
	
	
}
