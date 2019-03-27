package core;

public class Artillerie extends Bataille {

	public Artillerie(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ATTAQUE_STATUS playerAttack(final Position pos) {
		return null;
	}

	@Override
	public boolean attacking_player_can_reattack() {
		// TODO Auto-generated method stub
		return false;
	}
}
