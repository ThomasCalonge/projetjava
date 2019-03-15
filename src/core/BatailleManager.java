package core;

public class BatailleManager 
{
	private Bataille.TYPE m_type;
	private Bataille.MODE m_mode;
	
	public void setType (final Bataille.TYPE type) { m_type = type; }
	public void setMode (final Bataille.MODE mode) { m_mode = mode; }
	public Bataille create () { return new Bataille(m_type); }
}
