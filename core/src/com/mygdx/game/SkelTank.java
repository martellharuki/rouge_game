package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SkelTank{
	Texture texture[] = new Texture[6];

	public SkelTank() {
		for(int i = 0; i < 6; i++) {
			texture[i] = new Texture(Gdx.files.internal("Images/Skeleton/SkelTank" + i + ".png"));
		}
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY) {
		monsters.add(new Monster(locX, locY, 30, 30, 60, texture, 15, 350));
		int[] hitbox = new int[] {0, 0, 30, 0, 30, 60, 0, 60, 30, 30, 0, 30, 15, 0, 15, 60, 15, 30, 15, 15, 15, 30, 15, 45};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelTank";
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).buffs[0][0] < 8) {
				monsters.get(i).buffs[0][0] = 8;
				monsters.get(i).buffs[1][0] = (float).35;
			}
		}
	}
	
	public void makeMonst(ArrayList<Monster> monsters, float locX, float locY, int round) {
		monsters.add(new Monster(locX, locY, 30, 30, 60, texture, 15, 350 + 40 * round));
		int[] hitbox = new int[] {0, 0, 30, 0, 30, 60, 0, 60, 30, 30, 0, 30, 15, 0, 15, 60, 15, 30};
		monsters.get(monsters.size() - 1).hitbox = new HitBoxes(hitbox);
		monsters.get(monsters.size() - 1).monstType = "skelTank";
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).buffs[0][0] < 8) {
				monsters.get(i).buffs[0][0] = 8;
				monsters.get(i).buffs[1][0] = (float).35;
			}
		}
	}
	
	public void attackPattern(MainCharacter mainChar, Monster monster, Batch batch, ArrayList<Monster> monsters, ArrayList<gameEntity> attackEntities, Slash slash, float graphicWidth, float graphicHeight) {
		if(monster.attacks[0] <= 0 && monster.attacks[3] <= 0) {
			if((Math.pow((mainChar.currX + 15) - (monster.currX + monster.sizeX / 2), 2) + Math.pow((mainChar.currY + 25) - (monster.currY + monster.sizeY / 2), 2) < 400 || monster.attacks[2] == 1) && monster.attacks[2] != 2) {
				if(monster.attacks[1] == 3){
					monster.sizeX = 30;
					monster.sizeY = 60;
					monster.textNum = 0;
					monster.attacks[0] = 0;
					monster.attacks[1] = 0;
					monster.attacks[2] = 0;
					monster.attacks[3] = 5;
					if(checkDiags(mainChar, monster)){
						attackEntities.add(new gameEntity(monster.currX + 5, monster.currY + 60, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 42, 20));
						attackEntities.get(attackEntities.size() - 1).tRegion = new TextureRegion(attackEntities.get(attackEntities.size()-1).texture[1]);
						attackEntities.get(attackEntities.size() - 1).hitbox = new HitBoxes(33, 33, 44, 0, 44, 44, 0, 44);
						attackEntities.get(attackEntities.size() - 1).type = 'a';
					} else {
						attackEntities.add(new gameEntity(monster.currX - 5, monster.currY + 30, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 41, 20));
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
						monster.sizeX = 30;
						monster.sizeY += 7;
					}
					monster.attacks[0] = (float) .1;
				}
			} else if (true) {
				Random rando = new Random();
				if(rando.nextInt(300) == 8) {
						monster.attacks[3] = 8;
						monster.attacks[2] = 2;
					}
				if(monster.attacks[2] == 2) {
					for(int i = 0; i < monsters.size(); i++) {
						if(monsters.get(i).buffs[0][0] < 8) {
							monsters.get(i).buffs[0][0] = 6;
							monsters.get(i).buffs[1][0] = (float).3;
						}
					}
				}
				monster.attacks[2] = 0;
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
