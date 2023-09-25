package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkelKing{
	Texture texture[] = new Texture[13];
	int spawnCheck = 2;

	public SkelKing() {
		for(int i = 0; i < 10; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Skeleton/skelKing" + i+ ".png"));
		}
		texture[10] = new Texture(Gdx.files.internal("Images/Skeleton/SkelKingBasic.png"));
		texture[11] = new Texture(Gdx.files.internal("Images/Skeleton/skelKingBomb.png"));
		texture[12] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/enemyIndicator.png"));
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY) {
		monsters.add(new Monster(locX, locY, 30, 80, 120, texture, 10, 1500));
		int hitbox[] = new int[] {0, 0, 50, 0, 50, 120, 0, 120, 20, 0, 40, 0, 40, 30, 40, 60, 40, 90, 40, 120, 20, 120, 0, 30, 0, 60, 0, 90, 20, 30, 20, 60, 20, 90, 50, 30, 50, 60, 50, 90};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelKing";
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY, int round) {
		monsters.add(new Monster(locX, locY, 30, 80, 120, texture, 10, 750 + 10 * round));
		int hitbox[] = new int[] {0, 0, 50, 0, 50, 120, 0, 120, 20, 0, 40, 0, 40, 30, 40, 60, 40, 90, 40, 120, 20, 120, 0, 30, 0, 60, 0, 90, 20, 30, 20, 60, 20, 90, 50, 30, 50, 60, 50, 90};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelKing";
	}
	
	public void attackPattern(MainCharacter mainChar, Monster monster, Batch batch, ArrayList<gameEntity> attackEntities, ArrayList<gameEntity> otherEntities, Slash slash, Random rando, float graphicWidth, float graphicHeight, SkelWizard skelWizard, ArrayList<Monster> monsters) {
		if(monster.health <= monster.totalHealth / 2 && spawnCheck == 2) {
			System.out.println("first stage done");
			skelWizard.makeMonst(monsters, 20, graphicHeight - 100);
			skelWizard.makeMonst(monsters, graphicWidth - 85, graphicHeight - 100);
			spawnCheck--;
		} else if(monster.health <= monster.totalHealth / 4 && spawnCheck == 1) {
			skelWizard.makeMonst(monsters, 20, 20);
			skelWizard.makeMonst(monsters, graphicWidth - 85, 20);
			skelWizard.makeMonst(monsters, graphicWidth / 2 - 32, graphicHeight - 100);
			spawnCheck--;
		}
		if(monster.attacks[0] <= 0 && monster.attacks[3] <= 0) {
			if ((rando.nextInt(20) == 7 && monster.attacks[2] == 0) || (monster.attacks[2] == 2)) {
				monster.attacks[2] = 2;
				if(monster.attacks[1] == 0) {
					monster.textNum = 4;
					monster.attacks[4] = 3;
				}
				if(monster.textNum < 7) {
					monster.textNum++;
				}
				float tempDamage = 0;
				if(monster.attacks[4] == 3) {
					tempDamage = 15;
				} else {
					tempDamage = 5;
				}
				if(monster.attacks[1] < 3) {
					monster.attacks[0] = (float).25;
					monster.sizeY += 10 * monster.attacks[1];
				} else {
					monster.attacks[0] = (float).4;
					float theta = (float) Math.atan((Math.abs((mainChar.currY + 25) - (monster.currY + 60))) / (Math.abs((monster.currX + 40) - (mainChar.currX + 15))));
					if(monster.currX + 40 > mainChar.currX + 15 && monster.currY + 80 < mainChar.currY + 25) {
						theta = (float)(Math.PI - theta);
					} else if((monster.currX + 40 > mainChar.currX + 15 && monster.currY + 80 > mainChar.currY + 25)){
						theta+= (float)Math.PI;
					} else if((monster.currX + 40 < mainChar.currX + 15 && monster.currY + 80 > mainChar.currY + 25)) {
						theta= (float)(2 * Math.PI - theta);
						}
					for(int i = -2; i < 3; i++) {
						attackEntities.add(new gameEntity(monster.currX + 40, monster.currY + 80, 0, 20, 20, texture, (float)3.5, 0, 0, 0, (int)(600 * Math.cos(theta + Math.PI / 24 * i)), (int)(600 * Math.sin(theta + Math.PI / 24 * i)), tempDamage));
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(10);
						attackEntities.get(attackEntities.size() - 1).textNum = 10;
						}
					monster.attacks[4]--;
				}
				monster.attacks[1]++;
				if(monster.attacks[4] <= 0) {
					monster.sizeY = 120;
					monster.attacks[0] = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 3;
					monster.attacks[4] = 0;
					monster.textNum = 0;
				}
			} else if ((rando.nextInt(30) == 7 && monster.attacks[2] == 0) || (monster.attacks[2] == 3)) {
				System.out.println("In Second Attack Phase");
				monster.attacks[2] = 3;
				if(monster.attacks[1] == 0) {
					monster.textNum = 1;
					monster.attacks[4] = 30;
				}
				if(monster.textNum < 5) {
					monster.textNum++;
				}
				if(monster.attacks[1] < 3) {
					System.out.println("In first phase");
					monster.attacks[0] = (float).25;
					monster.sizeY += 10 * monster.attacks[1];
				} else {
					System.out.println("In phase now");
					monster.attacks[0] = (float).1;
					for(int i = 0; i < 4; i++) {
						attackEntities.add(new gameEntity(monster.currX + 40, monster.currY + 80, 0, 20, 20, texture, (float)7, 0, 0, 0, (int)(1200 * Math.cos(Math.PI / 30 * (30 - monster.attacks[4]) + Math.PI / 2 * i)), (int)(1200 * Math.sin(Math.PI / 30 * (30 - monster.attacks[4]) + Math.PI / 2 * i)), 10));
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(10);
						attackEntities.get(attackEntities.size() - 1).textNum = 10;
						}
					monster.attacks[4]--;
				}
				monster.attacks[1]++;
				if(monster.attacks[4] <= 0) {
					System.out.println("in final Stage");
					monster.sizeY = 120;
					monster.attacks[0] = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 5;
					monster.attacks[4] = 0;
					monster.textNum = 0;
				}
			} else if((rando.nextInt(30) == 7 && monster.attacks[2] == 0) || (monster.attacks[2] == 4)) {
				monster.attacks[2] = 4;
				if(monster.attacks[1] == 0) {
					monster.textNum = 7;
					monster.attacks[4] = 30;
				}
				if(monster.textNum < 9) {
					monster.textNum++;
				}
				if(monster.attacks[1] < 3) {
					System.out.println("In first phase");
					monster.attacks[0] = (float).35;
					monster.sizeY += 10 * monster.attacks[1];
				} else {
					monster.attacks[0] = (float).165;
					if(monster.attacks[1] < 8) {
						monster.mainCharCoords.add(mainChar.currX + 15);
						monster.mainCharCoords.add(mainChar.currY + 25);
						otherEntities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 25, 0, 0, 0, texture, (float) .6, (float) 0, 120, 120, -60, -60, 0));
						otherEntities.get(otherEntities.size() - 1).textNum = 12;
						otherEntities.get(otherEntities.size() - 1).hitbox = new HitBoxes(0);
					} else if(monster.attacks[1] < 18){
						monster.mainCharCoords.add(mainChar.currX + 15);
						monster.mainCharCoords.add(mainChar.currY + 25);
						otherEntities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 25, 0, 0, 0, texture, (float) .6, (float) 0, 120, 120, -60, -60, 0));
						otherEntities.get(otherEntities.size() - 1).textNum = 12;
						otherEntities.get(otherEntities.size() - 1).hitbox = new HitBoxes(0);
						attackEntities.add(new gameEntity(monster.mainCharCoords.get(0), monster.mainCharCoords.get(1), 0, 0, 0, texture, (float).45,(float) .3, 120, 120, -60, -60, 20));
						attackEntities.get(attackEntities.size() - 1).centerType = 'p';
						attackEntities.get(attackEntities.size() - 1).textNum = 11;
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(60, monster.mainCharCoords.get(0), monster.mainCharCoords.get(1));
						monster.mainCharCoords.remove(0);
						monster.mainCharCoords.remove(0);
					} else if(monster.attacks[1] < 23) {
						attackEntities.add(new gameEntity(monster.mainCharCoords.get(0), monster.mainCharCoords.get(1), 0, 0, 0, texture, (float).45,(float) .3, 120, 120, -60, -60, 20));
						attackEntities.get(attackEntities.size() - 1).centerType = 'p';
						attackEntities.get(attackEntities.size() - 1).textNum = 11;
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(60, monster.mainCharCoords.get(0), monster.mainCharCoords.get(1));
						monster.mainCharCoords.remove(0);
						monster.mainCharCoords.remove(0);
					}
				}
				monster.attacks[1]++;
				if(monster.attacks[1] == 23) {
					monster.sizeY = 120;
					monster.attacks[0] = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 5;
					monster.attacks[4] = 0;
					monster.textNum = 0;
				}
			}
		} else if(monster.attacks[0] > 0){
			monster.attacks[0] -= Gdx.graphics.getDeltaTime();
		}
	}
	
	public boolean checkDiags(MainCharacter mainChar, Monster monster){
		float yInt = monster.currY - (45 / 20) * monster.currX;
		return mainChar.currY < (45 / 20) * mainChar.currX + yInt;
	}
}
