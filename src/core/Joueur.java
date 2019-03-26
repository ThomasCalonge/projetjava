package core;

import java.util.ArrayList;

public class Joueur
{
	private String m_name;
	private ArrayList<Bateau> m_boats;

	public Joueur (final String name)
	{
		m_name = new String(name);
		m_boats = new ArrayList<Bateau>();
	}
	
	public ArrayList<Bateau> getBoatsList() { return m_boats; }

	public String getName() { return m_name; }

	public void placeBoat(final Bateau b)
	{
		m_boats.add(b);
	}
	
	public ATTAQUE_STATUS getAttacked(final Position pos)
	{
		ATTAQUE_STATUS ret = ATTAQUE_STATUS.EAU;
		
		for (Bateau b: m_boats)
		{
			switch (b.o)
			{
				case H:
				{
					if (testHCollision(b, pos))
					{
						b.setDamage(pos.x - b.pos.x);
						ret = b.isDestroyed() ? ATTAQUE_STATUS.COULE : ATTAQUE_STATUS.TOUCHE;
					}
				} break;
				
				case V:
				{
					if(testVCollision(b, pos))
					{	
						b.setDamage(pos.y - b.pos.y);
						ret = b.isDestroyed() ? ATTAQUE_STATUS.COULE : ATTAQUE_STATUS.TOUCHE;
					}
				} break;
			}
		}
		
		return ret;
	}
	
	private boolean testHCollision (final Bateau b, final Position pos)
	{
		boolean ret = false;
		
		if (pos.y == b.pos.y)
		{
			if ((pos.x >= b.pos.x) && (pos.x < b.pos.x + b.getSize()))
				ret = true;
		}
		
		return ret;
	}
	
	private boolean testVCollision (final Bateau b, final Position pos)
	{
		boolean ret = false;
		
		if (pos.x == b.pos.x)
		{
			if ((pos.y >= b.pos.y) && (pos.y < b.pos.y + b.getSize()))
				ret = true;
		}
		
		return ret;
	}
}