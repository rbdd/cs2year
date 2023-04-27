/**
 *  =====================================================================================================
 *  A1.java : Extends JFrame and contains a panel where shapes move around on the screen (entire project)
 *  YOUR UPI: mcho868
 *  =====================================================================================================
 */

import java.util.ArrayList;
import java.awt.*;
public class A1 {
	private AnimationViewer panel;  // panel for bouncing area
	public static void main(String[] args) {
		A1 program = new A1();
	}
	public A1() {
		panel = new AnimationViewer();
		for (int i=0; i<4; i++) {
			panel.setCurrentShapeType(ShapeType.values()[i]);
			panel.setCurrentPathType(PathType.values()[i%2]);
			panel.createNewShape(i, i+5);
		 }
		 panel.paintComponent(null);
		 panel.paintComponent(null);
	}
}

