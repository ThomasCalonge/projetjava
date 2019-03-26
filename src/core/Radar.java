package core;

import java.util.ArrayList;

public class Radar extends Bataille {

	public Radar(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ATTAQUE_STATUS playerAttack(final Position pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean player_can_attack(PLAYER_N p) {
		// TODO Auto-generated method stub
		return false;
	}

	public void radar_reponse(final Position pos, ArrayList<Bateau> bateau_ennemi) {

        //Il faut l'attaque status et la position lors de l'attaque en argument
        //Par exemple lorsque l'on tire, et que l'on est en radar si on touche l'eau ça appelle cette fonction

        
        int pivot_deb_x = pos.x;
        int pivot_fin_x = pos.x;
        int pivot_deb_y = pos.y;
        int pivot_fin_y = pos.y;
        boolean found = false;
        boolean recherche = true;
        int count = 0;
        
		int[][] matrice = new int[10][10];
		

        for(int y = 0; y < 10; ++y){
	        for (int x = 0; x < 10; ++x){
				matrice[x][y] = 0;
				for (Bateau b: bateau_ennemi)
				{
					if (b.o == ORIENTATION.H){
						if (y == b.pos.y){
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.toInt(b.type))){
								matrice[x][y] = 1;
							}
						}
					}
					else{
						if (x == b.pos.x){
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.toInt(b.type))){
								matrice[x][y] = 1;
							}
						}
					}
				}
            }
        }

        while (found != true) {
            while (recherche){
                for(int i = pivot_deb_x; pivot_fin_x <= 10; i++){
                    for(int j = pivot_deb_y; pivot_fin_y <= 10; j++){
                        if(matrice[i][j]==1) found = true;
                    }
                    if (pivot_deb_y > 0) pivot_deb_y--;
                    if (pivot_fin_y < 10) pivot_fin_y++;
                }  
                if(pivot_deb_x == 0 && pivot_deb_y == 0 && pivot_fin_x == 0 && pivot_fin_y == 0) recherche = false;

                if (pivot_deb_x > 0) pivot_deb_x--;
                if (pivot_fin_x < 10) pivot_fin_x++;

                count++;

            }    
		}
		
		// POUR TOI THOMAS, TU DOIS L'INTEGRER A L'INTERFACE GRAPH
		System.out.print("Il y a un navire ennemi dans les "+count+" cases alentour.");
		//ATTENTION ! Thomas, quand tu feras l'affichage graphique, c'est ici que ça se passe.
    }

}