package gui;

import javax.swing.*;

import core.*;

import java.awt.*;
import core.Case.STATUS;

public class GameScreen extends JFrame {
	private static final long serialVersionUID = 8467739566752896272L;
	private JPanel plateau = new JPanel();
	private JPanel contenu = new JPanel();
	private JPanel contenu2 = new JPanel();
	private JButton[][] tabdecase1 = new JButton[10][10];
	private JButton[][] tabdecase2 = new JButton[10][10];
	private Case[][] tabdecases = new Case[10][10];
	private Case[][] tabdecases2 = new Case[10][10];
	
	public GameScreen() {
		initab(tabdecases);
		initab(tabdecases2);
	}
	
	public void initab(Case[][] tab) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				tab[i][j] = new Case();
			}
		}
	}
	
	public void remplirtableau(JPanel plateau) {
		contenu.setLayout(new GridLayout(10, 10, 1, 1));
		contenu2.setLayout(new GridLayout(10, 10, 1, 1));
		String images = "";
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				switch (tabdecases[i][j].getStatus()) {
					case PLACER:
						images = "images/casearemplir.png";
						break;
					case APPUIYER:
						images = "images/casehover.png";
						break;
					case RATE:
						images = "images/casecoule.png";
						break;
					case TOUCHE:
						images = "images/casetouche.png";
						break;
				}
				Icon ic1 = new ImageIcon(images);
				tabdecase1[i][j] = new JButton();
				tabdecase1[i][j].setIcon(ic1);
				
				contenu.add(tabdecase1[i][j]);
				}
			}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				switch (tabdecases2[i][j].getStatus()) {
				case PLACER:
					images = "images/casearemplir.png";
					break;
				case APPUIYER:
					images = "images/casehover.png";
					break;
				case RATE:
					images = "images/casecoule.png";
					break;
				case TOUCHE:
					images = "images/casetouche.png";
					break;
				}
				Icon ic1 = new ImageIcon(images);
				tabdecase2[i][j] = new JButton();
				tabdecase2[i][j].setIcon(ic1);
				contenu2.add(tabdecase2[i][j]);
			}
		}
		plateau.add(contenu);
		plateau.add(contenu2);
	}
	
	public void refreshscreen() {
		setTitle("Bataille Navale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		plateau.setLayout(new GridLayout(1, 2, 10, 0));
		remplirtableau(plateau);
		plateau.add(contenu);
		setContentPane(plateau);
		setVisible(true);
	}
	
	public void setGame(BatailleManager game) {
		game.create();
	}

	public JButton[][] getplateau() {
		return tabdecase1;
	}
	
	public JButton getfirst() {
		return tabdecase1[0][0];
	}
	
	public void setStatusplateau(int x, int y,STATUS Status) {
		tabdecases[x][y].setStatus(Status);
	}
}