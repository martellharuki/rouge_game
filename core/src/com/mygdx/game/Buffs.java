package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class Buffs {
	float stunMult = 0;
	float infernalMult = 0;
	float infernalCount = 0;
	float infernalPlus = 0;
	int upgrades[];
	int currBuffs[];
	String mainType[] = new String[]{"War", "Unity", "Fortune", "Precision", "Magic", "Light", "Music", "Strength", "Structure"};
	String levelType[] = new String[] {"Damage Dealt: +15%", "Adds 10 Armor\nPerfect Block\nFrames: +.1 Sec", "Crit Chance: +15%", "Crit Damage: +30%", "Channel Attacks\nDamage: +25%", "Dash Cooldown: -1\nCrit Chance: +15%\n(Applies for 1 Sec)",
										"All Attack\nCooldowns: -10%", "Basic Attack\nDamage: +30%", "Damage Taken: -15%\nBlock Efficiency: +15%"};
	Random rando;
	
	public Buffs() {
		upgrades = new int[9];
		currBuffs = new int[4];
		for(int i = 0; i < 4; i++) {
			currBuffs[i] = -1;
		}
		rando = new Random();
	}
	
	public int currSize() {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			if(currBuffs[i] != -1) {
				count++;
			}
		}
		System.out.println("Size: " + count);
		return count;
	}
	
	public String mainType(int index) {
		if(upgrades[index] == 0) {
			return "Follower of\n" + mainType[index];
		} else if(upgrades[index] == 1) {
			return "Disciple of\n" + mainType[index];
		} else if (upgrades[index] == 2) {
			return "Prophet of\n" + mainType[index];
		} else {
			return "God of\n" + mainType[index];
		}
	}
	
	public void addBuff(int index) {
		for(int i = 0; i < 4; i++) {
			if(currBuffs[i] == -1) {
				currBuffs[i] = index;
				break;
			}
		}
	}
	
	public float getCooldown(float cooldown) {
		if(upgrades[6] > 0) {
			return cooldown * (1 - (float).1 * upgrades[6]);
		} else {
			return cooldown;
		}
	}
	
	public void levelChange(int index, MainCharacter mainChar, ArrayList<Casts> totalCasts, Dash dash, Block block) {
		if(index == 0) {
			if(upgrades[index] == 0) {
				upgrades[index]++;
			} else if(upgrades[index] == 1) {
				upgrades[index]++;
			} else if(upgrades[index] == 2) {
				upgrades[index]++;
			} else if(upgrades[index] == 3) {
				upgrades[index]++;
			}
		} else if(index == 1) {
			if(upgrades[index] == 0) {
				block.perfectFrame += .1;
				mainChar.addArmour(10);
				upgrades[index]++;
			} else if(upgrades[index] == 1) {
				block.perfectFrame += .1;
				mainChar.addArmour(10);
				upgrades[index]++;
			} else if(upgrades[index] == 2) {
				block.perfectFrame += .1;
				mainChar.addArmour(10);
				upgrades[index]++;
			} else if(upgrades[index] == 3) {
				block.perfectFrame += .1;
				mainChar.addArmour(10);
				upgrades[index]++;
			}
		} else if(index == 2) {
			if(upgrades[index] == 0) {
				upgrades[index]++;
			} else if(upgrades[index] == 1) {
				upgrades[index]++;
			} else if(upgrades[index] == 2) {
				upgrades[index]++;
			} else if(upgrades[index] == 3) {
				upgrades[index]++;
			}
			} else if(index == 3) {
				if(upgrades[index] == 0) {
					upgrades[index]++;
				} else if(upgrades[index] == 1) {
					upgrades[index]++;
				} else if(upgrades[index] == 2) {
					upgrades[index]++;
				} else if(upgrades[index] == 3) {
					upgrades[index]++;
				}
			} else if(index == 4) {
				if(upgrades[index] == 0) {
					upgrades[index]++;
				} else if(upgrades[index] == 1) {
					upgrades[index]++;
				} else if(upgrades[index] == 2) {
					upgrades[index]++;
				} else if(upgrades[index] == 3) {
					upgrades[index]++;
				}
			} else if(index == 5) {
				if(upgrades[index] == 0) {
					upgrades[index]++;
					dash.cooldown--;
					dash.maxTimer = 1;
				} else if(upgrades[index] == 1) {
					upgrades[index]++;
					dash.cooldown--;
				} else if(upgrades[index] == 2) {
					upgrades[index]++;
					dash.cooldown--;
				} else if(upgrades[index] == 3) {
					upgrades[index]++;
					dash.cooldown--;
				}
			} else if(index == 6) {
				if(upgrades[index] == 0) {
					upgrades[index]++;
				} else if(upgrades[index] == 1) {
					upgrades[index]++;
				} else if(upgrades[index] == 2) {
					upgrades[index]++;
				} else if(upgrades[index] == 3) {
					upgrades[index]++;
				}
				for(int i = 0; i < totalCasts.size(); i++) {
					totalCasts.get(i).cooldown *= .85;
				}
			}else if(index == 7) {
				if(upgrades[index] == 0) {
					upgrades[index]++;
				} else if(upgrades[index] == 1) {
					upgrades[index]++;
				} else if(upgrades[index] == 2) {
					upgrades[index]++;
				} else if(upgrades[index] == 3) {
					upgrades[index]++;
						}
			} else if(index == 8) {
				if(upgrades[index] == 0) {
					block.dmgRdc -= .15;
					upgrades[index]++;
				} else if(upgrades[index] == 1) {
					block.dmgRdc -= .15;
					upgrades[index]++;
				} else if(upgrades[index] == 2) {
					block.dmgRdc -= .15;
					upgrades[index]++;
				} else if(upgrades[index] == 3) {
					block.dmgRdc -= .15;
					upgrades[index]++;
			}
		}
		}
	
	public float calcDamage(gameEntity thisEntity, Monster monster, MainCharacter mainChar) {
		float mult = 1;
		float damage = thisEntity.damage;
		if(thisEntity.castType.contains("c")) {
			mult += .25 * upgrades[4];
		}
		if(thisEntity.castType.contains("d")) {
			int temp = rando.nextInt(100) + 1;
			if(temp < 15 * upgrades[5] + 15 * upgrades[2] + 5) {
				mult += .5 + .3 * upgrades[3];
			}
		} else {
			int temp = rando.nextInt(100) + 1;
			if(temp < 15 * upgrades[2] + 5) {
				mult += .5 + .3 * upgrades[3];
			}
		}
		if(thisEntity.castType.contains("b")) {
			mult += .3 * upgrades[7];
		}
		if(monster.stunTimer > 0) {
			mult += stunMult;
			if(thisEntity.castType.contains("i")) {
				damage += getNumber(thisEntity.castType, 'i');
			}
		}
		if(thisEntity.castType.contains("m")) {
			damage += infernalPlus;
		}
		if(monster.vulnTimer > 0) {
			mult += .5;
		}
		mult += .15 * upgrades[0];
		mult += infernalMult;
		mult += mainChar.buffs[1][0];
		mult -= monster.buffs[1][0];
		System.out.println("Mult: " + mult);
		//add various other stuff
		if(thisEntity.castType.contains("s") && getNumber(thisEntity.castType, 's') > monster.stunTimer) {
			monster.stunTimer = getNumber(thisEntity.castType, 's');
		}
		if(thisEntity.castType.contains("v") && getNumber(thisEntity.castType, 'v') > monster.vulnTimer) {
			monster.vulnTimer = getNumber(thisEntity.castType, 'v');
		}
		if(thisEntity.castType.contains("w") && getNumber(thisEntity.castType, 'w') > monster.weakTimer) {
			monster.weakTimer = getNumber(thisEntity.castType, 'w');
		}
		if(monster.health - damage * mult < 0 && thisEntity.castType.contains("m")) {
			infernalCount++;
			infernalPlus = getNumber(thisEntity.castType, 'm') * infernalCount;
		}
		if(monster.health - damage * mult < 0 && thisEntity.castType.contains("n")) {
			infernalCount++;
			infernalMult = infernalCount * getNumber(thisEntity.castType, 'n') / 1000;
		}
		if(monster.health - damage * mult < 0 && thisEntity.castType.contains("x")) {
			infernalCount++;
		}
		if(monster.health - damage * mult < 0 && thisEntity.castType.contains("g")) {
			mainChar.heal(getNumber(thisEntity.castType, 'g'));
		}
		if(thisEntity.castType.contains("h")) {
			mainChar.heal(getNumber(thisEntity.castType, 'h'));
		}
		if(thisEntity.castType.contains("b")) {
			if(mainChar.buffs[1][2] == 1 && monster.vulnTimer < 5) {
				monster.vulnTimer = 5;
			} else if(mainChar.buffs[1][2] == 2) {
				if(monster.vulnTimer < 5) {
					monster.vulnTimer = 5;
				}
				if(monster.weakTimer < 5) {
					monster.weakTimer = 5;
				}
			} else if(mainChar.buffs[1][2] == 3) {
				//
			} else if(mainChar.buffs[1][2] == 4) {
				//
			}
		}
		if(thisEntity.castType.contains("k")) {
			monster.knockBackTimer = (float) .1;
		}
		System.out.println("Infernal: " + infernalCount);
		return damage * mult;
	}
	
	public float takeDamage(gameEntity thisEntity, Block block, MainCharacter mainChar) {
		float redMult = 1;
		if(block.active) {
			redMult -= block.dmgRdc;
		}
		redMult -= upgrades[8] * .1;
		redMult -= mainChar.buffs[1][1];
		redMult -= mainChar.buffs[1][4];
		return thisEntity.damage * redMult;
	}
	
	public float getNumber(String type, char target) {
		float temp = 0;
		for(int i = type.indexOf(target) - 1; i >= 0; i--) {
			if(!Character.isDigit(type.charAt(i)) || i == 0) {
				temp = Integer.parseInt(type.substring(i, type.indexOf(target)));
			}
		}
		if(target == 's' || target == 'v' || target == 'w') {
			return temp / 2;
		} else {
			return temp;
		}
	}
	
	public float damageReduction() {
		float mult = 1;
		return (float)(mult - .15 * upgrades[8]);
	}
}
