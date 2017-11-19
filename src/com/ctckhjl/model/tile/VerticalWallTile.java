package com.ctckhjl.model.tile;

import com.ctckhjl.model.Sprite;
import com.ctckhjl.model.SpriteSheet;

public class VerticalWallTile extends Tile {
	public VerticalWallTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}


	private static final Tile verticalWallTile = new VerticalWallTile( new Sprite(64, new SpriteSheet("resources/map/basic-map/tiled/vertical-wall-tiled.png", 64), 0, 0));

	
	public static Tile getInstance() {
		return verticalWallTile;
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
