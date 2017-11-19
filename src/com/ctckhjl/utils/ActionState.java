package com.ctckhjl.utils;

public enum ActionState {
	MELEE_ACTTACK, MOVE, RELOAD, SHOOT;
	private String simpleName = "";

	public String getSimpleName(ActionState s) {
		switch (s) {
		case MELEE_ACTTACK: {
			simpleName = "meleeattack";
			break;
		}
		case MOVE: {
			simpleName = "move";
			break;
		}
		case RELOAD: {
			simpleName = "reload";
			break;
		}
		case SHOOT: {
			simpleName = "shoot";
			break;
		}

		}
		return simpleName;
	}
}
