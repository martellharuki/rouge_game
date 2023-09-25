package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkelWizard {
	Texture texture[] = new Texture[4];
	
	public SkelWizard() {
		for(int i = 0; i < 3; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Skeleton/SkelWizard" + i + ".png"));
		}
		texture[3] = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/enemyIndicator.png"));
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY) {
		monsters.add(new Monster(locX, locY, 30, 30, 65, texture, 0, 375));
		int[] hitbox = new int[] {0, 0, 30, 0, 30, 65, 0, 65, 30, 33, 0, 33, 15, 0, 15, 65, 15, 33};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelWizard";
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY, int round) {
		monsters.add(new Monster(locX, locY, 30, 30, 65, texture, 0, 375 + 2 * round));
		int[] hitbox = new int[] {0, 0, 30, 0, 30, 65, 0, 65, 30, 33, 0, 33, 15, 0, 15, 65, 15, 33};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelWizard";
	}
	public void attackPattern(MainCharacter mainChar, Monster monster, Batch batch, ArrayList<gameEntity> attackEntities, ArrayList<gameEntity> otherEntities, Slash slash, float graphicWidth, float graphicHeight, Random rando, FlameExplosion explosion) {
		if(monster.attacks[0] <= 0 && monster.attacks[3] <= 0) {
			if(monster.attacks[2] == 0) {
				if(rando.nextInt(100) == (int) monster.currX % 100) {
					if(monster.hitbox.entitiesHit.size() > 0) {
						monster.hitbox.entitiesHit.remove(0);
						}
					if(rando.nextInt(3) == 0) {
						monster.attacks[2] = 2;
						monster.mainCharCoords.add(mainChar.currX + 15);
						monster.mainCharCoords.add(mainChar.currY + 25);
						otherEntities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 25, 0, 0, 0, texture, (float) .75, (float) .3, 90, 90, -45, -45, 0));
						otherEntities.get(otherEntities.size() - 1).textNum = 3;
						otherEntities.get(otherEntities.size() - 1).hitbox = new HitBoxes(0);
						monster.textNum = 2;
						monster.attacks[0] = (float) 1;
					} else {
						monster.attacks[2] = 1;
						monster.attacks[1] = rando.nextInt(3) + 3;
						monster.textNum = 2;
						monster.attacks[0] = (float).4;
					}
				}
			}else if(monster.attacks[2] == 1 && monster.attacks[1] > 0 && monster.attacks[0] <= 0 && monster.attacks[3] <=0) {
				float theta = (float) Math.atan((Math.abs((monster.currY + 23) - (mainChar.currY + 25))) / (Math.abs((monster.currX) + 10 - (mainChar.currX + 15))));
				if(mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 > monster.currY + 23) {
					theta = (float)(Math.PI - theta);
				} else if((mainChar.currX + 15 < monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)){
					theta+= (float)Math.PI;
				} else if((mainChar.currX + 15 > monster.currX + 10 && mainChar.currY + 25 < monster.currY + 23)) {
					theta= (float)(2 * Math.PI - theta);
				}
				for(int i = -1; i < 2; i++) {
				attackEntities.add(new gameEntity(monster.currX, monster.currY, 0, 12, 12, explosion.texture, (float)2.5, 0, 0, 0, (int)(400 * Math.cos(theta + Math.PI / 12 * i)), (int)(400 * Math.sin(theta + Math.PI / 12 * i)), 8));
				attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(6);
				}
				monster.attacks[0] = (float).15;
				monster.attacks[1]--;
			} else if(monster.attacks[2] == 1 && monster.attacks[1] <= 0 && monster.attacks[0] <= 0 && monster.attacks[3] <=0) {
				monster.attacks[2] = 0;
				monster.textNum = 0;
				monster.attacks[1] = 0;
				monster.attacks[3] = 3;
			} else if(monster.attacks[2] == 2) {
				attackEntities.add(new gameEntity(monster.mainCharCoords.get(0), monster.mainCharCoords.get(1), 0, 0, 0, explosion.texture, (float).45,(float) .3, 90, 90, -45, -45, 20));
				attackEntities.get(attackEntities.size() - 1).centerType = 'p';
				attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(45, monster.mainCharCoords.get(0), monster.mainCharCoords.get(1));
				monster.mainCharCoords.remove(0);
				monster.mainCharCoords.remove(0);
				monster.attacks[2] = 0;
				monster.textNum = 0;
				monster.attacks[1] = 0;
				monster.attacks[3] = 8;
			}
			
		} else if(monster.attacks[0] > 0){
			monster.attacks[0] -= Gdx.graphics.getDeltaTime();
		}
	}
}