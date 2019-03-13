import java.util.ArrayList;

class ObjetBateau
{
	public Bateau boat;
	public Position pos;

	public ObjetBateau(final Bateau boat, final Position pos)
	{
		this.boat = boat;
		this.pos  = pos;
	}
}

public class Joueur
{
	private String m_name;
	private ArrayList<ObjetBateau> m_boats;

	public Joueur (final String name)
	{
		m_name = new String(name);
		m_boats = new ArrayList<ObjetBateau>();
	}

	public String getName() { return m_name; }

	public ATTAQUE_STATUS getAttacked(final Position pos)
	{
		return ATTAQUE_STATUS.EAU;
	}

	public void placeBoat(final Bateau b, final Position pos)
	{
	}
}