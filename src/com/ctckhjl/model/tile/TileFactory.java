package com.ctckhjl.model.tile;

public class TileFactory {
	public static final int BACKGROUND_TITLE = 0xff0094FF;
	public static final int HORIZONTAL_WALL_TILE = 0xff0026FF;
	public static final int VERTICAL_WALL_TILE = 0xffFF00DC;

	public static Tile getInstance(int tileKind) {
		Tile o = null;
		switch (tileKind) {
		case BACKGROUND_TITLE: {
//			System.out.println("background");
			o = BackgroundTile.getInstance();
			break;
		}

		case HORIZONTAL_WALL_TILE: {
			o = HorizontalWallTile.getInstance();
			break;
		}
		case VERTICAL_WALL_TILE: {
			o = VerticalWallTile.getInstance();
			break;
		}

		default:
			o = VoidTiled.getInstance();
		}
		return o;
	}
}
