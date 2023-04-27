/**
 *	===============================================================================
 *	OvalShape.java : A shape that is an oval.
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.awt.*;
class OvalShape extends Shape{
    public OvalShape(){super();}
    public OvalShape(Color c, Color bc, PathType pt){super(c, bc, pt);}
    public OvalShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt)
    {super(x, y, width, height, panelWidth, panelHeight, c, bc, pt);}
    public void draw(Graphics g){System.out.println(color + "\n" + borderColor + "\n" + super.toString());}
    public boolean contains(Point mousePt){
        double dx = (2.0 * mousePt.x - 2 * x  + width); double dy = (2.0 * mousePt.y - 2 * y + height); double d = dx * dx + dy + dy; return(d < 1.0);}
}