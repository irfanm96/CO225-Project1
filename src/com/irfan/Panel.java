package  com.irfan;
import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;


public  class Panel extends JPanel { // inherit JPanel

	private int width, height;
	public Panel(int width, int height) {
		// set the panel size
		this.width  = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
	}

	private static void printPoint(Graphics2D frame, Color c, Point p) {

		frame.setColor(c);
		frame.draw(new Line2D.Double(p.getX(), p.getY(),
				p.getX(), p.getY()));
	}

	public void paintComponent(Graphics g) {
		// call paintComponent from parent class
		super.paintComponent(g);

		for(int i=0 ; i<this.width+1 ; i++) {
			for(int j =0 ; j<this.height+1;j++) {
				Point point=new Point(i,j); // create points
				Complex z=point.findMapping(this,-1,1,-1,1); // map to complex plane
				 printPoint((Graphics2D) g, z.test1(1000), point);
			}
		}
	}

	public static void main(String [] args) {

		// create a frame
		JFrame frame = new JFrame("Serpenski's Triangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set the content of the frame as one of this panel
		frame.setContentPane(new Panel(600, 800));

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
