package core;

import java.util.ArrayList;
import java.lang.Math;

// J'ai donc besoin d'un couple pour faire une liste de couples puisque qu'on
// est sur un tableau 2D.
class Couple {
	public int a;
	public int b;
}

public class IA extends Joueur {
	static private int ia_count = 0;

	public enum DIFFICULTE {
		FACILE, NORMAL, DUR,
	}

	// Je crée deux listes afin d'ajouter dans la liste pos_vide les positions dans
	// l'eau
	// et dans la liste pos_pleine les position avec bateaux
	// Adrien: J'ai déplacé les tableaux ici sinon la fonction pick_pos ne les
	// voyait pas.
	// plus je les ai passé en privés et mis l'initialisation dans le constructeur
	// de la classe (c'est plus dans l'esprit de la POO)
	private ArrayList<Couple> pos_vide;
	private ArrayList<Couple> pos_pleine;
	private int[][] matrice;
	private DIFFICULTE m_mode;

	public IA(DIFFICULTE difficulte, Joueur ennemis) 
	{
		super("IA" + (++ia_count));
		pos_vide = new ArrayList<Couple>();
		pos_pleine = new ArrayList<Couple>();
		matrice = Bataille.getBoatsMatrice(ennemis);
		m_mode = difficulte;
		m_boats.add(new Bateau(Bateau.TYPE.PORTE_AVION, ORIENTATION.H, new Position(4,4)));
	}


	public void setMode(final DIFFICULTE mode) {
		m_mode = mode;
	}

	public Couple pick_pos(ArrayList<Couple> position) {
		int taille_tab = position.size();
		int k = (int) (Math.random() * taille_tab);
		/*
		 * Lorsque l'on pick une position, il faut la retirer de la liste des positions
		 * possibles Sinon, l'ia risque de la reprendre plusieurs fois si le hasard le
		 * décide. Si elle se rpéèten se sera encore plus simple qu'un mode facile, car
		 * jamais un joueur ne ferait cela
		 */
		if (pos_pleine.contains(position.get(k))) {
			pos_pleine.remove(position.get(k));
		} else {
			pos_vide.remove(position.get(k));
		}

		return position.get(k);
	}

	public Couple level_ia(/*final Position pos, ArrayList<Bateau> bateau_ennemi, final DIFFICULTE difficulte*/) {

		Couple ret = null;

		// pos_pleine ou pos_vide le type de case que c'est.
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

		switch (m_mode) 
		{
		case FACILE:
		{
			if (x < 5) {
				ret = pick_pos(pos_vide);
			} else {
				ret = pick_pos(pos_pleine);
			}
		} break;
		case NORMAL:
		{
			if (x < 4) {
				ret = pick_pos(pos_vide);
			} else {
				ret = pick_pos(pos_pleine);
			}
		} break;
		case DUR:
		{
			if (x < 3) {
				ret = pick_pos(pos_vide);
			} else {
				ret = pick_pos(pos_pleine);
			}
		} break;

		/// Pour gérer l'erreur.
		}

		return ret;
	}

	@Override
	public Position getAttackPos(Bataille.TYPE type) {
		Couple c = level_ia();
		// TODO: Implémenter la sélection des coordonnées
		return new Position(c.a,c.b);
	}

	@Override
	public void placeBoat(Bateau b) {
		// Cette fonction ne devrait jamais être appelée depuis le code pour un joueur
		// IA
		// C'est une erreur du programmeur et non de l'utilisateur donc on utilise une
		// assertion
		// plutôt qu'une exception
		System.err.println("\"IA.placeBoat\" ne devrais jamais être appelée");
		System.exit(-1);
	}

	@Override
	public TYPE getType() {
		return TYPE.IA;
	}
}
