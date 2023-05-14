/*
 *	===============================================================================
 *	OvalShape.java : A shape that is an Octagon.
	Brendan CHoi
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.awt.*;
import java.util.Arrays;
class OctagonShape extends Shape {
	public OctagonShape() {}
	public OctagonShape(int x, int y, int w, int h, int pw, int ph, Color c, Color bc, PathType pt) {
		super(x ,y ,w, h ,pw ,ph, c, bc, pt);
	}
	public OctagonShape(Color c, Color bc, PathType pt) {
		super(c, bc, pt);
	}
	@Override
	public void draw(Graphics g) {
		int[] xCoord = new int[]{x+width/3, x+2*width/3, x+width, x+width, x+2*width/3, x+width/3, x, x};
		int[] yCoord = new int[]{y, y, y+height/3, y+2*height/3, y+height, y+height, y+2*height/3, y+height/3};
		Polygon polygon = new Polygon(xCoord, yCoord, 8);
		g.setColor(color);
		g.fillPolygon(polygon);
		g.setColor(borderColor);
		g.drawPolygon(polygon);
	}
	@Override
	public boolean contains(Point mousePt) {
		int[] xCoord = new int[]{x+width/3, x+2*width/3, x+width, x+width, x+2*width/3, x+width/3, x, x};
		int[] yCoord = new int[]{y, y, y+height/3, y+2*height/3, y+height, y+height, y+2*height/3, y+height/3};
		Polygon polygon = new Polygon(xCoord, yCoord, 5);
		return polygon.contains(mousePt);
	}
}