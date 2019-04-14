package core;

/**
 * Inplémente le mode bataille navale.
 */
public class BatailleNavale extends Bataille
{
	public BatailleNavale(Bataille.MODE mode)
	{
		super(mode);
	}

	@Override
	public TYPE getType() {
		return TYPE.NAVALE;
	}
}