package com.ctckhjl.utils;

public class Vector {
	public double x, y;

	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Vector(Vector v) {
		this.x = v.x;
		this.y = v.y;
	}

	private Vector() {

	}

	public Vector sub(Vector v) {
		Vector p = new Vector();
		p.x = v.x - this.x;
		p.y = v.y - this.y;
		return p;
	}

	public Vector add(Vector v) {
		Vector p = new Vector();
		p.x = v.x + this.x;
		p.y = v.y + this.y;
		return p;
	}

	public double length() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public Vector normalLized() {
		double length = this.length();
		Vector v = new Vector();
		v.x = this.x / length;
		v.y = this.y / length;
		return v;
	}
	public double product(Vector u){
		return u.x*this.x + u.y*this.y; 
	}

	public boolean compare(Vector vecto) {
		return this.x == vecto.x && this.y == vecto.y;
	}
}
