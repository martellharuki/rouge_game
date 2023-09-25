package com.mygdx.game;

public class Channel {
	float currChannel;
	float finChannel;
	float startChannel;
	String type;
	
	public Channel() {
	}
	
	public Channel(float finChannel, float startChannel, String type) {
		currChannel = 0;
		this.finChannel = finChannel;
		this.startChannel = startChannel;
		this.type = type;
	}
}
