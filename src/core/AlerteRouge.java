package core;

/**
 * Implémente le mode AlerteRouge d'une bataille navale
 */
public class AlerteRouge extends Bataille {

	public AlerteRouge(final MODE mode) {
		super(mode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TYPE getType() {
		return TYPE.ALERTE_ROUGE;
	}
}
