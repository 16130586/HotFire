package com.ctckhjl.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ctckhjl.model.tile.Tile;
import com.ctckhjl.model.tile.TileFactory;

import com.ctckhjl.model.tile.VoidTiled;

import com.ctckhjl.view.Screen;

public class AvailableMap extends Map {
	private String path;
	private int[] pixels = new int[width * height];
	private Tile[] tiles;

	private AvailableMap(int width, int height) {
		super(width, height);
	}

	public AvailableMap(int width, int height, String path) {
		super(width, height);
		this.path = path;
		tiles = new Tile[(this.width * this.height) / (Tile.WIDTH * Tile.HEIGHT)];
		loadMap();
		convertToTiles();
		converToPixels();
	}

	private void converToPixels() {
		int FAC = 6; // ~ Tile.Size = 64 ~ 2^6
		for (int r = 0; r < this.height; r += Tile.HEIGHT) {
			for (int col = 0; col < this.width; col += Tile.WIDTH) {
				Tile t = tiles[(col >> FAC) + (r >> FAC) * (this.width >> FAC)];
				addTileToMap(r, col, t);
			}
		}

	}

	private void addTileToMap(int r, int col, Tile tile) {
		int[] pixels = tile.getPixels();
		int maxMapR = r + Tile.HEIGHT;
		int maxMapCol = col + Tile.WIDTH;
		for (int mR = r , i = 0; mR < maxMapR; mR++) {
			for (int mC = col; mC < maxMapCol; mC++) {
				int index = mC + mR*this.width;
				this.pixels[index] = pixels[i++];
			}
		}

	}

	private void convertToTiles() {
		int i = 0;
		for (int r = 0; r < this.height; r += Tile.HEIGHT)
			for (int col = 0; col < this.width; col += Tile.WIDTH) {
				int color = this.pixels[col + r * this.width];
				Tile t = TileFactory.getInstance(color);
				tiles[i++] = t;
			}
	}

	private void loadMap() {
		try {
			BufferedImage map = ImageIO.read(new File(path));
			BufferedImage temp = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
			Graphics g = temp.createGraphics();
			g.drawImage(map, 0, 0, null);
			g.dispose();
			temp.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderMap(int currentXScroll, int currentYScroll, Screen screen) {
		int[] scrPixels = new int[screen.width * screen.height];
		int scrWidth = screen.width;
		int scrHeight = screen.height;
		for (int r = 0; r < scrHeight; r++) {
			int yP = currentYScroll + r;
			for (int col = 0; col < scrWidth; col++) {
				int xP = currentXScroll + col;
				if (xP < 0 || yP < 0 || xP >= this.width || yP >= this.height) {
					scrPixels[col + r * scrWidth] = 0x000000;
					continue;
				}
				int color = this.pixels[xP + yP * this.width];
				scrPixels[col + r * scrWidth] = color;

			}
		}

		screen.drawPixels(scrPixels);

	
	}

	public Tile getTile(int x, int y) {
		int currentXOfTile = x / Tile.WIDTH;
		int currentYOfTile = y / Tile.HEIGHT;
//		System.out.println(  "nextX " + currentXOfTile + " nextY " + currentYOfTile);
		int maxXOfTile = this.width / Tile.WIDTH;
		int maxYOfTile = this.height / Tile.HEIGHT;
		if (currentXOfTile < 0 || currentXOfTile >= maxXOfTile || currentYOfTile < 0 || currentYOfTile >= maxYOfTile) {
			return VoidTiled.getInstance();
		}
//		System.out.println(currentXOfTile + currentYOfTile*this.height/Tile.HEIGHT  + " of " + tiles.length );
//		System.out.println(this.height/Tile.HEIGHT + " day ne ");
		return tiles[currentXOfTile + currentYOfTile*(this.height/Tile.HEIGHT)];
	}

}
