package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ElectroWave extends Casts{
	float damage = 20;
	Texture texture[];
	Channel channel;
	
	public ElectroWave() {
		super("Shock Wave", "Damage: 25\nCooldown: 4", 1, 4);
		texture = new Texture[2];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Electrocasts/electroAC0.png"));
		texture[1] = new Texture(Gdx.files.internal("Images/Cast Attacks/Electrocasts/electroWave1.png"));
		damage = 25;
	}
	
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if(check) {
		cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
		if(levelTree[0] == 0) {
			standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "d";
			} else {
				entities.get(entities.size() - 1).castType = "";
				}
		}	else if(levelTree[0] == 1) {
			if(levelTree[1] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d2s";
				} else {
					entities.get(entities.size() - 1).castType = "2s";
					}
			} else if(levelTree[1] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d6s";
					} else {
						entities.get(entities.size() - 1).castType = "6s";
						}
			} else if(levelTree[1] == 2) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d3s";
				} else {
					entities.get(entities.size() - 1).castType = "3s";
					}
			} else if (levelTree[1] == 3) {
				float theta = 0;
				for(int i = 0; i < 4; i++) {
					entities.add(new gameEntity((float)((mainChar.currX + 15) - Math.cos(theta - Math.PI/4) * 0), (float)((mainChar.currY + 25) - Math.sin(theta - Math.PI/4) * 0), 0, 0, 0, texture, (float).25, (float) .1, 100, 100, 0, (int)(287 * Math.sin(theta)), 0, 0, 43, 43, (float)0, (float)0, (float) (theta * 180 / Math.PI), false, damage));
					entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
					entities.get(entities.size() - 1).textNum = 0;
					entities.get(entities.size() - 1).type = 'a';
					theta += Math.PI / 2;
				}
				entities.get(entities.size() - 1).centerType = 'm';
				entities.get(entities.size() - 1).hitbox = new HitBoxes(180);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d3s";
				} else {
					entities.get(entities.size() - 1).castType = "3s";
					}
			}
		} else if(levelTree[0] == 2) {
			if(levelTree[1] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
					}
			} else if(levelTree[1] == 1) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
					}
			} else if(levelTree[1] == 2) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
					}
			} else if(levelTree[1] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "de";
				} else {
					entities.get(entities.size() - 1).castType = "e";
					}
			}
		} else if(levelTree[0] == 3) {
			mainChar.channels[0] = new Channel(channel.finChannel, channel.startChannel, channel.type);
			channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
		} 
		} else if(!check && levelTree[0] == 3 && mainChar.channels[0].currChannel >= mainChar.channels[0].startChannel && mainChar.channels[0].currChannel <= mainChar.channels[0].finChannel && mainChar.channels[0].type.equals("electroWave")) {
			float tempDamage = damage + damage * (mainChar.channels[0].currChannel / mainChar.channels[0].finChannel);
			if(levelTree[1] == 2) {
				tempDamage = damage + damage * (mainChar.channels[0].currChannel / (mainChar.channels[0].finChannel - 2));
			}
			float cursorX = Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth();
			float cursorY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight();
			float theta = (float) Math.atan((Math.abs(cursorY - (mainChar.currY + 25))) / (Math.abs(cursorX - (mainChar.currX + 15))));
			if(cursorX < mainChar.currX + 15 && cursorY > mainChar.currY + 25) {
				theta = (float)(Math.PI - theta);
			} else if((cursorX < mainChar.currX + 15 && cursorY < mainChar.currY + 25)){
				theta+= (float)Math.PI;
			} else if((cursorX > mainChar.currX + 15 && cursorY < mainChar.currY + 25)) {
				theta= (float)(2 * Math.PI - theta);
				}
			theta += (float) Math.PI / 4;
			entities.add(new gameEntity((float)((mainChar.currX + 15) - Math.cos(theta - Math.PI/4) * 0), (float)((mainChar.currY + 25) - Math.sin(theta - Math.PI/4) * 0), 0, 0, 0, texture, (float).25, (float) .1, 75, 75, (int)(0), (int)(170 * Math.sin(theta - Math.PI/4) + 170 * Math.cos(theta - Math.PI/4)), 0, 0, 43, 43, (float)0, (float)0, (float) (theta * 180 / Math.PI), false, tempDamage));
			entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[1]);
			entities.get(entities.size() - 1).textNum = 1;
			entities.get(entities.size() - 1).type = 'a';
			theta -= (float) Math.PI / 4;
			entities.get(entities.size() - 1).centerType = 'm';
			entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(25 * Math.cos(theta - Math.PI / 2)), (int)(25 * Math.sin(theta - Math.PI / 2)), (int)(160 * Math.cos(theta) + 25 * Math.cos(theta - Math.PI / 2)), (int)(160 * Math.sin(theta) + 25 * Math.sin(theta - Math.PI / 2)), (int)(160 * Math.cos(theta) + 25 * Math.cos(theta + Math.PI / 2)), (int)(160 * Math.sin(theta) + 25 * Math.sin(theta + Math.PI / 2)), (int)(25 * Math.cos(theta + Math.PI / 2)), (int)(25 * Math.sin(theta + Math.PI / 2)));
			mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
			/*if(levelTree[1] == 0) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "c";
					}
			} else if(levelTree[1] == 1) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "c";
					}
			} else if(levelTree[1] == 2) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "c";
					}
			} else if(levelTree[1] == 3) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "d";
					}
				if(healCast > 0) {
					entities.get(entities.size() - 1).castType += "7h";
					healCast--;
				}
			} */
		}
	}
	
	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		float cursorX = Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth();
		float cursorY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight();
		float theta = (float) Math.atan((Math.abs(cursorY - (mainChar.currY + 25))) / (Math.abs(cursorX - (mainChar.currX + 15))));
		if(cursorX < mainChar.currX + 15 && cursorY > mainChar.currY + 25) {
			theta = (float)(Math.PI - theta);
		} else if((cursorX < mainChar.currX + 15 && cursorY < mainChar.currY + 25)){
			theta+= (float)Math.PI;
		} else if((cursorX > mainChar.currX + 15 && cursorY < mainChar.currY + 25)) {
			theta= (float)(2 * Math.PI - theta);
			}
		theta += (float) Math.PI / 4;
		entities.add(new gameEntity((float)((mainChar.currX + 15) - Math.cos(theta - Math.PI/4) * 0), (float)((mainChar.currY + 25) - Math.sin(theta - Math.PI/4) * 0), 0, 0, 0, texture, (float).25, (float) .1, 100, 100, 0, (int)(287 * Math.sin(theta)), 0, 0, 43, 43, (float)0, (float)0, (float) (theta * 180 / Math.PI), false, damage));
		entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
		entities.get(entities.size() - 1).textNum = 0;
		entities.get(entities.size() - 1).type = 'a';
		theta -= (float) Math.PI / 2;
		entities.get(entities.size() - 1).centerType = 'm';
		entities.get(entities.size() - 1).hitbox = new HitBoxes((int)0, (int)0, (int)(150 * Math.cos(theta)), (int)(150 * Math.sin(theta)), (int)(200 /** Math.sqrt(2)*/ * Math.cos(theta + Math.PI / 4)), (int)(200 /** Math.sqrt(2)*/ * Math.sin(theta + Math.PI / 4)), (int)(-150 * Math.sin(theta)), (int)(150 * Math.cos(theta)));
		if(levelTree[1] == 0) {
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "c";
				}
		} else if(levelTree[1] == 1) {
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "c";
				}
		} else if(levelTree[1] == 2) {
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "c";
				}
		} else if(levelTree[1] == 3) {
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "d";
				}
			if(healCast > 0) {
				entities.get(entities.size() - 1).castType += "7h";
				healCast--;
			}
		}
	}
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +15";
			upgrade++;
		}  else if(upgrade == 1) {
			damage = 40;
			levelType = "Damage: +15\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 55;
			cooldown = 3;
			levelType = "Damage: +15";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				cooldown += 2;
			} else if(levelTree[0] == 2) {
				damage += 40;
			} else if(levelTree[0] == 3) {
				channel = new Channel(4, 1, "electroWave");
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					damage += 40;
				} else if(levelTree[1] == 2) {
					buffs.stunMult += .5;
				} else if(levelTree[1] == 3) {
					cooldown--;
					damage += 20;
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					cooldown--;
				} else if(levelTree[1] == 2) {
					damage += 40;
				} else if(levelTree[1] == 3) {
					cooldown += 2;
				}
			} else if(levelTree[0] == 3) {
				
				if(levelTree[1] == 1) {
					cooldown -= 2;
					channel.finChannel -= 2;
				} else if(levelTree[1] == 2) {
					channel.finChannel += 2;
					damage += 15;
				} else if(levelTree[1] == 3) {
					totalHealCast = 1;
					healCast = 1;
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Taser";
				levelType = "The Wave Stuns For 1.5 Sec\nCooldown: +2";
			} else if(currLev == 1) {
				type = "Storm Wave";
				levelType = "Damage: +40";
			} else if(currLev == 2) {
				type = "Shock Beam";
				levelType = "Narrow Hitbox\nMore Damage For Channel Time\nCooldown: +4";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Disabler";
					levelType = "Stun Time: +2\nDamage: +40";
				} else if(currLev == 1) {
					type = "Stun Slayer";
					levelType = "Do 50% More Damage On\nStunned Targets On ALL MOVES";
				} else if(currLev == 2) {
					type = "Stun Pulse";
					levelType = "Fires in all 4 directions\nCooldown: -1\nDamage: +20";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Storm Surge";
					levelType = "Cooldown: -1";
				}else if(currLev == 1) {
					type = "Super Storm";
					levelType = "Damage: +40";
				} else if(currLev == 2) {
					type = "Electrocute";
					levelType = "Adds DOT\nCooldown: +2";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Quick Beam";
					levelType = "Channel Time: -2\nCooldown: -2";
				}else if(currLev == 1) {
					type = "Hyper Beam";
					levelType = "Channel Duration: +2\nBase Damage: +15";
				}else if(currLev == 2) {
					type = "Shock Drain";
					levelType = "Heal 7 Health For Each\nEnemy Hit If The Remaining\nChannel Time < 1 Sec";
				}
			}
		}
	}
}
