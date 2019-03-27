package gui;

import javax.swing.*;
import javax.swing.border.*;

import core.*;

import java.awt.*;

public class GameScreen extends JFrame {
	private static final long serialVersionUID = 8467739566752896272L;
	private JPanel plateau = new JPanel();
	JPanel contenu = new JPanel();
	JPanel contenu2 = new JPanel();
	private Border raisedbevel;
	private Border loweredbevel;
	private Border compound;
	JLabel[][] tabdecase = new JLabel[10][10];
	
	public GameScreen() {
		setTitle("Bataille Navale");
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
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
	
	public void remplirtableau(JPanel plateau) {
		contenu.setLayout(new GridLayout(10, 10, 1, 1));
		contenu2.setLayout(new GridLayout(10, 10, 1, 1));
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				tabdecase[i][j] = new JLabel(new ImageIcon("images/casearemplir.png"), JLabel.CENTER);
				contenu.add(tabdecase[i][j]);
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				tabdecase[i][j] = new JLabel(new ImageIcon("images/casearemplir.png"), JLabel.CENTER);
				contenu2.add(tabdecase[i][j]);
			}
		}
		plateau.add(contenu);
		plateau.add(contenu2);
	}

}