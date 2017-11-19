package com.ctckhjl.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	private boolean[] keys = new boolean[256];
	private boolean up,down,left,right;
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	
	}
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		keys[k.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		keys[k.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
	public boolean isUp() {
		return this.up;
	}
	public boolean isDown() {
		return this.down;
	}
	public boolean isLeft() {
		return this.left;
	}
	public boolean isRight() {
		return this.right;
	}
	public boolean isPress(int keycode){
		return this.keys[keycode];
	}

}
