package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Skeleton{
	Texture texture[] = new Texture[14];

	public Skeleton() {
		for(int i = 0; i < 14; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Skeleton/" + i + ".png"));
		}
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY) {
		monsters.add(new Monster(locX, locY, 30, 20, 45, texture, 10, 100));
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(0, 0, 20, 0, 20, 45, 0, 45, 20, 23, 0, 23);
		monsters.get(monsters.size() - 1).monstType = "skeleton";
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY, int round) {
		monsters.add(new Monster(locX, locY, 30, 20, 45, texture, 10, 100 + 5 * round));
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(0, 0, 20, 0, 20, 45, 0, 45, 20, 23, 0, 23);
		monsters.get(monsters.size() - 1).monstType = "skeleton";
	}
	
	public void attackPattern(MainCharacter mainChar, Monster monster, Batch batch, ArrayList<gameEntity> attackEntities, Slash slash, float graphicWidth, float graphicHeight) {
		if(monster.attacks[0] <= 0 && monster.attacks[3] <= 0) {
			if((Math.pow((mainChar.currX + 15) - (monster.currX + monster.sizeX / 2), 2) + Math.pow((mainChar.currY + 25) - (monster.currY + monster.sizeY / 2), 2) < 400 || monster.attacks[2] == 1) && monster.attacks[2] != 2) {
				if(monster.attacks[1] == 3){
					monster.sizeX = 20;
					monster.sizeY = 45;
					monster.textNum = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 5;
					if(checkDiags(mainChar, monster)){
						attackEntities.add(new gameEntity(monster.currX + 5, monster.currY + 60, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 42, 10));
						attackEntities.get(attackEntities.size() - 1).tRegion = new TextureRegion(attackEntities.get(attackEntities.size()-1).texture[1]);
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(33, 33, 44, 0, 44, 44, 0, 44);
						attackEntities.get(attackEntities.size() - 1).type = 'a';
					} else {
						attackEntities.add(new gameEntity(monster.currX - 5, monster.currY + 30, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 41, 10));
						attackEntities.get(attackEntities.size() - 1).tRegion = new TextureRegion(attackEntities.get(attackEntities.size()-1).texture[0]);
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(0, 0, 44, 0, 33, 33, 0, 44);
						attackEntities.get(attackEntities.size() - 1).type = 'a';
					}
				} else {
					if(monster.attacks[1] == 0) {
						monster.textNum = 1;
					}
					monster.textNum++;
					monster.attacks[1]++;
					monster.attacks[2] = 1;
					if(monster.attacks[1] == 2) {
						monster.sizeX += 3;
						monster.sizeY += 3;
					} else if(monster.attacks[1] ==3) {
						monster.sizeX = 20;
						monster.sizeY += 7;
					}
					monster.attacks[0] = (float) .1;
				}
			} else if (true) {
				Random rando = new Random();
				float tempTime = Gdx.graphics.getDeltaTime();
				while(tempTime > 0 && monster.attacks[2] != 2) {
					if(rando.nextInt(300) == (int) monster.currX % 300) {
						if(monster.hitbox.entitiesHit.size() > 0) {
							monster.hitbox.entitiesHit.remove(0);
							}
						monster.attacks[2] = 2;
						monster.attacks[1] = 7;
						monster.textNum = 7;
						monster.mainCharX = mainChar.currX;
						monster.mainCharY = mainChar.currY;
						break;
					}
					tempTime -= (float)1 / 60;
				}
				if(monster.attacks[1] >= 7) {
					monster.attacks[1]++;
					monster.textNum++;
					if(monster.textNum < 14) {
					monster.attacks[0] = (float) .2;
					}
				}
				if(monster.attacks[1] == 11) {
					monster.setAngle();
					monster.type = 'd';
				}
				if(monster.attacks[1] >= 11) {
					monster.attacks[0] = (float) .2;
				}
				if(monster.attacks[1] >= 14) {
					monster.attacks[0] = 0;
					monster.type = ' ';
					monster.sizeX = 20;
					monster.sizeY = 45;
					monster.textNum = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 5;
					monster.travelAngle = 0;
					if(monster.hitbox.entitiesHit.size() > 0) {
					monster.hitbox.entitiesHit.remove(0);
					}
				}
				if(monster.textNum > 13) {
					monster.textNum = 0;
				}
			}
		} else if(monster.attacks[0] > 0){
			monster.attacks[0] -= Gdx.graphics.getDeltaTime();
			if(monster.attacks[1] >= 11) {
				monster.pathInDirection(400, graphicWidth, graphicHeight);
			}
		}
	}
	
	public boolean checkDiags(MainCharacter mainChar, Monster monster){
		float yInt = monster.currY - (45 / 20) * monster.currX;
		return mainChar.currY < (45 / 20) * mainChar.currX + yInt;
	}
}
