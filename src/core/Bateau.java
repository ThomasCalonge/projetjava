package core;

public class Bateau
{
	public BATEAU_TYPE type;
	public ORIENTATION o;
	public Position pos;

	private int m_damage;

	/**
	 * Constructeur
	 * 
	 * @param type le type du bateau
	 * @param o l'orientation du bateau
	 * @param p la position du bateau
	 */
	public Bateau(final BATEAU_TYPE type, final ORIENTATION o, final Position p)
	{
		this.type = type;
		this.o    = o;
		this.pos  = p;
		m_damage = 0;
	}

	public void setDamage (final int damage_pos)
	{ m_damage |= (1 << damage_pos); }

	public int getDamage (final int damage_pos)
	{ return (m_damage & (1 << damage_pos)) >> damage_pos; }

	public int getSize() { return BATEAU_TYPE.toInt(type); }
}