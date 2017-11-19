package com.ctckhjl.view;

public class Camera {
	public int x, y;
	public int width, height;
	public int maxX, maxY;
	private static final Camera instance = new Camera();

	private Camera(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		calMaxView();
	};

	private Camera() {
		calMaxView();
	}

	private void calMaxView() {
		this.maxX = this.x + width;
		this.maxY = this.y + height;
	}

	public static Camera getInstance() {
		return instance;
	}

	public void setDefault() {
		this.x = 0;
		this.y = 0;
		calMaxView();
	}

	public void setCamera(int x, int y) {
		this.x = x;
		this.y = y;
		calMaxView();
	}

	public Camera setWidth(int width) {
		this.width = width;
		calMaxView();
		return this;
	}

	public Camera setHeight(int height) {
		this.height = height;
		calMaxView();
		return this;
	}

}
