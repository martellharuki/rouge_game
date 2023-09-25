package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkelMage {
	Texture texture[] = new Texture[3];
	
	public SkelMage() {
		for(int i = 0; i < 3; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Skeleton/SkelMage" + i + ".png"));
		}
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY) {
		monsters.add(new Monster(locX, locY, 30, 20, 45, texture, 0, 75));
		System.out.println("in here");
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(0, 0, 20, 0, 20, 45, 0, 45, 20, 23, 0, 23);
		monsters.get(monsters.size() - 1).monstType = "skelmage";
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY, int round) {
		monsters.add(new Monster(locX, locY, 30, 20, 45, texture, 0, 75 + 5 * round));
		System.out.println("in here");
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(0, 0, 20, 0, 20, 45, 0, 45, 20, 23, 0, 23);
		monsters.get(monsters.size() - 1).monstType = "skelmage";
	}
	public void attackPattern(MainCharacter mainChar, Monster monster, Batch batch, ArrayList<gameEntity> attackEntities, Slash slash, float graphicWidth, float graphicHeight, Random rando, FlameExplosion explosion) {
		if(monster.attacks[0] <= 0 && monster.attacks[3] <= 0) {
			if(monster.attacks[2] == 0) {
			float tempTime = Gdx.graphics.getDeltaTime();
			while(tempTime > 0 && monster.attacks[2] != 2) {
				if(rando.nextInt(100) == (int) monster.currX % 100) {
					if(monster.hitbox.entitiesHit.size() > 0) {
						monster.hitbox.entitiesHit.remove(0);
						}
					monster.attacks[2] = 1;
					monster.attacks[1] = rando.nextInt(2) + 2;
					monster.textNum = 2;
					monster.attacks[0] = (float).4;
					break;
				}
				tempTime -= (float)1 / 60;
			}
			
			}else if(monster.attacks[2] == 1 && monster.attacks[1] > 0 && monster.attacks[0] <= 0 && monster.attacks[3] <=0) {
				float theta = (float) Math.atan((Math.abs((monster.currY + 23) - (mainChar.currY + 25))) / (Math.abs((monster.currX) + 10 - (mainChar.currX + 15))));
				if((mainChar.xDir == 0 && mainChar.yDir == 0) || proximityCheck(mainChar, monster)) {
					if(mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 > monster.currY + 23) {
						theta = (float)(Math.PI - theta);
					} else if((mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)){
						theta+= (float)Math.PI;
					} else if((mainChar.currX + 15 > monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)) {
						theta= (float)(2 * Math.PI - theta);
					}
				} else if(mainChar.xDir != 0 && mainChar.yDir != 0) {
					theta = (float) Math.atan((Math.abs((monster.currY + 23) - (mainChar.currY + 25 + 170 * mainChar.yDir))) / (Math.abs((monster.currX) + 10 - (mainChar.currX + 15 + 170 * mainChar.xDir))));
					if(mainChar.currX + 15 + 170 * mainChar.xDir < monster.currX + 10 && mainChar.currY + 25 + 170 * mainChar.yDir > monster.currY + 23) {
						theta = (float)(Math.PI - theta);
					} else if((mainChar.currX + 15 + 170 * mainChar.xDir < monster.currX + 10 && mainChar.currY + 25 + 170 * mainChar.yDir < monster.currY + 23)){
						theta+= (float)Math.PI;
					} else if((mainChar.currX + 15 + 170 * mainChar.xDir > monster.currX + 10 && mainChar.currY + 25 + 170 * mainChar.yDir < monster.currY + 23)) {
						theta= (float)(2 * Math.PI - theta);
					}
				} else if (mainChar.xDir == 0 && mainChar.yDir !=0) {
					theta = (float) Math.atan((Math.abs((monster.currY + 23) - (mainChar.currY + 25 + 200 * mainChar.yDir))) / (Math.abs((monster.currX) + 10 - (mainChar.currX + 15))));
					if(mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 + 200 * mainChar.yDir > monster.currY + 23) {
						theta = (float)(Math.PI - theta);
					} else if((mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 + 200 * mainChar.yDir < monster.currY + 23)){
						theta+= (float)Math.PI;
					} else if((mainChar.currX + 15> monster.currX + 10 && mainChar.currY + 25 + 200 * mainChar.yDir < monster.currY + 23)) {
						theta= (float)(2 * Math.PI - theta);
					}
				} else if(mainChar.xDir != 0 && mainChar.yDir == 0) {
					theta = (float) Math.atan((Math.abs((monster.currY + 23) - (mainChar.currY + 25))) / (Math.abs((monster.currX) + 10 - (mainChar.currX + 15  + 200 * mainChar.xDir))));
					if(mainChar.currX + 15 + 200 * mainChar.xDir < monster.currX + 10 && mainChar.currY + 25 > monster.currY + 23) {
						theta = (float)(Math.PI - theta);
					} else if((mainChar.currX + 15 + 200 * mainChar.xDir < monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)){
						theta+= (float)Math.PI;
					} else if((mainChar.currX + 15 + 200 * mainChar.xDir > monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)) {
						theta = (float)(2 * Math.PI - theta);
					}
				}
				attackEntities.add(new gameEntity(monster.currX, monster.currY, 0, 12, 12, explosion.texture, (float)2.5, 0, 0, 0, (int)(400 * Math.cos(theta)), (int)(400 * Math.sin(theta)), 5));
				attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(6);
				monster.attacks[0] = (float).25;
				monster.attacks[1]--;
			} else if(monster.attacks[2] == 1 && monster.attacks[1] <= 0 && monster.attacks[0] <= 0 && monster.attacks[3] <=0) {
				monster.attacks[2] = 0;
				monster.textNum = 0;
				monster.attacks[1] = 0;
				monster.attacks[3] = 7;
			}
			
		} else if(monster.attacks[0] > 0){
			monster.attacks[0] -= Gdx.graphics.getDeltaTime();
		}
	}
	
	public boolean proximityCheck(MainCharacter mainChar, Monster monster) {
		boolean answer = true;
		if(mainChar.xDir != 0 && mainChar.yDir == 0) {
			answer = (Math.pow((mainChar.currX + 15 + 200 * mainChar.xDir) - (monster.currX + 10),2) + Math.pow((mainChar.currY + 25) - (monster.currY + 23), 2) < Math.pow(250, 2));
		} else if (mainChar.xDir == 0 && mainChar.yDir != 0) {
			answer = (Math.pow((mainChar.currX + 15) - (monster.currX + 10),2) + Math.pow((mainChar.currY + 25 + 200 * mainChar.yDir) - (monster.currY + 23), 2) < Math.pow(250, 2));
		} else if (mainChar.xDir != 0 && mainChar.yDir != 0) {
			answer = (Math.pow((mainChar.currX + 15 + 170 * mainChar.xDir) - (monster.currX + 10),2) + Math.pow((mainChar.currY + 25 + 170 * mainChar.yDir) - (monster.currY + 23), 2) < Math.pow(250, 2));
		}
		return answer;
	}
}
