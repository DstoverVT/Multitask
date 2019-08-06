package com.multitask.dannystover;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


//main class that runs the game, window, and background operations


public class Multitask extends Canvas implements Runnable{

	
	//dimensions of window/JFrame
	public static final int WIDTH = 1920, HEIGHT = 1080;
	
	
	//Entire game runs through this thread
	private Thread thread;
	
	//keeps track of if game is running or not, stops thread if not (in stop() method), runs the
	//game loop inside of the run() method, which is called when the Thread is started (start())
	public boolean running = false;
	
	//OBJECTS
	//ScoreTracker keeps track of the score and stage of the game
	private ScoreTracker scoreTracker;
	
	//Updater contains of the game objects and updates and renders them each frame
	private Updater mainUpdater;
	
	//Spawner uses the game loop to keep a timer, which is used to spawn enemies periodically, and
	//spawn the players as well as transition between stages.
	private Spawner objectSpawner;
	
	//Allows keys to be registered and serve a function when pressed
	private KeyInput keyInput;
	
	//The Menu holds and maintains everything when the game is not running, so in the beginning menu, 
	//when you die, and in the pause menu. 
	private Menu menu;
	
	//Sound Clips for in-game and when you die
	private Clip gameClip;
	public Clip gameOverClip;
	
	//tells whether the in-game music is playing. Public so other classes such as Menu can use it.
	public boolean musicPlaying = false;
	
	//Important- keeps track of the state of the game, so the game provides the graphical interface
	//according to what state it is in. 
	public String menuState = "menu";



	//main method creates a new Multitask()
	public Multitask() {

		//creates window that contains this Multitask class as a parameter.
		//the Window starts the start() method, which calls the run() method, which calls the render() and update() methods 
		//that call the Updater update and render methods. So when a Multitask() is created in main, the game starts through this sequence. 

		//OBJECT initializations and mouse and keyboard recognition
		mainUpdater = new Updater();
		scoreTracker = new ScoreTracker();
		objectSpawner = new Spawner(mainUpdater, scoreTracker);
		menu = new Menu(this, mainUpdater, objectSpawner, scoreTracker);
		this.addKeyListener(new KeyInput(mainUpdater, this, scoreTracker));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Multitask", this);

		
		
		
	}
	
	
	//this method is called when a new Window is created (in the Multitask constructor)
	public void start() {
		//creates new thread, starts up thread, which starts the game loop
		thread = new Thread(this);
		//this calls the run() method of whatever was put into the Thread class, in this case "this", which
		//refers to the Multitask class what put into the Thread so the Multitask class' run() method is called
		thread.start();
		running = true;
		
		
	}
	
	public void stop() {
		//makes sure you can stop at this time, and stops the thread
		//when the game loop stops (running = false), this method is called and terminates game
		try {
			//join method kills off thread
			thread.join();
			running = false;
			
		}catch(Exception e) {
			//if error, this is ran (prints in console)
			e.printStackTrace();
		}
		
	}
	//method to play in-game saxophone song
	public void playMusic() {
		try {
			File menuSong = new File("Game music final.wav");
			AudioInputStream musicMenu = AudioSystem.getAudioInputStream(menuSong);
			//field
			gameClip = AudioSystem.getClip();
			gameClip.open(musicMenu);
			gameClip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//called when Thread is started in start() method
	//makes sure update() and render() methods are called each millisecond/frame (a little longer) or so.
	public void run() {
		
		//game loop (while loop), necessary. The rest calculates the frames per second.
		//needs to check whether enough time has passed (1/60 sec) 
		//to refresh the game, and checks whether enough time has
		//passed (1 second) to refresh the FPS counter
		this.requestFocus();
		long lastTime = System.nanoTime();	//get current time to the nanosecond
		double amountOfTicks = 60.0;	//set the number of ticks
		double ns = 1000000000 / amountOfTicks;		//determines how many times we can divide 60 into 1e9 of nano seconds or about 1 second
		double delta = 0; 	//change in time 
		long timer = System.currentTimeMillis();	//get current time
		int frames = 0;		//set frame variable
		while(running) {
			//while loop runs and adds time it took adding it to delta, 
			//which is 1 and once it reaches 1 delta the game refreshes
			//FPS counter
			long now = System.nanoTime();	//get current time in nanoseconds during current iteration
			delta += (now - lastTime) / ns;		//add the amount of change since the last iteration
			lastTime = now;		//set lastTime to now prepare for next iteration
			while(delta >= 1) {
				//Renders next frame once 60 frames per one second is reached, so game doesn't run way too fast
				update();
				delta--;	//lower delta to 0 to start our next frame wait
			}
				render();	//render visuals of game
			frames++;	//frame has passed
			
			//if one second has passed
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000; 	//add a thousand to timer for next time
				System.out.println("FPS: " + frames);	//print how many frames have happened in last second
				frames = 0;		//reset frame count for next second
			}
		}
			stop(); 	//stop thread if running = false
		
	}
	
	private void update() {
		//updates each frame
		//when game is over or in pause menu, makes sure enemies don't spawn
		if(!menuState.equalsIgnoreCase("game over") && !menuState.equals("pause"))
			mainUpdater.stage1ObjectsUpdate();
		
		if(scoreTracker.getStage() != 1 && !menuState.equals("game over") && !menuState.equals("pause"))
			mainUpdater.stage2ObjectsUpdate();
		
		//during the game
		if(menuState.equals("game")) {
			
			if(menu.fastSpeed)
				mainUpdater.stage1ObjectsUpdate();
			
			//game music playing, boolean makes sure it only plays once
			if(!musicPlaying) {
				playMusic();
				musicPlaying = true;
			}
			//updates the score object each frame
			scoreTracker.update();
			
			//updates the spawner object each frame
			objectSpawner.update();
		
			//after stage 1. Updates stage 2 objects when stage 1 is complete
			if(scoreTracker.getStage() != 1 && menu.fastSpeed)
				mainUpdater.stage2ObjectsUpdate();
			
			//after stage 2. Updates stage 3 objects when stage 2 is complete
			if(scoreTracker.getStage() != 1 && scoreTracker.getStage() != 2) {
				mainUpdater.stage3ObjectsUpdate();
			}
			
			//when stage 4 arrives, updates stage 4 objects to track movement
			if(scoreTracker.getStage() == 4) {
				mainUpdater.stage4ObjectsUpdate();
			}
		
			//when game ends
			if(scoreTracker.getGameState()) {
				//stop game music and change state
				gameClip.stop();
				menuState = "game over";
				//play game over, laughing sound
				try {
					File gOLaugh = new File("Game over laugh.wav");
					AudioInputStream musicMenu = AudioSystem.getAudioInputStream(gOLaugh);
					gameOverClip = AudioSystem.getClip();
					gameOverClip.open(musicMenu);
					gameOverClip.start();
				}
				catch(Exception p) {
					p.printStackTrace();
				}
			}
		}
		//menu is the only thing that updates when the game is off
		else if(!menuState.equals("directions") && !menuState.equals("game")){
			menu.update();
		}

	}
	
	private void render() {
		
		//ensures smooth graphics when rendering
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		//provides as simple drawing and showing graphics
		Graphics g = bs.getDrawGraphics();
				
		g.setColor(Color.blue.darker());
		g.fillRect(0, 1080-objectSpawner.stage1Height, objectSpawner.stage1Width, objectSpawner.stage1Height);

		//Class render() methods
		if(!menuState.equalsIgnoreCase("game over"))
			mainUpdater.stage1ObjectsRender(g);
		
		if(menuState.equals("game")) {
			
			scoreTracker.render(g);
		
			//after stage 1
			if(scoreTracker.getStage() !=1) {
				g.setColor(Color.black);
				g.fillRect(WIDTH/2, 1080-objectSpawner.stage2Height, objectSpawner.stage2Width, objectSpawner.stage2Height);
				mainUpdater.stage2ObjectsRender(g);
			}	
			if(scoreTracker.getStage() != 1 && scoreTracker.getStage() != 2) {
				mainUpdater.stage3ObjectsRender(g);
			}
			if(scoreTracker.getStage() == 4) {
				mainUpdater.stage4ObjectsRender(g);
			}
		}
		else {
			menu.render(g);
			
		}
		
		g.dispose();
		bs.show();
		
	}
	
	
	//method that bounds anything inside a minimum and a maximum value
	//used for player's x and y limits 
	public static float setBounds(float value, float min, float max) {
		//returning this means that if var is greater than max it can only return max, so it's position
		//can never reach beyond max
		if(value >= max)
			return value = max;
		else if(value <= min)
			return value = min;
		else
			return value;
	}
	
	
	public static void main(String[] args) {
		
		//provides hardware acceleration for rendering 2D games
		System.setProperty("sun.java2d.opengl", "true");
		//****calls Game constructor which creates a Window which the start() method, which calls the run() method, which calls the render() and tick() methods 
		//that call the Handler tick and render methods. So when a Game() is created, the game starts through this sequence.
		new Multitask();
		
	}

}
