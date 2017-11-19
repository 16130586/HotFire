package com.ctckhjl.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	public boolean isLeftPress;
	public boolean isRightPress;
	public boolean isMouseMoved;
	public Point currentMouse = new Point(0, 0);
	
	public void update(){
		this.isLeftPress = false;
		this.isRightPress = false;
		this.isMouseMoved = false;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.isLeftPress = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			this.isRightPress = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.isLeftPress = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			this.isRightPress = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.isMouseMoved = true;
		this.currentMouse.x = e.getX();
		this.currentMouse.y = e.getY();
		
	}

}
