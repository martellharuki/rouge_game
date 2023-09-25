package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class gameEntity {
	int speed;
	float sizeX;
	float sizeY;
	float currX;
	float currY;
	int textNum = 0;
	int offSet;
	float damage;
	Texture[] texture;
	smallObject commands;
	TextureRegion tRegion;
	HitBoxes hitbox;
	char type = ' ';
	char centerType = ' ';
	String castType = "";
	
	public gameEntity(float currX, float currY,int speed, float sizeX, float sizeY, float damage, Texture[] image) {
		this.currX = currX;
		this.currY = currY;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.damage = damage;
		texture = new Texture[image.length];
		for(int i = 0; i < image.length; i++) {
			texture[i] = image[i];
		}
		commands = new smallObject();
	}
	
	public gameEntity(float currX, float currY,int speed, float sizeX, float sizeY, Texture[] image, float turn, float stillTurn, int xSizeChange, int ySizeChange, int xpositionChange, int yPositionChange, float damage) {
		this.currX = currX;
		this.currY = currY;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.damage = damage;
		commands = new smallObject(turn, stillTurn, xSizeChange, ySizeChange, xpositionChange, yPositionChange);
		texture = new Texture[image.length];
		for(int i = 0; i < image.length; i++) {
			texture[i] = image[i];
		}
		
		//tRegion = new TextureRegion(texture[0]);
	}
	
	public gameEntity(float currX, float currY,int speed, float sizeX, float sizeY, Texture[] image, float turn, float stillTurn, int xSizeChange, int ySizeChange, int xpositionChange, int yPositionChange, float startX, float startY, float finX, float finY, float damage) {
		this.currX = currX;
		this.currY = currY;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.damage = damage;
		commands = new smallObject(turn, stillTurn, xSizeChange, ySizeChange, xpositionChange, yPositionChange, startX, startY, finX, finY);
		texture = new Texture[image.length];
		for(int i = 0; i < image.length; i++) {
			texture[i] = image[i];
		}
		//tRegion = new TextureRegion(texture[0]);
	}
	
	public gameEntity(float currX, float currY,int speed, float sizeX, float sizeY, Texture[] image, float turn, float stillTurn, int xSizeChange, int ySizeChange, int xpositionChange, int yPositionChange, float startX, float startY, float finX, float finY,float srcX, float srcY, float angle, boolean clockwise, float damage) {
		this.currX = currX;
		this.currY = currY;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.damage = damage;
		commands = new smallObject(turn, stillTurn, xSizeChange, ySizeChange, xpositionChange, yPositionChange, startX, startY, finX, finY, srcX, srcY, angle, clockwise);
		texture = new Texture[image.length];
		for(int i = 0; i < image.length; i++) {
			texture[i] = image[i];
		}
		//tRegion = new TextureRegion(texture[0]);
	}
	
	public gameEntity(boolean permaRotate, float turn, int xSizeChange, int ySizeChange, int finX, int finY, Texture[] image, float damage) {
		texture = new Texture[image.length];
		for(int i = 0; i < image.length; i++) {
			texture[i] = image[i];
		}
		commands = new smallObject(permaRotate, turn, xSizeChange, ySizeChange, finX, finY);
		sizeX = 10;
		sizeY = 10;
		this.damage = damage;
	}
	
	public gameEntity(float currX, float currY,int speed, float sizeX, float sizeY, float damage) {
		this.currX = currX;
		this.currY = currY;
		this.speed = speed;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.damage = damage;
	}
	
	public void valueChange() {
		if(commands.turn - commands.stillTurn > 0) {
			sizeX += commands.xSizeChange * Gdx.graphics.getDeltaTime() / (commands.totalTurn - commands.stillTurn);
			sizeY += commands.ySizeChange * Gdx.graphics.getDeltaTime() / (commands.totalTurn - commands.stillTurn);
			currX += commands.xChange * Gdx.graphics.getDeltaTime() / (commands.totalTurn - commands.stillTurn);
			currY += commands.yChange * Gdx.graphics.getDeltaTime() /(commands.totalTurn - commands.stillTurn);
			if(commands.finX != 0 || commands.finY != 0) {
			commands.startX += (float)((double)commands.finX * (double)Gdx.graphics.getDeltaTime() / ((double)commands.totalTurn - (double)commands.stillTurn)); 
			commands.startY += (float)((double)commands.finY * (double)Gdx.graphics.getDeltaTime() / ((double)commands.totalTurn - (double)commands.stillTurn)); 
			if(commands.startX > commands.finX && commands.finX != 0){
				commands.startX = commands.finX;
			}
			if(commands.startY > commands.finY && commands.finY != 0){
				commands.startY = commands.finY;
			}
			}
		}
		if(commands.turn > 0) {
			commands.turn -= Gdx.graphics.getDeltaTime();
		} else if(commands.stillTurn > 0) {
			commands.stillTurn -= Gdx.graphics.getDeltaTime();
		}
	}
}
