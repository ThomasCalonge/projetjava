public class Bateau
{
	public BATEAU_TYPE type;
	public ORIENTATION o;
	public Position pos;

	private int m_damage;

	public Bateau(final BATEAU_TYPE type, final ORIENTATION o, final Position p)
	{
		this.type = type;
		this.o    = o;
		this.pos  = pos;
		m_damage = 0;
	}

	public void setDamage (final int damage_pos)
	{ m_damage |= (1 << damage_pos); }

	public boolean getDamage (final int damage_pos)
	{ return (m_damage & (1 << damage_pos)) >> damage_pos; }

	public int getSize() { return type.toInt(); }

	public float getTotalDamage () 
	{
		
	}

	public void roate ()
	{
		switch (this.o)
		{
			case H:
				this.o = V;
				break;
			
			case V:
				this.o = H;
				break;
		}
	}
}