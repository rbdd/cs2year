package t;
import java.awt.Point;
public class t {
    public static void main(String args[]){
        Point p = new Point(10, 20);
        Rectangle r1 = new Rectangle();
        Point p2 = new Rectangle();
        System.out.println(p2 instanceof Point);
        System.out.println(p2 instanceof Rectangle);
        System.out.println(p instanceof Rectangle);
        //System.out.println(p instanceof Integer);
    }
}
