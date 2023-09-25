package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class IceHeal extends Casts{;
	float damage = 10;
	Texture texture[];
	
	public IceHeal() {
		super("Heal", "Heal: 10\nCooldown: 12", 7, 12);
		healCast = 2;
		totalHealCast = 2;
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Icecasts/IceHeal.png"));
	}
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if(check) {
			cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
			if(levelTree[0] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
			} else if (levelTree[0] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(levelTree[1] == 0) {
					mainChar.buffs[0][0] = 5;
					mainChar.buffs[1][0] = (float).2;
				}else if(levelTree[1] == 1) {
					mainChar.buffs[0][0] = 5;
					mainChar.buffs[1][0] = (float).4;
				}else if(levelTree[2] == 2) {
					mainChar.buffs[0][0] = 5;
					mainChar.buffs[1][0] = (float).2;
				} else if(levelTree[2] == 3) {
					mainChar.buffs[0][0] = 5;
					mainChar.buffs[1][0] = (float).2;
				}
			} else if(levelTree[0] == 2){
				if(levelTree[1] == 0) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					mainChar.buffs[0][1] = 5;
					mainChar.buffs[1][1] = (float).2;
				}else if(levelTree[1] == 1) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					mainChar.buffs[0][1] = 5;
					mainChar.buffs[1][1] = (float).2;
					for(int i = 0; i < cooldowns.size(); i++) {
						if(cooldowns.get(i).type == 'd' || cooldowns.get(i).type == 'b') {
							cooldowns.remove(i);
							i--;
						}
					}
				}else if(levelTree[2] == 2) {
					mainChar.buffs[0][1] = 5;
					mainChar.buffs[1][1] = (float).2;
					float ratio = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
					entities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 25, 0, 3, 3, texture, (float).45,(float) .3, (int)(50.0 / ratio), (int)(50.0 * ratio), -25, -25, 50));
					if(healCast > 0) {
						mainChar.health += damage;
						mainChar.addArmour(3);
						if(mainChar.health  > mainChar.maxHealth) {
							mainChar.armour += mainChar.health - mainChar.maxHealth;
							mainChar.health = mainChar.maxHealth;
							if(mainChar.armour > mainChar.maxArmour) {
								mainChar.armour = mainChar.maxArmour;
							}
						}
						healCast--;
					}
				} else if(levelTree[2] == 3) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					mainChar.buffs[0][1] = 5;
					mainChar.buffs[1][1] = (float).2;
				}
			} else if(levelTree[0] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(levelTree[1] == 0) {
					mainChar.buffs[0][2] = 5;
					mainChar.buffs[1][2] = 1;
				}else if(levelTree[1] == 1) {
					mainChar.buffs[0][2] = 5;
					mainChar.buffs[1][2] = 2;
				}else if(levelTree[2] == 2) {
					mainChar.buffs[0][2] = 5;
					mainChar.buffs[1][2] = 3;
				}else if(levelTree[2] == 3) {
					mainChar.buffs[0][2] = 5;
					mainChar.buffs[1][2] = 4;
				}
			}
	}
	}

	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		float ratio = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		entities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 25, 0, 3, 3, texture, (float).45,(float) .3, (int)(50.0 / ratio), (int)(50.0 * ratio), -25, -25, 50));
		if(healCast > 0) {
			mainChar.health += damage;
			if(mainChar.health  > mainChar.maxHealth) {
				mainChar.armour += mainChar.health - mainChar.maxHealth;
				mainChar.health = mainChar.maxHealth;
				if(mainChar.armour > mainChar.maxArmour) {
					mainChar.armour = mainChar.maxArmour;
				}
			}
			healCast--;
		}
	}
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Heal: +5\nCooldown: -1";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 15;
			cooldown = 11;
			levelType = "Heal: +5\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 20;
			cooldown = 10;
			levelType = "Heal: +5\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				
			} else if(levelTree[0] == 2) {
				
			} else if(levelTree[0] == 3) {
				
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					charges = 2;
					currCharge = 1;
				} else if(levelTree[1] == 3) {
					healCast++;
					totalHealCast = 3;
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					healCast++;
					totalHealCast = 3;
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Call To Ares";
				levelType = "Increases Damage Dealt\nBy 20% For 5 Sec";
			} else if(currLev == 1) {
				type = "Call To Athena";
				levelType = "Reduces Damage Taken\n By 20% For 5 Sec";
			} else if(currLev == 2) {
				type = "Call To Hades";
				levelType = "Basic Attacks Apply Vulnerability\nFor 5 Sec\nLasts 6 Sec";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Bloodshed";
					levelType = "Increases Damage Dealt: +20%";
				} else if(currLev == 1) {
					type = "Renewed Rage";
					levelType = "Gain A Charge";
				} else if(currLev == 2) {
					type = "Battle Medic";
					levelType = "Allows 1 More Heal\n A Round\nHeal: +5";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Cleansing";
					levelType = "Reset Dash and Block Cooldown";
				}else if(currLev == 1) {
					type = "Fortify";
					levelType = "Convert 3 Health Into Armour\nWorks 2 Times A Round";
				} else if(currLev == 2) {
					type = "Modern Medicine";
					levelType = "Allows 1 More Heal\n A Round\nHeal: +5";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Hubris Of Man";
					levelType = "Gain An Emblem Of Corruption\nBasic Attacks Also Apply Posion\nWhile Buff is Active";
				}else if(currLev == 1) {
					type = "Empowered Call";
					levelType = "Applies Weakness\nWhile Buff Is Active";
				}else if(currLev == 2) {
					type = "Ice Cold Realm";
					levelType = "Applies Frostbite\nWhile Buff Is Active";
				}
			}
		}
	}
}
