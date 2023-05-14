/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * Brendan Choi
 * YOUR UPI: mcho868
 * ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;

class AnimationViewer extends JComponent implements Runnable {
	private Thread animationThread = null;		// the thread for animation
	private static int DELAY = 120;				 // the current animation speed
	private ArrayList<Shape> shapes = new ArrayList<Shape>(); //create the ArrayList to store shapes
	private ShapeType currentShapeType=Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType=Shape.DEFAULT_PATHTYPE;	// the current path type
	private Color currentColor=Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth=Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT, currentWidth=Shape.DEFAULT_WIDTH, currentHeight=Shape.DEFAULT_HEIGHT;
	//Q7
	private String currentLabel = Shape.DEFAULT_LABEL;

	public String getCurrentLabel(){return currentLabel;}
	public void setCurrentLabel(String label){
		currentLabel = label;
		for (Shape currentShape: shapes)
			if (currentShape.selected) 
				currentShape.label = currentLabel;
	}
	//Q9
	public void loadShape(String line){
		String[] shapeInfo = line.split(",");
		if (shapeInfo[0].equals("OCTAGON")) currentShapeType = ShapeType.OCTAGON;
		if (shapeInfo[0].equals("RECTANGLE")) currentShapeType = ShapeType.RECTANGLE;
		if (shapeInfo[0].equals("OVAL")) currentShapeType = ShapeType.OVAL;
		if (shapeInfo[1].equals("BOUNCING")) currentPathType = PathType.BOUNCING;
		if (shapeInfo[1].equals("FLOATING")) currentPathType = PathType.FLOATING;
		createNewShape(0, 0);
	}
	//Q10
	public void loadShapesFromFile(String filename){
		Scanner fileInput = null;
		try{
			fileInput = new Scanner(new File(filename));
			while (fileInput.hasNextLine()){
				String line = fileInput.nextLine();
				loadShape(line);
			}
		}catch (FileNotFoundException e) {
			System.out.printf("ERROR: The file %s does not exist.\n", filename);
		}finally{
			if (fileInput != null) fileInput.close();
			}
	}

	public AnimationViewer() {
		start();
		addMouseListener(new MyMouseAdapter());
	}
	protected void createNewShape(int x, int y) {
		switch (currentShapeType) {
			case RECTANGLE: {
				shapes.add( new RectangleShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
                break;
			}  case OVAL: {
        		shapes.add( new OvalShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
                break;
			} case OCTAGON: {
				shapes.add( new OctagonShape(x, y,currentWidth,currentHeight,currentPanelWidth,currentPanelHeight,currentColor,currentBorderColor,currentPathType));
                break;
		 	}
		}
    }
	public final void paintComponent(Graphics g) {
		for (Shape currentShape: shapes) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawHandles(g);
			currentShape.drawString(g);
		}
	}
	public void setCurrentBorderColor(Color bc){
		currentBorderColor = bc; 
		for (Shape currentShape: shapes)
			if (currentShape.isSelected()){currentShape.borderColor = currentBorderColor;}
	}
	public Color getCurrentBorderColor(){return currentBorderColor;}
	public void setCurrentPathType(PathType value) { currentPathType = value; }
	public void setCurrentShapeType(ShapeType value) { currentShapeType = value; }

	// you don't need to make any changes after this line ______________
	class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked( MouseEvent e ) {
			boolean found = false;
			for (Shape currentShape: shapes)
				if ( currentShape.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
					currentShape.setSelected( ! currentShape.isSelected() );
					found = true;
				}
			if (!found) createNewShape(e.getX(), e.getY());
		}
	}
	public void update(Graphics g){ paint(g); }
	public void resetMarginSize() {
		currentPanelWidth = getWidth();
		currentPanelHeight = getHeight() ;
		for (Shape currentShape: shapes)
			currentShape.resetPanelSize(currentPanelWidth,currentPanelHeight );
	}
	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}
	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}
	public void run() {
		Thread myThread = Thread.currentThread();
		while(animationThread==myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long)milliseconds);
		} catch(InterruptedException ie) {}
	}
}
