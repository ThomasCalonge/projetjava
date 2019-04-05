package core;

public class Radar extends Bataille {

	public Radar(final MODE mode) {
		super(mode);
	}

	public int radar_reponse(final Position pos) {
		// Il faut l'attaque status et la position lors de l'attaque en argument
		// Par exemple lorsque l'on tire, et que l'on est en radar si on touche l'eau ça
		// appelle cette fonction

		int pivot_deb_x = Math.max(pos.x - 1, 0);
		int pivot_fin_x = Math.min(pos.x + 1, 9);
		int pivot_deb_y = Math.max(pos.y - 1, 0);
		int pivot_fin_y = Math.min(pos.y + 1, 9);
		boolean found = false;
		int count = 0;
		
		// Modification Adrien (aka DridriLaBastos): J'ai ajouté une fonction qui retourne la matrice des bateaux dans la classe bataille
		// Comme on veut les bateaux ennemis, si le joueurs qui attaque est le joueur 0 on utilise la PLAYER_N.TWO (le joueur deux)
		// sinon le joueur 1
		int[][] matrice = getBoatsMatrice((m_current_attacking_player == 0) ? PLAYER_N.TWO : PLAYER_N.ONE);

		while (found != true) 
		{
			count++;
			for (int j = pivot_deb_y; j <= pivot_fin_y; j++) 
			{
				for (int i = pivot_deb_x; i <= pivot_fin_x; i++) 
				{
					// Adrien: La matrice contient une valeur supérieure ou égale à un s'il y a un bateau
					if (matrice[i][j] >= 1)
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
