package com.mygdx.game;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HitBoxes{
	public int hitboxes[];
	public float pointX;
	public float pointY;
	ArrayList<gameEntity> entitiesHit = new ArrayList<gameEntity>();
	//points start at x,y and go counter clockwise

	public HitBoxes(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
		hitboxes = new int[] {x1, y1, x2, y2, x3, y3, x4, y4};
	}
	
	public HitBoxes(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, float pointX, float pointY){
		hitboxes = new int[] {x1, y1, x2, y2, x3, y3, x4, y4};
		this.pointX = pointX;
		this.pointY = pointY;
	}
	
	public HitBoxes(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6){
		hitboxes = new int[] {x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6};
	}
	
	public HitBoxes(int dist) {
		hitboxes = new int[] {dist};
	}
	
	public HitBoxes(int dist, float pointX, float pointY) {
		hitboxes = new int[] {dist};
		this.pointX = pointX;
		this.pointY = pointY;
	}
	
	public HitBoxes(int[] list) {
		hitboxes = new int[list.length];
		for(int i = 0; i < list.length; i++) {
			hitboxes[i] = list[i];
		}
	}
	public void isHit(Monster otherEntity, gameEntity thisEntity, MainCharacter mainChar, Buffs buffs){
		float thisX, thisY;
		if(thisEntity.centerType == 'm') {
			thisX = mainChar.currX + 15;
			thisY = mainChar.currY + 25;
		} else if(thisEntity.centerType == 'p') {
			thisX = pointX;
			thisY = pointY;
		}else {
			thisX = thisEntity.currX;
			thisY = thisEntity.currY;
		}
		float otherX = otherEntity.currX;
		float otherY = otherEntity.currY;
		/*SpriteBatch sprite = new SpriteBatch();
		sprite.begin();
		for (int i = 0; i < 8; i+=2){
		sprite.draw(mainChar.texture[5], thisX + hitboxes[i], thisY + hitboxes[i + 1], 10, 10);
		}
		sprite.end();
		sprite.dispose();*/
		//SpriteBatch sprite = new SpriteBatch();
		//sprite.begin();
		/*for (int i = 0; i < otherEntity.hitbox.hitboxes.length; i+=2){
		sprite.draw(mainChar.texture[5], otherEntity.currX + otherEntity.hitbox.hitboxes[i], otherEntity.currY + otherEntity.hitbox.hitboxes[i + 1], 10, 10);
		}
		sprite.end();
		sprite.dispose();*/
		/*if(hitboxes.length== 8) {
			System.out.println("hero location: " + mainChar.currX +", " + mainChar.currY);
		System.out.println("Cursor Loc: " + Gdx.input.getX() + " , " + (Gdx.graphics.getHeight() - Gdx.input.getY()) + ")");
		System.out.println("Slash 4 Points: (" + (thisX + hitboxes[0]) + ", " + (thisY + hitboxes[1]) + ") (" + (thisX + hitboxes[2]) + ", " + (thisY + hitboxes[3]) + ") (" + (thisX + hitboxes[4]) + ", " + (thisY + hitboxes[5]) + ") (" + (thisX + hitboxes[6]) + ", " + (thisY + hitboxes[7]) + ")");
		System.out.println("Skel 4 Points: (" + (otherX + otherEntity.hitbox.hitboxes[0]) + ", " + (otherY + otherEntity.hitbox.hitboxes[1]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[2]) + ", " + (otherY + otherEntity.hitbox.hitboxes[3]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[4]) + ", " + (otherY + otherEntity.hitbox.hitboxes[5]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[6]) + ", " + (otherY + otherEntity.hitbox.hitboxes[7]) + ")");
		System.out.println();
		}*/
		
		if(!entitiesHit.contains(otherEntity) && (hitboxes.length == 8)) {
			float slope;
			float yInt;
			float slope2;
			float yInt2;
			float slopeTracker[] = new float[4];
			int intTracker[] = new int[]{0, 1, 2, 3};
			for(int i = 0; i < 3; i++) {
				//System.out.println(hitboxes[2 * (i + 1)] +  " - " + hitboxes[2 * i]);
				if(hitboxes[2 * (i + 1)] - hitboxes[2 * i] == 0) {
					slopeTracker[i] = 10000;
				} else {
					//System.out.println(hitboxes[2 * (i + 1) + 1] +  " - " + hitboxes[2 * i + 1]);
					slopeTracker[i] = (float) (hitboxes[2 * (i + 1) + 1] - hitboxes[2 * i + 1]) / (float) (hitboxes[2 * (i + 1)] - hitboxes[2 * i]);
				}
			}
			if(hitboxes[6] - hitboxes[0] == 0) {
				slopeTracker[3] = 10000;
			} else {
				slopeTracker[3] = (float) (hitboxes[7] - hitboxes[1]) / (float) (hitboxes[6] - hitboxes[0]);
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = i; j < 4; j++) {
					if(Math.abs(slopeTracker[i]) > Math.abs(slopeTracker[j])) {
						float temp = slopeTracker[j];
						slopeTracker[j] = slopeTracker[i];
						slopeTracker[i] = temp;
						temp = intTracker[j];
						intTracker[j] = intTracker[i];
						intTracker[i] = (int) temp;
						i = 0;
					}
				}
			}
			int tempTracker = intTracker[2];
			float tempSlope = slopeTracker[2];
			intTracker[2] = (intTracker[3] + 2) % 4;
			slopeTracker[2] = (slopeTracker[(intTracker[3] + 2) % 4]);
			if(intTracker[0] == intTracker[2]) {
				intTracker[0] = (int) tempTracker;
				slopeTracker[0] = tempSlope;
			} else if(intTracker[1] == intTracker[2]){
				intTracker[1] = (int) tempTracker;
				slopeTracker[1] = tempSlope;
			}
			for(int i = 2; i < 4; i++) {
				if(slopeTracker[i] >= 10000) {
					slopeTracker[i] = 0;
				} else {
					if(intTracker[i] == 3) {
						slopeTracker[i] = (float) (hitboxes[6] - hitboxes[0]) / (float) (hitboxes[7] - hitboxes[1]);
					} else {
						//System.out.println("x: " + (hitboxes[2 * (intTracker[i] + 1)] - hitboxes[2 * intTracker[i]]) + " y: " + (hitboxes[(2 * (intTracker[i] + 1 )+ 1)] - hitboxes[(2 * intTracker[i] + 1)]));
						slopeTracker[i] = (float) (hitboxes[2 * (intTracker[i] + 1)] - hitboxes [2 * intTracker[i]]) / (float) (hitboxes[2 * (intTracker[i] + 1) + 1] - hitboxes [2 * intTracker[i] + 1]);
					}
				}
			}
			for(int i = 0; i < 4; i++) {
				//System.out.println(slopeTracker[i]);
				//System.out.println(intTracker[i]);
			}
			for(int j = 0; j < otherEntity.hitbox.hitboxes.length / 2; j++) {
				slope = slopeTracker[0];
				yInt = (thisY + hitboxes[2 * intTracker[0] + 1]) - slope * (thisX + hitboxes[2 * intTracker[0]]);
				slope2 = slopeTracker[1];
				yInt2 = (thisY + hitboxes[2 * intTracker[1] + 1]) - slope2 * (thisX + hitboxes[2 * intTracker[1]]);
				//System.out.println(yInt + ", " + yInt2);
				if((((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) > slope * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt) && ((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) < slope2 * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt2)) || (((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) < slope * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt) && ((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) > slope2 * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt2))) {
					//System.out.println("Passed");	
					slope = slopeTracker[2];
						yInt = (thisX + hitboxes[2 * intTracker[2]]) - slope * (thisY + hitboxes[2 * intTracker[2] + 1]);
						slope2 = slopeTracker[3];
						yInt2 = (thisX + hitboxes[2 * intTracker[3]]) - slope2 * (thisY + hitboxes[2 * intTracker[3] + 1]);
						//System.out.println(yInt + ": " + yInt2);
						if((((otherX + otherEntity.hitbox.hitboxes[2 * j]) > slope * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt) && ((otherX + otherEntity.hitbox.hitboxes[2 * j]) < slope2 * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt2)) || (((otherX + otherEntity.hitbox.hitboxes[2 * j]) < slope * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt) && ((otherX + otherEntity.hitbox.hitboxes[2 * j]) > slope2 * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt2))) {
								entitiesHit.add(otherEntity);
								if(otherEntity.textNum == 0) {
									otherEntity.textNum = 1;
									otherEntity.hurtTimer = (float) .3;
								}
								System.out.println("Hit");
								breakDown(thisEntity, otherEntity, buffs, mainChar);
								//otherEntity.health -= thisEntity.damage * buffs.damageMult(thisEntity.castType);
								break;
						}
				}
			}
		} else if (!entitiesHit.contains(otherEntity) && hitboxes.length == 1) {
			/*SpriteBatch sprite = new SpriteBatch();
			sprite.begin();
			sprite.draw(mainChar.texture[5], thisX, thisY - hitboxes[0], 10, 10);
			sprite.end();
			sprite.dispose();*/
				/*System.out.println("(" + (thisX + hitboxes[0]) + ", " + thisY + ")");
				System.out.println("(" + (thisX - hitboxes[0]) + ", " + thisY + ")");
				System.out.println("(" + thisX  + ", " + (thisY + hitboxes[0])+ ")");
				System.out.println("(" + thisX + ", " + (thisY - hitboxes[0])+ ")");*/
			for(int i = 0; i < 6; i++) {
				if(Math.pow(thisX - (otherX + otherEntity.hitbox.hitboxes[2 * i]), 2) + Math.pow(thisY - (otherY + otherEntity.hitbox.hitboxes[2 * i + 1]), 2) <= Math.pow(hitboxes[0], 2)) {
					entitiesHit.add(otherEntity);
					if(otherEntity.textNum == 0) {
						otherEntity.textNum = 1;
						otherEntity.hurtTimer = (float) .3;
					}
					breakDown(thisEntity, otherEntity, buffs, mainChar);
					//otherEntity.health -= thisEntity.damage * buffs.damageMult(thisEntity.castType);
					System.out.println("Hit");
					break;
				}
			}
		}
	}
	
	
	public void isHit(MainCharacter otherEntity, gameEntity thisEntity, Block block, Buffs buffs){
		float thisX = thisEntity.currX;
		float thisY = thisEntity.currY;
		float otherX = otherEntity.currX;
		float otherY = otherEntity.currY;
		/*SpriteBatch sprite = new SpriteBatch();
		sprite.begin();
		for (int i = 0; i < 8; i+=2){
		sprite.draw(otherEntity.texture[5], thisX + hitboxes[i], thisY + hitboxes[i + 1], 10, 10);
		}
		sprite.end();
		sprite.dispose();
		
		/*if(hitboxes.length== 8) {
			System.out.println("hero location: " + mainChar.currX +", " + mainChar.currY);
		System.out.println("Cursor Loc: " + Gdx.input.getX() + " , " + (Gdx.graphics.getHeight() - Gdx.input.getY()) + ")");
		System.out.println("Slash 4 Points: (" + (thisX + hitboxes[0]) + ", " + (thisY + hitboxes[1]) + ") (" + (thisX + hitboxes[2]) + ", " + (thisY + hitboxes[3]) + ") (" + (thisX + hitboxes[4]) + ", " + (thisY + hitboxes[5]) + ") (" + (thisX + hitboxes[6]) + ", " + (thisY + hitboxes[7]) + ")");
		System.out.println("Skel 4 Points: (" + (otherX + otherEntity.hitbox.hitboxes[0]) + ", " + (otherY + otherEntity.hitbox.hitboxes[1]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[2]) + ", " + (otherY + otherEntity.hitbox.hitboxes[3]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[4]) + ", " + (otherY + otherEntity.hitbox.hitboxes[5]) + ") (" + (otherX + otherEntity.hitbox.hitboxes[6]) + ", " + (otherY + otherEntity.hitbox.hitboxes[7]) + ")");
		System.out.println();
		}*/
		
		if(!entitiesHit.contains(otherEntity) && hitboxes.length >= 8) {
			float slope;
			float yInt;
			float slope2;
			float yInt2;
			float slopeTracker[] = new float[4];
			int intTracker[] = new int[]{0, 1, 2, 3};
			for(int i = 0; i < 3; i++) {
				//System.out.println(hitboxes[2 * (i + 1)] +  " - " + hitboxes[2 * i]);
				if(hitboxes[2 * (i + 1)] - hitboxes[2 * i] == 0) {
					slopeTracker[i] = 10000;
				} else {
					//System.out.println(hitboxes[2 * (i + 1) + 1] +  " - " + hitboxes[2 * i + 1]);
					slopeTracker[i] = (float) (hitboxes[2 * (i + 1) + 1] - hitboxes[2 * i + 1]) / (float) (hitboxes[2 * (i + 1)] - hitboxes[2 * i]);
				}
			}
			if(hitboxes[6] - hitboxes[0] == 0) {
				slopeTracker[3] = 10000;
			} else {
				slopeTracker[3] = (float) (hitboxes[7] - hitboxes[1]) / (float) (hitboxes[6] - hitboxes[0]);
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = i; j < 4; j++) {
					if(Math.abs(slopeTracker[i]) > Math.abs(slopeTracker[j])) {
						float temp = slopeTracker[j];
						slopeTracker[j] = slopeTracker[i];
						slopeTracker[i] = temp;
						temp = intTracker[j];
						intTracker[j] = intTracker[i];
						intTracker[i] = (int) temp;
						i = 0;
					}
				}
			}
			int tempTracker = intTracker[2];
			float tempSlope = slopeTracker[2];
			intTracker[2] = (intTracker[3] + 2) % 4;
			slopeTracker[2] = (slopeTracker[(intTracker[3] + 2) % 4]);
			if(intTracker[0] == intTracker[2]) {
				intTracker[0] = (int) tempTracker;
				slopeTracker[0] = tempSlope;
			} else if(intTracker[1] == intTracker[2]){
				intTracker[1] = (int) tempTracker;
				slopeTracker[1] = tempSlope;
			}
			for(int i = 2; i < 4; i++) {
				if(slopeTracker[i] >= 10000) {
					slopeTracker[i] = 0;
				} else {
					if(intTracker[i] == 3) {
						slopeTracker[i] = (float) (hitboxes[6] - hitboxes[0]) / (float) (hitboxes[7] - hitboxes[1]);
					} else {
						//dSystem.out.println("x: " + (hitboxes[2 * (intTracker[i] + 1)] - hitboxes[2 * intTracker[i]]) + " y: " + (hitboxes[(2 * (intTracker[i] + 1 )+ 1)] - hitboxes[(2 * intTracker[i] + 1)]));
						slopeTracker[i] = (float) (hitboxes[2 * (intTracker[i] + 1)] - hitboxes [2 * intTracker[i]]) / (float) (hitboxes[2 * (intTracker[i] + 1) + 1] - hitboxes [2 * intTracker[i] + 1]);
					}
				}
			}
			for(int i = 0; i < 4; i++) {
				//System.out.println(slopeTracker[i]);
				//System.out.println(intTracker[i]);
			}
			for(int j = 0; j < 6; j++) {
				slope = slopeTracker[0];
				yInt = (thisY + hitboxes[2 * intTracker[0] + 1]) - slope * (thisX + hitboxes[2 * intTracker[0]]);
				slope2 = slopeTracker[1];
				yInt2 = (thisY + hitboxes[2 * intTracker[1] + 1]) - slope2 * (thisX + hitboxes[2 * intTracker[1]]);
				//System.out.println(yInt + ", " + yInt2);
				if((((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) > slope * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt) && ((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) < slope2 * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt2)) || (((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) < slope * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt) && ((otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) > slope2 * (otherX + otherEntity.hitbox.hitboxes[2 * j]) + yInt2))) {
					//System.out.println("Passed");	
					slope = slopeTracker[2];
						yInt = (thisX + hitboxes[2 * intTracker[2]]) - slope * (thisY + hitboxes[2 * intTracker[2] + 1]);
						slope2 = slopeTracker[3];
						yInt2 = (thisX + hitboxes[2 * intTracker[3]]) - slope2 * (thisY + hitboxes[2 * intTracker[3] + 1]);
						//System.out.println(yInt + ": " + yInt2);
						if((((otherX + otherEntity.hitbox.hitboxes[2 * j]) > slope * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt) && ((otherX + otherEntity.hitbox.hitboxes[2 * j]) < slope2 * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt2)) || (((otherX + otherEntity.hitbox.hitboxes[2 * j]) < slope * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt) && ((otherX + otherEntity.hitbox.hitboxes[2 * j]) > slope2 * (otherY + otherEntity.hitbox.hitboxes[2 * j + 1]) + yInt2))) {
								entitiesHit.add(otherEntity);
								//System.out.println("Hit");
								if(block.active) {
									if(block.perfectFrame >= 0) {
										System.out.println("Perfect");
									} else {
										otherEntity.takeDamage(buffs.takeDamage(thisEntity, block, otherEntity));
										System.out.println("block");
									}
								} else {
									otherEntity.takeDamage(buffs.takeDamage(thisEntity, block, otherEntity));
								break;
								}
						}
				}
			}
		} else if (!entitiesHit.contains(otherEntity) && hitboxes.length == 1) {
			if(thisEntity.centerType == 'p') {
				thisX = pointX;
				thisY = pointY;
			}else {
				thisX = thisEntity.currX + thisEntity.sizeX / 2;
				thisY = thisEntity.currY + thisEntity.sizeY / 2;
			}
			/*
			SpriteBatch sprite = new SpriteBatch();
			sprite.begin();
			sprite.draw(otherEntity.texture[5], thisX, thisY - hitboxes[0], 10, 10);
			sprite.end();
			sprite.dispose();*/
				/*System.out.println("(" + (thisX + hitboxes[0]) + ", " + thisY + ")");
				System.out.println("(" + (thisX - hitboxes[0]) + ", " + thisY + ")");
				System.out.println("(" + thisX  + ", " + (thisY + hitboxes[0])+ ")");
				System.out.println("(" + thisX + ", " + (thisY - hitboxes[0])+ ")");*/
			for(int i = 0; i < 6; i++) {
				if(Math.pow(thisX - (otherX + otherEntity.hitbox.hitboxes[2 * i]), 2) + Math.pow(thisY - (otherY + otherEntity.hitbox.hitboxes[2 * i + 1]), 2) <= Math.pow(hitboxes[0], 2)) {
					entitiesHit.add(otherEntity);
					if(block.active) {
						if(block.perfectFrame >= 0) {
							System.out.println("Perfect");
						} else {
							otherEntity.takeDamage(buffs.takeDamage(thisEntity, block, otherEntity));
							System.out.println("block");
						}
					} else {
						otherEntity.takeDamage(buffs.takeDamage(thisEntity, block, otherEntity));
					break;
					}
				}
			}
		}
	}
	
	public void breakDown(gameEntity thisEntity, Monster otherEntity, Buffs buffs, MainCharacter mainChar) {
		float temp = buffs.calcDamage(thisEntity, otherEntity, mainChar);
		otherEntity.health -= temp;
		otherEntity.damageCounter[0] = (float).3;
		otherEntity.damageCounter[1] += temp;
		if(thisEntity.castType.contains("s")) {
			//otherEntity.stunTimer = Integer.parseInt(thisEntity.castType.substring(thisEntity.castType.indexOf('s') - 1, thisEntity.castType.indexOf('s'))) / (float) 2.0;
		}
	}
}


