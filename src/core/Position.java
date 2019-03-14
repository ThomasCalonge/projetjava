package core;

public class Position
{
	public int x;
	public int y;

	public Position(final int x, final int y)
	{ this.x = x; this.y = y; }

	public Position (final Position pos)
	{ x = pos.x; y = pos.y; }
}