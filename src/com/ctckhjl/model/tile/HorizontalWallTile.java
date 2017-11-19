package com.ctckhjl.model.tile;

import com.ctckhjl.model.Sprite;
import com.ctckhjl.model.SpriteSheet;

public class HorizontalWallTile extends Tile {
	public HorizontalWallTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	private static final Tile horizontalWallTile = new HorizontalWallTile(
			new Sprite(64, new SpriteSheet("resources/map/basic-map/tiled/horizontal-wall-tiled.png", 64), 0, 0));

	public static Tile getInstance() {
		return horizontalWallTile;
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
