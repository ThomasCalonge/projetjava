package core;

import java.util.ArrayList;
import java.lang.Math;

class Couple {
	public int a;
	public int b;
}

public class IA extends Joueur {
	static int ia_count = 0;
	public enum DIFFICULTE {
		FACILE, NORMAL, DUR,
	}

	public IA(DIFFICULTE difficulte) {
		super("IA" + (++ia_count));
	}

	private DIFFICULTE m_mode;

	public void setMode(final DIFFICULTE mode) {
		m_mode = mode;
	}

	public Couple pick_pos(ArrayList<Couple> position) {
		int taille_tab = position.size();
		int k = (int) (Math.random() * taille_tab);
		return position.get(k);

	}

	public void level_ia(final Position pos, ArrayList<Bateau> bateau_ennemi, final DIFFICULTE difficulte) {

		int[][] matrice = new int[10][10];

		for (int y = 0; y < 10; ++y) {
			for (int x = 0; x < 10; ++x) {
				matrice[x][y] = 0;
				for (Bateau b : bateau_ennemi) {
					if (b.o == ORIENTATION.H) {
						if (y == b.pos.y) {
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.toInt(b.type))) {
								matrice[x][y] = 1;
							}
						}
					} else {
						if (x == b.pos.x) {
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.toInt(b.type))) {
								matrice[x][y] = 1;
							}
						}
					}
				}
			}
		}

		// Je crée deux listes afin d'ajouter dans la liste pos_vide les positions dans
		// l'eau
		// et dans la liste pos_pleine les position avec bateaux

		ArrayList<Couple> pos_vide = new ArrayList<Couple>();
		ArrayList<Couple> pos_pleine = new ArrayList<Couple>();

		// J'ai donc besoin d'un couple pour faire une liste de couples puisque qu'on
		// est sur un tableau 2D.

		// os_pleine ou pos_vide le type de case que c'est.

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Couple c = new Couple();
				c.a = i;
				c.b = j;
				if (matrice[i][j] == 1) {
					pos_pleine.add(c);
				} else {
					pos_vide.add(c);
				}
			}
		}

		// On va créer la fonction qui choisi une position vide ou plein et qui sera
		// appelée
		// selon la difficulté pour ressortir une position choisie de manière random

		/*
		 * public Couple pick_pos(ArrayList<Couple> position) { taille_tab =
		 * position.size(); k = (Math.random()*taille_tab); return position.get(k); }
		 */

		// Maintenant on passe concrètement à la difficulté
		// On va créer un random afin de déterminer le nombre de chances de tomber sur
		// une pos vide ou plein selon la difficulté choisie dans mode (donc avec un
		// switch pour plus de clareté).

		int x = (int) (Math.random() * 9);

		switch (m_mode) {
		case FACILE:
			if (x < 5) {
				System.out.println(pick_pos(pos_vide));
			} else {
				System.out.println(pick_pos(pos_pleine));
			}
			break;
		case NORMAL:
			if (x < 4) {
				System.out.println(pick_pos(pos_vide));
			} else {
				System.out.println(pick_pos(pos_pleine));
			}
			break;
		case DUR:
			if (x < 3) {
				System.out.println(pick_pos(pos_vide));
			} else {
				System.out.println(pick_pos(pos_pleine));
			}
			break;

		/// Pour gérer l'erreur.

		default:
			break;
		}

	}

	@Override
	public Position getAttackPos(Bataille.TYPE type) {
		//TODO: Implémenter la sélection des coordonnées
		return null;
	}

	@Override
	public void placeBoat(Bateau b) {
		//Cette fonction ne devrait jamais être appelée depuis le code pour un joueur IA
		//C'est une erreur du programmeur et non de l'utilisateur donc on utilise une assertion
		//plutôt qu'une exception
		assert(false);
	}

	@Override
	public TYPE getType() {
		return TYPE.IA;
	}

}
