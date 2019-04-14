package core;

/**
 * Status d'une attaque : touché, coulé ou dans l'eau.
 */
public enum ATTAQUE_STATUS
{
	/** l'attaque est tombée dans l'eau */EAU,
	/** l'attaque a touché un bateau */TOUCHE,
	/** l'attaque a fait couler le bateau */COULE;

	/**
	 * Retourne une chaîne de caractère décrivant un status
	 * @param s le status que l'on veut afficher
	 * @return une chaîne de caractère décrivant le status
	 */
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