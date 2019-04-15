package core;

/**
 * Cette classe fournit des fonctions pour pouvoir créer une Bataille Navale.
 * Par défaut la classe sélectionne le type {@linkplain core.Bataille.TYPE#NAVALE <b>Bataille Navale</b>} et le mode {@linkplain core.Bataille.MODE#DEUX_JOUEURS <b>Deux Joueurs</b>}.
 * Les options sélectionnées sont stockées, il est donc possible de les changer pour pouvoir créer d'autres instances d'autre bataille,
 * ou de créer plein de fois la même. Une seule instance de la classe <b>Bataille</b> devrait suffir pour une application
 */
public class BatailleManager 
{
	private Bataille.TYPE m_type;
	private Bataille.MODE m_mode;
	
	/**
	 * Constructeur. Les options sont initialisées à {@linkplain core.Bataille.TYPE#NAVALE <b>Bataille Navale</b>}
	 * et {@linkplain core.Bataille.MODE#DEUX_JOUEURS <b>Deux Joueurs</b>}
	 */
	public BatailleManager()
	{
		m_type = Bataille.TYPE.NAVALE;
		m_mode = Bataille.MODE.DEUX_JOUEURS;
	}
	
	/**
	 * Choix du {@linkplain core.Bataille.TYPE <b>type</b>} de la bataille
	 * @param type le type voulu
	 */
	public void setType (final Bataille.TYPE type) { m_type = type; }
	
	/**
	 * Vhoix du {@linkplain core.Bataille.MODE <b>mode</b>} de la bataille
	 * @param mode le mode voulu
	 */
	public void setMode (final Bataille.MODE mode) { m_mode = mode; }

	/**
	 * Retourne le {@linkplain core.Bataille.TYPE <b>type</b>} sélectionné
	 * @return le type sélectionné
	 */
	public Bataille.TYPE getType() { return m_type; }
	
	/**
	 * Retourne le {@linkplain core.Bataille.MODE <b>mode</b>} sélectionné
	 * @return le type sélectionné
	 */
	public Bataille.MODE getMode() { return m_mode; }

	/**
	 * Instancie une bataille navale à partir des options qui ont été sélectionnée.
	 * @return Une bataille navale correspondant aux options sélectionnées
	 */
	public Bataille create () 
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