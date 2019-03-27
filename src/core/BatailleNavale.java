package core;

public class BatailleNavale extends Bataille 
{
	private ATTAQUE_STATUS[] m_last_attaque_status;
	public BatailleNavale(final MODE mode) 
	{
		super(mode);
		m_last_attack_status = new ATTAQUE_STATUS[2];
	}

	@Override
	public ATTAQUE_STATUS playerAttack(final Position pos) 
	{
		final ATTAQUE_STATUS s = (m_current_attacking_player == PLAYER_N.ONE) ? player_one_attacke_player_two(pos) : player_two_attacke_player_one(pos);
		m_players[PLAYER_N.toInt(m_current_attacking_player)].pushAttaqueData(new AttaqueData(pos, s));
		m_last_attack_status[PLAYER_N.toInt(m_current_attacking_player)] = s;
		return s;
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
	public boolean player_can_attack(PLAYER_N p) 
	{
		return m_last_attack_status[PLAYER_N.toInt(m_current_attacking_player)] != ATTAQUE_STATUS.EAU;
	}

}