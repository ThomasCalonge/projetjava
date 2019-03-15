package core;

public class Bataille
{
	private Joueur[] m_players;
	
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
	
	public Bataille(final TYPE type)
	{
		m_players = new Joueur[2];
	}
}