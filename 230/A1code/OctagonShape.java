/**
 *	===============================================================================
 *	OctagonShape.java : A shape that is a hexagon.
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.awt.*;
import java.util.*;
class OctagonShape extends Shape{
    public OctagonShape(){super();}
    public OctagonShape(Color c, Color bc, PathType pt){super(c, bc, pt);}
    public OctagonShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt)
    {super(x, y, width, height, panelWidth, panelHeight, c, bc, pt);}
    public void draw(Graphics g){
        int[] a = {x + width/3, x + (2 * width)/3, x + width, x + width, x + (2 * width)/3, x + width/3, x, x};
        int[] b = {y, y, y+height/3, y+(2 * height)/3, y + height, y + height, y + (2*height)/3, y + height/3};
        System.out.println(color + "\n" + borderColor + "\n" + Arrays.toString(a) + "\n" + Arrays.toString(b));
    }
    public boolean contains(Point mousePt){
        int[] a = {x + width/3, x + (2 * width)/3, x + width, x + width, x + (2 * width)/3, x + width/3, x, x};
        int[] b = {y, y, y+height/3, y+(2 * height)/3, y + height, y + height, y + (2*height)/3, y + height/3};
        Polygon p = new Polygon(a, b, 8);
        return p.contains(mousePt);
    }
}