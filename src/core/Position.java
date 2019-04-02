package core;

/**
 * Représente une position en deux dimensions
 */
public class Position
{
	public int x;
	public int y;
	
	/**
	 * Constructeur de la classe Position. Permet de créer une nouvelle instance de Position
	 * en précisant la valeur de x et de y
	 * @param x x
	 * @param y y
	 */
	public Position(final int x, final int y)
	{ this.x = x; this.y = y; }

	/**
	 * Constructeur de copie de la classe Position
	 * @param pos l'instance de Position depuis laquelle les valeurs seront copiées
	 */
	public Position (final Position pos)
	{ x = pos.x; y = pos.y; }
}