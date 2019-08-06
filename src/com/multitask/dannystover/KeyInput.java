package com.multitask.dannystover;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	//handler keeps track of each object in game, used here to loop through objects and make sure
	//key input only works on those with Player ID so we don't control the enemies too
	private Updater updater;
	private Multitask mainGame;
	//tells whether the key ([0]W [1]S [2]A [3]D) is down or not
	//used for smoother movement
	private boolean[] stage1PlayerKeyDown = {false, false};
	private boolean[] stage2PlayerKeyDown = {false, false};
	private ScoreTracker scoreTracker;
	
	public KeyInput(Updater u, Multitask game, ScoreTracker st) {
		updater = u;
		mainGame = game;
		scoreTracker = st;
		//true = key is pressed, false = not pressed

	}
	
	//stores key that is pressed and if it was left/right arrow, changes player's x velocity
	//velocity creates a smooth movement due to tick() method of GameObject
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//loops 
		for(int i = 0; i < updater.allStageObjects.size(); i++) {
			StageEntities temp = updater.allStageObjects.get(i);
			
			if(temp.getRole().equals("Stage1Player")) {
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					temp.setVelX(-5f);
					stage1PlayerKeyDown[0] = true;
				}
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					temp.setVelX(5f);
					stage1PlayerKeyDown[1] = true;
				}
					
				if(scoreTracker.getStage() != 1 && scoreTracker.getStage() != 2) {
					if(key == KeyEvent.VK_SPACE) {
						if(temp.getVelY() == 0)
							temp.setVelY(-100);
						if(!stage1PlayerKeyDown[0] && !stage1PlayerKeyDown[1]) {
							temp.setVelX(0);
						}
						else if(stage1PlayerKeyDown[1]) {
							temp.setVelX(5f);
						}
						else if(stage1PlayerKeyDown[0])
							temp.setVelX(-5f);
					}
				}
			}
			
			else if(temp.getRole().equals("Stage2Player")) {
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					temp.setVelY(-5f);
					stage2PlayerKeyDown[0] = true;
				}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					temp.setVelY(5f);
					stage2PlayerKeyDown[1] = true;
				}
			}
		
		}
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if(key == KeyEvent.VK_P) {
			mainGame.menuState = "pause";
		}
		
	}
	
	
	//when the specified key is released, changes velocity to 0
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		//loops through the list of objects in handler and only applies the change in position to those with player ID
		for(int i = 0; i < updater.allStageObjects.size(); i++) {
			StageEntities temp = updater.allStageObjects.get(i);
			
			if(temp.getRole().equals("Stage1Player")) {
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
					stage1PlayerKeyDown[0] = false;
					if(stage1PlayerKeyDown[1])
						temp.setVelX(5f);
					else
						temp.setVelX(0f);
				 
				}
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
					stage1PlayerKeyDown[1] = false; 
					if(stage1PlayerKeyDown[0])
						temp.setVelX(-5f);
					else
						temp.setVelX(0f);
				}
				
				//these are so that if keys are tapped rapidly, the player doesn't stop moving
				//horizontal stoppage of movement
				if(!stage1PlayerKeyDown[0] && !stage1PlayerKeyDown[1])
					temp.setVelX(0f);
			}
				

			
			else if(temp.getRole().equals("Stage2Player")) {
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
					stage2PlayerKeyDown[0] = false;
					if(stage2PlayerKeyDown[1])
						temp.setVelY(5f);
					else
						temp.setVelY(0f);
				}
				
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
					stage2PlayerKeyDown[1] = false;
					if(stage2PlayerKeyDown[0])
						temp.setVelY(-5f);
					else
						temp.setVelY(0f);
				}
				
				if(!stage2PlayerKeyDown[0] && !stage2PlayerKeyDown[1])
					temp.setVelY(0f);
			}
		
		}
			
		}
	}

