package com.ctckhjl.model.weapon;

public class Gun extends Weapon {
	private int mag;
	private int bulletPerMag;
	private int maxDistanceOfBullet;

	public Gun(int damge, int speedOfAttack, int distanceAttack, int mag, int bulletPerMag, int maxDistanceOfBullet) {
		super(damge, speedOfAttack, distanceAttack);
		this.mag = mag;
		this.distanceAttack = distanceAttack;
		this.bulletPerMag = bulletPerMag;
		this.maxDistanceOfBullet = maxDistanceOfBullet;
	}

}
