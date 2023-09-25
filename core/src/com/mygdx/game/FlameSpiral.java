package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FlameSpiral extends Casts{
	float damage = 20;
	ArrayList<Float> timers;
	Texture texture[];
	
	public FlameSpiral() {
		super("Inferno",  "Damage: 30\nCooldown: 6", 4, 6);
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Firecasts/flameSprial.png"));
		timers = new ArrayList<Float>();
	}
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if((timers.size() < 1 || timers.get(0) > 0)) System.out.println("Bool check");
		if(check && (timers.size() < 1 || timers.get(0) > 0)) {
			cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
			if(levelTree[0] == 0) {
			standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
		if(dash.dashTimer > 0) {
			entities.get(entities.size() - 1).castType = "xd";
		} else {
			entities.get(entities.size() - 1).castType = "x";
		}
		} else if(levelTree[0] == 1) {
			if(levelTree[1] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "5md";
				} else {
					entities.get(entities.size() - 1).castType = "5m";
				}
			} else if(levelTree[1] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "5m5gd";
				} else {
					entities.get(entities.size() - 1).castType = "5m5g";
				}
			} else if(levelTree[1] == 2) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "5md";
				} else {
					entities.get(entities.size() - 1).castType = "5m";
				}
			} else if(levelTree[1] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "7md";
				} else {
					entities.get(entities.size() - 1).castType = "7m";
				}
			}
		} else if(levelTree[0] == 2) {
			if(levelTree[1] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "2nd";
				} else {
					entities.get(entities.size() - 1).castType = "2n";
				}
			} else if(levelTree[1] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "5nd";
				} else {
					entities.get(entities.size() - 1).castType = "5n";
				}
			} else if(levelTree[1] == 2) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "2nd";
				} else {
					entities.get(entities.size() - 1).castType = "2n";
				}
				if(healCast > 0) {
					mainChar.addArmour(2);
					healCast--;
				}
			} else if(levelTree[1] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "2nd";
				} else {
					entities.get(entities.size() - 1).castType = "2n";
				}
				}
		} else if(levelTree[0] == 3) {
			if(levelTree[1] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				timers.add((float)1);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				timers.add((float)1);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 2) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				timers.add((float)1);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				timers.add((float)1);
				currCharge--;
				
				if(input == 'q') {
					cooldowns.add(new Cooldowns((float).25, 't'));
				} else if(input == 'e') {
					cooldowns.add(new Cooldowns((float).25, 'y'));
				}else if(input == 'r') {
					cooldowns.add(new Cooldowns((float).25, 'u'));
				}
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			}
		}
		} else if(check && levelTree[0] == 3) {
			for(int i = 0; i < 8; i++) {
				entities.add(new gameEntity(0, 0, -1, 20, 50, 30, texture));
				entities.get(entities.size() - 1).commands = new smallObject((float)1 , 0, 0, 0, 0, 1, 200, 1, 0, "flameSpiral", i);;
			}
			entities.get(entities.size() - 1).hitbox = new HitBoxes(250);
			entities.get(entities.size() - 1).centerType = 'm';
			entities.get(entities.size() - 1).type = 'a';
			timers.remove(0);
			if(levelTree[1] == 0) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 1) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 2) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "k2sd";
				} else {
					entities.get(entities.size() - 1).castType = "k2s";
				}
			} else if(levelTree[1] == 3) {
				System.out.println("error");
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			}
		}
	}
	
	
	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		for(int i = 0; i < 8; i++) {
			entities.add(new gameEntity(0, 0, -1, 20, 50, 30, texture));
			entities.get(entities.size() - 1).commands = new smallObject((float)1 , 0, 0, 0, 0, 200, -200, 1, 0, "flameSpiral", i);
		}
		entities.get(entities.size() - 1).hitbox = new HitBoxes(250);
		entities.get(entities.size() - 1).centerType = 'm';
		entities.get(entities.size() - 1).type = 'a';
	}
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +15\nCooldown: -1";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 35;
			cooldown = 5;
			levelType = "Damage: +25";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 60;
			levelType = "Damage: +25";
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
					damage += 80;
				} else if(levelTree[1] == 3) {
					buffs.infernalPlus += (buffs.infernalPlus * 2) / 5;
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					if(/*gluttony &&*/ true) {
						buffs.infernalMult += (buffs.infernalMult * 2) / 3;
					}
				} else if(levelTree[1] == 2) {
					healCast = 2;
					totalHealCast = 2;
				} else if(levelTree[1] == 3) {
					damage += 60;
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					damage += 50;
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					charges = 2;
					currCharge = 2;
					cooldown += 2;
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Soul Reaper";
				levelType = "Kills With This Move Increases\nThe Damage Dealt\nBY THIS MOVE: +5";
			} else if(currLev == 1) {
				type = "Soul Eater";
				levelType = "Kills With This Move Increases\nThe Damage Dealt\nBY ALL MOVES: +.3%";
			} else if(currLev == 2) {
				type = "Dual Spiral";
				levelType = "Hits Twice";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Consumption";
					levelType = "Heal 5 Health Per Kill";
				} else if(currLev == 1) {
					type = "Devil";
					levelType = "Damage: +80";
				} else if(currLev == 2) {
					type = "Satan";
					levelType = "Stacks Are Worth: +2";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Unsateable";
					levelType = "If You Have The Sin of Gluttony\nDamage Per Stack: +.2%";
				}else if(currLev == 1) {
					type = "Reaped";
					levelType = "Gain 20 Armor After Cast\nFor 2 Seconds";
				} else if(currLev == 2) {
					type = "Easy Pickings";
					levelType = "Damage: +60";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Wild Fire";
					levelType = "Damage: +50";
				}else if(currLev == 1) {
					type = "Explosive Push";
					levelType = "Knocks Enemies Back\nStuns: 1 Sec";
				}else if(currLev == 2) {
					type = "Quadra Spiral";
					levelType = "Adds A Second Charge\nCooldown: +2";
				}
			}
		}
	}
}
