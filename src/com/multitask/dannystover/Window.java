package com.multitask.dannystover;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	
public Window(int width, int height, String title, Multitask mainRunner) {
		
		JFrame frame = new JFrame(title);
		
		//set size of frame
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//x button, no resizing, center frame, add game into frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(mainRunner);
		
		//see it and run Game start method
		frame.setVisible(true);
		//THIS IS WHAT STARTS EVERYTHING IN THE GAME
		mainRunner.start();
	}

}
	

