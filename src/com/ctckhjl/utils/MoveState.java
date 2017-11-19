package com.ctckhjl.utils;

public enum MoveState {
	LEFT, RIGHT, UP, DOWN, STAND, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN;
	private Vector v = new Vector(0, 0);

	public Vector getVecto(MoveState mstate) {
		switch (mstate) {
		case STAND:
			v.x = 0;
			v.y = 0;
			break;
		case LEFT:
			v.x = -1;
			v.y = 0;
			break;
		case RIGHT:
			v.x = 1;
			v.y = 0;
			break;
		case UP:
			v.x = 0;
			v.y = -1;
			break;
		case DOWN:
			v.x = 0;
			v.y = 1;
			break;
		case LEFT_UP:
			v.x = -1;
			v.y = -1;
			break;
		case LEFT_DOWN:
			v.x = -1;
			v.y = 1;
			break;
		case RIGHT_UP:
			v.x = 1;
			v.y = -1;
			break;
		case RIGHT_DOWN:
			v.x = 1;
			v.y = 1;
			break;
		default:
			v.x = 0;
			v.y = 0;
			break;
		}
		return v;
	}

}
