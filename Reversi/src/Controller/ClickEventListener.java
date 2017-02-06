package Controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.GUIBoard;
import GUI.GUISquare;

public class ClickEventListener implements MouseListener {
	
	private GUISquare square;
	private Reversi game;
	private GUIBoard board;
	
	public ClickEventListener(GUISquare square , Reversi game, GUIBoard board) {
		this.square = square;
		this.game = game;
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(game.isMyTurn()) {
			game.putPieceOnSquare(square.getPoint(), Color.BLACK, board);			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		square.setBackground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		square.setBackground(Color.GRAY);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
