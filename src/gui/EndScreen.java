package gui;

import javax.swing.*;

import java.awt.*;

public class EndScreen extends JFrame {
	private static final long serialVersionUID = -7141081697553925112L;
	private JPanel screen = new JPanel();
	private JLabel victoire = new JLabel("Victoire !", JLabel.CENTER);
	private JLabel defaite = new JLabel("Défaite !", JLabel.CENTER);
	private JButton boutonquitter = new JButton("QUITTER");
	private JLabel gap0 = new JLabel("");
	private JLabel gap1 = new JLabel("");
	
	public EndScreen(boolean isVictory) {
		refreshScreen(isVictory);
	}
	
	public void refreshScreen(boolean isVictory) {
		screen.removeAll();

		setTitle("Office Simulator");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		screen.setLayout(new GridLayout(1, 1, 0, 0));
		JPanel contenu = new JPanel();
		Font police = new Font("Impact", Font.BOLD, 40);
		gap0.setPreferredSize(new Dimension(300, 80));
		gap1.setPreferredSize(new Dimension(300, 80));
		boutonquitter.setPreferredSize(new Dimension(200, 30));
		contenu.setBackground(Color.WHITE);
		contenu.add(gap0);
		if (isVictory == true) {
			victoire.setFont(police);
			victoire.setPreferredSize(new Dimension(200, 50));
			contenu.add(victoire);
		} else {
			defaite.setFont(police);
			defaite.setPreferredSize(new Dimension(200, 50));
			contenu.add(defaite);
		}
		contenu.add(gap1);
		contenu.add(boutonquitter);
		screen.add(contenu);
		setContentPane(screen);
		setVisible(true);
	}
	
	public JButton getBoutonquitter() {
		return boutonquitter;
	}

}
