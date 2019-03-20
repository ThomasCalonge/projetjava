package core;

public class BatailleNavale extends Bataille 
{
	public BatailleNavale(final MODE mode) {
		super(mode);
	}

	@Override
	public ATTAQUE_STATUS playerAttack(final Position pos) 
	{
		return (m_current_attacking_player == 0) ? player_one_attacke_player_two(pos) : player_two_attacke_player_one(pos);
	}

	private ATTAQUE_STATUS player_one_attacke_player_two(final Position pos)
	{
		return m_players[1].getAttacked(pos);
	}

	private ATTAQUE_STATUS player_two_attacke_player_one(final Position pos)
	{
		return m_players[0].getAttacked(pos);
	}

	@Override
	public boolean player_can_attack(PLAYER_N p) {
		// TODO Auto-generated method stub
		return false;
	}

}
