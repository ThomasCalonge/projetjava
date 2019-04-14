import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import core.Bataille;
import core.Bateau;
import core.Joueur;
import core.Position;

public class HumanCmdPlayer extends Joueur {

	private Scanner m_sc;
	private static boolean cont = true;
	private static boolean init = false;
	private static JFrame frame;

	public HumanCmdPlayer(final String name, Scanner sc) {
		super(name);

		m_sc = sc;
	}

	private static void initFrame() {
		if (!init) {
			frame = new JFrame("Sélection de y");
			frame.add(new JLabel(
					"Veuillez regarder la console de jeu. \nAppuyez sur une touche pour valider la coordonnée y"));
			frame.pack();
			frame.setEnabled(false);
			frame.setResizable(false);
			frame.setVisible(false);
			frame.addKeyListener(new Keyboard());
			frame.transferFocus();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setAlwaysOnTop(true);
			init = true;
		}
	}

	@Override
	public Position getAttackPos(Bataille.TYPE type) {
		try {
			if ((type == Bataille.TYPE.NAVALE) || (type == Bataille.TYPE.RADAR))
				return getCmdPos();
			else
				return getHalfCmdPos();
		} catch (Exception e) {
			return null;
		}
	}

	private Position getCmdPos() {
		System.out.println();
		System.out.print("x");
		final int x = Helper.choose_int(0, 9, m_sc);

		System.out.print("y");
		final int y = Helper.choose_int(0, 9, m_sc);

		return new Position(x, y);
	}

	@Override
	public void placeBoat(Bateau b) 
	{ m_boats.add(b); }

	private Position getHalfCmdPos() throws InterruptedException 
	{
		initFrame();

		System.out.println("Entrer les coordonnée de l'attaque: ");
		System.out.print("x");
		cont = true;

		final int x = Helper.choose_int(0, 9, m_sc);
		int y = 0;

		switchFrameStatus();
		System.out.print("y > ");
		while (cont) {
			System.out.print(y + " ");
			Thread.sleep(1000);
			if (cont)
				y = ++y % 10;
		}

		switchFrameStatus();
		System.out.println();
		return new Position(x, y);
	}

	private void switchFrameStatus() {
		frame.setEnabled(!frame.isEnabled());
		frame.setVisible(!frame.isVisible());
	}

	static class Keyboard implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			cont = false;
		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	@Override
	public TYPE getType() {
		return TYPE.HUMAIN;
	}
}