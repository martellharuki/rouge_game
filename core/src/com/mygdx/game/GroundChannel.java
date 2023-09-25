package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GroundChannel {
	int turn;
	Texture texture[];
	
	public GroundChannel() {
		turn =  0;
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Main Character/channelGround.png"));
	}
	
	public void turnUpdate() {
		turn++;
		if(turn > 40) {
			turn = 0;
		}
	}
}
