import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrawLinesFrame extends JFrame {  
	DrawPanel drawPanel;
	private ArrayList<Point> points = new ArrayList<Point>();
	public DrawLinesFrame() {
		drawPanel = new DrawPanel();
		drawPanel.addMouseListener(new MyMouseAdapter());
		getContentPane().add(drawPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 250);
		setVisible(true);
	}
  
	class DrawPanel extends JPanel {		
		public void paintComponent(Graphics g) {
		    super.paintComponent(g);  // paint parent's background
		    Point pt1, pt2;
		    for (int i=0; i<points.size(); i+=2) {
		    	pt1 = (Point) points.get(i);
		    	pt2 = (Point) points.get(i+1);
		    	g.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
		    }
		}
	}

    class MyMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            Point pt1 = new Point(e.getX(), e.getY());
            points.add(pt1);            
        }
        public void mouseReleased(MouseEvent e){
            Point pt2 = new Point(e.getX(), e.getY());
            points.add(pt2);
            drawPanel.paintComponent(drawPanel.getGraphics());
            System.out.printf("Draw a line from (%s, %s) to (%s, %s)", points.get(0).getX(),points.get(0).getY(), points.get(1).getX(), points.get(1).getY());
        }
            
    }
	
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() { new DrawLinesFrame(); }
	    });
	}
}