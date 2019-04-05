package core;

import java.util.ArrayList;

public abstract class Bataille
{
	protected Joueur[] m_players;
	protected int m_current_attacking_player;
	protected AttackData [] m_current_attack_data;
	
	public enum TYPE
	{
		NAVALE,
		ARTILLERIE,
		RADAR,
		ALERTE_ROUGE;
	}
	
	public enum MODE
	{
		DEMO,
		UN_JOUEUR,
		DEUX_JOUEURS;
	}

	public enum PLAYER_N
	{
		ONE,
		TWO;

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
	
	public void addPlayer(final PLAYER_N n, final Joueur p)
	{ m_players[playerToInt(n)] = p; }

	public abstract TYPE getType();
	
	public Joueur getPlayer(final PLAYER_N n)
	{ return m_players[playerToInt(n)]; }

	public Joueur getCurrentAttackingPlayer()
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

	protected int playerToInt(final PLAYER_N n)
	{ return (n == PLAYER_N.ONE) ? 0 : 1; }
	
	private boolean attackTouche()
	{ return m_current_attack_data[m_current_attacking_player].status != ATTAQUE_STATUS.EAU; }
	
	private ATTAQUE_STATUS player_one_attacke_player_two(final Position pos)
	{ return m_players[1].getAttacked(pos); }
	
	private ATTAQUE_STATUS player_two_attacke_player_one(final Position pos)
	{ return m_players[0].getAttacked(pos); }
	

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