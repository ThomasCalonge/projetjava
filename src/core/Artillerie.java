package core;

public class Artillerie extends Bataille {

	public Artillerie(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ATTAQUE_STATUS playerAttack(final Position pos) {
		System.out.println("Artillerie");
		return null;
	}

	@Override
	public boolean player_can_attack(PLAYER_N p) {
		// TODO Auto-generated method stub
		return false;
	}

}
