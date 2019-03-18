package core;

public abstract class Bataille
{
	private Joueur[] m_players;
	private int m_current_attacking_player;
	
	public enum TYPE
	{
		NAVALE,
		ARTILLERIE,
		RADAR,
		ALERTE_ROUGE
	}
	
	public enum MODE
	{
		DEMO,
		UN_JOUEUR,
		DEUX_JOUEURS;
	}
	
	public Bataille(final MODE mode)
	{
		m_players = new Joueur[2];
	}
	
	public void initFirstAttackingPlayer() { m_current_attacking_player = 0; }
	
	public abstract ATTAQUE_STATUS playerAttack();
}