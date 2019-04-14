package core;

/**
 * Décris un bateau. Un bateau est caractérisé par un {@linkplain core.Bateau.TYPE <b>type</b>}, une {@linkplain core.ORIENTATION <b>orientation</b>} et une {@linkplain core.Position <b>position</b>}
 * ainsi qu'un niveau de dommage. Les bateaux sont représentés par différentes parties, chaque partie allant sur une case du plateau de la bataille navale. Une partie peut être endomagée ou pas.
 * Le bateau est en vie tant que toutes ses parties n'ont pas été touchées sinon il est coulé.
 */
public class Bateau
{
	public TYPE type;
	public ORIENTATION o;
	public Position pos;

	//Partie endomagée ou intactes du bateau
	private int m_damages;
	//Dommages totaux du bateau
	private int m_totalDamages;
	
	/**
	 * Les différents types de bateaux dans une bataille navale.
	 */
	public enum TYPE
	{
		/** A une taille de 5 */ PORTE_AVION,
	    /** A une taille de 4 */ SOUS_MARIN,
		/** A une taille de 3 */ CUIRASSE,
		/** A une taille de 1 */ ZODIAC;
		
		/**
		 * Retourne la taille associée à un type de bateau
		 * @param t le type dont on veut connaître la taille
		 * @return la taille associée au type
		 */
		static public int size (final TYPE t)
	    {
	        int ret = 0;
	        switch (t)
	        {
	            case PORTE_AVION:
	                ret = 5;
	                break;
	            
	            case SOUS_MARIN:
	                ret = 4;
	                break;
	            
	            case CUIRASSE:
	                ret = 3;
					break;
				
				case ZODIAC:
					ret = 1;
					break;
	        }
	        return ret;
	    }
		
		/**
		 * Retourne une chaîne de caractère décrivant un type.
		 * @param t Le type dont on veut récupérer une chaîne
		 * @return Une chaîne de caractère décrivant le type
		 */
		public static String toString (final TYPE t)
		{
			String ret = "";
			
			switch(t)
			{
			case PORTE_AVION:
				ret = "Porte-avion";
				break;
				
			case CUIRASSE:
				ret = "Cuirassé";
				break;
				
			case SOUS_MARIN:
				ret = "Sous-marin";
				break;
				
			case ZODIAC:
				ret = "Zodiac";
				break;
			}
			
			return ret;
		}
	}

	/**
	 * Constructeur
	 * 
	 * @param type le type du bateau
	 * @param o l'orientation du bateau
	 * @param p la position du bateau
	 */
	public Bateau(final TYPE type, final ORIENTATION o, final Position p)
	{
		this.type = type;
		this.o    = o;
		this.pos  = p;
		m_damages = 0;
		m_totalDamages = 0;
	}

	/**
	 * Permet de définir une partie du bateau comme endomagée. Pour choisir une partie on indique son numéro. Le numéro d'une partie correspond à sa distance depuis le début du bateau.
	 * On considère que le début du bateau (et donc sa première partie) se situe au niveau des coordonnées décrit par la position du bateau.
	 * Par exemple un bateau de taille 10 contient 10 parties numérotée de 0 jusqu'a 9, 0 étant la première partie et 9 étant la dernière. Pour endomager la 5e partie d'un tel bateau,
	 * on écrira <br>
	 * {@code setDamage(4); }<br>
	 * Si les dommages ont pu être appliqués, la fonction retourne vraie, sinon elle retourne faux. Il y a deux conditions pour que les dommages soit appliqués:
	 * <ol>
	 * 	<li>La partie que l'on veut toucher est bien dans l'intervalle [0; taille du bateau - 1]</li>
	 * 	<li>La partie n'a jamais été touchée avant</li>
	 * </ol>
	 * @param damage_pos la position de la partie a endomager
	 * @return vrai si les dommages ont été appliqués, faux sinon
	 */
	public boolean setDamage (final int damage_pos)
	{
		if (damage_pos < TYPE.size(type))
		{
			final int oldDamages = m_damages;
			
			m_damages |= (1 << damage_pos);
			
			// Si m_damage n'est pas différent de sa valeur avant modification
			// C'est que l'on a attaqué une partie déjà touché
			// Sinon le nombre total de domages est augmenté
			if (m_damages != oldDamages)
				++m_totalDamages;
			
			return m_damages != oldDamages;
		}
		return false;
	}

	/**
	 * Indique par un booléen si une partie du bateau a été endomagée ou pas. Pour avoir la définition de ce qu'est une partie, voir la documentation de la fonction {@linkplain core.Bateau#setDamage(int) <b>setDamage</b>}
	 * @param damage_pos la partie du bateau du bateau
	 * @return vraie si la partie est endomagée, faux sinon
	 */
	public boolean getDamage (final int damage_pos)
	{ return ((m_damages & (1 << damage_pos)) >> damage_pos) == 1; }
	
	/**
	 * Indique si la bateau est détruit ou pas
	 * @return vrai si le bateau est détruit, faux sinon
	 */
	public boolean isDestroyed ()
	{ return m_totalDamages >= TYPE.size(type); }

	/**
	 * Retourne la taille en nombre de case du bateau
	 * @return la taille en nombre de case
	 */
	public int getSize() { return TYPE.size(type); }
}