package com.mygdx.game;

import java.util.ArrayList;

public class Cooldowns {
	float seconds;
	float totalSec;
	float chargeSec;
	char type;
	
	public Cooldowns(float time, char type){
		seconds = time;
		totalSec = time;
		this.type = type;
	}
	
	
}
