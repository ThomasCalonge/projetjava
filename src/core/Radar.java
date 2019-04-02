package core;

import java.util.ArrayList;

public class Radar extends Bataille {

	public Radar(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	public int radar_reponse(final Position pos) {

		ArrayList<Bateau> bateau_ennemi = m_players[(PLAYER_N.toInt(getCurrentAttackingPlayer()) + 1) % 2].getBoatsList();
		// Il faut l'attaque status et la position lors de l'attaque en argument
		// Par exemple lorsque l'on tire, et que l'on est en radar si on touche l'eau ça
		// appelle cette fonction

		int pivot_deb_x = Math.max(pos.x - 1, 0);
		int pivot_fin_x = Math.min(pos.x + 1, 9);
		int pivot_deb_y = Math.max(pos.y - 1, 0);
		int pivot_fin_y = Math.min(pos.y + 1, 9);
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

		while (found != true) 
		{
			count++;
			for (int j = pivot_deb_y; j <= pivot_fin_y; j++) 
			{
				for (int i = pivot_deb_x; i <= pivot_fin_x; i++) 
				{
					if (matrice[i][j] == 1)
					{
						found = true;
						break;
					}
				}
				
				if (found)
					break;
			}

			if (pivot_deb_x > 0)
				pivot_deb_x--;
			if (pivot_fin_x < 9)
				pivot_fin_x++;

			if (pivot_deb_y > 0)
				pivot_deb_y--;
			if (pivot_fin_y < 9)
				pivot_fin_y++;
		}

		return count;
	}

	@Override
	public TYPE getType() {
		return TYPE.RADAR;
	}
}
