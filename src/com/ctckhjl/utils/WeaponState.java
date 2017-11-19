package com.ctckhjl.utils;

public enum WeaponState {
	HAND_GUN, KNIFE, RIFLE, SHOT_GUN;
	private String simpleName = "";

	public String getSimpleName(WeaponState s) {

		switch (s) {
		case HAND_GUN: {
			simpleName = "handgun";
			break;
		}
		case KNIFE: {
			simpleName = "knife";
			break;
		}
		case RIFLE: {
			simpleName = "rifle";
			break;
		}
		case SHOT_GUN: {
			simpleName = "shotgun";
			break;
		}

		}
		return simpleName;
	}

	public int getID(WeaponState state) {
		int id = 0;
		switch (state) {
		
		case RIFLE: {
			id = 0;
			break;
		}
		case SHOT_GUN: {
			id = 1;
			break;
		}
		case HAND_GUN: {
			id = 2;
			break;
		}
		case KNIFE: {
			id = 3;
			break;
		}
		}

		return id;
	}
}
