package gui;

import javax.swing.*;
import javax.swing.border.*;

import core.*;

import java.awt.*;

public class GameScreen extends JFrame {
	private static final long serialVersionUID = 8467739566752896272L;
	private JPanel plateau = new JPanel();
	JPanel contenu = new JPanel();
	private Border raisedbevel;
	private Border loweredbevel;
	private Border compound;
	
	
	public GameScreen() {
		setTitle("Bataille Navale");
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 750);
		setResizable(false);
		setLocationRelativeTo(null);
		plateau.setLayout(new GridLayout(1, 1, 0, 0));
		JLabel casearemplir = new JLabel(new ImageIcon("images/casearemplir.png"), JLabel.CENTER);
		
	}

}