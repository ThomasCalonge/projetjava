package core;

public enum ATTAQUE_STATUS
{
	EAU,
	TOUCHE,
	COULE;

	static public String toString(final ATTAQUE_STATUS s)
	{
		switch(s)
		{
			case EAU:
				return "EAU";
			
			case TOUCHE:
				return "TOUCHE";
			
			case COULE:
				return "COULE";
		}
		
		//Jamais atteind
		return "";
	}
}