/*
 *	===============================================================================
 *	NestedShape.java : A shape that is Nested. Q2-4
 *  YOUR UPI: mcho868
 *	=============================================================================== */
import java.util.ArrayList;

import javafx.scene.transform.Translate;

import java.awt.*;

class NestedShape extends RectangleShape{
    private ArrayList<Shape> innerShapes = new ArrayList<Shape>();
    public NestedShape(){
        super(); 
        Shape shape;
        shape = createInnerShape(0,0,width/2,height/2,color,borderColor,PathType.BOUNCING,ShapeType.RECTANGLE);
        shape.setLabel("" + (Integer.parseInt(this.label) + 1));
    }
    public NestedShape(int width, int height){
        x = 0; y = 0; this.width = width; this.height = height; panelWidth = DEFAULT_PANEL_WIDTH; panelHeight = DEFAULT_PANEL_HEIGHT;
        color = Color.black; borderColor = Color.black; path = new BouncingPath(1, 2);
    }
    public NestedShape(int x, int y, int w, int h, int mw, int mh, Color c, Color bc, PathType pt){
        super(x,y,w,h,mw,mh,c,bc,pt);
        Shape shape;
        shape = createInnerShape(0,0,width/2,height/2,color,borderColor,PathType.BOUNCING,ShapeType.RECTANGLE);
        shape.setLabel("" + (Integer.parseInt(this.label) + 1));
    }
    public Shape createInnerShape(PathType pt, ShapeType st){
        return createInnerShape(0,0,width/2,height/2,color,borderColor,pt,st);
    }
    public Shape createInnerShape(int x, int y, int w, int h, Color c, Color bc, PathType pt, ShapeType st){
        Shape shape;
        if (st == ShapeType.OVAL){shape = new OvalShape( x, y, w, h, width, height, c, bc, pt);}
        else if (st == ShapeType.RECTANGLE){shape = new RectangleShape(x, y, w, h, width, height, c, bc, pt);}
        else {shape = new NestedShape(x, y, w, h, width, height, c, bc, pt);}
        innerShapes.add(shape);
        shape.setParent(this);
        return shape;
    }
    public Shape getInnerShapeAt(int index){return innerShapes.get(index);}
    public int getSize(){return innerShapes.size();}
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.translate(x, y);
        for (Shape innerShape: innerShapes){           
            innerShape.draw(g);
            if(innerShape.isSelected()) innerShape.drawHandles(g);
            innerShape.setLabel(label);              
        }
        g.translate(-x, -y);  
    }
    public void move(){
        super.move();
        for (Shape innerShape: innerShapes){
            innerShape.move();
        }
    }
    public int indexOf(Shape s){return innerShapes.indexOf(s);}
    public void addInnerShape(Shape s){innerShapes.add(s); s.setParent(this);}
    public void removeInnerShape(Shape s){innerShapes.remove(s); s.setParent(null);}
    public void removeInnerShapeAt(int index){Shape s = innerShapes.get(index); innerShapes.remove(index); s.setParent(null);}
    public ArrayList<Shape> getAllInnerShapes(){return innerShapes;}
}
