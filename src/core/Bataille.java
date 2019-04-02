package core;

import java.util.ArrayList;

public abstract class Bataille
{
	protected Joueur[] m_players;
	protected PLAYER_N m_current_attacking_player;
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
		
		public static int toInt(final PLAYER_N p)
		{
			int ret = 0;
			switch(p)
			{
			case ONE:
				ret = 0;
				break;
				
			case TWO:
				ret = 1;
				break;
			}
			return ret;
		}

		public static PLAYER_N fromInt(final int n)
		{
			PLAYER_N ret = ONE;

			if (n == 1)
				ret = TWO;
			
			return ret;
		}
		
		public static String toString (final PLAYER_N p)
		{
			String ret = "1";
			
			switch(p)
			{
			case TWO:
				ret = "2";
				break;

			default:
				break;
			}
			
			return ret;
		}
	}
	
	public Bataille(final MODE mode)
	{
		m_players = new Joueur[2];
		m_current_attacking_player = PLAYER_N.ONE;
		m_current_attack_data = new AttackData[2];
	}
	
	public void addPlayer(final PLAYER_N n, final Joueur p)
	{ m_players[PLAYER_N.toInt(n)] = p; }

	public abstract TYPE getType();
	
	public Joueur getPlayer(final PLAYER_N n)
	{
		Joueur ret = m_players[0];
		
		switch(n)
		{
		case TWO:
			ret = m_players[1];
			break;
		
		default:
			break;
		}
		
		return ret;
	}
	
	public void placePlayerBoat(final PLAYER_N p, final Bateau b) { m_players[PLAYER_N.toInt(p)].placeBoat(b); }
	public PLAYER_N getCurrentAttackingPlayer() { return m_current_attacking_player; }
	public void switchAttackingPlayer(){ m_current_attacking_player = PLAYER_N.fromInt((PLAYER_N.toInt(m_current_attacking_player) + 1) % 2); }
	public boolean canContinue() 
	{
		return (!playerWins(0)) && (!playerWins(1));
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
	
	public ATTAQUE_STATUS playerAttack(final Position pos) 
	{
		final ATTAQUE_STATUS s = (m_current_attacking_player == PLAYER_N.ONE) ? player_one_attacke_player_two(pos) : player_two_attacke_player_one(pos);
		final AttackData     d = new AttackData(pos,s);
		
		m_current_attack_data[PLAYER_N.toInt(m_current_attacking_player)] = d;
		m_players[PLAYER_N.toInt(m_current_attacking_player)].pushAttaqueData(d);
		
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

	private boolean firstTimeAttack()
	{	
		final ArrayList<AttackData> attacking_player_attack_datas = m_players[PLAYER_N.toInt(m_current_attacking_player)].getAttaqueData();
		final AttackData attacking_player_current_attack_data = m_current_attack_data[PLAYER_N.toInt(m_current_attacking_player)];
		
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
	
	private boolean attackTouche()
	{
		return m_current_attack_data[PLAYER_N.toInt(m_current_attacking_player)].status != ATTAQUE_STATUS.EAU;
	}

	private ATTAQUE_STATUS player_one_attacke_player_two(final Position pos)
	{
		return m_players[1].getAttacked(pos);
	}

	private ATTAQUE_STATUS player_two_attacke_player_one(final Position pos)
	{
		return m_players[0].getAttacked(pos);
	}
}