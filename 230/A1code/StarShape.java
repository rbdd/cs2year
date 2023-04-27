/**
 *	===============================================================================
 *	StarShape.java : A shape that is a star.
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.awt.*;
import java.util.*;
class StarShape extends RectangleShape{
    public StarShape(){super();}
    public StarShape(Color c, Color bc, PathType pt){super(c, bc, pt);}
    public StarShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color c, Color bc, PathType pt)
    {super(x, y, width, height, panelWidth, panelHeight, c, bc, pt);}
    public void draw(Graphics g){
        System.out.println(color + "\n" + borderColor);
        int[][] a = {{x, y}, {x + width/2, y}, {x + width, y}, {x + width, y + height/2}, {x + width, y + height}, {x + width/2, y + height}, {x, y + height}, {x, y + height/2}};
        for (int i = 4; i > 0; i--){
            System.out.printf("%d, %d, %d, %d\n%d, %d, %d, %d\n", a[i][0], a[i][1], a[i+3][0], a[i+3][1], a[i-1][0], a[i-1][1], a[(i+4)%8][0], a[(i+4)%8][1]);
        }
    }
}