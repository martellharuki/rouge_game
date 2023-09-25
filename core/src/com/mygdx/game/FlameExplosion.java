package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FlameExplosion extends Casts{
	float damage = 40;
	float tempDamage;
	float radius = 100;
	Channel channel;
	Texture texture[];
	
	public FlameExplosion() {
		super("Explosion", "Damage: 40\nCooldown: 10", 5, 10);
		texture = new Texture[1];
		texture[0] = new Texture(Gdx.files.internal("Images/Cast Attacks/Firecasts/flameExplosion.png"));
		channel = new Channel(6, 3, "flameExplosion");
	}
	public void makeCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		if(check && levelTree[0] != 3) {
			cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
			mainChar.channels[0] = new Channel(channel.finChannel, channel.startChannel, channel.type);
			channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
		} else if (mainChar.channels[0].currChannel > mainChar.channels[0].startChannel && mainChar.channels[0].currChannel <= mainChar.channels[0].finChannel && levelTree[0] != 3 && mainChar.channels[0].type.equals("flameExplosion")) {
			if(levelTree[0] == 0) {
			standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
			if(dash.dashTimer > 0) {
			entities.get(entities.size() - 1).castType = "cd";
		} else {
			entities.get(entities.size() - 1).castType = "c";
		}
			} else if(levelTree[0] == 1) {
				if(levelTree[1] == 0) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "4scd";
				} else {
					entities.get(entities.size() - 1).castType = "4sc";
				}
				} else if(levelTree[1] == 1) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "4scd";
				} else {
					entities.get(entities.size() - 1).castType = "4sc";
				}
				} else if(levelTree[1] == 2) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "4scd";
				} else {
					entities.get(entities.size() - 1).castType = "4sc";
				}
				} else if(levelTree[1] == 3) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "4s10wcd";
				} else {
					entities.get(entities.size() - 1).castType = "4s10wc";
				}
				}
			} else if(levelTree[0] == 2) {
				radius = mainChar.channels[0].currChannel / mainChar.channels[0].finChannel * 90 + 10;
				tempDamage = damage;
				damage *= 1.5 - .5 *( mainChar.channels[0].currChannel / mainChar.channels[0].finChannel);
				if(levelTree[1] == 0) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "c";
				}
				} else if(levelTree[1] == 1) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "cd";
				} else {
					entities.get(entities.size() - 1).castType = "c";
				}
				} else if(levelTree[1] == 2) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "80icd";
				} else {
					entities.get(entities.size() - 1).castType = "80ic";
				}
				} else if(levelTree[1] == 3) {
					standardCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, input, check);
					if(dash.dashTimer > 0) {
					entities.get(entities.size() - 1).castType = "ucd";
				} else {
					entities.get(entities.size() - 1).castType = "uc";
				}
				}
				damage = tempDamage;
			}
	} else if(check && levelTree[0] == 3) {
		cooldowns.add(new Cooldowns(buffs.getCooldown(cooldown), input));
		float ratio = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		entities.add(new gameEntity(mainChar.currX + 13, mainChar.currY + 23, 0, 3, 3, texture, (float).45,(float) .3, (int)((radius - 10) / ratio), (int)((radius - 10) * ratio), -(int)((radius - 10) / 2), -(int)((radius - 10) / 2), damage));
		entities.get(entities.size() - 1).centerType = 'm';
		entities.get(entities.size() - 1).type = 'a';
		entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(radius / 2));
		if(dash.dashTimer > 0) {
			entities.get(entities.size() - 1).castType = "ad";
		} else {
			entities.get(entities.size() - 1).castType = "a";
		}
	}
	}
	
	public void standardCast(MainCharacter mainChar,ArrayList<gameEntity> entities, float graphicWidth, float graphicHeight, ArrayList<Cooldowns> cooldowns, ArrayList<gameEntity> channelEntities, Dash dash, Buffs buffs, char input, boolean check) {
		float ratio = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		entities.add(new gameEntity(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth(),(Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight(), 0, 3, 3, texture, (float).45,(float) .3, (int)((radius - 10) / ratio), (int)((radius - 10) * ratio), -(int)((radius - 10) / 2), -(int)((radius - 10) / 2), damage));
		entities.get(entities.size() - 1).centerType = 'p';
		entities.get(entities.size() - 1).type = 'a';
		entities.get(entities.size() - 1).hitbox = new HitBoxes((int)(radius / 2), Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth(), (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight());
		mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
	}
	
	public void levelChange(Buffs buffs) {
		if(upgrade == 0) {
			levelType = "Damage: +20\nCooldown: -1";
			upgrade++;
		} else if(upgrade == 1) {
			damage = 60;
			cooldown = 9;
			levelType = "Damage: +20\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 2) {
			damage = 80;
			cooldown = 8;
			levelType = "Damage: +20\nCooldown: -1";
			upgrade++;
		} else if (upgrade == 3) {
			upgrade++;
			levelTree[0] = currLev + 1;
			if(levelTree[0] == 1) {
				
			} else if(levelTree[0] == 2) {
				
			} else if(levelTree[0] == 3) {
				cooldown -= 2;
			}
		} else if (upgrade == 4) {
			upgrade++;
			levelTree[1] = currLev + 1;
			if(levelTree[0] == 1) {
				if(levelTree[1] == 1) {
					radius += 50;
				} else if(levelTree[1] == 2) {
					buffs.stunMult += .5;
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 2) {
				if(levelTree[1] == 1) {
					channel.startChannel--;
					cooldown -= 2;
				} else if(levelTree[1] == 2) {
					
				} else if(levelTree[1] == 3) {
					
				}
			} else if(levelTree[0] == 3) {
				if(levelTree[1] == 1) {
					cooldown--;
				} else if(levelTree[1] == 2) {
					damage += 60;
				} else if(levelTree[1] == 3) {
					radius += 50;
				}
			}
		}
	}

	public void getLevelType() {
		//currLev = rando.nextInt(3);
		if(upgrade == 3) {
			if(currLev == 0) {
				type = "Stun Grenade";
				levelType = "Stuns Enemies For 2 Sec";
			} else if(currLev == 1) {
				type = "Concentrated Fire";
				levelType = "Smaller Radius Based Off of\nChannel Time\nDamage: Up To +100%";
			} else if(currLev == 2) {
				type = "Human Bomb";
				levelType = "Explosion Is Done Around Your\nCharacter Periodically\nCooldown: -2";
			}
		} else if(upgrade == 4) {
			if(levelTree[0] == 1) {
				if(currLev == 0) {
					type = "Flash Bang";
					levelType = "Radius: +50";
				} else if(currLev == 1) {
					type = "Stun Slayer";
					levelType = "Do 50% more damage on\nstunned targets on ALL MOVES";
				} else if(currLev == 2) {
					type = "Weakening Fire";
					levelType = "Applies Weakness For 5 Sec";
				}
			} else if(levelTree[0] == 2) {
				if(currLev == 0) {
					type = "Quick Cast";
					levelType = "Channel Time: -1\nCooldown: -2";
				}else if(currLev == 1) {
					type = "Stun Slayer";
					levelType = "Does More Damage\n To Stunned Targets\nDamage: +80";
				} else if(currLev == 2) {
					type = "Melting Wave";
					levelType = "Applies Burn";
				}
			} else if(levelTree[0] == 3) {
				if(currLev == 0) {
					type = "Highly Reactable";
					levelType = "Rate: -1";
				}else if(currLev == 1) {
					type = "Hotter Flames";
					levelType = "Damage: +60";
				}else if(currLev == 2) {
					type = "Super Nova";
					levelType = "Radius: +50";
				}
			}
		}
	}
}