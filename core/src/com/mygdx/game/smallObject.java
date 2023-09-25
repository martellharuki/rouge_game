package com.mygdx.game;

public class smallObject {
	float xSizeChange;
	float ySizeChange;
	float xChange;
	float yChange;
	float turnTimers[];
	int stage;
	float turn;
	float startX;
	float startY;
	float finX;
	float finY;
	float totalTurn;
	float stillTurn;
	boolean permaRotate;
	float xCircChange;
	float yCircChange;
	int circTurn;
	float distance;
	float changeInDist;
	int distChange;
	String type = " ";
	rotationObject rotation;
	
	public smallObject() {
		rotation = new rotationObject();
	}
	public smallObject(float turn, float stillTurn, int xSizeChange, int ySizeChange, int xPositionChange, int yPositionChange) {
		this.turn = turn;
		this.stillTurn = stillTurn;
		this.totalTurn = turn;
		this.xSizeChange = xSizeChange;
		this.ySizeChange = ySizeChange;
		this.xChange = xPositionChange;
		this.yChange = yPositionChange;
		rotation = new rotationObject();
	}
	
	public smallObject(float turn, float stillTurn, int xSizeChange, int ySizeChange, int xPositionChange, int yPositionChange, float startX, float startY, float finX, float finY) {
		this.turn = turn;
		this.totalTurn = turn;
		this.stillTurn = stillTurn;
		this.xSizeChange = xSizeChange;
		this.ySizeChange = ySizeChange;
		this.xChange = xPositionChange;
		this.yChange = yPositionChange;
		this.startX = startX;
		this.startY = startY;
		this.finX = finX;
		this.finY = finY;
		rotation = new rotationObject();
	}
	
	public smallObject(float turn, float stillTurn, int xSizeChange, int ySizeChange, int xPositionChange, int yPositionChange, float startX, float startY, float finX, float finY, float srcX, float srcY, float angle, boolean clockwise) {
		this.turn = turn;
		this.totalTurn = turn;
		this.stillTurn = stillTurn;
		this.xSizeChange = xSizeChange;
		this.ySizeChange = ySizeChange;
		this.xChange = xPositionChange;
		this.yChange = yPositionChange;
		this.startX = startX;
		this.startY = startY;
		this.finX = finX;
		this.finY = finY;
		rotation = new rotationObject(srcX, srcY, angle, clockwise);
	}
	
	public smallObject(float turn, float xCircChange, float yCircChange, float finX, float finY, float distance, float changeInDist, int distChange, int stage, String type, int circTurn) {
		this.turn = turn;
		this.totalTurn = turn;
		this.finX = finX;
		this.finY = finY;
		this.xCircChange = (float) (xCircChange * Math.PI);
		this.yCircChange = (float) (yCircChange * Math.PI);
		this.distance = distance;
		this.changeInDist = changeInDist;
		this.distChange = distChange;
		//this.turnTimers = new float[turnTimers.length];
		/*for(int i = 0; i < turnTimers.length; i++) {
			this.turnTimers[i] = turnTimers[i];
		}*/
		this.stage = stage;
		this.type = type;
		this.circTurn = circTurn;
		rotation = new rotationObject();
	}
	
	public smallObject(boolean permaRotate, float turn, int xSizeChange, int ySizeChange, int finX, int finY) {
		this.permaRotate = permaRotate;
		this.totalTurn = turn;
		this.turn = turn;
		this.xSizeChange = xSizeChange;
		this.ySizeChange = ySizeChange;
		this.finX = finX;
		this.finY = finY;
	}
}
