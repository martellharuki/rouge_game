package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Block {
	float dmgRdc = (float) .70;
	float cooldown = 8;
	boolean active;
	float held = 0;
	double perfectFrame = .1;
	Texture texture[];
	
	public Block() {
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Main Character/Shield.png"));
	}
	
	public void makeBlock(ArrayList<gameEntity> entities, ArrayList<Cooldowns> cooldowns, MainCharacter mainChar) {
		if(held > 2) {
			cooldowns.add(new Cooldowns(cooldown + held * 2, 'b'));
			reset();
		} else {
			active = true;
			perfectFrame -= Gdx.graphics.getDeltaTime();
			held += Gdx.graphics.getDeltaTime();
			entities.add(new gameEntity(mainChar.currX - 25, mainChar.currY - 15, 0, 80, 80, 0, texture));
		}
	}
	
	public void clean(ArrayList<Cooldowns> cooldowns) {
		if(active == false && held > 0) {
			cooldowns.add(new Cooldowns(cooldown, 'b'));
			reset();
		}
		active = false;
	}
	
	public void reset() {
		active = false;
		held = 0;
		perfectFrame = .1;
	}
}
