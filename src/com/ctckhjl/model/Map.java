package com.ctckhjl.model;

import java.util.ArrayList;
import java.util.List;

import com.ctckhjl.entity.Entity;
import com.ctckhjl.model.tile.Tile;
import com.ctckhjl.view.Screen;

public abstract class Map {
	public final int width, height;

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	

	}

	public void renderMap(int currentXScroll, int currentYScroll, Screen screen) {
	}



	public abstract Tile getTile(int x , int y);


}
