import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import core.*;
import core.Bataille.MODE;
import core.Bataille.PLAYER_N;
import core.Bataille.TYPE;

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
	public static void main(String[] args) 
	{
		sc = new Scanner(System.in);
		BatailleManager bm = new BatailleManager();
		create_bataille(bm);
		bm.setMode(MODE.DEUX_JOUEURS);
		Bataille b = bm.create();

		b.addPlayer(PLAYER_N.ONE, create_player(PLAYER_N.ONE));
		b.addPlayer(PLAYER_N.TWO, create_player(PLAYER_N.TWO));

		//add_player_boat(b, PLAYER_N.ONE);
		//add_player_boat(b, PLAYER_N.TWO);

		b.placePlayerBoat(PLAYER_N.ONE, new Bateau(Bateau.TYPE.PORTE_AVION, ORIENTATION.H, new Position(2,2)));
		b.placePlayerBoat(PLAYER_N.TWO, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.V, new Position(5,5)));
		
		try {
			play(b);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

	private static void create_bataille(BatailleManager bm)
	{
		bm.setType(select_type());
		bm.setMode(select_mode());
	}

	private static Bataille.TYPE select_type()
	{
		Bataille.TYPE type = TYPE.NAVALE;

		System.out.println("Choisire un type de bataille:");
		System.out.println("1 - Navale");
		System.out.println("2 - Radar");
		System.out.println("3 - Artillerie");
		System.out.println("4 - Alerte Rouge");

		int choice = choose_int(1,4);

		if (choice == 2)
			type = TYPE.RADAR;
		else if (choice == 3)
			type = TYPE.ARTILLERIE;
		else if (choice == 4)
			type = TYPE.ALERTE_ROUGE;

		return type;
	}

	private static Bataille.MODE select_mode()
	{
		Bataille.MODE mode = Bataille.MODE.DEMO;

		System.out.println("Sélectionner le mode de Bataille:");
		System.out.println("1 - Démo");
		System.out.println("2 - Un Joueur");
		System.out.println("3 - Deux Joueurs");

		final int choice = choose_int(1, 3);

		if (choice == 2)
			mode = Bataille.MODE.UN_JOUEUR;
		else if (choice == 3)
			mode = Bataille.MODE.DEUX_JOUEURS;

		return mode;
	}

	private static void play(Bataille b) throws Exception
	{
		System.out.println(" * LA PARTIE COMMENCE * ");

		while (b.canContinue())
		{
			System.out.println("*-------------------------------*");
			System.out.println("*-------------------------------*");
			System.out.println("Le Joueur " + b.getCurrentAttackingPlayer().getName() + " attaque:");
			print(b.getCurrentAttackingPlayer().getBoatsList());
			System.out.println("*-------------------------------*");
			printAttaqueData(b.getCurrentAttackingPlayer().getAttaqueData());
			Position attackPos = b.getCurrentAttackingPlayer().getAttackPos(b.getType());
			
			final ATTAQUE_STATUS s = b.playerAttack(attackPos);
			System.out.println(ATTAQUE_STATUS.toString(s));
	
			if (!b.attacking_player_can_reattack())
			{
				if (b.getType() == Bataille.TYPE.RADAR)
				{
					System.out.println("Bateau le plus proche: " + ((Radar)b).radar_reponse(attackPos));
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
			try
			{
				choix = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.err.println("Veuillez entrer un nombre entier");
			}
			
			sc.nextLine();

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