package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class MainCharacter extends gameEntity{
	float graphicWidth;
	float graphicHeight;
	float health = 100;
	float maxHealth = 100;
	float maxArmour = 0;
	float buffs[][];
	float armour = 0;
	int xDir = 0;
	int yDir = 0;
	Channel channels[];
	
	
	public MainCharacter(float currX, float currY, int speed, float sizeX, float sizeY, Texture[] image, float graphicWidth, float graphicHeight){
		super(currX, currY, speed, sizeX, sizeY, 0, image);
		this.graphicWidth = graphicWidth;
		this.graphicHeight = graphicHeight;
		channels = new Channel[1];
		channels[0] = new Channel();
		buffs = new float[2][5];
	}
	
	public void addArmour(int amount){
		maxHealth -= amount;
		maxArmour += amount;
		if(maxArmour > 100) {
			maxArmour = 100;
			maxHealth = 0;
		}
		heal(amount);
	}
	
	public void heal(float amount) {
		health += amount;
		if(health  > maxHealth) {
			armour += health - maxHealth;
			health = maxHealth;
			if(armour > maxArmour) {
				armour = maxArmour;
			}
		}
	}
	
	public void takeDamage(float damage) {
		if(damage > armour) {
			armour = 0;
			health -= damage - armour;
		} else {
			armour -= damage;
		}
	}
	public void cleanBuffs() {
		for(int i = 0; i < buffs.length; i++) {
			buffs[0][i] -= Gdx.graphics.getDeltaTime();
			if(buffs[0][i] <= 0) {
				buffs[1][i] = 0;
			}
		}
	}
}
