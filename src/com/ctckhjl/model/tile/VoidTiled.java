package com.ctckhjl.model.tile;

import com.ctckhjl.model.Sprite;
import com.ctckhjl.model.SpriteSheet;

public class VoidTiled extends Tile {
	public VoidTiled(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	private static final Tile voidTiled = new VoidTiled(new Sprite(64, 64, new SpriteSheet("resources/map/basic-map/tiled/void-tiled.png", 64), 0, 0));

	public static Tile getInstance() {
		return voidTiled;
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
