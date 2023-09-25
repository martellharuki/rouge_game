package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	BitmapFont font;
	Texture texture[];
	Random rando;
	Round round;
	LevelUp level;
	float graphicWidth;
	float graphicHeight;
	MainCharacter mainChar;
	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<gameEntity> entities = new ArrayList<gameEntity>();
	ArrayList<gameEntity> attackEntities = new ArrayList<gameEntity>();
	ArrayList<gameEntity> channelEntities = new ArrayList<gameEntity>();
	ArrayList<gameEntity> otherEntities = new ArrayList<gameEntity>();
	ArrayList<Casts> totalCasts = new ArrayList<Casts>();
	ArrayList<Casts> currCasts = new ArrayList<Casts>();
	Skeleton skeleton;
	SkelMage skelmage;
	SkelTank skelTank;
	SkelKing skelKing;
	SkelWizard skelWizard;
	Dash dash;
	Slash slash;
	Block block;
	HPBar hpBar;
	Buffs buffs;
	Emblem emblem;
	GroundChannel groundChannel;
	ArrayList<Cooldowns> cooldowns = new ArrayList<Cooldowns>();
	ElectroWave electroWave;
	ElectroBurst electroBurst;
	ElectroBolt electroBolt;
	FlameSpiral flameSpiral;
	FlameExplosion flameExplosion;
	IceArrow iceArrow;
	IceHeal iceHeal;
	IceSpike iceSpike;
	int rounds;
	String rotateList[];
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		texture = new Texture[2];
		texture[0] = new Texture(Gdx.files.internal("Images/Maps/stoneBrickFloorMap.png"));
		texture[1] = new Texture(Gdx.files.internal("Images/Maps/levelUpMap.png"));
		rotateList = new String[] {"Arrow", "Spike"};
		rando = new Random();
		round = new Round();
		level = new LevelUp();
		graphicWidth = Gdx.graphics.getWidth();
		graphicHeight = Gdx.graphics.getHeight();
		skeleton = new Skeleton();
		skelmage = new SkelMage();
		skelTank = new SkelTank();
		skelKing = new SkelKing();
		skelWizard = new SkelWizard();
		dash = new Dash();
		slash = new Slash();
		block = new Block();
		Texture mcTextures[] = new Texture[6];
		mcTextures[0] = new Texture(Gdx.files.internal("Images/Main Character/frontbody0.png"));
		mcTextures[1] = new Texture(Gdx.files.internal("Images/Main Character/leftBody.png"));
		mcTextures[2] = new Texture(Gdx.files.internal("Images/Main Character/rightBody.png"));
		mcTextures[3] = new Texture(Gdx.files.internal("Images/Main Character/fullBody.png"));
		mcTextures[4] = new Texture(Gdx.files.internal("Images/Main Character/channelBar.png"));
		mcTextures[5] = new Texture(Gdx.files.internal("Images/Main Character/smallSquare.png"));
		mainChar = new MainCharacter(graphicWidth / 2 - 15, graphicHeight / 2 - 25, 80, 30, 50, mcTextures, graphicWidth, graphicHeight);
		round.makeRound(monsters, skeleton, skelmage, skelTank, skelWizard, skelKing, totalCasts, rando, mainChar, graphicWidth, graphicHeight, level);
		mainChar.hitbox = new HitBoxes(2, 2, 28, 2, 28, 48, 2, 48, 28, 23, 2, 23);
		hpBar = new HPBar();
		emblem = new Emblem();
		buffs = new Buffs();
		emblem.subText[1] = emblem.mainText[0];
		emblem.subText[2] = emblem.mainText[0];
		emblem.subText[3] = emblem.mainText[0];
		groundChannel = new GroundChannel();
		electroWave = new ElectroWave();
		electroBurst = new ElectroBurst();
		electroBolt = new ElectroBolt();
		flameSpiral = new FlameSpiral();
		flameExplosion = new FlameExplosion();
		iceArrow = new IceArrow();
		iceHeal = new IceHeal();
		iceSpike = new IceSpike();
		totalCasts.add(electroWave);
		totalCasts.add(electroBurst);
		totalCasts.add(electroBolt);
		totalCasts.add(flameSpiral);
		totalCasts.add(flameExplosion);
		totalCasts.add(iceArrow);
		totalCasts.add(iceHeal);
		totalCasts.add(iceSpike);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(level.EXP < level.EXPNeeded) {
        	if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && typeCheck('b', cooldowns)) {
    			block.makeBlock(entities, cooldowns, mainChar);
    		} else {
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            mainChar.currX += mainChar.speed * Gdx.graphics.getDeltaTime();
            mainChar.textNum = 2;
            mainChar.offSet = -10;
            mainChar.xDir++;
        }
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
            mainChar.currX -= mainChar.speed * Gdx.graphics.getDeltaTime();
            mainChar.textNum= 1;
            mainChar.offSet = 10;
            mainChar.xDir--; 
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
        	mainChar.currY += mainChar.speed * Gdx.graphics.getDeltaTime();
        	mainChar.offSet = 0;
            mainChar.textNum = 3;
            mainChar.yDir++;
        }
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
            mainChar.currY -= mainChar.speed * Gdx.graphics.getDeltaTime();
            mainChar.textNum = 0;
            mainChar.offSet = 0;
            mainChar.yDir--;
        }
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if(typeCheck('d', cooldowns)) {
				cooldowns.add(new Cooldowns(dash.cooldown, 'd'));
				dash.dashTimer = dash.maxTimer;
				entities.add(new gameEntity(mainChar.currX + 14, mainChar.currY + 24, 0, 3, 3, dash.texture, (float).1, 0, 45, 45, -29, -29, 0));
				float move = (float) 84.85;
				if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)){
					mainChar.currX -= move;
					mainChar.currY += move;
				} else if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
					mainChar.currX += move;
					mainChar.currY += move;
				} else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
					mainChar.currX -= move;
					mainChar.currY -= move;
				} else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
					mainChar.currX += move;
					mainChar.currY -= move;
				} else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
					mainChar.currY -= 120;
				} else if(Gdx.input.isKeyPressed(Input.Keys.A)){
					mainChar.currX -= 120;
				} else if(Gdx.input.isKeyPressed(Input.Keys.D)){
					mainChar.currX += 120;
				} else {
					mainChar.currY +=120;
				}
				entities.add(new gameEntity(mainChar.currX + 14, mainChar.currY + 24, 0, 3, 3, dash.texture, (float).1, 0, 45, 45, -29, -29, 0));
			}
		} 
    		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(typeCheck('a', cooldowns)) {
				cooldowns.add(new Cooldowns((float) 1, 'a'));
				if(mainDiag()) {
					entities.add(new gameEntity(mainChar.currX + 5, mainChar.currY + 60, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 42, 15));
					entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[1]);
					entities.get(entities.size() - 1).hitbox = new HitBoxes(0, 0, 44, 0, 44, 44, 0, 44);
					entities.get(entities.size() - 1).type = 'a';
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "bd";
					} else {
						entities.get(entities.size() - 1).castType = "b";
					}
				} else {
					entities.add(new gameEntity(mainChar.currX - 5, mainChar.currY + 30, 0, -10, 1, slash.texture, (float) .25, (float) .1, 0, 5, 0, -47, 43, 0, 0, 42, 15));
					entities.get(entities.size() - 1).tRegion = new TextureRegion(entities.get(entities.size()-1).texture[0]);
					entities.get(entities.size() - 1).hitbox = new HitBoxes(0, 0, 44, 0, 33, 33, 0, 44);
					entities.get(entities.size() - 1).type = 'a';
					entities.get(entities.size() - 1).type = 'a';
					if(dash.dashTimer > 0) {
						entities.get(entities.size() - 1).castType = "bkd";
					} else {
						entities.get(entities.size() - 1).castType = "b";
					}
				}
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			if(currCasts.size() >= 1) {
			if(typeCheck('q', cooldowns)) {
				currCasts.get(0).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'q', true);
			} else {
				currCasts.get(0).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'q', false);
			}
				/*if(true) {
				cooldowns.add(new Cooldowns(electroWave.cooldown, 'q'));
				electroWave.makeCast(mainChar, entities, graphicWidth, graphicHeight);
				} else if(false) {
				cooldowns.add(new Cooldowns(flameSpiral.cooldown, 'q'));
				flameSpiral.makeCast(mainChar, entities, graphicWidth, graphicHeight);
				} else {
				cooldowns.add(new Cooldowns(iceHeal.cooldown, 'q'));
				iceHeal.makeCast(mainChar, entities, graphicWidth, graphicHeight);
			}
		}*/
		}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			if(currCasts.size() >= 2) {
				if(typeCheck('e', cooldowns)) {
					currCasts.get(1).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'e', true);
				} else {
					currCasts.get(1).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'e', false);
				}
				/*cooldowns.add(new Cooldowns(electroBurst.cooldown, 'e'));
				electroBurst.makeCast(mainChar, entities, graphicWidth, graphicHeight, 3);
				mainChar.channels[0] = new Channel(8, 4);
				channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
			} else if(!typeCheck('e', cooldowns) && mainChar.channels[0].currChannel > mainChar.channels[0].startChannel && mainChar.channels[0].currChannel < mainChar.channels[0].finChannel){
				mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
				for(int i = 0; i < entities.size(); i++) {
					if(entities.get(i).commands.type.equals("electroBurst")) {
						entities.remove(i);
						i--;
					}
				}
				electroBurst.makeCast(mainChar, entities, graphicWidth, graphicHeight, 1);
				/*cooldowns.add(new Cooldowns(iceArrow.cooldown, 'e'));
				iceArrow.makeCast(mainChar, entities, graphicWidth, graphicHeight);*/
		}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			if(currCasts.size() >= 3) {
			if(typeCheck('r', cooldowns)) {
				currCasts.get(2).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', true);
			} else {
				currCasts.get(2).makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', false);
			}
				/*
				//cooldowns.add(new Cooldowns(iceSpike.cooldown, 'r'));
				//iceSpike.makeCast(mainChar, entities, graphicWidth, graphicHeight);
				//cooldowns.add(new Cooldowns(flameExplosion.cooldown, 'r'));
				//mainChar.channels[0] = new Channel(6, 3);
				//channelEntities.add(new gameEntity(graphicWidth / 2, graphicHeight / 2, 0, 0, 0, 0));
				cooldowns.add(new Cooldowns(electroBolt.cooldown, 'r'));
				electroBolt.makeCast(mainChar, entities, graphicWidth, graphicHeight);
			}// else if(!typeCheck('r', cooldowns) && mainChar.channels[0].currChannel > mainChar.channels[0].startChannel && mainChar.channels[0].currChannel < mainChar.channels[0].finChannel) {
				//flameExplosion.makeCast(mainChar, entities, graphicWidth, graphicHeight);
				//mainChar.channels[0].currChannel = mainChar.channels[0].finChannel;
			//} */
		}
		}
		for(int i = 0; i < monsters.size(); i++) {
			monsters.get(i).monsterCheck(monsters);
		}
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).knockBackTimer > 0) {
				monsters.get(i).knocked(mainChar, monsters);
			} else {
				if(monsters.get(i).monstType.equals("skeleton")) {
					monsters.get(i).pathToMain(mainChar);
				} else if(monsters.get(i).monstType.equals("skelmage")) {
					monsters.get(i).pathAwayDistance(mainChar, graphicWidth, graphicHeight);
				} else if(monsters.get(i).monstType.equals("skelTank")) {
					monsters.get(i).pathToMain(mainChar);
				} else if(monsters.get(i).monstType.equals("skelWizard")) {
					monsters.get(i).pathAwayDistance(mainChar, graphicWidth, graphicHeight);
				} else if(monsters.get(i).monstType.equals("skelKing")) {
					monsters.get(i).pathToMain(mainChar);
			}
		}
		}
		
		
		//Drawing Begins here
		
		spriteBatch.begin();
		monstAttack();
		spriteBatch.draw(texture[0], (float)0, (float)0, graphicWidth, graphicHeight);
		for(int i = 0; i < otherEntities.size(); i++) {
			if(otherEntities.get(i).commands.turn <= 0 && otherEntities.get(i).commands.stillTurn <= 0) {
				System.out.println("removed");
				otherEntities.remove(i);
				i--;
			} else {
				spriteBatch.draw(otherEntities.get(i).texture[otherEntities.get(i).textNum], otherEntities.get(i).currX, otherEntities.get(i).currY, otherEntities.get(i).sizeX, otherEntities.get(i).sizeY);
				otherEntities.get(i).valueChange();
			}
		}
		spriteBatch.draw(hpBar.texture[1], 2, 10, 266 * (mainChar.health / 100), 10);
		spriteBatch.draw(hpBar.texture[3], 266 * (mainChar.health / 100) + 2, 10, 266 * (mainChar.armour / 100), 10);
		spriteBatch.draw(level.expBar, 2, 6, 266 * (((float)level.EXP) / level.EXPNeeded), 4);
		font.getData().setScale(1);
		font.draw(spriteBatch, "Round: " + round.round, 2, graphicHeight - 4);
		if(mainChar.channels[0].currChannel > 0) {
			spriteBatch.draw(groundChannel.texture[0], (mainChar.currX - 15) + ((float) groundChannel.turn) / 2, mainChar.currY - 4, 60 - groundChannel.turn, 10);
			groundChannel.turnUpdate();
		}
		font.getData().setScale((float) .6);
		for(int i = 0; i < monsters.size(); i++) {
			spriteBatch.draw(monsters.get(i).texture[monsters.get(i).textNum], monsters.get(i).currX, monsters.get(i).currY, monsters.get(i).sizeX, monsters.get(i).sizeY);
			if(monsters.get(i).vulnTimer > 0) {
				spriteBatch.draw(hpBar.texture[2], monsters.get(i).currX, monsters.get(i).currY - 5, monsters.get(i).hitbox.hitboxes[2] * (monsters.get(i).health / monsters.get(i).totalHealth), 5);
			} else {
				spriteBatch.draw(hpBar.texture[0], monsters.get(i).currX, monsters.get(i).currY - 5, monsters.get(i).hitbox.hitboxes[2] * (monsters.get(i).health / monsters.get(i).totalHealth), 5);
			}
			if(monsters.get(i).buffs[0][0] > 0) {
				spriteBatch.draw(monsters.get(i).buffText[0], monsters.get(i).currX - 5, monsters.get(i).currY + monsters.get(i).sizeY - 2, 8, 8);
			}
			if(monsters.get(i).damageCounter[0] > 0) {
				font.draw(spriteBatch, (int)monsters.get(i).damageCounter[1] + "", monsters.get(i).currX + monsters.get(i).sizeX - 2, monsters.get(i).currY + monsters.get(i).sizeY - 4);
			}
		}
		spriteBatch.draw(mainChar.texture[mainChar.textNum], mainChar.currX + mainChar.offSet, mainChar.currY, mainChar.sizeX, mainChar.sizeY);
		boolean repeatCheck = true;
		for(int i = 0; i < entities.size(); i++) {
			if (entities.get(i).commands.turn < 0) {
				if(entities.get(i).commands.type.equals("electroBurst") && electroBurst.stage == 3 && repeatCheck) {
					electroBurst.makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', true);
					repeatCheck = false;
				}
			} else {	
				if(entities.get(i).commands.permaRotate == true) {
					entities.get(i).tRegion = new TextureRegion(entities.get(i).texture[entities.get(i).textNum], 0, 0, (int) entities.get(i).commands.finX, (int) entities.get(i).commands.finY);
					spriteBatch.draw(entities.get(i).tRegion, mainChar.currX - entities.get(i).sizeX / 2 + 15, mainChar.currY - entities.get(i).sizeY / 2 + 25, entities.get(i).sizeX / 2, entities.get(i).sizeY / 2, entities.get(i).sizeX, entities.get(i).sizeY, (float)1, (float)1, (float)entities.get(i).commands.turn * 540, true);
				}else if(entities.get(i).commands.finX == 0 && entities.get(i).commands.finY == 0 && entities.get(i).commands.rotation.angle == 0 && entities.get(i).commands.distance == 0) {
					spriteBatch.draw(entities.get(i).texture[entities.get(i).textNum], entities.get(i).currX, entities.get(i).currY, entities.get(i).sizeX, entities.get(i).sizeY);
				}else if(entities.get(i).commands.rotation.angle == 0 && entities.get(i).commands.distance == 0){
					entities.get(i).tRegion.setRegionWidth((int) entities.get(i).commands.startX);
					entities.get(i).tRegion.setRegionHeight((int) entities.get(i).commands.startY);
					spriteBatch.draw(entities.get(i).tRegion, entities.get(i).currX, entities.get(i).currY, entities.get(i).sizeX + entities.get(i).commands.startX, entities.get(i).sizeY + entities.get(i).commands.startY);
				} else if(entities.get(i).commands.distance == 0 && !inArray(rotateList, entities.get(i).commands.type)){
					/*entities.get(i).tRegion.setRegionWidth((int) entities.get(i).commands.startX);
					entities.get(i).tRegion.setRegionHeight((int) entities.get(i).commands.startY);*/
					
					if(entities.get(i).commands.finY - entities.get(i).commands.startY >= -1) {
					entities.get(i).tRegion = new TextureRegion(entities.get(i).texture[entities.get(i).textNum], 0, (int)entities.get(i).commands.finY - (int) entities.get(i).commands.startY, (int) entities.get(i).commands.startX, (int) entities.get(i).commands.startY);
					} else {
						entities.get(i).tRegion = new TextureRegion(entities.get(i).texture[entities.get(i).textNum], 0, (int) entities.get(i).commands.startY, (int) entities.get(i).commands.startX, (int) entities.get(i).commands.startY);
					}
					spriteBatch.draw(entities.get(i).tRegion, (float)((entities.get(i).currX) + Math.cos(entities.get(i).commands.rotation.angle / 180 * Math.PI) * (entities.get(i).sizeX + entities.get(i).commands.startX)), (float)((entities.get(i).currY) - Math.sin(entities.get(i).commands.rotation.angle / 180 * Math.PI) * ( entities.get(i).sizeY + entities.get(i).commands.startY)), entities.get(i).commands.rotation.srcX, entities.get(i).commands.rotation.srcY, entities.get(i).sizeX + entities.get(i).commands.startX, entities.get(i).sizeY + entities.get(i).commands.startY, 1, 1, entities.get(i).commands.rotation.angle - 180, false);
				} else if(entities.get(i).commands.distance == 0 && !entities.get(i).commands.type.equals("Arrow")) {
					System.out.println("In draw Stage");
					entities.get(i).tRegion = new TextureRegion(entities.get(i).texture[entities.get(i).textNum], 0, (int)entities.get(i).commands.finY - (int) entities.get(i).commands.startY, (int) entities.get(i).commands.startX, (int) entities.get(i).commands.startY);
					spriteBatch.draw(entities.get(i).tRegion, (float)((entities.get(i).currX) + Math.cos(entities.get(i).commands.rotation.angle / 180 * Math.PI) * (entities.get(i).sizeY + entities.get(i).commands.startY * 1.15/* + entities.get(i).commands.startX*/)), (float)((entities.get(i).currY) + Math.sin(entities.get(i).commands.rotation.angle / 180 * Math.PI) * (entities.get(i).sizeY + entities.get(i).commands.startY * 1.15)), entities.get(i).commands.rotation.srcX, entities.get(i).commands.rotation.srcY, entities.get(i).sizeX/* + entities.get(i).commands.stSartX*/, entities.get(i).sizeY/* + entities.get(i).commands.startY*/, 1, 1, entities.get(i).commands.rotation.angle - 180, false);
				} else if(entities.get(i).commands.distance == 0){
					
					entities.get(i).tRegion = new TextureRegion(entities.get(i).texture[entities.get(i).textNum], 0, (int)entities.get(i).commands.finY - (int) entities.get(i).commands.startY, (int) entities.get(i).commands.startX, (int) entities.get(i).commands.startY);
					spriteBatch.draw(entities.get(i).tRegion, entities.get(i).currX, entities.get(i).currY, entities.get(i).commands.rotation.srcX, entities.get(i).commands.rotation.srcY, entities.get(i).sizeX + entities.get(i).commands.startX, entities.get(i).sizeY + entities.get(i).commands.startY, 1, 1, entities.get(i).commands.rotation.angle - 90, false);
				} else{
					
					//System.out.println("X: "+((mainChar.currX + 15) + (entities.get(i).commands.distance - entities.get(i).commands.distance * (((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn * entities.get(i)))) * Math.cos((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn)))) + "Y: " + ((mainChar.currY + 25) + (entities.get(i).commands.distance - entities.get(i).commands.distance * (((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn))) * Math.sin((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn)))));
					spriteBatch.draw(entities.get(i).texture[entities.get(i).textNum], (float)((mainChar.currX + 5) + (entities.get(i).commands.distance + entities.get(i).commands.changeInDist * (((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn * entities.get(i).commands.distChange))) * Math.cos(entities.get(i).speed * ((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn))) - Math.PI  / 4 * entities.get(i).commands.circTurn)) + (float)(entities.get(i).commands.finX* Math.cos(entities.get(i).speed * ((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn))) - Math.PI  / 4 * entities.get(i).commands.circTurn)), (float)((mainChar.currY + 20) + (entities.get(i).commands.distance + entities.get(i).commands.changeInDist * (((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn * entities.get(i).commands.distChange))) * Math.sin(entities.get(i).speed * ((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn))) - Math.PI  / 4 * entities.get(i).commands.circTurn)) + (float)(entities.get(i).commands.finY * Math.sin(entities.get(i).speed * ((2 * Math.PI) - (2 * Math.PI * ((entities.get(i).commands.totalTurn - entities.get(i).commands.turn) / entities.get(i).commands.totalTurn))) - Math.PI  / 4 * entities.get(i).commands.circTurn)), 20, 20);
				}
				entities.get(i).valueChange();
			}
		}
		for(int i = 0; i < attackEntities.size(); i++) {
			if (attackEntities.get(i).commands.turn < 0 && attackEntities.get(i).commands.stillTurn <= 0) {
				attackEntities.remove(i);
				i--;
			} else {
				if(attackEntities.get(i).commands.finX == 0 && attackEntities.get(i).commands.finY == 0 && attackEntities.get(i).commands.rotation.angle == 0) {
					spriteBatch.draw(attackEntities.get(i).texture[attackEntities.get(i).textNum], attackEntities.get(i).currX, attackEntities.get(i).currY, attackEntities.get(i).sizeX, attackEntities.get(i).sizeY);
				}else if(attackEntities.get(i).commands.rotation.angle == 0){
					attackEntities.get(i).tRegion.setRegionWidth((int) attackEntities.get(i).commands.startX);
					attackEntities.get(i).tRegion.setRegionHeight((int) attackEntities.get(i).commands.startY);
					spriteBatch.draw(attackEntities.get(i).tRegion, attackEntities.get(i).currX, attackEntities.get(i).currY, attackEntities.get(i).sizeX + attackEntities.get(i).commands.startX, attackEntities.get(i).sizeY + attackEntities.get(i).commands.startY);
				} else if(attackEntities.get(i).commands.finX == 0 && attackEntities.get(i).commands.finY == 0) {
					attackEntities.get(i).tRegion.setRegionWidth((int) attackEntities.get(i).commands.startX);
					attackEntities.get(i).tRegion.setRegionHeight((int) attackEntities.get(i).commands.startY);
					spriteBatch.draw(attackEntities.get(i).tRegion, (float)((attackEntities.get(i).currX) + Math.cos(attackEntities.get(i).commands.rotation.angle / 180 * Math.PI) * (attackEntities.get(i).sizeX + attackEntities.get(i).commands.startX)), (float)((attackEntities.get(i).currY) - Math.sin(attackEntities.get(i).commands.rotation.angle / 180 * Math.PI) * ( attackEntities.get(i).sizeY + attackEntities.get(i).commands.startY)), attackEntities.get(i).sizeX / 2, attackEntities.get(i).sizeY / 2, attackEntities.get(i).sizeX, attackEntities.get(i).sizeY, 1, 1, attackEntities.get(i).commands.rotation.angle - 180, false);
				}
				attackEntities.get(i).valueChange();
			}
		}
		for(int i = 0; i < channelEntities.size(); i++) {
			if(mainChar.channels[0].currChannel > mainChar.channels[0].startChannel){
				spriteBatch.draw(mainChar.texture[4], (float)(graphicWidth / 2 - 37.5), 50, 75 - 75 * ((mainChar.channels[0].currChannel - mainChar.channels[0].startChannel) % (mainChar.channels[0].finChannel - mainChar.channels[0].startChannel)) / (mainChar.channels[0].finChannel - mainChar.channels[0].startChannel), 10);
			} else if(mainChar.channels[0].currChannel < mainChar.channels[0].finChannel){
				spriteBatch.draw(mainChar.texture[4], (float)(graphicWidth / 2 - 37.5), 50, 75 * (mainChar.channels[0].currChannel) / mainChar.channels[0].startChannel, 10);
			}
		}
		font.getData().setScale((float).6);
		for(int i = 0; i < 5; i++) {
			char temp[] = new char[]{'d', 'q', 'e', 'r', 'b'};
			spriteBatch.draw(emblem.subText[i], Gdx.graphics.getWidth() * graphicWidth / Gdx.graphics.getWidth() / 2 + 25 * i - 50, 5, 20, 20);
			if(!typeCheck(temp[i], cooldowns) && cooldowns.size() > 0) {
				TextureRegion tempText = new TextureRegion(emblem.mainText[9], 0, 0, 40, (int)(40 - 40 * getType(cooldowns, temp[i])));
				spriteBatch.draw(tempText, Gdx.graphics.getWidth() * graphicWidth / Gdx.graphics.getWidth() / 2 + 25 * i - 50, 5 + 20 * getType(cooldowns, temp[i]), 20, 20 - 20 * getType(cooldowns, temp[i]));	
			}
			if(i < currCasts.size() + 1 && i > 0 && currCasts.get(i - 1).charges > 0) {
				font.draw(spriteBatch, currCasts.get(i - 1).currCharge + "", Gdx.graphics.getWidth() * graphicWidth / Gdx.graphics.getWidth() / 2 + 25 * i - 35, 13);
			}
		}
		for(int i = 0; i < 4; i++) {
			spriteBatch.draw(emblem.subBuffText[i], graphicWidth - 25 * (i + 1), graphicHeight - 25, 20, 20);
		}
		spriteBatch.end();
		update();
		checkForHits();
		checkMonstHP();
		clean();
		if(mainChar.channels[0] != null && mainChar.channels[0].currChannel < mainChar.channels[0].finChannel && mainChar.channels[0].currChannel >=0) {
		mainChar.channels[0].currChannel += Gdx.graphics.getDeltaTime();
		} else if(mainChar.channels[0].finChannel != 0 && mainChar.channels[0].currChannel >= mainChar.channels[0].finChannel) {
			mainChar.channels[0].currChannel = -1;
			mainChar.channels[0].finChannel = 0;
			channelEntities.remove(0);
		}
        } else {
        	spriteBatch.begin();
        	spriteBatch.draw(texture[1], 0, 0, graphicWidth, graphicHeight);
        	if(level.turn <= 0) {
        	level.level(rando, spriteBatch, currCasts, totalCasts, buffs, emblem);
        	if(Gdx.input.isTouched()) {
        		level.levelInput(Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth(), (Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight(), currCasts, totalCasts, buffs, emblem, mainChar, dash, block);
        	}
        	} else {
        		level.turn -= Gdx.graphics.getDeltaTime();
        	}
        	spriteBatch.end();
        }
	}
	
	
	public boolean mainDiag(){
		float tempYInt = mainChar.currY + mainChar.currX + 25;
		if((Gdx.graphics.getHeight() - Gdx.input.getY()) * graphicHeight / Gdx.graphics.getHeight() >= tempYInt - Gdx.input.getX() * graphicWidth / Gdx.graphics.getWidth()) {
			return true;
		}
		return false;
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		for(int i = 0; i < texture.length; i++) {
		texture[i].dispose();
		}
		for(int i = 0; i < 4; i++) {
			mainChar.texture[i].dispose();
		}
		for(int i = 0; i < 14; i++) {
			skeleton.texture[i].dispose();
		}
		for(int i = 0; i < 2; i++) {
			slash.texture[i].dispose();
		}
		for(int i = 0; i < emblem.mainText.length; i++) {
			emblem.mainText[i].dispose();
		}
		for(int i = 0; i < emblem.buffText.length; i++) {
			emblem.buffText[i].dispose();
		}
		for(int i = 0; i < hpBar.texture.length; i++) {
			hpBar.texture[i].dispose();
		}
		for(int i = 0; i < electroBolt.texture.length; i++) {
			electroBolt.texture[i].dispose();
		}
		for(int i = 0; i < 6; i++) {
			skelTank.texture[i].dispose();
		}
		dash.texture[0].dispose();
		electroWave.texture[0].dispose();
		electroWave.texture[1].dispose();
		electroBurst.texture[0].dispose();
		flameSpiral.texture[0].dispose();
		flameExplosion.texture[0].dispose();
		iceArrow.texture[0].dispose();
		iceHeal.texture[0].dispose();
		iceSpike.texture[0].dispose();
		block.texture[0].dispose();
		Texture temp = new Texture(Gdx.files.internal("Images/Skeleton/Shield.png"));
		temp.dispose();
	}
	
	public void update() {
		if(dash.dashTimer > 0) {
			dash.dashTimer -= Gdx.graphics.getDeltaTime();
		}
		for(int i = 0; i < cooldowns.size(); i++) {
			cooldowns.get(i).seconds -= Gdx.graphics.getDeltaTime();
			if(cooldowns.get(i).seconds <= 0) {
				if(cooldowns.get(i).type == 'q' && currCasts.get(0).currCharge < currCasts.get(0).charges) {
					currCasts.get(0).currCharge++;
				} else if(cooldowns.get(i).type == 'e' && currCasts.get(1).currCharge < currCasts.get(1).charges) {
					currCasts.get(1).currCharge++;
				} else if(cooldowns.get(i).type == 'r' && currCasts.get(2).currCharge < currCasts.get(2).charges) {
					currCasts.get(2).currCharge++;
				}
				cooldowns.remove(i);
				i--;
			}
		}
	}
	public void clean() {
		boolean repeatCheck = true;
		for(int i = 0; i < electroBolt.timers.size(); i++) {
				electroBolt.timers.set(i, electroBolt.timers.get(i) - Gdx.graphics.getDeltaTime());
		}
		for(int i = 0; i < flameSpiral.timers.size(); i++) {
			flameSpiral.timers.set(i, flameSpiral.timers.get(i) - Gdx.graphics.getDeltaTime());
			System.out.println("time value: " + flameSpiral.timers.get(i));
		}
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).commands.turn <= 0 && entities.get(i).commands.type.equals("electroBurst") && electroBurst.stage == 2) {
				entities.remove(i);
				i--;
				if(repeatCheck) {
					System.out.println("electro here. Stage: " + electroBurst.stage);
				electroBurst.makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', true);
				repeatCheck = false;
				}
		} else if (entities.get(i).commands.turn <= 0 && entities.get(i).commands.type.equals("electroBurst") && entities.get(i).commands.stage == 2){
			entities.remove(i);
			i--;
			electroBurst.stage = 3;
		}else if(entities.get(i).commands.type.equals("flameSpiral") && entities.get(i).commands.turn <= 0 && (flameSpiral.timers.size() > 0 && flameSpiral.timers.get(0) <= 0)) {
			System.out.println("In sprial stage 2");
			flameSpiral.makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', true);
		}else if(entities.get(i).commands.type.equals("electroBolt") && entities.get(i).commands.turn <= 0 && ((electroBolt.levelTree[0] == 2 && electroBolt.levelTree[1] != 2 && electroBolt.stage == 2) || (electroBolt.levelTree[0] == 2 && electroBolt.levelTree[1] == 2))) {
			System.out.println("Stage 2");
			entities.remove(i);
			i--;
			electroBolt.makeCast(mainChar, entities, graphicWidth, graphicHeight, cooldowns, channelEntities, dash, buffs, 'r', true);
		}else if(entities.get(i).commands.turn <= 0) {
				entities.remove(i);
				i--;
			}
		}
		mainChar.cleanBuffs();
	}
	
	public void checkForHits() {
		for(int i = 0; i < entities.size(); i++) {
			for(int j = 0; j < monsters.size(); j++) {
				if(entities.get(i).type == 'a') {
					entities.get(i).hitbox.isHit(monsters.get(j), entities.get(i), mainChar, buffs);
				}
			}
		}
		for(int i = 0; i < attackEntities.size(); i++) {
			attackEntities.get(i).hitbox.isHit(mainChar, attackEntities.get(i), block, buffs);
			mainChar.xDir = 0;
			mainChar.yDir = 0;
		}
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).type == 'd') {
				monsters.get(i).hitbox.isHit(mainChar, monsters.get(i), block, buffs);
			}
		}
		block.clean(cooldowns);
	}
	
	boolean typeCheck(char type, ArrayList<Cooldowns> cooldowns) {
		if(mainChar.channels[0].finChannel > 0 && type != 'd' && type != 'b') {
			return false;
		}
		for(int i = 0; i < cooldowns.size(); i++) {
			if(cooldowns.get(i).type == 't' && type == 'q') {
				return false;
			} else if(cooldowns.get(i).type == 'y' && type == 'e') {
				return false;
			} else if(cooldowns.get(i).type == 'u' && type == 'r') {
				return false;
			}
		}
		for(int i = 0; i < cooldowns.size(); i++) {
			if(cooldowns.get(i).type == type && type == 'q' && currCasts.get(0).currCharge <= 0) {
				return false;
			} else if(cooldowns.get(i).type == type && type == 'e' && currCasts.get(1).currCharge <= 0) {
				return false;
			}else if(cooldowns.get(i).type == type && type == 'r' && currCasts.get(2).currCharge <= 0) {
				return false;
			} else if(cooldowns.get(i).type == type && (type != 'r' && type != 'e' && type != 'q')){
				return false;
			}
		}
		return true;
	}


	public void checkMonstHP() {
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).hurtTimer > 0) {
				monsters.get(i).hurtTimer -= Gdx.graphics.getDeltaTime();
				if(monsters.get(i).hurtTimer <= 0) {
					monsters.get(i).textNum = 0;
				}
			}
			if(monsters.get(i).attacks[3] > 0) {
				monsters.get(i).attacks[3] -= Gdx.graphics.getDeltaTime();
			}
			if(monsters.get(i).health <= 0) {
				monsters.remove(i);
				level.EXP += 10;
				i--;
			}
		}
		if(monsters.size() == 0) {
			if(round.roundTimer == -1) {
				round.roundTimer = 2;
			} else {
				round.makeRound(monsters, skeleton, skelmage, skelTank, skelWizard, skelKing, totalCasts, rando, mainChar, graphicWidth, graphicHeight, level);
			}
		}
	}
	
	public void monstAttack() {
		for(int i = 0; i < monsters.size(); i++) {
			if(monsters.get(i).monstType.equals("skeleton")) {
				skeleton.attackPattern(mainChar, monsters.get(i), spriteBatch, attackEntities, slash, graphicWidth, graphicHeight);
			} else if (monsters.get(i).monstType.equals("skelmage")) {
				skelmage.attackPattern(mainChar, monsters.get(i), spriteBatch, attackEntities, slash, graphicWidth, graphicHeight, rando, flameExplosion);
			} else if(monsters.get(i).monstType.equals("skelTank")) {
				skelTank.attackPattern(mainChar, monsters.get(i), spriteBatch, monsters, attackEntities, slash, graphicWidth, graphicHeight);
			} else if(monsters.get(i).monstType.equals("skelWizard")) {
				skelWizard.attackPattern(mainChar, monsters.get(i), spriteBatch, attackEntities, otherEntities, slash, graphicWidth, graphicHeight, rando, flameExplosion);
			} else if(monsters.get(i).monstType.equals("skelKing")) {
				skelKing.attackPattern(mainChar, monsters.get(i), spriteBatch, attackEntities, otherEntities, slash, rando, graphicWidth, graphicHeight, skelWizard, monsters);
			}
		}
	}
	
	public float getType(ArrayList<Cooldowns> cooldowns, char target) {
		for(int i = 0; i < cooldowns.size(); i++) {
			if(cooldowns.get(i).type == 't' && target == 'q' && currCasts.get(0).currCharge > 0) {
				return (float)((.25 - cooldowns.get(i).seconds) / .25);
			} else if(cooldowns.get(i).type == 'y' && target == 'e' && currCasts.get(1).currCharge > 0) {
				return (float)((.25 - cooldowns.get(i).seconds) / .25);
			} else if(cooldowns.get(i).type == 'u' && target == 'r' && currCasts.get(2).currCharge > 0) {
				return (float)((.25 - cooldowns.get(i).seconds) / .25);
			}
		}
		for(int i = 0; i < cooldowns.size(); i++) {
			if(cooldowns.get(i).type == target) {
				return (cooldowns.get(i).totalSec - cooldowns.get(i).seconds) / cooldowns.get(i).totalSec;
			}
		}
		return 0;
	}
	
	public boolean inArray(String[] array, String comparison) {
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(comparison)) {
				return true;
			}
		}
		return false;
	}
}
