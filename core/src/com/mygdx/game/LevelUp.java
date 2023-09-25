package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelUp {
	float turn = (float).25;
	boolean isLeveling = false;
	int maxBuffs = 0;
	int maxCasts = 0;
	Texture levelSquare;
	Texture expBar;
	BitmapFont font;
	int EXP = 70;
	int EXPNeeded = 70;
	int temp[] = new int[2];
	int temp2[] = new int[2];
	
	public LevelUp() {
		levelSquare = new Texture(Gdx.files.internal("Images/Cast Attacks/Emblems/LevelSquare.png"));
		expBar = new Texture(Gdx.files.internal("Images/Main Character/EXPBar.png"));
		font = new BitmapFont();
	}
	
	public void level(Random rando, SpriteBatch sprite, ArrayList<Casts> currCasts, ArrayList<Casts> totalCasts, Buffs buffs, Emblem emblem) {
		if(!isLeveling) {
			int total = 2;
			if(maxCasts == 2) {
				total = 1;
			} else if(maxCasts > 2) {
				total = 0;
			}
		if(currCasts.size() < 3) {
			for(int i = 0; i < 2; i++) {
				temp[i] = rando.nextInt(totalCasts.size()) + 1;
				if(dupeCheck(temp, i, totalCasts)) {
					i--;
				}
			}
		} else {
			for(int i = 0; i < total; i++) {
				temp[i] = rando.nextInt(3);
				temp[i] = currCasts.get(temp[i]).num;
				if(dupeCheck(temp, i, totalCasts)) {
					i--;
				}
			}
		}
		if(total > 1) {
			totalCasts.get(temp[0] - 1).currLev = rando.nextInt(3);
			totalCasts.get(temp[1] - 1).currLev = rando.nextInt(3);
		} else if(total == 1) {
			totalCasts.get(temp[0] - 1).currLev = rando.nextInt(3);
		}
		total = 2;
		if(maxBuffs == 3) {
			total = 1;
		} else if(maxBuffs > 3) {
			total = 0;
		}
		if(buffs.currSize() < 4) {
			for(int i = 0; i < 2; i++) {
				temp2[i] = rando.nextInt(9) + 1;
				if(dupeCheck(temp2, i, buffs)) {
					i--;
				}
			}
		} else {
			for(int i = 0; i < total; i++) {
				temp2[i] = rando.nextInt(4);
				temp2[i] = buffs.currBuffs[temp2[i]];
				if(dupeCheck(temp2, i, buffs)) {
					i--;
				}
			}
		}
		}
		for(int i = 0; i < 2; i++) {
			sprite.draw(levelSquare, 7 * (i + 1) + 150 * (i), 90, 150, 250);
			if(temp[i] == 1) {
				font.getData().setScale((float)1.3);
				totalCasts.get(0).getLevelType();
				font.draw(sprite, totalCasts.get(0).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[1], 157 * (i) + 27, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(0).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 2) {
				font.getData().setScale((float)1.3);
				totalCasts.get(1).getLevelType();
				font.draw(sprite, totalCasts.get(1).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[2], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(1).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 3) {
				font.getData().setScale((float)1.3);
				totalCasts.get(2).getLevelType();
				font.draw(sprite, totalCasts.get(2).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[3], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(2).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 4) {
				font.getData().setScale((float)1.3);
				totalCasts.get(3).getLevelType();
				font.draw(sprite, totalCasts.get(3).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[4], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(3).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 5) {
				font.getData().setScale((float)1.3);
				totalCasts.get(4).getLevelType();
				font.draw(sprite, totalCasts.get(4).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[5], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(4).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 6) {
				font.getData().setScale((float)1.3);
				totalCasts.get(5).getLevelType();
				font.draw(sprite, totalCasts.get(5).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[6], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(5).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 7) {
				font.getData().setScale((float)1.3);
				totalCasts.get(6).getLevelType();
				font.draw(sprite, totalCasts.get(6).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[7], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(6).levelType, 157 * i + 27, 130);
			} else if(temp[i] == 8) {
				font.getData().setScale((float)1.3);
				totalCasts.get(7).getLevelType();
				font.draw(sprite, totalCasts.get(7).type, 157 * i + 27, 325);
				sprite.draw(emblem.mainText[8], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).5);
				font.draw(sprite, totalCasts.get(7).levelType, 157 * i + 27, 130);
			} 
		}
		for(int i = 2; i < 4; i++) {
			sprite.draw(levelSquare, 7 * (i + 1) + 150 * (i), 90, 150, 250);
			if(temp2[i - 2] == 1) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(0), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[0], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[0], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 2) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(1), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[1], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[1], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 3) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(2), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[2], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[2], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 4) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(3), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[3], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[3], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 5) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(4), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[4], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[4], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 6) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(5), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[5], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[5], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 7) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(6), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[6], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[6], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 8) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(7), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[7], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[7], 157 * i + 27, 130);
			} else if(temp2[i - 2] == 9) {
				font.getData().setScale((float)1.1);
				font.draw(sprite, buffs.mainType(8), 157 * i + 27, 332);
				sprite.draw(emblem.buffText[8], 157 * (i) + 28, 160, 110, 110);
				font.getData().setScale((float).7);
				font.draw(sprite, buffs.levelType[8], 157 * i + 27, 130);	
			}
		}
		isLeveling = true;
	}
	
	public void levelInput(float xPoint, float yPoint, ArrayList<Casts> currCasts, ArrayList<Casts> totalCasts, Buffs buffs, Emblem emblem, MainCharacter mainChar, Dash dash, Block block) {
		if(yPoint > 90 && yPoint < 240) {
		if(xPoint < 157 && xPoint > 7) {
			if(totalCasts.get(temp[0] - 1).upgrade <= 0) {
				currCasts.add(totalCasts.get(temp[0] - 1));
				totalCasts.get(temp[0] - 1).levelChange(buffs);
				emblem.subText[currCasts.size()] = emblem.mainText[temp[0]];
			} else {
				totalCasts.get(temp[0] - 1).levelChange(buffs);
			}
			EXP -= EXPNeeded;
			EXPNeeded += 5;
			isLeveling = false;
			turn = (float).25;
			if(totalCasts.get(temp[0] - 1).upgrade > 4) {
				System.out.println("max worked");
				maxCasts++;
			}
		} else if(xPoint > 164 && xPoint < 314) {
			if(totalCasts.get(temp[1] - 1).upgrade <= 0) {
				currCasts.add(totalCasts.get(temp[1] - 1));
				totalCasts.get(temp[1] - 1).levelChange(buffs);
				emblem.subText[currCasts.size()] = emblem.mainText[temp[1]];
			} else {
				totalCasts.get(temp[1] - 1).levelChange(buffs);
			}
			EXP -= EXPNeeded;
			EXPNeeded += 5;
			isLeveling = false;
			turn = (float).25;
			if(totalCasts.get(temp[1] - 1).upgrade > 4) {
				maxCasts++;
			}
		} else if (xPoint > 321 && xPoint < 471) {
			if(buffs.upgrades[temp2[0] - 1] <= 0) {
				buffs.addBuff(temp2[0]);
				buffs.levelChange(temp2[0] - 1, mainChar, totalCasts, dash, block);
				emblem.subBuffText[buffs.currSize() - 1] = emblem.buffText[temp2[0] - 1];
			} else {
				buffs.levelChange(temp2[0] - 1, mainChar, totalCasts, dash, block);
			}
			mainChar.heal(20);
			EXP -= EXPNeeded;
			EXPNeeded += 5;
			isLeveling = false;
			turn = (float).25;
			if(buffs.upgrades[temp2[0] - 1] > 3) {
				maxBuffs++;
			}
		} else if (xPoint > 478 && xPoint < 628) {
			if(buffs.upgrades[temp2[1] - 1] <= 0) {
				buffs.addBuff(temp2[1]);
				buffs.levelChange(temp2[1] - 1, mainChar, totalCasts, dash, block);
				emblem.subBuffText[buffs.currSize() - 1] = emblem.buffText[temp2[1] - 1];
			} else {
				buffs.levelChange(temp2[1] - 1, mainChar, totalCasts, dash, block);
			}
			mainChar.heal(20);
			EXP -= EXPNeeded;
			EXPNeeded += 5;
			isLeveling = false;
			turn = (float).25;
			if(buffs.upgrades[temp2[1] - 1] > 3) {
				maxBuffs++;
			}
		}
		}
		
	}
	public boolean dupeCheck(int array[], int index, ArrayList<Casts> totalCasts) {
		for(int i = 0; i < 2; i++) {
			if(array[index] == array[i] && i != index) {
				return true;
			}
		}
		if(totalCasts.get(array[index] - 1).upgrade > 4) {
			return true;
		}
		return false;
	}
	
	public boolean dupeCheck(int array[], int index, Buffs buffs) {
		for(int i = 0; i < 2; i++) {
			if(array[index] == array[i] && i != index) {
				return true;
			}
		}
		if(buffs.upgrades[array[index] - 1] > 3) {
			return true;
		}
		return false;
	}
}
