package gui;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {
	private static final long serialVersionUID = -4530832044829531443L;
	private JPanel conteneur = new JPanel();
	private JLabel nomMode = new JLabel("Mode : ");
	String[] tabMode = { "DEMO", "UN JOUEUR", "DEUX JOUEURS" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox Mode = new JComboBox(tabMode);
	private JLabel nomDifficulte = new JLabel("Difficulté : ");
	String[] tabDifficulte = { "FACILE", "MOYEN", "DUR" };
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox Difficulte = new JComboBox(tabDifficulte);
	private JLabel nomType = new JLabel("Type : ");
	String[] tabType = { "NAVALE", "ARTILLERIE", "RADAR", "ALERTE ROUGE" };
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox Type = new JComboBox(tabType);
	private JLabel gap0 = new JLabel("");
	private JLabel gap1 = new JLabel("");
	private JLabel gap2 = new JLabel("");
	private JLabel gap3 = new JLabel("");
	JLabel imageintro = new JLabel(new ImageIcon("images/bateau.png"), JLabel.CENTER);
	private JButton boutonfinal = new JButton("COMMENCER UNE PARTIE");
	
public StartScreen(){
    setTitle("Bataille Navale");
    setSize(400, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    conteneur.setLayout(new GridLayout(1, 1, 0, 0));
    JPanel contenu = new JPanel();
    Mode.setPreferredSize(new Dimension(150, 20));
	Difficulte.setPreferredSize(new Dimension(150, 20));
	gap0.setPreferredSize(new Dimension(400, 15));
	gap1.setPreferredSize(new Dimension(400, 30));
	gap2.setPreferredSize(new Dimension(400, 30));
	gap3.setPreferredSize(new Dimension(400, 50));
	imageintro.setPreferredSize(new Dimension(280, 180));
	contenu.setBackground(Color.white);
	contenu.add(imageintro);
	contenu.add(gap0);
	contenu.add(nomMode);
	contenu.add(Mode);
	contenu.add(gap1);
	contenu.add(nomDifficulte);
	contenu.add(Difficulte);
	contenu.add(gap2);
	contenu.add(nomType);
	contenu.add(Type);
	contenu.add(gap3);;
	contenu.add(boutonfinal);
	conteneur.add(contenu);
	setContentPane(conteneur);
	setVisible(true);
  }

public JComboBox<?> getMode() {
	return Mode;
}

public JComboBox<?> getDifficulte() {
	return Difficulte;
}

public JComboBox<?> geType() {
	return Type;
}

}
