package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import gui.*;
import core.*;

public class Controller {
	private StartScreen viewStart;
	private GameScreen viewGame;
	private EndScreen viewEnd;
	
	public Controller(StartScreen viewStart) {
		this.viewStart = viewStart;
		this.viewGame = new GameScreen();
		this.viewGame.setVisible(false);
		this.viewEnd = new EndScreen(true);
		this.viewEnd.setVisible(false);
	}
	
	public void control() {
		viewStart.getBoutonfinal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				viewEnd.setVisible(false);
				viewStart.refreshScreen();
			}
		});
		
		viewEnd.getBoutonquitter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
	}
}
