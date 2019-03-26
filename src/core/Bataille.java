package core;

public abstract class Bataille
{
	protected Joueur[] m_players;
	protected int m_current_attacking_player;
	
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
		
		public static String toString (final PLAYER_N p)
		{
			String ret = "1";
			
			switch(p)
			{
			case TWO:
				ret = "2";
				break;
			}
			
			return ret;
		}
	}
	
	public Bataille(final MODE mode)
	{
		m_players = new Joueur[2];
	}
	
	public void initFirstAttackingPlayer() { m_current_attacking_player = 0; }
	
	public void addPlayer(final PLAYER_N n, final Joueur p)
	{ m_players[PLAYER_N.toInt(n)] = p; }
	
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
	
	public void placePlayerBoat(final PLAYER_N p, final Bateau b)
	{
		m_players[PLAYER_N.toInt(p)].placeBoat(b);
	}
	
	/**
	 * Function abstraite appelé lorsque le joueur devant attquer attaque l'autre joueur
	 */
	public abstract ATTAQUE_STATUS playerAttack(final Position pos);
	public abstract boolean player_can_attack(final PLAYER_N p);
}