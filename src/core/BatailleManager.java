package core;

public class BatailleManager 
{
	private BatailleNavale.TYPE m_type;
	private BatailleNavale.MODE m_mode;
	
	public void setType (final BatailleNavale.TYPE type) { m_type = type; }
	public void setMode (final BatailleNavale.MODE mode) { m_mode = mode; }
	public BatailleNavale create () 
	{
		switch(m_type)
		{
			case NAVALE:
				return new BatailleNavale(m_mode);
			
			case ARTILLERIE:
				return new Artillerie(m_mode);
			
			case RADAR:
				return new Radar(m_mode);
			
			case ALERTE_ROUGE:
				return new AlerteRouge(m_mode);
			
			// Jamais atteind mais eclipse affiche un message d'erreur sinon et j'aime pas
			default:
				return null;
		}
	}
}