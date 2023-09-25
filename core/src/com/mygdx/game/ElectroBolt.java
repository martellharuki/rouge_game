package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ElectroBolt extends Casts{
	float damage = 40;
	int stage = 1;
	Texture texture[];
	float castCoords[];
	ArrayList<Float> points;
	ArrayList<Float> timers;
	
	public ElectroBolt() {
		super("Thunder", "Damage: 40\nCooldown: 10", 3, 10);
		texture = new Texture[2];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Electrocasts/electroBolt.png"));
		texture[1] = new Texture(Gdx.files.internal("Images/Cast Attacks/Electrocasts/electroBolt1.png"));
		points = new ArrayList<Float>();
		timers = new ArrayList<Float>();
	}
	
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if(check && ((((levelTree[0] != 2 || levelTree[1] != 2) && stage <= 1)) || (levelTree[0] == 2 && levelTree[1] == 2 && (timers.size() < 1 || (timers.get(0) > 0))))) {
			System.out.println("boolean: true");
		} else {
			System.out.println("boolean: false");
		}
	if(check && ((((levelTree[0] != 2 || levelTree[1] != 2) && stage <= 1)) || (levelTree[0] == 2 && levelTree[1] == 2 && (timers.size() < 1 || (timers.get(0) > 0))))) {
			cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
			if(levelTree[0] == 0) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
			if(dash.dashTimer > 0) {
				entities.get(entities.size() - 1).castType = "d";
			} else {
				entities.get(entities.size() - 1).castType = "";
			}
			} else if(levelTree[0] == 1) {
				System.out.println("In electrobolt");
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				currCharge--;
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
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(/*zues && */ healCast > 0) {
						mainChar.heal(8);
					}
				} else if(levelTree[1] == 2) {
					//standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "3sd";
					} else {
						entities.get(entities.size() - 1).castType = "3s";
					}
				} else if(levelTree[1] == 3) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "6vd";
					} else {
						entities.get(entities.size() - 1).castType = "6vs";
					}
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] != 1 && levelTree[1] != 2) {
					entities.add(new gameEntity(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth() - 10, (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight() - 10,0, 30, 30, texture, 4, 0, 0, 0, 0, 0, 0));
					entities.get(entities.size() - 1).textNum = 1;
					entities.get(entities.size() - 1).commands.type = "electroBolt";
					points.add(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth());
					points.add((Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight());
					stage = 2;
				} else if(levelTree[1] == 1) {
					entities.add(new gameEntity(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth(), (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight(),0, 30, 30, texture, 2, 0, 0, 0, 0, 0, 0));
					entities.get(entities.size() - 1).textNum = 1;
					entities.get(entities.size() - 1).commands.type = "electroBolt";
					points.add(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth());
					points.add((Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight());
					stage = 2;
				} else if(levelTree[1] == 2) {
					System.out.println("error bool: " + input);
					if(input == 'q') {
						cooldowns.add(new Cooldowns((float).25, 't'));
					} else if(input == 'e') {
						cooldowns.add(new Cooldowns((float).25, 'y'));
					}else if(input == 'r') {
						cooldowns.add(new Cooldowns((float).25, 'u'));
					}
					entities.add(new gameEntity(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth(), (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight(),0, 30, 30, texture, 4, 0, 0, 0, 0, 0, 0));
					entities.get(entities.size() - 1).textNum = 1;
					entities.get(entities.size() - 1).commands.type = "electroBolt";
					points.add(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth());
					points.add((Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight());
					timers.add((float)4.0);
					currCharge--;
				}
			} else if(levelTree[0] == 3) {
				standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
				if(levelTree[1] == 0) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(healCast > 0) {
						mainChar.heal(10);
						healCast--;
					}
				} else if(levelTree[1] == 1) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(healCast > 0) {
						mainChar.heal(20);
						healCast--;
					}
				} else if(levelTree[1] == 2) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(healCast > 0) {
						mainChar.heal(10);
						healCast--;
					}
				} else if(levelTree[1] == 3) {
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "d";
					} else {
						entities.get(entities.size() - 1).castType = "";
					}
					if(healCast > 0) {
						mainChar.heal(10);
						healCast--;
					}
					mainChar.buffs[0][3] = (float)2;
					mainChar.buffs[1][3] = (float) .25;
				}
			}
		}else if(check && levelTree[0] == 2 && ((levelTree[1] != 2 && stage == 2) || (levelTree[1] == 2 && timers.size() >= 1 && timers.get(0) <= 0))) {
			stage = 1;
			System.out.println("past electro");
			entities.add(new gameEntity(points.get(0) - 15, points.get(1) + 150, 0, 15, 1, texture, (float) .25, (float) .1, 0, 100, 0, -150, 19, 0, 0, 55, damage));
			entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
			entities.get(entities.size() - 1).centerType = 'p';
			entities.get(entities.size() - 1).type = 'a';
			entities.get(entities.size() - 1).hitbox = new HitBoxes(-10, -10, 10, -10, 10, 50, -10, 50, points.get(0), points.get(1));
			points.remove(0);
			points.remove(0);
			if(levelTree[1] == 2) {
				timers.remove(0);
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
					entities.get(entities.size() - 1).castType = "d";
				} else {
					entities.get(entities.size() - 1).castType = "";
				}
			} else if(levelTree[1] == 3) {
				if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "150id";
				} else {
					entities.get(entities.size() - 1).castType = "150i";
				}
			}
			
		}
	}
	
	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		float xValue = Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth();
		float yValue = (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight();
		entities.add(new gameEntity(xValue - 15, yValue + 150, 0, 15, 1, texture, (float) .25, (float) .1, 0, 100, 0, -150, 19, 0, 0, 55, damage));
		entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
		entities.get(entities.size() - 1).centerType = 'p';
		entities.get(entities.size() - 1).type = 'a';
		entities.get(entities.size() - 1).hitbox = new HitBoxes(-10, -10, 10, -10, 10, 50, -10, 50, xValue, yValue);
	}
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +20\nCooldown: -2";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 60;
			cooldown = 8;
			levelType = "Damage: +20\nCooldown: -2";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 80;
			cooldown = 6;
			levelType = "Damage: +20\nCooldown: -2";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				charges = 3;
				currCharge = 3;
				damage -= 30;
			} else if(levelTree[0] == 2) {
				stage = 1;
				damage += 100;
			} else if(levelTree[0] == 3) {
				totalHealCast = 2;
				healCast = 2;
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					totalHealCast = 3;
					healCast = 3;
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					cooldown += 2;
					charges = 2;
					currCharge = 1;
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					
				} else if(levelTree[1] == 2) {
					totalHealCast += 2;
					healCast += 2;
					cooldown -= 1;
				} else if(levelTree[1] == 3) {
					
				}
			}
		}
	}
	
	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Combo Bolt";
				levelType = "Has 3 Charges\nDamage: -30";
			} else if(currLev == 1) {
				type = "Gathering Storm";
				levelType = "Now Has A Cast Time That Takes\n4 Sec To Launch\nDamage: +100";
			} else if(currLev == 2) {
				type = "Healing Bolt";
				levelType = "Heals +10 Health On Cast\nUses per Round: 2";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Synergistic";
					levelType = "If The Zues Synergy Is Active\nHeals 8 Health On Cast\nUses Per Round: 3";
				} else if(currLev == 1) {
					type = "Shock Bolt";
					levelType = "Stuns for 1.5 Sec on Hit";
				} else if(currLev == 2) {
					type = "Dud Bolt";
					levelType = "Applies Vulernable For 2 Sec";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Future Sight";
					levelType = "Cast Time: -2";
				}else if(currLev == 1) {
					type = "Dual Storm";
					levelType = "Adds A Second Charge\nCooldown: +2";
				} else if(currLev == 2) {
					type = "Stun Slayer";
					levelType = "THIS MOVE Will Do Increased Damage\n To Stunned Targets\nDamage: +150";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Regenrative Bolt";
					levelType = "Healing: +10";
				}else if(currLev == 1) {
					type = "Bolt of Health";
					levelType = "Uses Per Round: +2\nCooldown: -1";
				}else if(currLev == 2) {
					type = "Protective Bolt";
					levelType = "Take -25% Damage After\nCast For 2 Sec";
				}
			}
		}
	}
}
