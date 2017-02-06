package Controller;

import Model.Node;

public class TreeBuilder {
	
	private Node root;
	private Reversi virtualGame;
	
	public TreeBuilder(Node root) {
		this.root = root;
	}
	
	public void setRootTo(Node node) {
		this.root = node;
	}
	
	public void buildTreeWithDepth(int depth) {
		
	}
	
	/**
	 * Returns the next Node with the highest value in the tree for a given depth
	 */
	public Node depthFirstLimited(int depth) {
		Node bestNode = null;
		int highestValue = Integer.MIN_VALUE;
		int currentNodeValue;
		
		for(Node childNode : root.getChildren()) {
			currentNodeValue = getMinValue(childNode, 0, depth, highestValue);
			if(currentNodeValue > highestValue) {
				highestValue = currentNodeValue;
				bestNode = childNode;
			}
		}
		return bestNode;
	}
	
	//Private methods
	
	private int getMinValue(Node root, int currentDepth, int depth, int largestNodeOnCurrentLevel) {
		if(currentDepth >= depth || root.children() == 0) return root.getValue();
		
		int lowestValue = Integer.MAX_VALUE;
		int currentNodeValue;
		
		for(Node childNode : root.getChildren()) {
			currentNodeValue = getMaxValue(childNode, 0, depth);
			if(currentNodeValue < largestNodeOnCurrentLevel) {
				return currentNodeValue;
			}
			if(currentNodeValue < lowestValue) {
				lowestValue = currentNodeValue;
			}
		}
		return lowestValue;
	}
	
	private int getMaxValue(Node root, int currentDepth, int depth) {
		if(currentDepth >= depth || root.children() == 0) return root.getValue();
		
		int highestValue = Integer.MIN_VALUE;
		int currentNodeValue;
		
		for(Node childNode : root.getChildren()) {
			currentNodeValue = getMinValue(childNode, 0, depth, highestValue);
			if(currentNodeValue < highestValue) {
				highestValue = currentNodeValue;
			}
		}
		return highestValue;
	}
	
}