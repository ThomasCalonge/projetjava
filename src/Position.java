public class Position
{
	public int x;
	public int y;

	Position(final int x, final int y)
	{ this.x = x; this.y = y; }

	public Position (final Position pos)
	{ x = pos.x; y = pos.y; }
}