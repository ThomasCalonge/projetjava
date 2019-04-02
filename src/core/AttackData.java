package core;

public class AttackData
{
	public Position pos;
	public ATTAQUE_STATUS status;

	public AttackData(final Position pos, final ATTAQUE_STATUS status) { this.pos = pos; this.status = status; }
}