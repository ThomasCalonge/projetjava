import java.util.ArrayList;
import java.util.Scanner;

import core.*;
import core.Bataille.MODE;
import core.Bataille.PLAYER_N;
import core.Bataille.PlayerNonNullException;
import core.Bataille.TYPE;

public class Game {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		BatailleManager bm = new BatailleManager();
		Helper.choose_int(4, 1, sc);
		create_bataille(bm);
		bm.setMode(MODE.DEUX_JOUEURS);
		Bataille b = bm.create();

		b.addPlayer(PLAYER_N.ONE, create_player(PLAYER_N.ONE));
		b.addPlayer(PLAYER_N.TWO, create_player(PLAYER_N.TWO));

		if (false) {
			add_player_boat(b, PLAYER_N.ONE);
			add_player_boat(b, PLAYER_N.TWO);
		} else {
			if ((bm.getMode() == Bataille.MODE.UN_JOUEUR) || (bm.getMode() == Bataille.MODE.DEUX_JOUEURS))
				b.placePlayerBoat(PLAYER_N.ONE, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(2, 2)));
			if (bm.getMode() == Bataille.MODE.DEUX_JOUEURS)
				b.placePlayerBoat(PLAYER_N.TWO, new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(5, 5)));
		}

		try {
			play(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}

	private static void create_bataille(BatailleManager bm) {
		bm.setType(select_type());
		bm.setMode(select_mode());
	}

	private static Bataille.TYPE select_type() {
		Helper.printGameFormatted("Choisire un type de bataille:",
				new String[] { "Navale", "Radar", "Artillerie", "Alerte Rouge" });

		int choice = Helper.choose_int(1, 4, sc);
		return (new Bataille.TYPE[] { TYPE.NAVALE, TYPE.RADAR, TYPE.ARTILLERIE, TYPE.ALERTE_ROUGE })[choice];
	}

	private static Bataille.MODE select_mode() {
		Helper.printGameFormatted("Sélectionner le mode de Bataille:",
				new String[] { "Démo", "Un Joueur", "Deux Joueurs" });

		final int choice = Helper.choose_int(1, 3, sc);
		return (new Bataille.MODE[] { MODE.DEMO, MODE.UN_JOUEUR, MODE.DEUX_JOUEURS })[choice];
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

			if (!b.attacking_player_can_reattack()) {
				if (b.getType() == Bataille.TYPE.RADAR) {
					System.out.println("Bateau le plus proche: " + ((Radar) b).radar_reponse(attackPos));
				}

				b.switchAttackingPlayer();

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

		System.out.println(" * Gagné *");
	}

	private static Joueur create_player(final Bataille.PLAYER_N n) {
		System.out.print("nom (Joueur " + PLAYER_N.toString(n) + ") : ");
		return new HumanCmdPlayer(sc.nextLine(), sc);
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
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.toInt(b.type))) {
								to_print = b.getDamage(x - b.pos.x) ? 'x' : '-';
							}
						}
					} else {
						if (x == b.pos.x) {
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.toInt(b.type))) {
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