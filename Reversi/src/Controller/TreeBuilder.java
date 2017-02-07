package Controller;

import java.awt.Color;
import java.awt.Point;

import Model.Node;

public class TreeBuilder {
	
	public TreeBuilder(Node root, Reversi game) {
		
	}
	
	public void buildTreeWithDepth(int depth, int currentDepth, Reversi game, Node gameNode, int myStartScore, int opponentStartScore) {
		Reversi virtualGame = new Reversi(game);
		
		if(virtualGame.getPossibleMoves().isEmpty() || depth == currentDepth) {
			int value = (virtualGame.getMyScore() - virtualGame.getOpponentsScore()) - (myStartScore - opponentStartScore) ;
			gameNode.setValue(value);
			return;
		}
		
		for(Point move : game.getPossibleMoves()) {
			Reversi gameBranch = new Reversi(game);
			Color pieceColor = gameBranch.isMyTurn() ? Color.BLACK : Color.WHITE;
			Node nodeBranch = new Node();
			nodeBranch.setMove(move);
			gameBranch.putPieceOnSquare(move, pieceColor, null);
			gameNode.addNode(nodeBranch);
			buildTreeWithDepth(depth, currentDepth+1, gameBranch, nodeBranch, myStartScore, opponentStartScore);
		}
	}
	
	/**
	 * Returns the next Node with the highest value in the tree for a given depth
	 */
	public Node depthFirstLimited(int depth, Node root) {
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
		System.out.println(highestValue);
		return bestNode;
	}
	
	//Private methods
	
	private int getMinValue(Node root, int currentDepth, int depth, int largestNodeOnCurrentLevel) {
		if(currentDepth >= depth || root.children() == 0) return root.getValue();
		
		int lowestValue = Integer.MAX_VALUE;
		int currentNodeValue;
		
		for(Node childNode : root.getChildren()) {
			currentNodeValue = getMaxValue(childNode, currentDepth+1, depth);
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
			currentNodeValue = getMinValue(childNode, currentDepth+1, depth, highestValue);
			if(currentNodeValue > highestValue) {
				highestValue = currentNodeValue;
			}
		}
		return highestValue;
	}
	
}