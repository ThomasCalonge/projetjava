package core;

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
		final int oldDamages = m_damages;
		m_damages |= (1 << damage_pos);
		
		if (m_damages != oldDamages)
			++m_totalDamages;
		
		return m_damages != oldDamages;
	}

	public boolean getDamage (final int damage_pos)
	{ return ((m_damages & (1 << damage_pos)) >> damage_pos) == 1; }
	
	public boolean isDestroyed ()
	{ return m_totalDamages >= TYPE.toInt(type); }

	public int getSize() { return TYPE.toInt(type); }
}