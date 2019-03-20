import java.util.ArrayList;
import java.util.Scanner;

import core.*;
import core.Bataille.PLAYER_N;

/**
 * Classe qui blablabla et
 * blablabla et bliblanlu et
 * bloblobloblan. 
 * <p>De plus,  
 * blabliblablu
 * 
 * @author Adrien COURNAND
 */
public class BatailleNavale 
{
	private static ArrayList<Bateau> m_boats;

	private static Scanner sc;

	public static void main(String[] args) 
	{
		sc = new Scanner(System.in);
		BatailleManager bm = new BatailleManager();
		bm.setMode(Bataille.MODE.DEUX_JOUEURS);
		bm.setType(Bataille.TYPE.NAVALE);

		Bataille b = bm.create();
		
		b.addPlayer(PLAYER_N.ONE, create_player(PLAYER_N.ONE));
		b.addPlayer(PLAYER_N.TWO, create_player(PLAYER_N.TWO));
		
		add_player_boat(b, PLAYER_N.ONE);
		add_player_boat(b, PLAYER_N.TWO);

		sc.close();
	}
	
	private static Joueur create_player(final Bataille.PLAYER_N n)
	{
		System.out.print("nom (Joueur " + PLAYER_N.toString(n) + ") : ");
		
		return new Joueur(sc.nextLine());
	}
	
	private static void add_player_boat(Bataille b, final PLAYER_N n)
	{
		System.out.println("Le joueur " + b.getPlayer(n).getName() + " place ses bateaux");
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.PORTE_AVION));
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.CUIRASSE));
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.SOUS_MARIN));
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.SOUS_MARIN));
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.ZODIAC));
		b.placePlayerBoat(n, create_boat(Bateau.TYPE.ZODIAC));
	}
	
	private static Bateau create_boat(final Bateau.TYPE t)
	{
		System.out.println("** Choix du " + Bateau.TYPE.toString(t) + " **");
		return choose_boat(t);
	}
	
	private static Bateau choose_boat(final Bateau.TYPE t)
	{
		return new Bateau(t, choose_o(), choose_p());
	}
	
	private static ORIENTATION  choose_o ()
	{
		System.out.println("Veuillez sélectionner l'orientation du bateau:");
		System.out.println(" - 1  : Verticale");
		System.out.println(" - 2+ : Horizontale");
		System.out.print("choix ? ");
		
		final int choix = sc.nextInt();
		ORIENTATION ret = ORIENTATION.V;
		
		if (choix >= 2)
			ret = ORIENTATION.H;
		
		

		return ret;
	}
	
	private static Position choose_p()
	{
		Position ret = new Position(0,0);
		System.out.println("Veuillez choisir une position:");
		System.out.println("* Choix de l'abssice (x) *");
		ret.x = choose_int(0, 9);
		
		System.out.println("* Choix de l'ordonnée (y) *");
		ret.y = choose_int(0, 9);
		
		return ret;
	}
	
	private static int choose_int(final int min, final int max)
	{
		int choix = 0;
		
		do
		{
			System.out.print(" > (" + min + ";" + max + ") ");
			choix = sc.nextInt();
		}while((choix < min) || (choix > max));
		return choix;
	}
	
	public static void print()
	{
		for(int y = 0; y < 10; ++y)
		{
			for (int x = 0; x < 10; ++x)
			{
				char to_print = '.';
				
				for (int i = 0; i < m_boats.size(); ++i)
				{
					if (m_boats.get(i).o == ORIENTATION.H)
					{
						if (y == m_boats.get(i).pos.y)
						{
							if ((x >= m_boats.get(i).pos.x) && (x < m_boats.get(i).pos.x + Bateau.TYPE.toInt(m_boats.get(i).type)))
							{
								to_print = m_boats.get(i).getDamage(x - m_boats.get(i).pos.x) ? 'x' : '-';
							}
						}
					}
					else
					{
						if (x == m_boats.get(i).pos.x)
						{
							if ((y >= m_boats.get(i).pos.y) && (y < m_boats.get(i).pos.y + Bateau.TYPE.toInt(m_boats.get(i).type)))
							{
								to_print = m_boats.get(i).getDamage(y - m_boats.get(i).pos.y) ? 'x' : '|';
							}
						}
					}
				}

				System.out.print(to_print + " ");
			}

			System.out.print("\n");
		}
	}
}
