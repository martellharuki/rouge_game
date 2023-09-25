package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class IceSpike extends Casts{
	float damage = 40;
	Texture texture[];
	Channel channel;
	
	public IceSpike() {
		super("Ice Spike", "Damage: 40\nCooldown: 7", 8, 7);
		texture = new Texture[2];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Icecasts/IceSpike.png"));
		texture[1] = new Texture(Gdx.files.internal("Images/Cast Attacks/Icecasts/blizzard0.png"));
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
				} else if(levelTree[0] == 1) {
					entities.add(new gameEntity(true, (float)1.5, 600, 600, 300, 300, texture, damage));
					entities.get(entities.size() - 1).textNum = 1;
					entities.get(entities.size() - 1).type = 'a';
					entities.get(entities.size() - 1).commands.type = "blizzard";
					entities.get(entities.size() - 1).centerType = 'm';
					entities.get(entities.size() - 1).hitbox = new HitBoxes(600);
					if(levelTree[1] == 0) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "d";
						} else {
							entities.get(entities.size() - 1).castType = "";
						}
					} else if(levelTree[0] == 1) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "d";
						} else {
							entities.get(entities.size() - 1).castType = "";
						}
						if(/*has jotun*/ true) {
							entities.get(entities.size() - 1).castType += "f";
						}
					} else if(levelTree[0] == 2) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "d";
						} else {
							entities.get(entities.size() - 1).castType = "";
						}
					} else if(levelTree[0] == 3) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "d";
						} else {
							entities.get(entities.size() - 1).castType = "";
						}
						if(/*has jotun*/ true) {
							entities.get(entities.size() - 1).castType += "10v";
						}
					}
				} else if(levelTree[0] == 2) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(input == 'q') {
						cooldowns.add(new Cooldowns((float).25, 't'));
					} else if(input == 'e') {
						cooldowns.add(new Cooldowns((float).25, 'y'));
					}else if(input == 'r') {
						cooldowns.add(new Cooldowns((float).25, 'u'));
					}
					if(levelTree[1] == 0) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "d";
						} else {
							entities.get(entities.size() - 1).castType = "";
						}
					} else if(levelTree[1] == 1) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "90id";
						} else {
							entities.get(entities.size() - 1).castType = "90i";
						}
					} else if(levelTree[1] == 2) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "kd";
						} else {
							entities.get(entities.size() - 1).castType = "k";
						}
					} else if(levelTree[1] == 3) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "20i1sd";
						} else {
							entities.get(entities.size() - 1).castType = "20i1s";
						}
					}
				}else if(levelTree[0] == 3) {
					mainChar.channels[0] = new Channel(channel.finChannel, channel.startChannel, channel.type);
					channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
				}
			} else if(!check && levelTree[0] == 3) {
				if(mainChar.channels[0].currChannel > mainChar.channels[0].startChannel && mainChar.channels[0].currChannel <= mainChar.channels[0].finChannel && mainChar.channels[0].type.equals("iceSpike")) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
					if(levelTree[1] == 0) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "cd";
						} else {
							entities.get(entities.size() - 1).castType = "c";
						}
					} else if(levelTree[1] == 1) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "90pcd";
						} else {
							entities.get(entities.size() - 1).castType = "90pc";
						}
					} else if(levelTree[1] == 2) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "90ocd";
						} else {
							entities.get(entities.size() - 1).castType = "90oc";
						}
					} else if(levelTree[1] == 3) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "cd";
						} else {
							entities.get(entities.size() - 1).castType = "c";
						}
					}
				}		
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
		//theta -= (float) Math.PI / 4;
		entities.add(new gameEntity((float)((mainChar.currX + 15) - Math.cos(theta) * 40 + Math.sin(theta) * -15), (float)((mainChar.currY + 0) - Math.sin(theta) * 40 + Math.cos(theta) * 15 + 25), 0, 0, 40, texture, (float).3, (float) .1, 90, 0, (int)(Math.cos(theta) * 35) * 0, (int)(Math.sin(theta) * -35 * 0), 40, 0, 40, 78, (float)0, (float)0, (float) (theta * 180 / Math.PI), false, 50));
		entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
		entities.get(entities.size() - 1).textNum = 0;
		entities.get(entities.size() - 1).type = 'a';
		entities.get(entities.size() - 1).commands.type = "Spike";
		//theta += (float) Math.PI / 4;
		entities.get(entities.size() - 1).centerType = 'm';
		entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(-10 * Math.cos(theta)), (int)(-10 * Math.sin(theta)), (int)(40 * Math.cos(theta - Math.PI / 4) + (-10 * Math.cos(theta))), (int)(40 * Math.sin(theta - Math.PI / 4) + (-10 * Math.cos(theta))), (int)(110 * Math.cos(theta) + (-10 * Math.cos(theta))), (int)(110 * Math.sin(theta) + (-10 * Math.sin(theta))), (int)(40 * Math.cos(theta + Math.PI / 4) + (-10 * Math.cos(theta))), (int)(40 * Math.sin(theta + Math.PI / 4) + (-10 * Math.cos(theta))));
	}
	
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +10\nCooldown: -1";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 50;
			cooldown = 6;
			levelType = "Damage: +10\nCooldown: -1";
			upgrade++;
		} else if (num == 2) {
			damage = 60;
			cooldown = 5;
			levelType = "Damage: +10\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				
			} else if(levelTree[0] == 2) {
				charges = 3;
				currCharge = 1;
				damage -= 20;
				cooldown += 3;
			} else if(levelTree[0] == 3) {
				channel = new Channel(4, 6, "iceSpike");
				cooldown += 2;
				damage += 100;
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					damage += 25;
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					cooldown -= 2;
				} else if(levelTree[1] == 3) {
					damage += 20;
					channel.startChannel -= 2;
					channel.finChannel--;
					cooldown -= 2;
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Blizzard";
				levelType = "New Move That Does\nMap-Wide AOE\n";
			} else if(currLev == 1) {
				type = "Retractable Spikes";
				levelType = "Gain 3 Charges\nDamage: -20\nCooldown: +2";
			} else if(currLev == 2) {
				type = "Hyper Spike";
				levelType = "Adds 4 Sec Channel\nDamage: +100\nCooldown: +2";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Dimension Of Ice";
					levelType = "Applies Frostbite If\nJotunheimr Synergy Is\nActive";
				} else if(currLev == 1) {
					type = "Icicle Storm";
					levelType = "Damage: +80";
				} else if(currLev == 2) {
					type = "Dimension Of Sickness";
					levelType = "Applies Vulnerability If\nJotunheimr Synergy Is\nActive For 5 Sec";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Stun Slayer";
					levelType = "Do 90 More Damage on\nStunned Targets On THIS MOVE";
				}else if(currLev == 1) {
					type = "Push";
					levelType = "Knocks Enemies Back\nDamage: +25";
				} else if(currLev == 2) {
					type = "Chilling Spikes";
					levelType = "Hits Stun For .5 Sec\nDo 20 More Damage On\nStunned Targets On THIS MOVE";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Cheap Shot";
					levelType = "Do 90 More Damage To\nVulnerable targets On\nTHIS MOVE";
				}else if(currLev == 1) {
					type = "King Slayer";
					levelType = "Damage ON BOSSES: +80\nCooldown: -2";
				}else if(currLev == 2) {
					type = "Cast Mastery";
					levelType = "Channel Time: -2\nCooldown: -2\nDamage: +20";
				}
			}
		}
	}
}
