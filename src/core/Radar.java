package core;

/**
 * Implémente le mode Radar d'une bataille Navale
 */
public class Radar extends Bataille {

	/**
	 * Constructeur de Radar
	 * @param mode le mode de la bataille
	 */
	public Radar(final MODE mode) {
		super(mode);
	}

	/**
	 * Fonction qui implémente la réponse du radar a une attaque. Elle scanne les positions autour du point d'attaque et retourne la distance
	 * vers le bateau le plus proche
	 * @param pos position de l'attaque
	 * @return distance vers le bateau le plus proche
	 */
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
		// Comme on veut les bateaux ennemis, si le joueurs qui attaque est le joueur 1 on utilise la valeur PLAYER_N.TWO qui correspond au joueur 2
		// sinon on utilise la valeur PLAYER_N.ONE qui correspond au joueur 1
		int[][] matrice = getBoatsMatrice((m_current_attacking_player == 0) ? PLAYER_N.TWO : PLAYER_N.ONE);

		while (found != true) 
		{
			count++;
			for (int j = pivot_deb_y; j <= pivot_fin_y; j++) 
			{
				for (int i = pivot_deb_x; i <= pivot_fin_x; i++) 
				{
					// Adrien: La matrice contient une valeur supérieure ou égale à 1 un s'il y a un bateau
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
