package com.multitask.dannystover;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Spawner {
		
	private Updater updater;
	//counter from this class to keep track of when to spawn objects
	private int stageTimer = 0, stage2Timer =0, stage3Timer=0, stage4Timer = 0, stage4VelocityXTimer = 0, stage4VelocityYTimer = 0;
	
	//access to ScoreTracker to this class can access the current stage
	private ScoreTracker scoreTracker;
	//each image for each player/enemy
	private BufferedImage meteor1, player1, player2, asteroid1, asteroid2, asteroid3, asteroid4, asteroid5, asteroid6, donut, sonic;
	public Stage1Player p;
	public Stage2Player p2;
	public int stage1Width, stage1Height;
	public int stage2Width, stage2Height;
	
	public float[] meteorWidthChoices = {126.4f, 143.6f, 158, 173.8f, 197.5f};
	public float[] meteorHeightChoices = {180, 204.54f, 225, 247.5f, 281.25f};
	
	public BufferedImage[] asteroids;
	public float[] asteroidWidthChoices = {142.35f, 124, 176.3f, 154.3f, 150, 150};
	public float[] asteroidHeightChoices = {130.7f, 123.1f, 132.24f, 125.7f, 150, 125};
			private Random r = new Random();
	
	public Color[] objectColors = {Color.red, Color.red, Color.red, Color.white, Color.green, Color.pink, Color.black, Color.lightGray, Color.yellow, Color.orange};
	
	private int timerVal = 50;
	private int timer2Val = 40;
	private int timer3Val = 20;
	private int timer4Val = 200;
	private int timer4VelX = 30;
	private int timer4VelY = 20;
	public boolean[] havePlayers = {false, false, false, false};
	public int nextStage = 2;
	private int firstStage3 = 0;
	
	public Spawner(Updater u, ScoreTracker st) {
		updater = u;
		scoreTracker = st;
		//images 
		try {
		meteor1 = ImageIO.read(new File("MeteorS1.png"));
		player1 = ImageIO.read(new File("stage1player final.png"));
		player2 = ImageIO.read(new File("stage2player final.png"));
		asteroid1 = ImageIO.read(new File("stage2asteroid1.png"));
		asteroid2 = ImageIO.read(new File("stage2asteroid2.png"));
		asteroid3 = ImageIO.read(new File("stage2asteroid3.png"));
		asteroid4 = ImageIO.read(new File("stage2asteroid4.png"));
		asteroid5 = ImageIO.read(new File("stage2asteroid5.png"));
		asteroid6 = ImageIO.read(new File("stage2asteroid6.png"));
		donut = ImageIO.read(new File("pngdonutface.png"));
		sonic = ImageIO.read(new File("sonic.png"));
		}
		catch(Exception e) {
			System.out.println("Stage1 Meteor image not read properly");
			e.printStackTrace();
		}
		stage1Width = 1920;
		stage1Height = 1080;
		
		//asteroids array contains each image to randomly generate via index
		asteroids = new BufferedImage[6];
		asteroids[0] = asteroid1;
		asteroids[1] = asteroid2;
		asteroids[2] = asteroid3;
		asteroids[3] = asteroid4;
		asteroids[4] = asteroid5;
		asteroids[5] = asteroid6;
	}
	
	public void update() {
		//creation of player 1
		if(!havePlayers[0]) {
			if(Menu.freakyFast)
				p = new Stage1Player(stage1Width/2 - 125, stage1Height - 254, 300, 250, sonic, "Stage1Player", this, updater, scoreTracker);
			else if(!Menu.freakyFast)
				p = new Stage1Player(stage1Width/2 - 125, stage1Height - 254, 300, 250, player1, "Stage1Player", this, updater, scoreTracker);
			updater.addStageObject(p);
			havePlayers[0] = true;
		}
		stageTimer++;
	
		if(stageTimer >= timerVal) {
			stageTimer = 0;
				float xValue = r.nextInt(stage1Width+20)-10;
				xValue = Multitask.setBounds(xValue, -20, stage1Width + 20);
				//random number from 0 to 4
				int widthHeightIndex = r.nextInt(5);
				if(scoreTracker.getStage() == 1)
					timerVal = r.nextInt(31)+15;
				else 
					timerVal = r.nextInt(26)+50;
				updater.addStageObject(new Stage1Enemies(xValue, -150, meteorWidthChoices[widthHeightIndex], meteorHeightChoices[widthHeightIndex], meteor1, "Stage1Enemy", this, updater));
			}
		
		if(scoreTracker.getStage() != 1) {
			stage2Timer++;
			//transition between stage 1 and 2
			if(nextStage == 2) {
				stage1Width = 960;
				stage2Width = stage1Width;
				stage2Height = stage1Height;
				updater.removeStageObject(p);
				updater.removeAllStage1Enemies();
				havePlayers[0] = false;

				nextStage = 3;
			}
			//creation of player 2
			if(!havePlayers[1]) {
				p2 = new Stage2Player(Multitask.WIDTH/2 + 10, stage1Height/2, 300, 250, player2, "Stage2Player", this, updater, scoreTracker);
				updater.addStageObject(p2);
				havePlayers[1] = true;
			}
			
			//stage 2's objects
			if(stage2Timer >= timer2Val) {
				stage2Timer = 0;
				float yValue = r.nextInt(stage2Height+20)-10;
				yValue = Multitask.setBounds(yValue, -10, stage2Height + 10);
				//random number from 0 to 5 for asteroid picture
				int randomAsteroid = r.nextInt(6);
				timer2Val = r.nextInt(26)+50;
				updater.addStageObject(new Stage2Enemies(stage2Width*2 + 200, yValue, asteroidWidthChoices[randomAsteroid], asteroidHeightChoices[randomAsteroid], asteroids[randomAsteroid], "Stage2Enemy", updater, this, scoreTracker));
			}
		}
		
		if(scoreTracker.getStage() != 1 && scoreTracker.getStage() != 2) {
			stage3Timer++;
			if(stage3Timer >= timer3Val) {
				stage3Timer = 0;
					float width = r.nextInt(21)+10;
					float height = r.nextInt(51)+75;
					//random number from 0 to 4
					if(timer3Val == 20) {
						timer3Val = r.nextInt(20)+30;
					}
					else {
						timer3Val = r.nextInt(101)+150;
						firstStage3 = 1;
					}
					int randomColorIndex = r.nextInt(10);
					if(firstStage3 == 1)
						updater.addStageObject(new Stage3Enemies(stage1Width, stage1Height-height, width, height, "Stage3Enemy", objectColors[randomColorIndex], updater, scoreTracker));

			}
		}
		
		if(scoreTracker.getStage() == 4) {
			stage4Timer++;
			stage4VelocityXTimer++;
			stage4VelocityYTimer++;
			if(stage4Timer >= timer4Val) {
				stage4Timer = 0;
					//random number from 0 to 4
					timer4Val = r.nextInt(301)+200;
					int randomXLocation = r.nextInt(Multitask.WIDTH);
					int randomYLocation = r.nextInt(Multitask.HEIGHT);
					
					updater.addStageObject(new Stage4Enemies(randomXLocation, randomYLocation, 50, 50, donut, "Stage4Enemy", updater, scoreTracker));
				}
			if(stage4VelocityXTimer >= timer4VelX) {
				stage4VelocityXTimer = 0;
				timer4VelX = r.nextInt(21)+10;
				for(int i = 0; i < updater.allStageObjects.size(); i++) {
					if(updater.allStageObjects.get(i).getRole().equals("Stage4Enemy")) {
						updater.allStageObjects.get(i).randomizeVelX();
					}
				}
			}
			if(stage4VelocityYTimer >= timer4VelY) {
				stage4VelocityYTimer = 0;
				timer4VelY = r.nextInt(21)+10;
				for(int i = 0; i < updater.allStageObjects.size(); i++) {
					if(updater.allStageObjects.get(i).getRole().equals("Stage4Enemy")) {
						updater.allStageObjects.get(i).randomizeVelY();
					}
				}
			}
		}
			
		/*
		if(scoreTracker.getStage() != 1 && scoreTracker.getStage() != 2) {
			//transition from stage 2 to stage 3
			if(nextStage == 3) {
				stage1Height = 540;
				stage2Height = 540;
				stage3Height = 540;
				stage3Width = 1920;
				updater.removeStageObject(p);
				havePlayers[0] = false;
				updater.removeStageObject(p2);
				havePlayers[1] = false;
				updater.removeAllStage1Enemies();
				updater.removeAllStage2Enemies();
				
				
				nextStage = 4;
			}
		
		}
		*/
	}
			

		
		
		
	}


