package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dash {
	Texture texture[] = new Texture[1];
	float cooldown = 6;
	float oldX = 0;
	float oldY = 0;
	float dashTimer = 0;
	float maxTimer = 0;
	
	public Dash() {
		texture[0] = new Texture(Gdx.files.internal("Images/Main Character/telePortCyrstal.png"));
	}
	
}
