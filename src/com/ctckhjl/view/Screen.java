package com.ctckhjl.view;

import java.util.Random;

import com.ctckhjl.entity.Entity;
import com.ctckhjl.model.tile.Tile;

public class Screen {
	public final int width, height;
	int[] pixels;

	public static final int MAP_SIZE = 64;
	public static final int MAP_SIZE_MARK = MAP_SIZE - 1;
	int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	Random rd = new Random();

	public Screen(int width, int height) {
		super();
		this.width = width;
		this.height = height;
//		System.out.println("pixels in screen : " + this.width * this.height);
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			this.tiles[i] = rd.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}



	public void drawTiles(Tile[] tiles) {

		for (int r = 0, index = 0; r < this.height; r = r + Tile.HEIGHT) {
			for (int col = 0; col < this.width; col = col + Tile.WIDTH) {
				this.drawTile(r, col, tiles[index++]);
			}
		}
	}

	private void drawTile(int r, int c, Tile tile) {
		for (int row = r, index = 0; row < r + Tile.HEIGHT; row++) {
			for (int col = c; col < c + Tile.WIDTH; col++) {
				this.pixels[col + row * this.width] = tile.getPixels()[index++];

			}
		}
	}

//	public void drawEntity(int xRelative, int yRelative, Entity entity) {
//		int xP = xRelative;
//		int yP = yRelative;
////		System.out.println("xRelative  + yRelative " + xP +" " + yP);
//		int maxXP = xP + entity.width;
//		int maxYP = yP + entity.height;
//		final int[] entityPixels = entity.getPixels();
//		for (int row = yP, index = 0; row < maxYP; row++) {
//			for (int col = xP; col < maxXP; col++) {
//				int color = entityPixels[index++];
//				this.pixels[col + row * this.width] = color;
//			}
//		}
//		
//		
//	}

	public void drawPixels(int[] currentPixels) {
		this.pixels = currentPixels;
	}

	

	
	

}
