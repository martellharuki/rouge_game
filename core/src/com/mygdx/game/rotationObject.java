package com.mygdx.game;

public class rotationObject {
	float srcX;
	float srcY;
	float angle;
	boolean clockwise;
	
	public rotationObject(float srcX, float srcY, float angle, boolean clockwise){
		this.srcX = srcX;
		this.srcY = srcY;
		this.angle = angle;
		this.clockwise = clockwise;
	}
	
	public rotationObject() {
		this.angle = 0;
	}
}
