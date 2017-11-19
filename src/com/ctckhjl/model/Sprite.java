package com.ctckhjl.model;

public class Sprite {
	public final int WIDTH;
	public final int HEIGHT;
	public int[] pixels;
	private SpriteSheet sheet;
	private int xLocatedInSheet, yLocatedInSheet;
	static int counter = 0;

	public Sprite(Sprite s) {
		super();
		this.WIDTH = this.HEIGHT = 16;
		this.pixels = new int[s.pixels.length];
		for (int i = 0; i < s.pixels.length; i++) {
			this.pixels[i] = s.pixels[i];
		}
		this.sheet = s.sheet;
		this.xLocatedInSheet = s.xLocatedInSheet;
		this.yLocatedInSheet = s.yLocatedInSheet;
		load();
	}

	public Sprite(int size, int[] pixels) {
		super();
		this.WIDTH = this.HEIGHT = size;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	public Sprite(int size, SpriteSheet sheet, int xLocatedInSheet, int yLocatedInSheet) {
		super();
		this.WIDTH = this.HEIGHT = size;
		this.pixels = new int[WIDTH * HEIGHT];
		this.sheet = sheet;
		this.xLocatedInSheet = xLocatedInSheet;
		this.yLocatedInSheet = yLocatedInSheet;
		load();

	}

	public Sprite(int width, int height, SpriteSheet sheet, int xLocatedInSheet, int yLocatedInSheet) {
		super();
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sheet = sheet;
		this.pixels = new int[this.WIDTH * this.HEIGHT];
		this.xLocatedInSheet = xLocatedInSheet;
		this.yLocatedInSheet = yLocatedInSheet;
		load();
	}

	private void load() {
		for (int y = 0; y < this.HEIGHT; y++) {
			for (int x = 0; x < this.WIDTH; x++) {
				int color = this.sheet.pixels[x + (this.xLocatedInSheet * this.WIDTH)
						+ (y + this.yLocatedInSheet * this.HEIGHT) * this.sheet.SIZE];
				this.pixels[x + y * this.WIDTH] = color;
			}
		}

	}

}
