package com.ctckhjl.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.ctckhjl.entity.Entity;
import com.ctckhjl.entity.Player;
import com.ctckhjl.input.KeyInput;
import com.ctckhjl.input.MouseInput;
import com.ctckhjl.model.AvailableMap;
import com.ctckhjl.model.Map;
import com.ctckhjl.model.RandomMap;
import com.ctckhjl.model.SpriteSheet;
import com.ctckhjl.utils.ImageLoader;

public class AnimationsCanvas extends Canvas implements Runnable, Launchale {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int SCALE = 2;
	private int HEIGHT = 800;// 300 * SCALE;
	private int WIDH = HEIGHT * SCALE;// HEIGHT * 16 / 9;
	private boolean running = false;
	private Thread animations;
	private double targetFPS = 60.0;
	private BufferedImage background;
	private int[] pixels;
	private Screen screen;
	private KeyInput keys;
	private MouseInput mouse;
	private Map map;
	private List<Entity> entities;
	private Player hero;
	private Camera camera;
	private double delta;

	public AnimationsCanvas() {
		super();

		setPreferredSize(new Dimension(WIDH, HEIGHT));
		background = new BufferedImage(WIDH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) background.getRaster().getDataBuffer()).getData();
		screen = new Screen(background.getWidth(), background.getHeight());
		System.out.println(background.getWidth() + " " + background.getHeight());
		keys = new KeyInput();
		mouse = new MouseInput();
		camera = Camera.getInstance();
		map = new AvailableMap(1984, 1984, "resources/map/basic-map/background/basic-background.png");
		entities = new LinkedList<>();
		camera.setWidth(this.WIDH).setHeight(this.HEIGHT);
		hero = new Player(0, 0, 64, 64, keys, mouse, map, "resources/player");
		entities.add(hero);
		System.out.println("current camera : " + this.camera.x + " " + this.camera.y);
		addKeyListener(keys);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	@Override
	public void run() {
		requestFocus();
		double nsPerFrame = 1000000000.0 / this.targetFPS;
		long lastTime = System.nanoTime();
		long counterTime = System.currentTimeMillis();
		double undraw = 0.0;

		int fpsCounter = 0;
		int tpsCounter = 0;
		boolean canRender = false;

		while (running) {
			long now = System.nanoTime();
			undraw += (now - lastTime) / nsPerFrame;
			delta = undraw;
			lastTime = now;

			if (undraw > 1.0) {
				tick(); // wtf ??
				undraw--;
				tpsCounter++;
				canRender = true;
			} else
				canRender = false;

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (canRender) {
				renderingAnimations();
				fpsCounter++;

			}
			if (System.currentTimeMillis() - 1000 > counterTime) {
				// System.out.println("fps: " + fpsCounter + " tps: " +
				// tpsCounter);
				counterTime += 1000;
				fpsCounter = 0;
				tpsCounter = 0;

			}
		}
		System.exit(0);
	}

	private void renderingAnimations() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		map.renderMap(camera.x, camera.y, screen);

		for (int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = screen.pixels[i];
		}

		Graphics2D g2D = (Graphics2D) bs.getDrawGraphics();

		g2D.drawImage(background, 0, 0, null);

		for (Entity e : this.entities) {
			if (!e.isRemove(this.camera))
				e.draw(g2D);
		}
		g2D.setColor(Color.BLUE);
		g2D.setFont(new Font("tahoma", Font.BOLD, 30));
		g2D.drawString("camera :" + this.camera.x + " " + this.camera.y, 0, 30);
		g2D.drawString("hero :" + this.hero.getLocation().x + " " + this.hero.getLocation().y, 0, 80);
		g2D.dispose();
		bs.show();
	}

	private void tick() {
		cameraMovementChecking();

		hero.update(delta);
		keys.update();
		mouse.update();

	}

	private void cameraMovementChecking() {
		int xRemain = camera.width >> 1;
		int yRemain = camera.height >> 1;
		int newX = this.hero.getLocation().x - xRemain;
		int newY = this.hero.getLocation().y - yRemain;
		camera.setCamera(newX, newY);
	}

	public void start() {
		if (animations == null) {
			this.animations = new Thread(this, "MainView-Animations-Thread!");
			this.running = true;
			this.animations.start();
		}
		return;
	}

	public void stop() {
		if (running) {
			running = !running;
		}
		return;
	}

	@Override
	public void pause() {
		try {
			this.animations.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void continuting() {
		this.animations.notify();
	}

}
