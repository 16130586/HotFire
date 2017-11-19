package com.ctckhjl.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.ctckhjl.model.Map;
import com.ctckhjl.model.Sprite;
import com.ctckhjl.utils.Vector;
import com.ctckhjl.view.Camera;

public abstract class Entity {
	protected Point location;
	protected Vector vector;
	protected BufferedImage img;
	protected boolean remove;
	public int width, height;
	protected String path;
	protected Map map;
	protected Vector imageDirector = new Vector(1, 0);
	protected Vector directionVector = new Vector(0 , 0);
	protected double deltaUpdate = 0.0;

	public Entity() {
		this.vector = new Vector(0, 0);
		this.location = new Point(0, 0);
		this.remove = false;
	}

	protected abstract boolean isCollision();

	public boolean isCollision(Entity e) {
		return false;
	}

	public Point getLocation() {
		return new Point(this.location.x, this.location.y);
	}

	public boolean isRemove(Camera c) {
		int xEntity = this.location.x;
		int yEntity = this.location.y;
		boolean r = false;
		if (xEntity > c.maxX || xEntity < c.x || yEntity < c.y || yEntity > c.maxY) {
			r = true;
		}
		if (xEntity > c.maxX && yEntity > c.maxY)
			r = true;
		return r;
	}

	public void setRemove(boolean value) {
		this.remove = value;
	}

	public BufferedImage getImg() {
		return this.img;
	}

	public abstract void update(double delta);

	public void draw(Graphics2D g2d) {

	}

}
