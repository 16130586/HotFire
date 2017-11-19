package com.ctckhjl.model.tile;

import com.ctckhjl.model.Sprite;
import com.ctckhjl.model.SpriteSheet;

public class BackgroundTile extends Tile {
	private static final Tile backgroundTile = new BackgroundTile(
			new Sprite(64, new SpriteSheet("resources/map/basic-map/tiled/background.png", 64), 0, 0));
	public BackgroundTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	

	public static Tile getInstance() {
		return backgroundTile;
	}
	@Override
	public boolean isSolid() {
		return false;
	}
}
