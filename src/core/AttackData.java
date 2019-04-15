package core;

/**
 * Enregistre les données d'une attaque. Ces données sont constituées de la position
 * de l'attaque et de son status.
 *
 */
public class AttackData
{
	/** Position de l'attaque */public Position pos;
	/** Status de l'attaque */public ATTAQUE_STATUS status;
	
	/**
	 * Constructeur de AttackData. Construit une instance de AttackData
	 * à partir d'une position et d'un status
	 * 
	 * @param pos la position où l'attaque a eu lieu
	 * @param status le status de l'attaque
	 */
	public AttackData(final Position pos, final ATTAQUE_STATUS status) { this.pos = pos; this.status = status; }
}