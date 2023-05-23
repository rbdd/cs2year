/*
 * ==========================================================================================
 * AnimationViewer.java : Moves shapes around on the screen according to different paths.
 * It is the main drawing area where shapes are added and manipulated.
 * YOUR UPI: mcho868
 * ==========================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListDataListener;
import java.lang.reflect.Field;

class AnimationViewer extends JComponent implements Runnable {
	private Thread animationThread = null; // the thread for animation
	private static int DELAY = 120; // the current animation speed
	//ArrayList<Shape> shapes = new ArrayList<Shape>(); removed by Q5
	private ShapeType currentShapeType = Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType = Shape.DEFAULT_PATHTYPE; // the current path type
	private Color currentColor = Shape.DEFAULT_COLOR; // the current fill colour of a shape
	private Color currentBorderColor = Shape.DEFAULT_BORDER_COLOR;
	private int currentPanelWidth = Shape.DEFAULT_PANEL_WIDTH, currentPanelHeight = Shape.DEFAULT_PANEL_HEIGHT,currentWidth = Shape.DEFAULT_WIDTH, currentHeight = Shape.DEFAULT_HEIGHT;
	private String currentLabel = Shape.DEFAULT_LABEL;
	protected MyModel model;

	//Q5 
	protected NestedShape root;

	public AnimationViewer() {
		start();
		addMouseListener(new MyMouseAdapter());
		root = new NestedShape(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT);
		model = new MyModel();
	}
	public void setCurrentLabel(String text) {
		currentLabel = text;
		for (Shape currentShape : root.getAllInnerShapes())
			if (currentShape.isSelected())
				currentShape.setLabel(currentLabel);
	}
	public void setCurrentColor(Color bc) {
	    currentColor = bc;
	    for (Shape currentShape: root.getAllInnerShapes())
	      if ( currentShape.isSelected())
	        currentShape.setColor(currentColor);
	  }
	public void setCurrentBorderColor(Color bc) {
	    currentBorderColor = bc;
	    for (Shape currentShape: root.getAllInnerShapes())
	      if ( currentShape.isSelected())
	        currentShape.setBorderColor(currentBorderColor);
	 }
	public void setCurrentHeight(int h) {
	    currentHeight = h;
	    for (Shape currentShape: root.getAllInnerShapes())
	      if ( currentShape.isSelected())
	        currentShape.setHeight(currentHeight);
	 }
	public void setCurrentWidth(int w) {
	    currentWidth = w;
	    for (Shape currentShape: root.getAllInnerShapes())
	      if ( currentShape.isSelected())
	        currentShape.setWidth(currentWidth);
	 }
	class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			boolean found = false;
			for (Shape currentShape : root.getAllInnerShapes())
				if (currentShape.contains(e.getPoint())) { // if the mousepoint is within a shape, then set the shape to
					currentShape.setSelected(!currentShape.isSelected());
					found = true;
				}
			if (!found) {
				Shape s = root.createInnerShape(e.getX(), e.getY(), currentWidth, currentHeight, currentColor, currentBorderColor, currentPathType, currentShapeType);
				model.insertNodeInto(s, root);
			}
		}
	}
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape currentShape : root.getAllInnerShapes()) {
			currentShape.move();
			currentShape.draw(g);
			currentShape.drawHandles(g);
			currentShape.drawString(g);
		}
	}
	public void resetMarginSize() {
		currentPanelWidth = getWidth();
		currentPanelHeight = getHeight();
		for (Shape currentShape : root.getAllInnerShapes())
			currentShape.resetPanelSize(currentPanelWidth, currentPanelHeight);
	}

	//Q6-8 Q11
	public class MyModel extends AbstractListModel<Shape> implements TreeModel{
		private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
		private ArrayList<Shape> selectedShapes;
		public MyModel(){selectedShapes = root.getAllInnerShapes();}
		public int getSize(){return selectedShapes.size();}
		public Shape getElementAt(int index){return selectedShapes.get(index);}
		public void reload(NestedShape selected){
			selectedShapes = new ArrayList<Shape>();
			for (Shape s: selected.getAllInnerShapes()){
				selectedShapes.add(s);
				fireContentsChanged(this, 0, selectedShapes.size());
			} 
		}
		public NestedShape getRoot(){return root;}
		public boolean isLeaf(Object node){return !(node instanceof NestedShape);}
		public boolean isRoot(Shape selectedNode){return selectedNode.equals(root);}
		public Shape getChild(Object parent, int index){if (isLeaf(parent) || index >= ((NestedShape)parent).getAllInnerShapes().size()) return null; return ((NestedShape) parent).getInnerShapeAt(index);}
		public int getChildCount(Object parent){if (isLeaf(parent)) return 0; return ((NestedShape) parent).getAllInnerShapes().size();}
		public int getIndexOfChild(Object parent, Object child){if (isLeaf(parent)) return -1; return ((NestedShape) parent).indexOf((Shape)child);}
		public void addTreeModelListener(final TreeModelListener tml) {treeModelListeners.add(tml);}
		public void removeTreeModelListener(final TreeModelListener tml) {treeModelListeners.remove(tml);}
		public void valueForPathChanged(TreePath path, Object newValue){}
		public void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children){
			TreeModelEvent tme = new TreeModelEvent(source, path, childIndices, children);
			for (TreeModelListener tml: treeModelListeners) tml.treeNodesInserted(tme);
			System.out.printf("Called fireTreeNodesInserted: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
		}
		public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,Object[] children){
			TreeModelEvent tme = new TreeModelEvent(source, path, childIndices, children);
			for (TreeModelListener tml: treeModelListeners) tml.treeNodesRemoved(tme);
			System.out.printf("Called fireTreeNodesRemoved: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
		}
		public void insertNodeInto(Shape newChild, NestedShape parent){
			Object[] path = parent.getPath();
			int[] ia = {parent.indexOf(newChild)};
			Object[] oa = {newChild};
			fireTreeNodesInserted(this, path, ia, oa);
		}
		public void removeNodeFromParent(Shape selectedNode){
			NestedShape parent  = selectedNode.getParent();
			Object[] path = parent.getPath();
			int[] ia = {parent.indexOf(selectedNode)};
			Object[] oa = {selectedNode};
			parent.removeInnerShape(selectedNode);
			fireTreeNodesRemoved(this, path, ia, oa);
		}	
		public void addShapeNode(NestedShape selectedNode){
			Shape s;
			if (isRoot(selectedNode)){s = selectedNode.createInnerShape(0, 0, currentWidth, currentHeight, currentColor, currentBorderColor, currentPathType, currentShapeType);}
			else{s = selectedNode.createInnerShape(0, 0, selectedNode.width/2, selectedNode.height/2, selectedNode.color, selectedNode.borderColor, currentPathType, currentShapeType);}
			insertNodeInto(s, selectedNode);
		}	
			
 	}

	// you don't need to make any changes after this line ______________
	public String getCurrentLabel() {return currentLabel;}
	public int getCurrentHeight() { return currentHeight; }
	public int getCurrentWidth() { return currentWidth; }
	public Color getCurrentColor() { return currentColor; }
	public Color getCurrentBorderColor() { return currentBorderColor; }
	public void setCurrentShapeType(ShapeType value) {currentShapeType = value;}
	public void setCurrentPathType(PathType value) {currentPathType = value;}
	public ShapeType getCurrentShapeType() {return currentShapeType;}
	public PathType getCurrentPathType() {return currentPathType;}
	public void update(Graphics g) {
		paint(g);
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
		while (animationThread == myThread) {
			repaint();
			pause(DELAY);
		}
	}
	private void pause(int milliseconds) {
		try {
			Thread.sleep((long) milliseconds);
		} catch (InterruptedException ie) {}
	}
}
