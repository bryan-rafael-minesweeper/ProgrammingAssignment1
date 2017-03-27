import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}
			JFrame myFrame2 = (JFrame) d;
			MyPanel myPanel2 = (MyPanel) myFrame2.getContentPane().getComponent(0);
			Insets myInsets2 = myFrame2.getInsets();
			int x2 = myInsets2.left;
			int y2 = myInsets2.top;
			e.translatePoint(-x2, -y2);
			int _x = e.getX();
			int _y = e.getY();
			myPanel2.x = _x;
			myPanel2.y = _y;
			myPanel2.mouseDownGridX = myPanel2.getGridX(_x, _y);
			myPanel2.mouseDownGridY = myPanel2.getGridY(_x, _y);
			myPanel2.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				
			} 
			else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
				} 
				else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} 
					else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} 
						else {
							if(myPanel.minesLock[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == (Color.BLACK)){
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();
								//AQUI IBA EL CODIGO DE PERDER PERO LO QUITE PARA QUE PUEDAS DEBUG
							}
							else{
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
								myPanel.repaint();
							}
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) {
				d = d.getParent();
				if (d == null) {
					return;
				}
			}
			JFrame myFrame2 = (JFrame) d;
			MyPanel myPanel2 = (MyPanel) myFrame2.getContentPane().getComponent(0);
			Insets myInsets2 = myFrame2.getInsets();
			int x2 = myInsets2.left;
			int y2 = myInsets2.top;
			e.translatePoint(-x2, -y2);
			int _x = e.getX();
			int _y = e.getY();
			myPanel2.x = _x;
			myPanel2.y = _y;
			int gridX2 = myPanel2.getGridX(_x, _y);
			int gridY2 = myPanel2.getGridY(_x, _y);
			if ((myPanel2.mouseDownGridX == -1) || (myPanel2.mouseDownGridY == -1)) {
				//Had pressed outside
				myFrame2.dispose();
				Main new1 = new Main();
				new1.main(null);
			} 
			else {
				if ((gridX2 == -1) || (gridY2 == -1)) {
					//Is releasing outside
					//Do nothing
				} 
				else {
					if ((myPanel2.mouseDownGridX != gridX2) || (myPanel2.mouseDownGridY != gridY2)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} 
					else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX2 == 0) || (gridY2 == 0)) {
							//On the left column and on the top row... do nothing
						} 
						else {
							if(myPanel2.minesLock[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY] == (Color.BLACK)){
								myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY] = Color.RED;
							}
							else{
								myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY] = Color.RED;
							}
							myPanel2.repaint();
						}
					}
				}
			}
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}