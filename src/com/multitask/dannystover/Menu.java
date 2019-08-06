package com.multitask.dannystover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Menu extends MouseAdapter{
	
	private Multitask multitask;
	private int menuTimer;
	private int timerVal = 50;
	Random r = new Random();
	private Updater updater;
	public float[] meteorWidthChoices = {126.4f, 143.6f, 158, 173.8f, 197.5f};
	public float[] meteorHeightChoices = {180, 204.54f, 225, 247.5f, 281.25f};
	private BufferedImage meteor1;
	private Spawner spawner;
	private ScoreTracker scoreTracker;
	public boolean clearMenuObjects = false;
	public static String howDied;
	public boolean menuSound = true;
	private Clip menuClip;
	public boolean fastSpeed = false;
	public static boolean freakyFast = false;
	
	public Menu(Multitask game, Updater upd, Spawner spawn, ScoreTracker st) {
		multitask = game;
		updater = upd;
		spawner = spawn;
		scoreTracker = st;
		
		try {
			meteor1 = ImageIO.read(new File("MeteorS1.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			File menuSong = new File("Menu music final.wav");
			AudioInputStream musicMenu = AudioSystem.getAudioInputStream(menuSong);
			menuClip = AudioSystem.getClip();
			menuClip.open(musicMenu);
			menuClip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(multitask.menuState.equals("menu")) {
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 200, 420, 100)) {
				multitask.menuState = "speed choose";
			}
		}
		
		if(multitask.menuState.equals("speed choose")) {
			
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 500, 420, 100)) {
				for(int i = 0; i < updater.allStageObjects.size(); i++) {
					updater.removeStageObject(updater.allStageObjects.get(i));
				}
				clearMenuObjects = true;
				multitask.menuState = "game";
		}
		
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 700, 420, 100)) {
				for(int i = 0; i < updater.allStageObjects.size(); i++) {
					updater.removeStageObject(updater.allStageObjects.get(i));
				}
				clearMenuObjects = true;
				multitask.menuState = "game";
				fastSpeed = true;
			}
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 900, 420, 100)) {
				for(int i = 0; i < updater.allStageObjects.size(); i++) {
					updater.removeStageObject(updater.allStageObjects.get(i));
				}
				clearMenuObjects = true;
				multitask.menuState = "game";
				fastSpeed = true;
				freakyFast = true;
			}
		}
		if(multitask.menuState == "game") {
			menuClip.stop();
			for(int i = 0; i < updater.allStageObjects.size(); i++) {
				StageEntities temp = updater.allStageObjects.get(i);
				
				if(temp.getRole().equals("Stage4Enemy")) {
					if(withinTarget(mouseX, mouseY, (int)temp.getX(), (int)temp.getY(), (int)temp.getWidth(), (int)temp.getHeight())) {
						updater.removeStageObject(temp);
					}
				}
			}
		}
		
		if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 400, 420, 100)) {
			multitask.menuState = "directions";
		}
		
		if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 600, 420, 100)) {
			multitask.menuState = "secret button";
		}
		
		if(multitask.menuState.equals("secret button")) {
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 600, 200, 100)) {
				multitask.menuState = "menu";
			}
		}
		if(multitask.menuState.equals("directions")) {
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 920, 200, 100)) {
				multitask.menuState = "menu";
			}
		}
		
		if(multitask.menuState.equals("pause")) {
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 200, 200, 420, 100)) {
				
				multitask.menuState = "game";
				
			}
		}
			
		
		
		
		if(multitask.menuState.equals("game over")) {
			
			
			if(withinTarget(mouseX, mouseY, Multitask.WIDTH/2 - 100, 900, 200, 100)) {
				multitask.gameOverClip.stop();
				scoreTracker.setStage(1);
				scoreTracker.setScore(0);
				updater.removeAllStage2Objects();
				spawner.stage2Height = 0;
				spawner.stage2Width = 0;
				spawner.stage1Height = Multitask.HEIGHT;
				spawner.stage1Width = Multitask.WIDTH;
				spawner.nextStage = 2;
				multitask.musicPlaying = false;
				spawner.havePlayers[1] = false;
				scoreTracker.setGameState(false);
				multitask.menuState = "game";
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean withinTarget(int mouseX, int mouseY, int targetX, int targetY, int width, int height) {
		if(mouseX > targetX && mouseX < targetX + width) {
			if(mouseY > targetY && mouseY < targetY + height) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public void update() {
		menuTimer++;
		if(menuTimer >= timerVal) {
			menuTimer = 0;
				float xValue = r.nextInt(Multitask.WIDTH + 20) -10;
				xValue = Multitask.setBounds(xValue, -20, Multitask.WIDTH + 20);
				//random number from 0 to 4
				int widthHeightIndex = r.nextInt(5);
				timerVal = r.nextInt(26)+50;
				updater.addStageObject(new Stage1Enemies(xValue, -150, meteorWidthChoices[widthHeightIndex], meteorHeightChoices[widthHeightIndex], meteor1, "Stage1Enemy", spawner, updater, this));
	}
	}
	
	public void render(Graphics g) {
		if(multitask.menuState.equals("menu")) {
			g.setFont(new Font("arial", 1, 50));
			g.setColor(Color.white);
			g.drawString("Menu", Multitask.WIDTH/2 - 60, 100);
		
			g.drawRect(Multitask.WIDTH/2 - 200, 200, 420, 100);
			g.drawRect(Multitask.WIDTH/2 - 200, 400, 420, 100);
			g.drawRect(Multitask.WIDTH/2 - 200, 600, 420, 100);
		
			g.drawString("Play", Multitask.WIDTH/2 - 43, 265);
			g.drawString("Directions", Multitask.WIDTH/2 - 100, 465);
			g.drawString("Secret Button", Multitask.WIDTH/2 - 150, 665);
		}
		else if(multitask.menuState.equals("directions")) {
			
			g.setFont(new Font("arial", Font.ITALIC, 50));
			g.setColor(Color.white);
			g.drawString("Directions", Multitask.WIDTH/2 - 60, 100);
			
			g.setFont(new Font("arial", 0, 40));
			g.drawString("The aim of the game is to survive as long as possible through the 4 stages.", 100, 200);
			g.drawString("In order to survive, you must have sufficient multitasking abilities.", 100, 250);
			g.drawString("Stage 1: The first stage requires you to move the donut astronaut left and right", 100, 350);
			g.drawString("with the ARROW KEYS, dodging falling meteors. Making contact with any meteor will end the game.", 100, 400);
			g.drawString("Stage 2: After reaching a score of 1000, the second stage will open, where you must continue to", 100, 500);
			
			g.setFont(new Font("arial", 0, 35));
			g.drawString("play the first stage while now using W and S to move the donut astronaut up and down, dodging incoming asteroids.", 100, 550);
			
			g.setFont(new Font("arial", 0, 40));
			g.drawString("Stage 3: After reaching a score of 2000, the third stage will begin.", 100, 650);
			g.drawString("During this stage, a new enemy is introduced against the stage 1 player, which is a rectangle that the", 100, 700);
			g.drawString("player must jump over with the SPACEBAR. Contact with a rectangle will result in game over.", 100, 750);
			g.drawString("Stage 4: After reaching a score of 3000, harmless floating donuts will appear all over the screen.", 100, 850);
			g.drawString("The donuts grow in size by the millisecond, and must be CLICKED to disappear so that the user can see.", 100, 900);
			
			g.drawRect(Multitask.WIDTH/2 - 200, 920, 200, 100);
			
			g.setColor(Color.white);
			g.setFont(new Font("arial", 0, 40));
			g.drawString("Back", Multitask.WIDTH/2 - 150, 980);
			
		}
		else if(multitask.menuState.equals("secret button")) {
			g.setColor(Color.pink);
			g.setFont(new Font("Times New Roman", 1,130));
			g.drawString("CREATED BY DSTOVERVT", 70, 540);
			
			g.drawRect(Multitask.WIDTH/2 - 200, 600, 200, 100);
			
			g.setColor(Color.white);
			g.setFont(new Font("arial", 0, 40));
			g.drawString("Back", Multitask.WIDTH/2 - 150, 665);
			
			
		}
		else if(multitask.menuState.equals("speed choose")) {
			g.setFont(new Font("arial", 2, 50));
			g.setColor(Color.white);
			g.drawString("Choose your speed", Multitask.WIDTH/2 - 200, 400);
		
			g.drawRect(Multitask.WIDTH/2 - 200, 500, 420, 100);
			g.drawRect(Multitask.WIDTH/2 - 200, 700, 420, 100);
			g.drawRect(Multitask.WIDTH/2 - 200, 900, 420, 100);
		
			g.setFont(new Font("arial", 1, 50));
			g.drawString("Normal", Multitask.WIDTH/2 - 70, 570);
			g.drawString("Fast", Multitask.WIDTH/2 - 55, 770);
			g.drawString("Freaky Fast", Multitask.WIDTH/2 - 130, 970);
		}
		else if(multitask.menuState.equals("pause")) {
			
				updater.stage1ObjectsRender(g);
				g.setFont(new Font("arial", 1, 50));
				g.setColor(Color.white);
				g.drawString("Pause", Multitask.WIDTH/2 - 60, 100);
			
				g.drawRect(Multitask.WIDTH/2 - 200, 200, 420, 100);
				g.drawString("Resume", Multitask.WIDTH/2 - 93, 265);
			
		}
		else if(multitask.menuState.equals("game over")) {
			updater.removeAllStage1Enemies();
			updater.removeAllStage2Enemies();
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD, 300));
			g.drawString("HAH you lose", 50, Multitask.HEIGHT/2);
			
			g.setColor(Color.white);
			g.drawRect(Multitask.WIDTH/2 - 100, 900, 200, 100);
			g.setFont(new Font("arial", 0, 40));
			g.drawString("Replay", Multitask.WIDTH/2-50, 960);
			
			int flashingColor = r.nextInt(10);
			g.setColor(spawner.objectColors[flashingColor]);
			g.setFont(new Font("arial", 0, 40));
			g.drawString("Score: " + scoreTracker.getScore(), Multitask.WIDTH/2 - 100, Multitask.HEIGHT/2 + 150);
			g.drawString("Stage: " + scoreTracker.getStage(), Multitask.WIDTH/2 - 100, Multitask.HEIGHT/2 + 200);
			g.drawString(howDied, Multitask.WIDTH/2 - 100, Multitask.HEIGHT/2 + 250);
		}
	}
	
}
