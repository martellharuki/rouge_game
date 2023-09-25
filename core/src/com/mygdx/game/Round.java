package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

public class Round {
	int round = 0;
	float roundTimer = 0;
	int typeOfMonst[];
	int locations[];
	
	public Round() {
		typeOfMonst = new int[4];
		locations = new int[4];
	}
	
	void makeRound(ArrayList<Monster> monsters, Skeleton skeleton, SkelMage skelmage, SkelTank skelTank, SkelWizard skelWizard, SkelKing skelKing, ArrayList<Casts> totalCasts, Random rando, MainCharacter mainChar, float graphicWidth, float graphicHeight, LevelUp level) {
		if(roundTimer <= 0) {
			System.out.println("in here");
			if(round < 9) {
				int numOfMonst = 0;
				if(round == 0) {
					numOfMonst = 2;
				} else if(round <= 4) {
					numOfMonst = 3 + rando.nextInt(3);
				} else {
					numOfMonst = 6 + rando.nextInt(3);
				}
				for(int i = 0; numOfMonst > 0; i ++) {
					int temp = rando.nextInt(2);
					if(temp > 0) {
						typeOfMonst[i % 2]++;
						numOfMonst--;
					}
				}
				for(int i = 0; typeOfMonst[0] > 0 || typeOfMonst[1] > 0; i++) {
					int temp = rando.nextInt(2);
					int tempLocation = rando.nextInt(4);
					while(locationCheck(tempLocation, graphicWidth, graphicHeight)) {
						tempLocation = rando.nextInt(4);
					}
					if(typeOfMonst[temp] > 0) {
						if(temp == 0) {
							skeleton.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
							typeOfMonst[temp]--;
							locations[tempLocation]++;
						} else if(temp == 1) {
							skelmage.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
							typeOfMonst[temp]--;
							locations[tempLocation]++;
						}
					}
				}
			} else if(round == 9) {
				typeOfMonst[0] = 5;
				typeOfMonst[1] = 4;
				typeOfMonst[2] = 1;
				for(int i = 0; typeOfMonst[0] > 0 || typeOfMonst[1] > 0 || typeOfMonst[2] > 0; i++) {
					
					int temp = rando.nextInt(3);
					int tempLocation = rando.nextInt(4);
					while(locationCheck(tempLocation, graphicWidth, graphicHeight)) {
						tempLocation = rando.nextInt(4);
					}
					if(typeOfMonst[temp] > 0) {
						if(temp == 0) {
							skeleton.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
							typeOfMonst[temp]--;
							locations[tempLocation]++;
						} else if(temp == 1) {
							skelmage.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
							typeOfMonst[temp]--;
							locations[tempLocation]++;
						}else if(temp == 2) {
							skelTank.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
							System.out.println("Made");
							typeOfMonst[temp]--;
							locations[tempLocation]++;
						}
					}
				}
					
				} else if(round < 15) {
					int numOfMonst = 4;
					for(int i = 0; numOfMonst > 0; i ++) {
						int temp = rando.nextInt(4);
						if(temp > 0) {
							typeOfMonst[i % 3]++;
							numOfMonst--;
						}
					}
					typeOfMonst[3] = 1;
					for(int i = 0; typeOfMonst[0] > 0 || typeOfMonst[1] > 0 || typeOfMonst[2] > 0 || typeOfMonst[3] > 0; i++) {
						int temp = rando.nextInt(4);
						System.out.println("IN Round 5 Here");
						int tempLocation = rando.nextInt(4);
						while(locationCheck(tempLocation, graphicWidth, graphicHeight)) {
							tempLocation = rando.nextInt(4);
						}
						if(typeOfMonst[temp] > 0) {
							if(temp == 0) {
								skeleton.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight), round);
								typeOfMonst[temp]--;
								locations[tempLocation]++;
							} else if(temp == 1) {
								skelmage.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight), round);
								typeOfMonst[temp]--;
								locations[tempLocation]++;
							} else if(temp == 2) {
								skelTank.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
								typeOfMonst[temp]--;
								locations[tempLocation]++;
							} else if(temp == 3) {
								skelWizard.makeMonst(monsters, locX(tempLocation, graphicWidth), locY(tempLocation, graphicHeight));
								typeOfMonst[temp]--;
								locations[tempLocation]++;
							}
						}
					}
			} else if(round == 15) {
				skelKing.makeMonst(monsters, graphicWidth / 2, graphicHeight - 130);
			}
			if(round > 0) {
			level.EXP += level.EXPNeeded / 2;
			}
			round++;
			roundTimer = -1;
		} else {
			roundTimer -= Gdx.graphics.getDeltaTime();
		}
		for(int i = 0; i < totalCasts.size(); i++) {
			totalCasts.get(i).healCast = totalCasts.get(i).totalHealCast;
		}
		mainChar.heal(mainChar.armour);
		locationClean();
	}
	
	boolean locationCheck(int tempLocation, float graphicWidth, float graphicHeight) {
		if(locations[tempLocation] * 40 + 10 > graphicHeight && tempLocation == 0) {
			return true;
		} else if(locations[tempLocation] * 40 + 60 > graphicWidth && tempLocation == 1) {
			return true;
		} else if(graphicWidth - locations[tempLocation] * 40 < 10 && tempLocation == 2) {
			return true;
		} else if(graphicHeight - locations[tempLocation] * 40 - 50 < 0 && tempLocation == 3) {
			return true;
		}
		return false;
	}
	
	float locX(int tempLocation, float graphicWidth) {
		if(tempLocation == 0) {
			return 5;
		} else if(tempLocation == 3) {
			return graphicWidth - 60;
		} else if(tempLocation == 1) {
			return 10 + locations[tempLocation] * 40;
		} else if(tempLocation == 2) {
			return graphicWidth - 40 * locations[tempLocation] - 60; 
		}
		return 0;
	}
	
	float locY(int tempLocation, float graphicHeight) {
		if(tempLocation == 0) {
			return 10 + locations[tempLocation] * 40;
		} else if(tempLocation == 3) {
			return graphicHeight - 40 * locations[tempLocation];
		} else if(tempLocation == 1) {
			return graphicHeight - 50;
		} else if(tempLocation == 2) {
			return 5; 
		}
		return 0;
	}
	
	void locationClean() {
		for(int i = 0; i < 4; i++) {
			locations[i] = 0;
		}
	}
}
