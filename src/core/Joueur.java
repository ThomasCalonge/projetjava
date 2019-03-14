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

	public String getName() { return m_name; }

	public ATTAQUE_STATUS getAttacked(final Position pos)
	{
		return ATTAQUE_STATUS.EAU;
	}

	public void placeBoat(final Bateau b)
	{
		m_boats.add(b);
	}
}