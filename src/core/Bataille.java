package core;

import java.util.ArrayList;

/**
 * Bataille est une classe abstraite qui sert de classe de base pour implémenter
 * la logique de toutes les autres batailles.
 * Elle fournit une interface ainsi qu'une fonction abstraite {@linkplain core.Bataille#getType() <b>getType</b>}
 * qui permet récupérer le type d'une bataille.
 * 
 * Enfin elle déclare plusieurs énumération afin de faciliter et d'encapsuler certaind élément
 * nécessaire à la description d'une bataille: le type, le mode de jeux et
 * une énumération pour accéder à un numéro de joueur.
 */
public abstract class Bataille
{
	protected Joueur[] m_players;
	protected int m_current_attacking_player;
	protected AttackData [] m_current_attack_data;
	
	/**
	 * Enumération qui permet de représenter les différents types d'une bataille.
	 * Les types vont influencer la façon de saisir les coordonnées et la façon d'attaquer
	 */
	public enum TYPE
	{
		/** 
		* Bataille Navale Classique: Les coordonées sont sélectionnées à la suite 
		*/
		NAVALE,
		
		/** 
		 * Bataille Navale Radar : Les coordonnées sont sélectionnées de façon standard<br>
		 * mais la distance entre le point d'attaque et le bateau le plus proche
		 * est affichée après chaque tirs ratés.
		 */
		RADAR,
		
		/**
		 * Bataille Navale Artillerie : La coordonnée verticale est dabord sélectionnée.
		 * Ensuite les coordonnées horizontale défile l'une après l'autre et il faut choisir la bonne
		 */
		ARTILLERIE,
		
		/**
		 * Bataille Navale Alerte-Rouge: Ce mode mélange tous les modes de bataille,
		 * l'attaque ce fait à la façon d'une bataille navale radar
		 * et la sélection des coordonnées se fait à la façon d'une bataille navale
		 * artillerie
		 * @see core.Bataille.TYPE#RADAR
		 * @see core.Bataille.TYPE#ARTILLERIE
		 */
		ALERTE_ROUGE;
	}
	
	/**
	 * Enumération qui permet de représenter les différents modes d'une bataille.
	 * Le mode d'une bataille définit le type de joueur qui pourront y jouer.<br>
	 * <ul>
	 * 	<li><strong>DEMO</strong>: ce mode fait s'affronter deux IA
	 * 	et laisse le spectateur regarder le déroulement de la bataille</li>
	 * <li><strong>UN_JOUEUR</strong>: ce mode fait s'affronter un joueur humain et une IA</li>
	 * <li><strong>DEUX_JOUEURS</strong>: ce mode fait s'affronter deux joueurs humains</li>
	 * </ul>
	 */
	public enum MODE
	{
		/** IA vs IA */ DEMO,
		/** Humain vs IA */ UN_JOUEUR,
		/** Humain vs Humain*/ DEUX_JOUEURS;
	}
	
	/**
	 * PLAYER_N permet d'associer un littéral à un numéro de joueur,
	 * afin d'éviter d'avoir des chiffres en dur dans le code.<br>
	 * Sans cette énumération, récupérer une instance d'un joueur de la bataille s'écrirait:<br>
	 * {@code b.getPlayer(0);}<br>
	 * Ce qui nécessite de savoir que la classe stocke le premier joueur à l'indice 0 
	 * de quelque chose. Or cette information n'est pas intéressante 
	 * pour qui que ce soit en dehors de la classe. Ainsi on préfèrera écrire:
	 * {@code b.getPLayer(PLAYER_N.ONE); }<br>
	 * pour un même résultat
	 */
	public enum PLAYER_N
	{
		/** littéral représentant le premier joueur */ONE,
		/** littéral représentant le deuxième joueur */TWO;

		/**
		 * Renvoie une chaîne de caractère décrivant une valeur de <strong>PLAYER_N</strong><br>
		 * La chaîne sera une traduction d'une valeur de PLAYER_N en le chiffre quelle représente. Par exemple:
		 * {@code toString(PLAYER_N.ONE)} retourne la chaîne de caractère "1"
		 * 
		 * @param n la valeur de PLAYER_N a transformer
		 * @return la chaîne de caractère correspondant à <b>n</b>
		 */
		static public String toString (final PLAYER_N n)
		{ return (n == PLAYER_N.ONE) ? "1" : "2"; }
	}
	
	public Bataille(final MODE mode)
	{
		m_players = new Joueur[2];
		m_current_attacking_player = 0;
		m_current_attack_data = new AttackData[2];
	}

	public int[][] getBoatsMatrice(final PLAYER_N n)
	{
		int[][] matrice = new int[10][10];
		ArrayList<Bateau> boats = m_players[playerToInt(n)].getBoatsList();

		for(int y = 0; y < 10; ++y)
		{
			for (int x = 0; x < 10; ++x)
			{
				int value = 0;	
				for (Bateau b: boats)
				{
					if (b.o == ORIENTATION.H)
					{
						if (y == b.pos.y)
						{
							if ((x >= b.pos.x) && (x < b.pos.x + Bateau.TYPE.toInt(b.type)))
								value = b.getDamage(x - b.pos.x) ? 2 : 1;
						}
					}
					else
					{
						if (x == b.pos.x)
						{
							if ((y >= b.pos.y) && (y < b.pos.y + Bateau.TYPE.toInt(b.type)))
								value = b.getDamage(y - b.pos.y) ? 2 : 1;
						}
					}
				}
				matrice[x][y] = value;
			}
		}

		return matrice;
	}
	
	public abstract TYPE getType();

	public void addPlayer(final PLAYER_N n, final Joueur p)
	{ m_players[playerToInt(n)] = p; }

	/**
	 * Retourne l'instance d'un joueur. L'instance que cette fonction retourne n'est pas modifiable
	 * 
	 * @param n le joueur dont on veut récupérer l'instance
	 * @return l'instance du joueur non modifiable
	 */
	public final Joueur getPlayer(final PLAYER_N n)
	{ return m_players[playerToInt(n)]; }

	/**
	 * Retourne l'instance du joueur qui attaque. Cette instance est non modifiable.
	 * 
	 * @return l'instance non modifiable du joueur qui attaque.
	 */
	public final Joueur getCurrentAttackingPlayer()
	{ return m_players[m_current_attacking_player]; }
	
	public void placePlayerBoat(final PLAYER_N n, final Bateau b)
	{ m_players[playerToInt(n)].placeBoat(b); }

	public void switchAttackingPlayer()
	{ m_current_attacking_player = ++m_current_attacking_player % 2; }

	public boolean canContinue() 
	{ return (!playerWins(0)) && (!playerWins(1)); }

	public ATTAQUE_STATUS playerAttack(final Position pos) 
	{
		final ATTAQUE_STATUS s = (m_current_attacking_player == 0) ? player_one_attacke_player_two(pos) : player_two_attacke_player_one(pos);
		final AttackData     d = new AttackData(pos,s);
		
		m_current_attack_data[m_current_attacking_player] = d;
		m_players[m_current_attacking_player].pushAttaqueData(d);
		
		return s;
	}
	
	public boolean attacking_player_can_reattack() 
	{	
		final boolean cond1 = attackTouche();
		final boolean cond2 = firstTimeAttack();
		
		// Le joueur peut de nouveau attaquer si
		//  1 - son attaque à touchée
		//  2 - c'est la première fois qu'il attaque à cette position
		return cond1 && cond2;
	}

	/**
	 * Permet de transformer une valeur {@linkplain core.Bataille.PLAYER_N PLAYER_N} en un entier.
	 * Cet entier pourra être utilisé comme index dans un tableau PLAYER_N.ONE est donc associé a 0, puis ensuite les velurs sont
	 * incrémentées.
	 * 
	 * @param n la valeur de PLAYER_N à transformer en entier
	 * @return en eniter correspondant à <b>n</b>
	 */
	protected int playerToInt(final PLAYER_N n)
	{ return (n == PLAYER_N.ONE) ? 0 : 1; }
	
	/** Renvoie true si l'attaque qui vient d'être effectuer à touché un bateau adverse, false sinon */
	private boolean attackTouche()
	{ return m_current_attack_data[m_current_attacking_player].status != ATTAQUE_STATUS.EAU; }
	
	/**  
	 * Ces deux fonctions font s'attaquer les joueurs. Elles prennent en paramètre la position à laquelle
	 * le joueur qui attaque, attaque le joueur qui se fait attaquer
	 */
	private ATTAQUE_STATUS player_one_attacke_player_two(final Position pos)
	{ return m_players[1].getAttacked(pos); }
	
	private ATTAQUE_STATUS player_two_attacke_player_one(final Position pos)
	{ return m_players[0].getAttacked(pos); }
	
	/**
	 * Cette fonction récupère l'attaque que le joueur vient de faire et dit s'il a déjà attaqué au même endroit ou pas
	 * Si la valeur de retour est true le joueur n'a jamais attaqué à cet endroit
	 * Si c'est false le joueur n'est pas très intelligent et a au moins attaquer une fois au même endroit
	 */
	private boolean firstTimeAttack()
	{	
		final ArrayList<AttackData> attacking_player_attack_datas = m_players[m_current_attacking_player].getAttaqueData();
		final AttackData attacking_player_current_attack_data = m_current_attack_data[m_current_attacking_player];
		
		// Si l'attaque courrante est aux mêmes coordonnées qu'une des autre attaque du joueur
		// Ce n'est pas la première fois qu'il attaque à cette position
		for (int i = 0; i < attacking_player_attack_datas.size() - 1; ++i)
		{
			if ((attacking_player_current_attack_data.pos.x == attacking_player_attack_datas.get(i).pos.x) &&
			(attacking_player_current_attack_data.pos.y == attacking_player_attack_datas.get(i).pos.y))
			return false;
		}
		
		return true;
	}

	/**
	 * Cette fonction permet de savoir si un joueur à gagné ou pas.
	 * Un joueur a gagné si tous les bateaux adverses ont été coulé
	 */
	private boolean playerWins(final int p)
	{
		boolean ret = true;
		for (Bateau b: m_players[p].getBoatsList())
		{
			if (!b.isDestroyed())
			{
				ret = false;
				break;
			}
		}
		return ret;
	}
}