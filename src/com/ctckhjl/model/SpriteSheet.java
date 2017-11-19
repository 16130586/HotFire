package com.ctckhjl.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public String path;
	public final int SIZE;
	public int[] pixels;

	public SpriteSheet(String path, int size) {
		super();
		this.path = path;
		SIZE = size;
		System.out.println(SIZE);
		this.pixels = new int[this.SIZE * this.SIZE];
		load(this.path);
	}

	private void load(String path) {
		try {

			BufferedImage img = ImageIO.read(new File(path));
			BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = copy.getGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();

			pixels = ((DataBufferInt)copy.getRaster().getDataBuffer()).getData();
		} catch (IOException e) {
			System.out.println("Error with " + path + " " + e.getMessage());
			e.printStackTrace();
		}

	}
}
