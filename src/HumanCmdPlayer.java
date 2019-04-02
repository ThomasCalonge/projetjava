import java.util.Scanner;

import core.Joueur;
import core.Position;

public class HumanCmdPlayer extends Joueur {

	private Scanner m_sc;

	public HumanCmdPlayer(final String name, Scanner sc)
	{
		super(name);

		m_sc = sc;
	}

	@Override
	public Position getAttackPos() 
	{
		System.out.println("Entrer les coordonnée de l'attaque: ");
		System.out.print("x");
		final int x = choose_int(0, 9);

		System.out.print("y");
		final int y = choose_int(0, 9);

		return new Position(x, y);
	}

	private int choose_int(final int min, final int max)
	{
		int choix = 0;
		
		do
		{
			System.out.print(" > (" + min + ";" + max + ") ");
			choix = m_sc.nextInt();
		}while((choix < min) || (choix > max));
		return choix;
	}
}