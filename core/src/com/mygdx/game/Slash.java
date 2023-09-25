package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Slash {
	Texture texture[] = new Texture[2];
	//float hitboxes[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}};
	public Slash() {
		for(int i = 0; i < 2; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Basic Slash/slash" + i + ".png"));
		}
	}
}
