package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ElectroBurst extends Casts{
	float damage = 40;
	int stage = 3;
	Channel channel;
	Texture texture[];
	
	public ElectroBurst() {
		super("Discharge", "Damage: 40\nCooldown: 16", 2, 16);
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Electrocasts/electroBall.png"));
		channel = new Channel(8, 4, "electroBurst");
	}
	
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if(stage == 3 && check) {
			cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
			mainChar.channels[0] = new Channel(channel.finChannel, channel.startChannel, channel.type);
			channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
		}
		for(int i = 0; i < 8; i++) {
		if(stage == 3 && check) {
			entities.add(new gameEntity(0, 0, -6, 20, 50, 70, texture));
			entities.get(entities.size() - 1).commands = new smallObject(2 , 0, 0, 50, 50, 200, -200, 1, stage, "electroBurst", i);
		}
		else if (stage == 2 && check) {
			entities.add(new gameEntity(0, 0, 6, 20, 50, 20, texture));
			entities.get(entities.size() - 1).commands = new smallObject(6 , 0, 0, 0, 0, 50, 0, 0, stage, "electroBurst", i);
		} else if(stage == 1 && mainChar.channels[0].currChannel >= mainChar.channels[0].startChannel && mainChar.channels[0].currChannel <= mainChar.channels[0].finChannel && mainChar.channels[0].type.equals("electroBurst")) {
			for(int j = 0; j < entities.size(); j++) {
				if(entities.get(j).commands.type.equals("electroBurst") && entities.get(j).commands.stage == 2){
					entities.get(j).commands.turn = 0;
				}
			}
			entities.add(new gameEntity(0, 0, -6, 20, 50, damage, texture));
			entities.get(entities.size() - 1).commands = new smallObject((float).5 , 0, 0, 50, 50, 50, 100, 1, stage, "electroBurst", i);
			mainChar.buffs[1][4] = 0;
			mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
		}
		}
		if(stage == 1 && mainChar.channels[0].currChannel == mainChar.channels[0].finChannel) {
			System.out.println("Made");
			entities.get(entities.size() - 1).hitbox = new HitBoxes(250);
			entities.get(entities.size() - 1).centerType = 'm';
			entities.get(entities.size() - 1).type = 'a';
			if(levelTree[0] == 0 || levelTree[0] == 2 || levelTree[0] == 3) {
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "c";
			}	
			} else if(levelTree[0] == 1) {
				if(levelTree[1] == 0) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "8vcd";
					} else {
						entities.get(entities.size() - 1).castType = "8vc";
					}
				} else if(levelTree[1] == 1) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "8vcd";
					} else {
						entities.get(entities.size() - 1).castType = "8vc";
					}
				} else if(levelTree[1] == 2) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "16vcd";
					} else {
						entities.get(entities.size() - 1).castType = "16vc";
					}
				} else if(levelTree[1] == 3) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "8v8wcd";
					} else {
						entities.get(entities.size() - 1).castType = "8v8wc";
					}
				}
			}
			stage = 3;
		} else if (stage == 3 && check) {
			stage = 2;
		} else if (stage == 2 && check) {
			if(levelTree[0] == 3 && levelTree[1] == 1) {
			entities.get(entities.size() - 1).hitbox = new HitBoxes(50);
			entities.get(entities.size() - 1).centerType = 'm';
			entities.get(entities.size() - 1).damage = 60;
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "cd";
			} else {
				entities.get(entities.size() - 1).castType = "";
			}
			mainChar.buffs[1][4] = (float).2;
			mainChar.buffs[0][4] = (float)channel.finChannel;
			} else if(levelTree[0] == 3 && levelTree[1] == 2) {
				mainChar.buffs[1][4] = (float).4;
				mainChar.buffs[0][4] = (float)channel.finChannel;
			} else if(levelTree[0] == 3) {
				mainChar.buffs[1][4] = (float).2;
				mainChar.buffs[0][4] = (float)channel.finChannel;
			}
			stage = 1;
		}
	}
	
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +20\nCooldown: -4";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 60;
			cooldown = 12;
			levelType = "Damage: +15\nChannel Time: -2";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 75;
			channel.startChannel -= 2;
			levelType = "";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				
			} else if(levelTree[0] == 2) {
				damage += 60;
			} else if(levelTree[0] == 3) {
				cooldown -= 4;
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					cooldown -= 4;
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					cooldown -= 6;
				} else if(levelTree[1] == 2) {
					damage += 80;
				} else if(levelTree[1] == 3) {
					channel.startChannel -= 1;
					cooldown -= 4;
					damage += 40;
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					channel.finChannel += 2;
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Draining Orb";
				levelType = "Applies Vulnerable To\nAllEnemies Hit\nFor 4 Sec";
			} else if(currLev == 1) {
				type = "Hyper Discharge";
				levelType = "Damage: +60";
			} else if(currLev == 2) {
				type = "Psuedo-Shield";
				levelType = "Reduced Damage While\nChanneling: -20%\nCooldown: -4";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Weakening Burst";
					levelType = "Cooldown: -4";
				} else if(currLev == 1) {
					type = "Radiation";
					levelType = "Vulnerable Duration: +4";
				} else if(currLev == 2) {
					type = "Cross Posion";
					levelType = "Also applies Weaken";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Rapid-Fire";
					levelType = "Cooldown: -6";
				}else if(currLev == 1) {
					type = "Overkill";
					levelType = "Damage: +80";
				} else if(currLev == 2) {
					type = "Well-Rounded";
					levelType = "Channel Time: -1\nCooldown: -4\nDamage: +40";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Shock-Aura";
					levelType = "The Channel Circle Now \nDamages: 60";
				}else if(currLev == 1) {
					type = "Fortress";
					levelType = "Reduced Damage While Channeling: -20%";
				}else if(currLev == 2) {
					type = "Fortitude";
					levelType = "Channel Duration: +2";
				}
			}
		}
	}
}
