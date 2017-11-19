package com.ctckhjl.model.tile;

import com.ctckhjl.model.Sprite;
import com.ctckhjl.view.Screen;

public abstract class Tile {
	public static final int WIDTH = 64;
	public static final int HEIGHT = 64;
	private final Sprite sprite;

	public Tile( Sprite sprite) {
		super();
		this.sprite = sprite;
	
	}

	public void render(int x, int y, Screen screen) {

	}

	public int[] getPixels() {
		return this.sprite.pixels;
	}
	public abstract boolean isSolid();

}
