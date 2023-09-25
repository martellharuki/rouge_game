package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class Casts {
	int upgradePattern[];
	int levelTree[];
	int currLev;
	String type;
	String levelType;
	Random rando;
	int num;
	int charges = 0;
	int currCharge = 0;
	int upgrade = 0;
	int healCast = 0;
	int totalHealCast = 0;
	float cooldown;
	
	public Casts( String type, String levelType, int num, float cooldown){
		upgradePattern = new int[3];
		levelTree = new int[2];
		this.type = type;
		this.levelType = levelType;
		this.num = num;
		this.cooldown = cooldown;
		rando = new Random();
	}
	
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		System.out.println("Error");
	}
	
	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		System.out.println("Error");
	}
	public void levelChange(Buffs buffs) {
	}
	
	public void getLevelType() {
	}
}
