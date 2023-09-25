package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class IceArrow extends Casts{
	float damage = 35;
	Texture texture[];
	Channel channel;
	
	public IceArrow() {
		super("Ice Arrow", "Damage: 35\nCooldown: 4", 6, 4);
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Icecasts/IceArrow.png"));
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
			} if(levelTree[0] == 1) {
				mainChar.channels[0] = new Channel(channel.finChannel, channel.startChannel, channel.type);
				channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 0) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "6w6vd";
					} else {
						entities.get(entities.size() - 1).castType = "6w6v";
					}
				} else if(levelTree[1] == 1) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "10w10vd";
					} else {
						entities.get(entities.size() - 1).castType = "10w10v";
					}
				} else if(levelTree[1] == 2) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					currCharge--;
					if(input == 'q') {
						cooldowns.add(new Cooldowns((float).25, 't'));
					} else if(input == 'e') {
						cooldowns.add(new Cooldowns((float).25, 'y'));
					}else if(input == 'r') {
						cooldowns.add(new Cooldowns((float).25, 'u'));
					}
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "4w4vd";
					} else {
						entities.get(entities.size() - 1).castType = "4w4v";
					}
				} else if(levelTree[1] == 3) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(/*buffs.checkSynergies("Jotun") && */healCast > 0) {
						entities.get(entities.size() - 1).castType += "4h";
						healCast--;
					}
				}
			} else if(levelTree[0] == 3) {
				for(int i = 1; i < 8; i++) {
					float theta = (float)(i * Math.PI / 4);
					//theta -= (float) Math.PI / 2;
					entities.add(new gameEntity((float)((mainChar.currX - 30) - Math.sin(theta - Math.PI) * 40), (float)((mainChar.currY + 15) + Math.cos(theta - Math.PI) * 40), 60, -80, 20, texture, (float)2.5, (float) .1, 0, 0, (int) (500 * Math.cos(theta)), (int)(500 * Math.sin(theta)), 90, 20, 90, 20, (float)45, (float)10, (float) (theta * 180 / Math.PI), false, damage));
					entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
					entities.get(entities.size() - 1).textNum = 0;
					entities.get(entities.size() - 1).type = 'a';
					entities.get(entities.size() - 1).commands.type = "Arrow";
					//theta += (float) Math.PI / 4;
					if(theta > Math.PI / 2 && theta <= Math.PI * 3 / 2) {
						entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 30 * Math.sin(theta) + 35), (int)(10 + 40 * Math.cos(theta)), (int)(40 * Math.cos(theta) - 30 * Math.sin(theta) + 35), (int)(40 * Math.sin(theta) + 40 * Math.cos(theta) + 10), (int)(44.73 * Math.cos(theta + .4636) - 30 * Math.sin(theta) + 35), (int)(44.73 * Math.sin(theta + .4636) + 40 * Math.cos(theta) + 10), (int)(20 * Math.cos(theta + Math.PI / 2) - 30 * Math.sin(theta) + 35), (int)(20 * Math.sin(theta + Math.PI / 2) + 40 * Math.cos(theta) + 10));
					} else if(theta > Math.PI * 3 / 2){
						entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 40 * Math.sin(theta) + 35), (int)( 40 * Math.cos(theta) - 5), (int)(40 * Math.cos(theta) - 40 * Math.sin(theta) + 35), (int)(40 * Math.sin(theta) + 40 * Math.cos(theta) - 5), (int)(44.73 * Math.cos(theta + .4636) - 40 * Math.sin(theta) + 35), (int)(44.73 * Math.sin(theta + .4636) + 40 * Math.cos(theta) - 5), (int)(20 * Math.cos(theta + Math.PI / 2) - 40 * Math.sin(theta) + 35), (int)(20 * Math.sin(theta + Math.PI / 2) + 40 * Math.cos(theta)- 5));
					}else {
						entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 20 * Math.sin(theta) + 25), (int)(-10 + 45 * Math.cos(theta)), (int)(40 * Math.cos(theta) - 20 * Math.sin(theta) + 25), (int)(40 * Math.sin(theta) + 45 * Math.cos(theta) - 10), (int)(44.73 * Math.cos(theta + .4636) - 20 * Math.sin(theta) + 25), (int)(44.73 * Math.sin(theta + .4636) + 45 * Math.cos(theta) - 10), (int)(20 * Math.cos(theta + Math.PI / 2) - 20 * Math.sin(theta) + 25), (int)(20 * Math.sin(theta + Math.PI / 2) + 45 * Math.cos(theta) - 10));
						}
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
							entities.get(entities.size() - 1).castType = "fd";
						} else {
							entities.get(entities.size() - 1).castType = "f";
						}
					}else if(levelTree[1] == 3) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "ad";
						} else {
							entities.get(entities.size() - 1).castType = "a";
						}
					}
				}
				entities.add(new gameEntity(mainChar.currX + 15, mainChar.currY + 17, 60, -52, -10, texture, (float)2.5, (float) .1, 0, 0, (int) (500), (int)(0), 90, 20, 90, 20, (float)45, (float)10, (float) (0), false, damage));
				entities.get(entities.size() - 1).hitbox = new HitBoxes(0, -10, 40, -10, 40, 20, 0, 20);
				entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
				entities.get(entities.size() - 1).textNum = 0;
				entities.get(entities.size() - 1).type = 'a';
				entities.get(entities.size() - 1).commands.type = "Arrow";
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
					entities.get(entities.size() - 1).castType = "fd";
				} else {
					entities.get(entities.size() - 1).castType = "f";
				}
			}else if(levelTree[1] == 3) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "ad";
				} else {
					entities.get(entities.size() - 1).castType = "a";
				}
			}
			}
			} else if(!check && levelTree[0] == 1) {
				if(mainChar.channels[0].currChannel > mainChar.channels[0].startChannel && mainChar.channels[0].currChannel <= mainChar.channels[0].finChannel && mainChar.channels[0].type.equals("iceArrow")) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
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
					}else if(levelTree[1] == 3) {
						if(dash.dashTimer > 0) {
							entities.get(entities.size() - 1).castType = "3scd";
						} else {
							entities.get(entities.size() - 1).castType = "3sc";
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
		//theta -= (float) Math.PI / 2;
		entities.add(new gameEntity((float)((mainChar.currX - 30) - Math.sin(theta - Math.PI) * 40), (float)((mainChar.currY + 15) + Math.cos(theta - Math.PI) * 40), 60, -80, 20, texture, (float)2.5, (float) .1, 0, 0, (int) (500 * Math.cos(theta)), (int)(500 * Math.sin(theta)), 90, 20, 90, 20, (float)45, (float)10, (float) (theta * 180 / Math.PI), false, damage));
		entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
		entities.get(entities.size() - 1).textNum = 0;
		entities.get(entities.size() - 1).type = 'a';
		entities.get(entities.size() - 1).commands.type = "Arrow";
		//theta += (float) Math.PI / 4;
		if(theta > Math.PI / 2 && theta <= Math.PI * 3 / 2) {
		entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 30 * Math.sin(theta) + 35), (int)(10 + 40 * Math.cos(theta)), (int)(40 * Math.cos(theta) - 30 * Math.sin(theta) + 35), (int)(40 * Math.sin(theta) + 40 * Math.cos(theta) + 10), (int)(44.73 * Math.cos(theta + .4636) - 30 * Math.sin(theta) + 35), (int)(44.73 * Math.sin(theta + .4636) + 40 * Math.cos(theta) + 10), (int)(20 * Math.cos(theta + Math.PI / 2) - 30 * Math.sin(theta) + 35), (int)(20 * Math.sin(theta + Math.PI / 2) + 40 * Math.cos(theta) + 10));
		} else if(theta > Math.PI * 3 / 2){
			entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 40 * Math.sin(theta) + 35), (int)( 40 * Math.cos(theta) - 5), (int)(40 * Math.cos(theta) - 40 * Math.sin(theta) + 35), (int)(40 * Math.sin(theta) + 40 * Math.cos(theta) - 5), (int)(44.73 * Math.cos(theta + .4636) - 40 * Math.sin(theta) + 35), (int)(44.73 * Math.sin(theta + .4636) + 40 * Math.cos(theta) - 5), (int)(20 * Math.cos(theta + Math.PI / 2) - 40 * Math.sin(theta) + 35), (int)(20 * Math.sin(theta + Math.PI / 2) + 40 * Math.cos(theta)- 5));
		}else {
			entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(0 - 20 * Math.sin(theta) + 25), (int)(-10 + 45 * Math.cos(theta)), (int)(40 * Math.cos(theta) - 20 * Math.sin(theta) + 25), (int)(40 * Math.sin(theta) + 45 * Math.cos(theta) - 10), (int)(44.73 * Math.cos(theta + .4636) - 20 * Math.sin(theta) + 25), (int)(44.73 * Math.sin(theta + .4636) + 45 * Math.cos(theta) - 10), (int)(20 * Math.cos(theta + Math.PI / 2) - 20 * Math.sin(theta) + 25), (int)(20 * Math.sin(theta + Math.PI / 2) + 45 * Math.cos(theta) - 10));
		}
	}
	
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +15\nCooldown: -1";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 50;
			cooldown = 3;
			levelType = "Cooldown: -1";
			upgrade++;
		} else if (upgrade == 2) {
			cooldown = 2;
			levelType = "Cooldown: -1";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				channel = new Channel(6, 3, "iceArrow");
				damage += 150;
				cooldown += 6;
			} else if(levelTree[0] == 2) {
				cooldown += 4;
			} else if(levelTree[0] == 3) {
				
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					channel.startChannel -= 1.5;
					channel.finChannel -= 1.5;
					cooldown -= 2;
				} else if(levelTree[1] == 2) {
					damage += 80;
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					charges = 2;
					currCharge = 1;
				} else if(levelTree[1] == 3) {
					totalHealCast = 1;
					healCast = 1;
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					damage += 80;
				} else if(levelTree[1] == 2) {
					damage += 40;
					cooldown += 3;
				} else if(levelTree[1] == 3) {
					cooldown -= .5;
					damage += 40;
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Compound Bow";
				levelType = "Has A 3 Sec Channel\nDamage: +150\nCooldown: +6";
			} else if(currLev == 1) {
				type = "Chilling Blow";
				levelType = "Applies Weakness For 3 Sec\nApplies Vulnerability For 3 Sec\nCooldown: +4";
			} else if(currLev == 2) {
				type = "Trick Shot";
				levelType = "Fires 8 Arrows In A\nRing Aound You";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Bow Mastery";
					levelType = "Channel Time: -1.5\nCooldown: -2";
				} else if(currLev == 1) {
					type = "Glacial Arrow";
					levelType = "Damage: +80";
				} else if(currLev == 2) {
					type = "Iceborne Arrow";
					levelType = "Stuns Enemies For 1.5 Sec";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Potent Chill";
					levelType = "Weaken And Vulnerability Time: +2\n";
				}else if(currLev == 1) {
					type = "Twin Arrows";
					levelType = "Has Two Charges\nNerf Time: -1";
				} else if(currLev == 2) {
					type = "Arrow Of Apollo";
					levelType = "Heals +4 Health On First Cast";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Reinforced Ice";
					levelType = "Damage: +80";
				}else if(currLev == 1) {
					type = "Sub-Zero Arrows";
					levelType = "Applies Frostbite\nDamage: +40\nCooldown: +3";
				}else if(currLev == 2) {
					type = "Human Armoury";
					levelType = "Fires Periodically\nRate: -.5\nDamage: +40";
				}
			}
		}
	}
}
