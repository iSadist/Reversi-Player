package GUI;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class GUISquare extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point position;
	
	public GUISquare(String label, Point pos, Color color) {
		super();
		position = pos;
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		this.setBorder(border);
		this.setBackground(color);
		this.setForeground(Color.BLUE);
		setOpaque(true);
	}
	
	public Point getPoint() {
		return position;
	}
	
	public void setColor(Color color) {
		this.setBackground(color);
	}
}
