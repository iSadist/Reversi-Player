package GUI;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;

public class GUISquare extends JButton {

	private Point position;
	
	public GUISquare(String label, Point pos, Color color) {
		super(label);
		position = pos;
		this.setBackground(color);
		this.setForeground(color);
		this.setOpaque(true);
	}
	
	public Point getPoint() {
		return position;
	}
}
