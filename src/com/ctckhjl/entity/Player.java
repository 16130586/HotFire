package com.ctckhjl.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.ctckhjl.input.KeyInput;
import com.ctckhjl.input.MouseInput;
import com.ctckhjl.model.Map;
import com.ctckhjl.model.tile.Tile;
import com.ctckhjl.model.weapon.Gun;
import com.ctckhjl.model.weapon.Knife;
import com.ctckhjl.model.weapon.Weapon;
import com.ctckhjl.utils.ActionState;
import com.ctckhjl.utils.AnimationState;
import com.ctckhjl.utils.ImageDependency;
import com.ctckhjl.utils.ImageLoader;
import com.ctckhjl.utils.MoveState;
import com.ctckhjl.utils.Vector;
import com.ctckhjl.utils.WeaponState;
import com.ctckhjl.view.Camera;

public class Player extends Entity implements ImageDependency {
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private MoveState moveState = MoveState.STAND;
	private WeaponState weaponState = WeaponState.KNIFE;
	private ActionState actionState = ActionState.MOVE;
	private AnimationState animationState = AnimationState.KNIFE_MOVE;
	private int currentFrame = 0;
	private int currentMaxFrame = animationState.getAnimateNumberFrame(animationState);
	private java.util.Map<String, BufferedImage> imgs = new HashMap<>();
	private ImageLoader imgLoader;
	private int counter = 0;
	private boolean isSwitchWeapon = false;
	private Weapon[] weapons = new Weapon[4];

	{
		weapons[0] = new Gun(15, 2, 50, 2, 31, 400);
		weapons[1] = new Gun(40, 1, 50, 2, 15, 650);
		weapons[2] = new Gun(7, 4, 20, 4, 8, 250);
		weapons[3] = new Knife(4, 3, 75);
	}

	public Player() {
		super();
		this.img = loadingDefaultImage();

	}

	public Player(int x, int y, int width, int height, KeyInput keys, MouseInput mouseInput, Map map, String path) {
		this.location.x = x;
		this.location.y = y;
		this.keyInput = keys;
		this.mouseInput = mouseInput;
		this.width = width;
		this.height = height;
		this.map = map;
		this.path = path;
		this.imgLoader = new ImageLoader(path);
		this.imgLoader.load(this);

	}

	private BufferedImage loadingDefaultImage() {
		return new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
	}

	@Override
	protected boolean isCollision() {
		return false;
	}

	public void update(double delta) {
		super.deltaUpdate = delta;
		moveChecking();
		switchWeaponChecking();
		updateLocation(delta);
		updateByMouseMoved();
		updateByMouseClicked();
	}

	private void updateByMouseClicked() {
		if(mouseInput.isLeftPress) {
			
		}
		
	}

	private void updateByMouseMoved(boolean isMouseMoved) {
		if (!isMouseMoved || this.img == null)
			return;
		int mX = mouseInput.currentMouse.x;
		int mY = mouseInput.currentMouse.y;
		Camera c = Camera.getInstance();
		int xRelative = getLocation().x - c.x + this.width / 2;
		int yRelative = getLocation().y - c.y + this.height / 2;
		Vector pMouse = new Vector(mX - xRelative, mY - yRelative).normalLized();
		double alpha = Math.acos(this.imageDirector.product(pMouse) / (this.imageDirector.length() * pMouse.length()));
		if (mY > yRelative)
			alpha *= -1;
		this.img = imgLoader.rotation(
				this.imgs.get(animationState.getCurrentAnimation(weaponState, moveState, actionState, currentFrame)),
				-alpha);
		// rotation with alpha is oppositation with current alpha
	}

	private void updateByMouseMoved() {
		updateByMouseMoved(mouseInput.isMouseMoved);
	}

	private void updateDirectionBySwitchWeapons() {
		// fakes mouseMoved to get rolatation effect
		updateByMouseMoved(true);
	}

	private void switchWeaponChecking() {
		if (keyInput.isPress(KeyEvent.VK_1)) {
			if (this.weaponState != WeaponState.RIFLE) {
				isSwitchWeapon = true;
				this.weaponState = WeaponState.RIFLE;

			}
		}
		if (keyInput.isPress(KeyEvent.VK_2)) {
			if (this.weaponState != WeaponState.SHOT_GUN) {
				isSwitchWeapon = true;
				this.weaponState = WeaponState.SHOT_GUN;
			}
		}
		if (keyInput.isPress(KeyEvent.VK_3)) {
			if (this.weaponState != WeaponState.HAND_GUN) {
				isSwitchWeapon = true;
				this.weaponState = WeaponState.HAND_GUN;
			}
		}
		if (keyInput.isPress(KeyEvent.VK_4)) {
			if (this.weaponState != WeaponState.KNIFE) {
				isSwitchWeapon = true;
				this.weaponState = WeaponState.KNIFE;
			}
		}
		if (isSwitchWeapon) {
			this.actionState = ActionState.MOVE;
			this.animationState = this.animationState.getAnimationSate(this.weaponState, this.actionState);
			loadCurrentImage();
			updateDirectionBySwitchWeapons();

		}
		isSwitchWeapon = false;
	}

	private void moveChecking() {

		if (keyInput.isLeft() && keyInput.isUp()) {
			this.moveState = MoveState.LEFT_UP;

		} else if (keyInput.isLeft() && keyInput.isDown()) {
			this.moveState = MoveState.LEFT_DOWN;

		} else if (keyInput.isRight() && keyInput.isUp()) {
			this.moveState = MoveState.RIGHT_UP;

		} else if (keyInput.isRight() && keyInput.isDown()) {
			this.moveState = MoveState.RIGHT_DOWN;

		} else if (keyInput.isDown()) {
			this.moveState = MoveState.DOWN;

		} else if (keyInput.isUp()) {
			this.moveState = MoveState.UP;

		} else if (keyInput.isLeft()) {
			this.moveState = MoveState.LEFT;

		} else if (keyInput.isRight()) {
			this.moveState = MoveState.RIGHT;

		}

		else {
			this.moveState = MoveState.STAND;

		}

		vector = moveState.getVecto(moveState);
	}

	private boolean isNextStepOk(Vector vector) {
		boolean rs = false;
		double remainXDrawingOfPlayer = Tile.WIDTH >> 1;
		double remainYDrawingOfPlayer = Tile.HEIGHT >> 1;
		int nextX = (int) (this.location.x + remainXDrawingOfPlayer + vector.x);
		int nextY = (int) (this.location.y + remainYDrawingOfPlayer + vector.y);
		Tile nextTile = super.map.getTile(nextX, nextY);
		rs = nextTile.isSolid();
		return rs;
	}

	private void updateLocation(double delta) {
		int speed = 2;

		switch (moveState) {
		case UP: {
			this.vector = this.vector.add(new Vector(0, -speed));
			break;
		}
		case DOWN: {
			this.vector = this.vector.add(new Vector(0, speed));
			break;
		}
		case LEFT: {
			this.vector = this.vector.add(new Vector(-speed, 0));
			break;
		}
		case RIGHT: {
			this.vector = this.vector.add(new Vector(speed, 0));
			break;
		}
		case LEFT_UP: {
			this.vector = this.vector.add(new Vector(-speed, -speed));
			break;
		}
		case LEFT_DOWN: {
			this.vector = this.vector.add(new Vector(-speed, speed));
			break;
		}
		case RIGHT_UP: {
			this.vector = this.vector.add(new Vector(speed, -speed));
			break;
		}
		case RIGHT_DOWN: {
			this.vector = this.vector.add(new Vector(speed, speed));
			break;
		}

		default:
			this.vector = new Vector(0, 0);
			break;
		}
		if (isNextStepOk(vector)) {
			// System.out.println("CAN'T MOVEEEEEEE");
			vector.x = 0;
			vector.y = 0;
		}
		calLocation(delta);
		int maxHeight = map.height - this.height;
		int maxWidth = map.width - this.width;
		if (location.y <= 0)
			location.y = 0;
		if (location.y >= maxHeight)
			location.y = maxHeight;
		if (location.x >= maxWidth)
			location.x = maxWidth;
		if (location.x <= 0)
			location.x = 0;

	}

	private void calLocation(double delta) {
		this.location.x = (int) (this.location.x + this.vector.x * delta);
		this.location.y = (int) (this.location.y + this.vector.y * delta);
		this.vector = new Vector(0, 0);
	}

	@Override
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		Camera c = Camera.getInstance();
		int xRelative = getLocation().x - c.x;
		int yRelative = getLocation().y - c.y;
		g2d.drawImage(this.img, xRelative, yRelative, null);
	}

	@Override
	public void setAnimations(java.util.Map<String, BufferedImage> map) {
		this.imgs = map;
		loadCurrentImage();

	}

	private void loadCurrentImage() {
		this.img = this.imgs.get(animationState.getCurrentAnimation(weaponState, moveState, actionState, currentFrame));

	}

}
