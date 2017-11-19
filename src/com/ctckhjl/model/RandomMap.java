package com.ctckhjl.model;

import java.util.Random;

import com.ctckhjl.entity.Entity;
import com.ctckhjl.model.tile.Tile;
import com.ctckhjl.model.tile.TileFactory;
import com.ctckhjl.model.tile.VoidTiled;
import com.ctckhjl.view.Screen;

public class RandomMap extends Map {
	private Tile[] tiles;
	private Random rd;

	public RandomMap(int width, int height) {
		super(width, height);
		tiles = new Tile[(this.width / Tile.WIDTH) * (this.height / Tile.HEIGHT) ];
		System.out.println("Random map : " + this.width + " " + this.height);
		rd = new Random();
		genateTiles();
	}

	private void genateTiles() {
		System.out.println(tiles.length);
		for (int i = 0; i < this.tiles.length; i++)
			tiles[i] = TileFactory.getInstance(rd.nextInt(4));
	}

	@Override
	public void renderMap(int currentXScroll, int currentYScroll, Screen screen) {
		int currentXOfTile = currentXScroll / Tile.WIDTH; // currentXScroll negative -> currentXOfTile -> negative
		int currentYOfTile = currentYScroll / Tile.HEIGHT;
		int lastYTile = currentYOfTile + (screen.height / Tile.HEIGHT) - 1; // negative + positive - > get lost data -> null pointer
		int lastXTile = currentXOfTile + (screen.width / Tile.WIDTH) - 1;
		Tile[] currentTiles = new Tile[(screen.height / Tile.HEIGHT) * (screen.width / Tile.WIDTH)]; // negative + positive - > get lost data

		for (int y = currentYOfTile, index = 0; y <= lastYTile; y++) {
			for (int x = currentXOfTile; x <= lastXTile; x++) {
				currentTiles[index++] = this.getTile(x, y);
			}
		}
		screen.drawTiles(currentTiles);
	}

	public Tile getTile(int x, int y) {
//		System.out.println("in get tiles : " + y + " " + x);
		if (x < 0 || x >= (this.width / Tile.WIDTH) || y < 0 || y >= (this.height / Tile.HEIGHT)) {
//			System.out.println(x + " " + y + " " + "comes heere");
			return VoidTiled.getInstance();
		}
		return tiles[x + y * (this.width / Tile.WIDTH)];
	}
//	@Override
//	protected void renderEntities(Screen screen) {
//		for(Entity e : entities){
//			renderEntity(e, screen);
//		}
//		
//	}
//	@Override
//	protected void renderEntity(Entity entity, Screen screen) {
//		int xP = entity.getLocation().x;
//		int yP = entity.getLocation().y;
//		
//		if (xP < 0 || yP < 0 || entity.isRemove())
//			return;
//		if (xP >= this.width - entity.width)
//			xP = 0;
//		if (yP >= this.height - entity.height)
//			yP = 0;
//		int maxXP = xP + entity.width;
//		int maxYP = yP + entity.height;
//		final int[] entityPixels = entity.getPixels();
//
//		for (int row = yP, index = 0; row < maxYP; row++) {
//			for (int col = xP; col < maxXP; col++) {
////				this.pixels[col + row * this.width] = entityPixels[index++];
//				// System.out.println(entityPixels[index]);
//			}
//		}
//		
//	}
}
