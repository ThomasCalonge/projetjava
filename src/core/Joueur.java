package core;

import java.util.ArrayList;

/**
 * Représente la classe de Base pour tous les joueurs.
 * Un joueur est caractérisé par son nom, une liste de ses bateaux et une liste de ses attaques
 */
public abstract class Joueur
{
	private String m_name; //Nom du joueur
	private ArrayList<AttackData> m_attackDatas; //Attaques du joueur

	protected ArrayList<Bateau> m_boats; //Bateaux du joueur

	public enum TYPE
	{
		HUMAIN, IA
	}

	/**
	 * Construit un joueur à partir du nom que l'on veut lui donner.
	 * Le nom est copié.
	 * 
	 * @param name nom du joueur
	 */
	public Joueur (final String name)
	{
		m_name = new String(name);
		m_boats = new ArrayList<Bateau>();
		m_attackDatas = new ArrayList<AttackData>();
	}
	
	public ArrayList<Bateau> getBoatsList() { return m_boats; }

	public String getName() { return m_name; }
	
	abstract public void placeBoat(final Bateau b);
	abstract public TYPE getType();
	public abstract Position getAttackPos(Bataille.TYPE type);
	
	/**
	 * Fonction appelée lorsque le joueur se fait attaquer
	 * 
	 * @param pos la position à laquelle il se fait attaquer
	 * @return le status de l'attaque (touché, coulé ou à l'eau)
	 */
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

	/**
	 * Introduit les données d'une attaque dans la liste des attaques du joueurs
	 * 
	 * @param data les données à être introduite
	 */
	public void pushAttackData (final AttackData data) { m_attackDatas.add(data); }
	public ArrayList<AttackData> getAttackDatas () { return m_attackDatas; }

	
	/// Fonctions interne à la classe
	
	/**
	 * Test la collision entre un point et bateau b orienté <strong>HORIZONTALEMENT</strong>
	 * 
	 * @param b la bateau à tester
	 * @param pos la position du point un tester
	 * @return <strong>true</strong> le point se situe dans le bateau<p>
	 *         <strong>false</strong> le point est en dehors du bateau
	 */
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
	
	/**
	 * Test la collision entre un point et bateau b orienté <strong>VERTICALEMENT</strong>
	 * 
	 * @param b la bateau à tester
	 * @param pos la position du point un tester
	 * @return <strong>true</strong> le point se situe dans le bateau<p>
	 *         <strong>false</strong> le point est en dehors du bateau
	 */
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