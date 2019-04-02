package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gui.*;
import core.*;
import core.BatailleNavale.MODE;
import core.BatailleNavale.TYPE;

public class Controller {
	private BatailleManager model;
	private StartScreen viewStart;
	private GameScreen viewGame;
	private EndScreen viewEnd;
	private JButton[][] tabdecase;
	
	public Controller(BatailleManager model,StartScreen viewStart) {
		this.model = model;
		this.viewStart = viewStart;
		this.viewGame = new GameScreen();
		this.viewGame.setVisible(false);
		this.viewEnd = new EndScreen(true);
		this.viewEnd.setVisible(false);
		
	}
	
	public void control() {
		viewStart.getBoutonfinal().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
					switch (viewStart.geType().getSelectedItem().toString()) {
					case "NAVALE":
						model.setType(TYPE.NAVALE);
						break;
					case "ARTILLERIE":
						model.setType(TYPE.ARTILLERIE);
						break;
					case "RADAR":
						model.setType(TYPE.RADAR);
						break;
					case "ALERTE_ROUGE":
						model.setType(TYPE.ALERTE_ROUGE);
						break;
					}
					switch (viewStart.getMode().getSelectedItem().toString()) {
					case "DEMO":
						model.setMode(MODE.DEMO);
						break;
					case "UN_JOUEUR":
						model.setMode(MODE.UN_JOUEUR);
						break;
					case "DEUX_JOUEURS":
						model.setMode(MODE.DEUX_JOUEURS);
						break;
					}
					viewGame.setGame(model);
					viewGame.refreshscreen();
					viewStart.setVisible(false);
			}
		});
			
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
		
		viewGame.getfirst().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
	}
}
