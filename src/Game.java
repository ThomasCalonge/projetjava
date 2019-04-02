import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class Game 
{
	private static Scanner sc;
	private static boolean cont = true;
	public static void main(String[] args) 
	{
		sc = new Scanner(System.in);
		BatailleManager bm = new BatailleManager();
		bm.setMode(Bataille.MODE.DEUX_JOUEURS);
		bm.setType(Bataille.TYPE.NAVALE);
		Bataille b = bm.create();
		
		Joueur p1 = create_player(PLAYER_N.ONE);
		Joueur p2 = create_player(PLAYER_N.TWO);

		b.addPlayer(PLAYER_N.ONE, p1);
		b.addPlayer(PLAYER_N.TWO, p2);

		add_player_boat(b, PLAYER_N.ONE);
		add_player_boat(b, PLAYER_N.TWO);
		
		b.addPlayer(PLAYER_N.ONE, p1);
		b.addPlayer(PLAYER_N.TWO, p2);
		
		try {
			play(b);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

	private static void play(Bataille b) throws Exception
	{
		System.out.println(" * LA PARTIE COMMENCE * ");

		while (b.canContinue())
		{
			System.out.println("*-------------------------------*");
			System.out.println("*-------------------------------*");
			System.out.println("Le Joueur " + PLAYER_N.toString(b.getCurrentAttackingPlayer()) + " attaque:");
			print(b.getPlayer(b.getCurrentAttackingPlayer()).getBoatsList());
			System.out.println("*-------------------------------*");
			printAttaqueData(b.getPlayer(b.getCurrentAttackingPlayer()).getAttaqueData());
			Position attackPos = b.getPlayer(b.getCurrentAttackingPlayer()).getAttackPos(); //enterAttackPos();
			
			final ATTAQUE_STATUS s = b.playerAttack(attackPos);
			System.out.println(ATTAQUE_STATUS.toString(s));
	
			if (!b.attacking_player_can_reattack())
			{
				if (b.getType() == Bataille.TYPE.RADAR)
				{
					System.out.println("Bateau le plus proche: " + ((Radar)b).radar_reponse(attackPos));
					System.in.read();
				}

				b.switchAttackingPlayer();

				System.out.println("Appuyer pour passer au Joueur suivant");
				System.in.read();
	
				int i = 3;
	
				System.out.println("Changement de Joueur dans:");
				while (i > 0)
				{
					System.out.println(i--);
					Thread.sleep(1000);
				}
	
				for (int j = 0; j < 100; ++j)
				{
					System.out.println();
				}
			}
		}

		System.out.println(" * Gagné *");
	}

	private static Position enterAttackPos()
	{
		System.out.println("Entrer les coordonnée de l'attaque: ");
		System.out.print("x");
		final int x = choose_int(0, 9);

		System.out.print("y");
		final int y = choose_int(0, 9);

		return new Position(x, y);
	}
	
	private static Joueur create_player(final Bataille.PLAYER_N n)
	{
		System.out.print("nom (Joueur " + PLAYER_N.toString(n) + ") : ");
		
		return new HumanCmdPlayer(sc.nextLine(), sc);
	}
	
	private static void add_player_boat(Bataille b, final PLAYER_N n)
	{
		System.out.println(b.getPlayer(n).getName() + " place ses bateaux");

		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		b.placePlayerBoat(n, create_boat(Bateau.TYPE.PORTE_AVION));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		b.placePlayerBoat(n, create_boat(Bateau.TYPE.SOUS_MARIN));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		b.placePlayerBoat(n, create_boat(Bateau.TYPE.CUIRASSE));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		b.placePlayerBoat(n, create_boat(Bateau.TYPE.CUIRASSE));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		b.placePlayerBoat(n, create_boat(Bateau.TYPE.ZODIAC));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();

		for (int i = 0; i < 100; ++i)
		{ System.out.println("*"); }
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
	
	public static void print(ArrayList<Bateau> boats)
	{
		System.out.println("  A B C D E F G H I J");
		for(int y = 0; y < 10; ++y)
		{
			System.out.print(y + " ");
			for (int x = 0; x < 10; ++x)
			{
				char to_print = '.';
				
				for (Bateau b: boats)
				{
					if (b.o == ORIENTATION.H)
					{
						if (y == b.pos.y)
						{
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.toInt(b.type)))
							{
								to_print = b.getDamage(x - b.pos.x) ? 'x' : '-';
							}
						}
					}
					else
					{
						if (x == b.pos.x)
						{
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.toInt(b.type)))
							{
								to_print = b.getDamage(y - b.pos.y) ? 'x' : '|';
							}
						}
					}
				}

				System.out.print(to_print + " ");
			}

			System.out.print("\n");
		}
	}

	static void printAttaqueData(final ArrayList<AttackData> datas)
	{
		System.out.println("  A B C D E F G H I J");
		for (int y = 0; y < 10; ++y)
		{
			System.out.print(y + " ");
			for (int x = 0; x < 10; ++x)
			{
				char to_print = '.';
				for (AttackData d: datas)
				{
					if ((d.pos.x == x) && (d.pos.y == y))
					{
						switch(d.status)
						{
							case EAU:
								to_print = 'E';
								break;
							case TOUCHE:
							case COULE:
								to_print = 'x';
								break;
						}
					}
				}

				System.out.print(to_print + " ");
			}
			System.out.println();
		}
	}
}


//Thread t = new Thread(){ public void run(){ }};