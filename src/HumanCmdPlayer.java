import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import core.Bataille;
import core.Joueur;
import core.Position;

public class HumanCmdPlayer extends Joueur {

	private Scanner m_sc;
	private boolean m_cont = true;

	public HumanCmdPlayer(final String name, Scanner sc) {
		super(name);

		m_sc = sc;
	}

	@Override
	public Position getAttackPos(Bataille.TYPE type) {
		try
		{
			if ((type == Bataille.TYPE.NAVALE) || (type == Bataille.TYPE.RADAR))
				return getCmdPos();
			else
				return getHalfCmdPos();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	private Position getCmdPos() {
		System.out.println();
		System.out.print("x");
		final int x = choose_int(0, 9);

		System.out.print("y");
		final int y = choose_int(0, 9);

		return new Position(x, y);
	}

	private Position getHalfCmdPos() throws InterruptedException {
		System.out.println("Entrer les coordonnée de l'attaque: ");
		System.out.print("x");
		final int x = choose_int(0, 9);

		int y = 0;
		m_cont = true;

		Thread t = new Thread() { public void run() { init_choice();} };
		t.start();

		System.out.print("y > ");

		while (m_cont)
		{
			System.out.print(y + " ");
			Thread.sleep(1000);
			if (m_cont)
				y = ++y % 10;
		}
		t.join();
		System.out.println();
		return new Position(x, y);
	}

	private void init_choice()
	{
		JFrame choiceFrame = new JFrame("Entrer les coordonnée de l'attaque: ");
		choiceFrame.add(new JLabel("Veuillez regarder la console de jeu. \nAppuyez sur une touche pour valider la coordonnée y"), BorderLayout.SOUTH);
		choiceFrame.pack();
		choiceFrame.setResizable(false);
		choiceFrame.setVisible(true);
		choiceFrame.addKeyListener(new Keyboard());
		choiceFrame.transferFocus();
		choiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private int choose_int(final int min, final int max) {
		int choix = 0;

		do {
			System.out.print(" > (" + min + ";" + max + ") ");
			choix = m_sc.nextInt();
		} while ((choix < min) || (choix > max));
		return choix;
	}

	class Keyboard implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) 
		{
			m_cont = false;
		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}
}