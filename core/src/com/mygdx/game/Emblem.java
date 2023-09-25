package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Emblem {
	Texture mainText[];
	Texture buffText[];
	Texture subBuffText[];
	Texture subText[];
	
	public Emblem() {
		mainText = new Texture[10];
		subText = new Texture[5];
		for(int i = 0; i < 10; i++) {
		mainText[i] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/Emblem" + i +".png"));
		}
		subText[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/DashEmblem.png"));
		subText[4] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/shieldEmblem.png"));
		for(int i = 1; i < 4; i++) {
			subText[i] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/Emblem0.png"));
		}
		buffText = new Texture[9];
		subBuffText = new Texture[4];
		for(int i = 0; i < 4; i++) {
			subBuffText[i] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/Emblem0.png"));
		}
		for(int i = 0; i < 9; i++) {
			buffText[i] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/buffEmblem" + i +".png"));
		}
	}
}
