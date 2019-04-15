package core;
/**
 * Bateau est une classe qui sert pour définir l'objet Bateau dans notre BatailleNavale
 * Elle est composée d'une orientation, d'une position et d'un type}
 * Type étant une énumération, 
 * Il contient aussi deux variables de dégats, permettant de savoir si le bateau à était touché et si il à était coulé
 */
public class Bateau
{
	public TYPE type;
	public ORIENTATION o;
	public Position pos;

	private int m_damages;
	private int m_totalDamages;
	
	public enum TYPE
	{
		PORTE_AVION,
	    SOUS_MARIN,
		CUIRASSE,
		ZODIAC;
		
		static public int toInt (final TYPE t)
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

	public boolean setDamage (final int damage_pos)
	{
		if (damage_pos < TYPE.toInt(type))
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

	public boolean getDamage (final int damage_pos)
	{ return ((m_damages & (1 << damage_pos)) >> damage_pos) == 1; }
	
	public boolean isDestroyed ()
	{ return m_totalDamages >= TYPE.toInt(type); }

	public int getSize() { return TYPE.toInt(type); }
}
