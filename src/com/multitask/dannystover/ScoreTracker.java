package com.multitask.dannystover;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ScoreTracker {
	
	private int score;
	private int stage;
	private boolean gameOver;
	public Clip gameClip;
	
	public ScoreTracker() {
		score = 0;
		stage = 1;
		gameOver = false;
		
		
	}
	
	public void update() {
		score++;
		
		if(score == 1000) 
			setStage(2);
		
		if(score == 2000)
			setStage(3);
		
		
		if(score == 3000)
			setStage(4);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Animated", 0, 80));
		g.drawString("Score: " + (int)score, 30, 80);
		g.drawString("Stage: " + (int)stage, 30, 150);
		
		if(score >= 0 && score <= 200) {
			g.setColor(Color.white);
			g.setFont(new Font("Animated", 1, 200));
			g.drawString("1", Multitask.WIDTH/2 - 50, 200);
		}
		
		if(score >= 1000 && score <= 1200) {
			g.setColor(Color.white);
			g.setFont(new Font("Animated", 1, 200));
			g.drawString("2", Multitask.WIDTH/2 - 150, 200);
		}
		
		if(score >= 2000 && score <= 2200) {
			g.setColor(Color.white);
			g.setFont(new Font("Animated", 1, 200));
			g.drawString("3", Multitask.WIDTH/2 - 150, 200);
		}
		
		if(score >= 3000 && score <= 3200) {
			g.setColor(Color.white);
			g.setFont(new Font("Animated", 1, 200));
			g.drawString("4", Multitask.WIDTH/2 - 150, 200);
		}
	}
	
	public void setStage(int s) {
		stage = s;
	}
	
	public int getStage() {
		return stage;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getGameState() {
		return gameOver;
	}
	
	public void setGameState(boolean gs) {
		gameOver = gs;
	}
	
	public void setScore(int s) {
		score = s;
	}

}
