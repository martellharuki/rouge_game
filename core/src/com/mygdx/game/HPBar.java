package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HPBar {
	Texture texture[];
	
	public HPBar() {
		texture = new Texture[4];
		texture[0] = new Texture(Gdx.files.internal("Images/Main Character/HPBar.png"));
		texture[1] = new Texture(Gdx.files.internal("Images/Main Character/HPBar1.png"));
		texture[2] = new Texture(Gdx.files.internal("Images/Main Character/VHPBar.png"));
		texture[3] = new Texture(Gdx.files.internal("Images/Main Character/ArmourBar0.png"));
	}
}
