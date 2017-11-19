package com.ctckhjl.utils;

public enum AnimationState {
	HAND_GUN_ACTTACK, HAND_GUN_MOVE, HAND_GUN_RELOAD, HAND_GUN_SHOOT, KNIFE_ACTTACK, KNIFE_MOVE, RIFLE_ACTTACK, RIFLE_MOVE, RIFLE_RELOAD, RIFLE_SHOOT, SHOT_GUN_ACTTACK, SHOT_GUN_MOVE, SHOT_GUN_RELOAD, SHOT_GUN_SHOOT;
	private int numAnimateFrame = 0;

	public int getAnimateNumberFrame(AnimationState s) {
		switch (s) {
		case HAND_GUN_ACTTACK: {
			numAnimateFrame = 15;
			break;
		}
		case HAND_GUN_MOVE: {
			numAnimateFrame = 20;
			break;
		}
		case HAND_GUN_RELOAD: {
			numAnimateFrame = 15;
			break;
		}
		case HAND_GUN_SHOOT: {
			numAnimateFrame = 3;
			break;
		}
		case KNIFE_ACTTACK: {
			numAnimateFrame = 15;
			break;
		}
		case KNIFE_MOVE: {
			numAnimateFrame = 20;
			break;
		}
		case RIFLE_ACTTACK: {
			numAnimateFrame = 15;
			break;
		}
		case RIFLE_MOVE: {
			numAnimateFrame = 20;
			break;
		}
		case RIFLE_RELOAD: {
			numAnimateFrame = 20;
			break;
		}
		case RIFLE_SHOOT: {
			numAnimateFrame = 3;
			break;
		}
		case SHOT_GUN_ACTTACK: {
			numAnimateFrame = 15;
			break;
		}
		case SHOT_GUN_MOVE: {
			numAnimateFrame = 20;
			break;
		}
		case SHOT_GUN_RELOAD: {
			numAnimateFrame = 20;
			break;
		}
		case SHOT_GUN_SHOOT: {
			numAnimateFrame = 3;
			break;
		}

		}
		return numAnimateFrame;
	}

	public String getCurrentAnimation(WeaponState wpS, MoveState mS, ActionState aS, int currentFrame) {
		String result = wpS.getSimpleName(wpS) + "." + aS.getSimpleName(aS) + "." + currentFrame;
		return result;
	}

	public AnimationState getAnimationSate(WeaponState weaponState, ActionState actionState) {
		switch (actionState) {
		case MELEE_ACTTACK: {
			switch (weaponState) {
			case HAND_GUN:
				return HAND_GUN_ACTTACK;
			case KNIFE:
				return KNIFE_ACTTACK;
			case RIFLE:
				return RIFLE_ACTTACK;
			case SHOT_GUN:
				return SHOT_GUN_ACTTACK;
			default:
				return null;
			}

		}
		case RELOAD: {
			switch (weaponState) {
			case HAND_GUN:

				return HAND_GUN_RELOAD;
			case RIFLE:

				return RIFLE_RELOAD;
			case SHOT_GUN:

				return SHOT_GUN_RELOAD;
			default:
				return null;
			}

		}
		case SHOOT: {
			switch (weaponState) {
			case HAND_GUN:
				return HAND_GUN_SHOOT;
			case RIFLE:
				return RIFLE_SHOOT;
			case SHOT_GUN:
				return SHOT_GUN_SHOOT;
			default:
				return null;
			}

		}
		case MOVE: {
			switch (weaponState) {
			case HAND_GUN:

				return HAND_GUN_MOVE;
			case KNIFE:
				return AnimationState.KNIFE_MOVE;
			case RIFLE:
				return AnimationState.RIFLE_MOVE;
			case SHOT_GUN:
				return AnimationState.SHOT_GUN_MOVE;

			default:
				return null;
			}

		}
		default:
			return null;
		}

	}
}
