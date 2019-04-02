package core;

import java.util.ArrayList;

public class Radar extends BatailleNavale {

	public Radar(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}
	
	public int radar_reponse(final Position pos, ArrayList<Bateau> bateau_ennemi) {

		// Il faut l'attaque status et la position lors de l'attaque en argument
		// Par exemple lorsque l'on tire, et que l'on est en radar si on touche l'eau ça
		// appelle cette fonction

		int pivot_deb_x = pos.x;
		int pivot_fin_x = pos.x;
		int pivot_deb_y = pos.y;
		int pivot_fin_y = pos.y;
		boolean found = false;
		boolean recherche = true;
		int count = 0;

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

		while (found != true) {
			while (recherche) {
				for (int i = pivot_deb_x; i <= pivot_fin_x; i++) {
					for (int j = pivot_deb_y; j <= pivot_fin_y; j++) {
						if (matrice[i][j] == 1)
							found = true;
					}
					if (pivot_deb_y > 0)
						pivot_deb_y--;
					if (pivot_fin_y < 10)
						pivot_fin_y++;
				}
				if (pivot_deb_x == 0 && pivot_deb_y == 0 && pivot_fin_x == 0 && pivot_fin_y == 0)
					recherche = false;

				if (pivot_deb_x > 0)
					pivot_deb_x--;
				if (pivot_fin_x < 10)
					pivot_fin_x++;

				count++;

			}
		}

		return count;
	}
}
