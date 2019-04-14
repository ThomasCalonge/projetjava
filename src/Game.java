import java.util.ArrayList;
import java.util.Scanner;

import core.*;
import core.Bataille.MODE;
import core.Bataille.PLAYER_N;
import core.Bataille.TYPE;
import core.IA.DIFFICULTE;

public class Game {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		BatailleManager bm = new BatailleManager();
		create_bataille(bm);

		Bataille b = bm.create();


		try {
			init_player(b, bm);
			play(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

	private static void init_player(Bataille b, final BatailleManager bm) throws Exception
	{
		if (false) {
			add_player_boat(b, PLAYER_N.ONE);
			add_player_boat(b, PLAYER_N.TWO);
		} 
		else 
		{
			switch (bm.getMode())
			{
				case DEMO:
				{
					b.addPlayer(PLAYER_N.ONE, new IA(DIFFICULTE.FACILE));
					b.addPlayer(PLAYER_N.TWO, new IA(DIFFICULTE.FACILE));
				} break;

				case UN_JOUEUR:
				{
					create_player(b, PLAYER_N.ONE);
					b.addPlayer(PLAYER_N.ONE, new IA(DIFFICULTE.DUR));

					b.placePlayerBoat(PLAYER_N.ONE, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(2, 2)));
				} break;

				case DEUX_JOUEURS:
				{
					create_player(b, PLAYER_N.ONE);
					create_player(b, PLAYER_N.TWO);

					b.placePlayerBoat(PLAYER_N.ONE, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(2, 2)));
					b.placePlayerBoat(PLAYER_N.TWO, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(5, 5)));
				} break;
			}	
		}
	}

	private static void create_bataille(BatailleManager bm) {
		bm.setType(select_type());
		bm.setMode(select_mode());
	}

	private static Bataille.TYPE select_type() {
		Helper.printGameFormatted("Choisire un type de bataille:",
				new String[] { "Navale", "Radar", "Artillerie", "Alerte Rouge" });

		int choice = Helper.choose_int(1, 4, sc);
		return (new Bataille.TYPE[] { TYPE.NAVALE, TYPE.RADAR, TYPE.ARTILLERIE, TYPE.ALERTE_ROUGE })[choice - 1];
	}

	private static Bataille.MODE select_mode() {
		Helper.printGameFormatted("Sélectionner le mode de Bataille:",
				new String[] { "Démo", "Un Joueur", "Deux Joueurs" });

		final int choice = Helper.choose_int(1, 3, sc);
		return (new Bataille.MODE[] { MODE.DEMO, MODE.UN_JOUEUR, MODE.DEUX_JOUEURS })[choice - 1];
	}

	private static void play(Bataille b) throws Exception {
		System.out.println(" * LA PARTIE COMMENCE * ");

		while (b.canContinue()) {
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
				if (b.getCurrentAttackingPlayer().getType() == Joueur.TYPE.HUMAIN)
				{
					if ((b.getType() == Bataille.TYPE.RADAR) || (b.getType() == Bataille.TYPE.ALERTE_ROUGE)) {
						System.out.println("Bateau le plus proche: " + ((Radar) b).radar_reponse(attackPos));
					}
				}

				b.switchAttackingPlayer();

				if (b.getCurrentAttackingPlayer().getType() == Joueur.TYPE.HUMAIN)
				{

					System.out.println("Appuyer pour passer au Joueur suivant");
					System.in.read();

					int i = 3;

					System.out.println("Changement de Joueur dans:");
					while (i > 0) {
						System.out.println(i--);
						Thread.sleep(1000);
					}
					
					Helper.pass(100);
				}
			}
		}

		System.out.println(" * Gagné *");
	}

	private static void create_player(final Bataille b, final Bataille.PLAYER_N n) {
		System.out.print("nom (Joueur " + PLAYER_N.toString(n) + ") : ");
		b.addPlayer(n,new HumanCmdPlayer(sc.nextLine(), sc));
	}

	private static void add_player_boat(Bataille b, final PLAYER_N n) {
		System.out.println(b.getPlayer(n).getName() + " place ses bateaux");

		placeAndDisplayAllPlayerBoats(b, n);
		Helper.pass(100);
	}

	private static void placeAndDisplayAllPlayerBoats(final Bataille b, final PLAYER_N n) {
		placeAndDisplayPLayerBoat(b, n, Bateau.TYPE.PORTE_AVION);
		placeAndDisplayPLayerBoat(b, n, Bateau.TYPE.SOUS_MARIN);
		placeAndDisplayPLayerBoat(b, n, Bateau.TYPE.CUIRASSE);
		placeAndDisplayPLayerBoat(b, n, Bateau.TYPE.CUIRASSE);
		placeAndDisplayPLayerBoat(b, n, Bateau.TYPE.ZODIAC);
	}

	private static void placeAndDisplayPLayerBoat(final Bataille b, final PLAYER_N n, final Bateau.TYPE t) {
		b.placePlayerBoat(n, create_boat(t));
		print(b.getPlayer(n).getBoatsList());
		System.out.println();
	}

	private static Bateau create_boat(final Bateau.TYPE t) {
		System.out.println("** Choix du " + Bateau.TYPE.toString(t) + " **");
		return choose_boat(t);
	}

	private static Bateau choose_boat(final Bateau.TYPE t) {
		return new Bateau(t, choose_o(), choose_p());
	}

	private static ORIENTATION choose_o() {
		Helper.printGameFormatted("Veuillez sélectionner l'orientation du bateau:",
				new String[] { "Verticale", "Horizontale" });

		final int choice = Helper.choose_int(1, 2, sc);
		return (choice == 1) ? ORIENTATION.V : ORIENTATION.H;
	}

	private static Position choose_p() {
		Position ret = new Position(0, 0);
		System.out.println("Veuillez choisir une position:");
		System.out.println("* Choix de l'abssice (x) *");
		ret.x = Helper.choose_int(0, 9, sc);

		System.out.println("* Choix de l'ordonnée (y) *");
		ret.y = Helper.choose_int(0, 9, sc);

		return ret;
	}

	public static void print(ArrayList<Bateau> boats) {
		System.out.println("  A B C D E F G H I J");
		for (int y = 0; y < 10; ++y) {
			System.out.print(y + " ");
			for (int x = 0; x < 10; ++x) {
				char to_print = '.';

				for (Bateau b : boats) {
					if (b.o == ORIENTATION.H) {
						if (y == b.pos.y) {
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.size(b.type))) {
								to_print = b.getDamage(x - b.pos.x) ? 'x' : '-';
							}
						}
					} else {
						if (x == b.pos.x) {
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.size(b.type))) {
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

	static void printAttaqueData(final ArrayList<AttackData> datas) {
		System.out.println("  A B C D E F G H I J");
		for (int y = 0; y < 10; ++y) {
			System.out.print(y + " ");
			for (int x = 0; x < 10; ++x) {
				char to_print = '.';
				for (AttackData d : datas) {
					if ((d.pos.x == x) && (d.pos.y == y)) {
						switch (d.status) {
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