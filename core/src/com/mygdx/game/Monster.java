package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Monster extends gameEntity{
	Texture buffText[];
	int otherMonst[] = new int[4];
	float health;
	float totalHealth;
	float stunTimer = 0;
	float vulnTimer = 0;
	float weakTimer = 0;
	float knockBackTimer = 0;
	String monstType = " ";
	float attacks[];
	float buffs[][];
	ArrayList<Float> mainCharCoords = new ArrayList<Float>();
	float mainCharX;
	float mainCharY;
	float travelAngle;
	float hurtTimer = 0;
	float damageCounter[];
	
	public Monster(float currX, float currY, int speed, float sizeX, float sizeY, Texture[] image, float damage, float health) {
		super(currX, currY, speed, sizeX, sizeY, damage, image);
		otherMonst[0] = 1;
		otherMonst[1] = 1;
		otherMonst[2] = 1;
		otherMonst[3] = 1;
		attacks = new float[5];
		buffs = new float[2][1];
		this.health = health;
		totalHealth = health;
		buffText = new Texture[1];
		buffText[0] = new Texture(Gdx.files.internal("Images/Skeleton/Shield.png"));
		damageCounter = new float[2];
	}
	public Monster(float currX, float currY, int speed, float sizeX, float sizeY) {
		super(currX, currY, speed, sizeX, sizeY, 0);
		otherMonst[0] = 1;
		otherMonst[1] = 1;
		otherMonst[2] = 1;
		otherMonst[3] = 1;
		attacks = new float[4];
		damageCounter = new float[2];
	}
	
	public void pathToMain(MainCharacter main) {
		if ((Math.abs(currY - main.currY) < 10 && Math.abs(currX - main.currX) < 10) || (attacks[0] > 0 || stunTimer > 0)) {
		} else {
			float theta = (float) Math.atan(Math.abs(currY - main.currY) / Math.abs(currX - main.currX));
		if(currY - main.currY < 0 && currX - main.currX < 0) {
			currX += speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
			currY += speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
		}else if(currY - main.currY < 0 && currX - main.currX > 0) {
			currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
			currY += speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
		}else if(currY - main.currY > 0 && currX - main.currX < 0){
			currX += speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
			currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
		}else{
			currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
			currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
		}
		}
		monstClean();
	}
	
	public void pathToPoint(float x, float y, float distance) {
		currX += distance * Math.cos(travelAngle) * Gdx.graphics.getDeltaTime();
		currY += distance * Math.sin(travelAngle) * Gdx.graphics.getDeltaTime();
		monstClean();
	}
	
	public void knocked(MainCharacter mainChar, ArrayList<Monster> monsters) {
		if (attacks[0] > 0) {
		} else {
			stunTimer = (float).2;
			float theta = (float) Math.atan(Math.abs(currY - mainChar.currY) / Math.abs(currX - mainChar.currX));
		if(currY - mainChar.currY > 0 && currX - mainChar.currX > 0) {
			currX += 1500 * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
			currY += 1500 * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
		}else if(currY - mainChar.currY > 0 && currX - mainChar.currX < 0) {
			currX -= 1500 * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
			currY += 1500 * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
		}else if(currY - mainChar.currY < 0 && currX - mainChar.currX > 0){
			currX += 1500 * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
			currY -= 1500 * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
		}else{
			currX -= 1500 * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
			currY -= 1500 * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
		}
		if(currX < 0) {
			currX = 0;
			monsterCheck(monsters);
			while(otherMonst[0] == 0 || otherMonst[1] == 0 || otherMonst[2] == 0 || otherMonst[3] == 0) {
				currX += 10;
			}
		} else if(currX > mainChar.graphicWidth - 20) {
			currX = mainChar.graphicWidth - 20;
			while(otherMonst[0] == 0 || otherMonst[1] == 0 || otherMonst[2] == 0 || otherMonst[3] == 0) {
				currX -= 10;
			}
		}
		if(currY < 0) {
			currY = 0;
			while(otherMonst[0] == 0 || otherMonst[1] == 0 || otherMonst[2] == 0 || otherMonst[3] == 0) {
				currY += 10;
			}
		} else if(currY > mainChar.graphicHeight - 40) {
			currY = mainChar.graphicHeight - 40;
			while(otherMonst[0] == 0 || otherMonst[1] == 0 || otherMonst[2] == 0 || otherMonst[3] == 0) {
				currY -= 10;
			}
		}
		}
		monstClean();
	}
	
	public void pathAwayDistance(MainCharacter mainChar, float graphicWidth, float graphicHeight) {
		if(Math.pow(mainChar.currX - this.currX, 2) + Math.pow(mainChar.currY - this.currY, 2) < Math.pow(200, 2) && stunTimer <= 0) {
			float theta = (float)Math.atan((Math.abs(this.currY + sizeY / 2 - mainChar.currY + 25)/Math.abs(this.currX + sizeX / 2 - mainChar.currX)));
			if(this.currX + sizeX / 2 > mainChar.currX + 15&& this.currY + sizeY / 2 < mainChar.currY + 25) {
				theta = (float)(Math.PI - theta);
			} else if( this.currX + sizeX / 2 >  mainChar.currX + 15&& this.currY + sizeY / 2 > mainChar.currY + 25){
				theta += (float)Math.PI;
			} else if(this.currX + sizeX / 2 < mainChar.currX + 15 && this.currY + sizeY / 2 > mainChar.currY + 25) {
				theta= (float)(2 * Math.PI - theta);
			}
			if(((currX + speed * Gdx.graphics.getDeltaTime() > graphicWidth - sizeX) || (currX - speed * Gdx.graphics.getDeltaTime() < 0))) {
				if(!((currY + speed * Gdx.graphics.getDeltaTime() > graphicHeight - 40) || (currY - speed * Gdx.graphics.getDeltaTime() < 0))) {
				if(Math.sin(theta) < 0) {
					currY += speed * Gdx.graphics.getDeltaTime() * otherMonst[0] * otherMonst[1];
				} else {
					currY -= speed * Gdx.graphics.getDeltaTime() * otherMonst[2] * otherMonst[3];
				}
				}
			} else if(!((currY + speed * Gdx.graphics.getDeltaTime() > graphicHeight - sizeY) || (currY - speed * Gdx.graphics.getDeltaTime() < 0))){
				if(Math.cos(theta) < 0) {
					if(Math.sin(theta) < 0) {
						currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
					} else {
						currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
					}
				} else {
					if(Math.sin(theta) < 0) {
						currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
					} else {
						currX -= speed * Math.cos(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
					}
				}
			}
			if((((currY + speed * Gdx.graphics.getDeltaTime() > graphicHeight - sizeY) || (currY - speed * Gdx.graphics.getDeltaTime() < 0)))) {
				if(!((currX + speed * Gdx.graphics.getDeltaTime() > graphicWidth - 40) || (currX - speed * Gdx.graphics.getDeltaTime() < 0))) {
				if(Math.cos(theta) < 0) {
					currX += speed * Gdx.graphics.getDeltaTime() * otherMonst[1] * otherMonst[3];
				} else {
					currX -= speed * Gdx.graphics.getDeltaTime() * otherMonst[0] * otherMonst[2];
				}
				}
			} else if(!((currX + speed * Gdx.graphics.getDeltaTime() > graphicWidth - sizeX) || (currX - speed * Gdx.graphics.getDeltaTime() < 0))){
				if(Math.sin(theta) < 0) {
					if(Math.cos(theta) < 0) {
						currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[0];
					} else {
						currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[1];
					}
				} else {
					if(Math.cos(theta) < 0) {
						currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[2];
					} else {
						currY -= speed * Math.sin(theta) * Gdx.graphics.getDeltaTime() * otherMonst[3];
					}
				}
			
		}
		monstClean();	
	} else {
		pathToMain(mainChar);
	}
	}
	
	public void pathInDirection(float distance, float graphicWidth, float graphicHeight) {
		if(currX + distance * Math.cos(travelAngle) * Gdx.graphics.getDeltaTime() <= 0) {
			distance = Math.abs(currX);
		} else if (currX + distance * Math.cos(travelAngle) * Gdx.graphics.getDeltaTime() >= graphicWidth) {
			distance = Math.abs(graphicWidth - distance);
		}
		if(currY + distance * Math.sin(travelAngle) * Gdx.graphics.getDeltaTime() <= 0) {
			distance = Math.abs(currY);
		} else if (currY + distance * Math.sin(travelAngle) * Gdx.graphics.getDeltaTime() >= graphicHeight) {
			distance = Math.abs(graphicHeight - distance);
		}
		currX -= distance * Math.cos(travelAngle) * Gdx.graphics.getDeltaTime();
		currY -= distance * Math.sin(travelAngle) * Gdx.graphics.getDeltaTime();
		monstClean();
	}
	
	public void setAngle() {
		float theta = (float) Math.atan(Math.abs(currY - mainCharY) / Math.abs(currX - mainCharX));
		if(currY - mainCharY > 0 && currX - mainCharX < 0) {
			theta = (float)(Math.PI - theta); 
		}else if(currY - mainCharY < 0 && currX - mainCharX < 0){
			theta += Math.PI;
		}else if(currY - mainCharY < 0 && currX - mainCharX > 0){
			theta = (float)((2 * Math.PI)- theta); 
		}
		travelAngle = theta;
	}
	
	public void monsterCheck(ArrayList<Monster> monsters) {
		otherMonst[0] = 1;
		otherMonst[1] = 1;
		otherMonst[2] = 1;
		otherMonst[3] = 1;
		for(int i = 0; i < monsters.size(); i++) {
			if(currY - monsters.get(i).currY < 20 && currY - monsters.get(i).currY > -20 && currY - monsters.get(i).currY != 0) {
				if(currY - monsters.get(i).currY < 0) {
					if(currX - monsters.get(i).currX < 10 && currX - monsters.get(i).currX > -10 && currX - monsters.get(i).currX != 0 && currX - monsters.get(i).currX < 0) {
						otherMonst[0] = 0;	
					} else if (currX - monsters.get(i).currX < 10 && currX - monsters.get(i).currX > -10 && currX - monsters.get(i).currX != 0 && currX - monsters.get(i).currX > 0) {
						otherMonst[1] = 0;
					}
				} else {
					if(currX - monsters.get(i).currX < 10 && currX - monsters.get(i).currX > -10 && currX - monsters.get(i).currX != 0 && currX - monsters.get(i).currX < 0) {
						otherMonst[2] = 0;
					} else if (currX - monsters.get(i).currX < 10 && currX - monsters.get(i).currX > -10 && currX - monsters.get(i).currX != 0 && currX - monsters.get(i).currX > 0) {
						otherMonst[3] = 0;
					}
				}
			}
		}
	}
	
	public void monstClean() {
		if(stunTimer > 0) {
			stunTimer -= Gdx.graphics.getDeltaTime();
		} else {
			stunTimer = 0;
		}
		if(vulnTimer > 0) {
			vulnTimer -= Gdx.graphics.getDeltaTime();
		} else {
			vulnTimer = 0;
		}
		if(weakTimer > 0) {
			weakTimer -= Gdx.graphics.getDeltaTime();
		} else {
			weakTimer = 0;
		}
		if(knockBackTimer > 0) {
			knockBackTimer -= Gdx.graphics.getDeltaTime();
		} else {
			knockBackTimer = 0;
		}
		for(int i = 0; i < buffs[0].length; i++) {
			buffs[0][i] -= Gdx.graphics.getDeltaTime();
			if(buffs[0][i] <= 0) {
				buffs[1][i] = 0;
			}
		}
		if(damageCounter[0] > 0) {
			damageCounter[0] -= Gdx.graphics.getDeltaTime();
		} else {
			damageCounter[1] = 0;
		}
	}
}
