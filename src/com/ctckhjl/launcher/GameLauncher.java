package com.ctckhjl.launcher;



import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.ctckhjl.view.AnimationsCanvas;
import com.ctckhjl.view.Launchale;;

public class GameLauncher  extends JFrame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Launchale canvas;
	public GameLauncher(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(this);
		setVisible(true);
	}

	public void setStarter(AnimationsCanvas canvas) {
		this.canvas = canvas;
		this.add(canvas);
		pack();
	}
	public void start() {
		this.canvas.start();
	}
	public void stop() {
		this.canvas.stop();
	}
	
	public static void main(String[] args) {
		GameLauncher launcher = new GameLauncher("InfiniteJourney");
		AnimationsCanvas canvas = new AnimationsCanvas();
		launcher.setStarter(canvas);
		launcher.start();
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
//		this.canvas.stop();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
//		this.canvas.pause();
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
//		this.canvas.pause();

		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
//		this.canvas.continuting();
		
	}
}
