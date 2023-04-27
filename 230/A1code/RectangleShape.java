/**
 *	===============================================================================
 *	RectangleShape.java : A shape that is a rectangle.
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.awt.*;
class RectangleShape extends Shape{
    public RectangleShape(){super();}
    public RectangleShape(Color c, Color bc, PathType pt){super(c, bc, pt);}
    public RectangleShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt)
    {super(x, y, width, height, panelWidth, panelHeight, c, bc, pt);}
    public void draw(Graphics g){System.out.println(color + "\n" + borderColor + "\n" + super.toString());}
    public boolean contains(Point mousePt){return (mousePt.x >= this.x) && (mousePt.y >= this.y) && (mousePt.x - this.x <= this.width) && (mousePt.y - this.y<= this.height);}
}