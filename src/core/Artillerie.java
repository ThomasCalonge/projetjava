package core;

/**
 * Implémente le mode artillerie
 */
public class Artillerie extends Bataille {

	public Artillerie(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TYPE getType() {
		return TYPE.ARTILLERIE;
	}
}
