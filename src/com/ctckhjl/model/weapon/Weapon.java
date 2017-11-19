package com.ctckhjl.model.weapon;

import com.ctckhjl.entity.Entity;

public abstract class Weapon {
	protected int damge;
	protected int speedOfAttack;
	protected int distanceAttack;

	public Weapon(int damge, int speedOfAttack, int distanceAttack) {
		super();
		this.damge = damge;
		this.speedOfAttack = speedOfAttack;
		this.distanceAttack = distanceAttack;
	}
}
