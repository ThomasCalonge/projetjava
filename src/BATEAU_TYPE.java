public enum BATEAU_TYPE
{
    PORTE_AVION,
    SOUS_MARIN,
	CUIRASSE,
	ZODIAC;

    /*static public PRIVILEGE fromInt (final int i)
    {
        if (i == 0)
            return CUSTOMER;
        
        if (i == 1)
            return SELLER;
        
        return SUDO;
    }*/

    static public int toInt (final BATEAU_TYPE t)
    {
        int ret = 0;
        switch (t)
        {
            case PORTE_AVION:
                ret = 5;
                break;
            
            case SOUS_MARIN:
                ret = 4;
                break;
            
            case CUIRASSE:
                ret = 3;
				break;
			
			case ZODIAC:
				ret = 1;
				break;
        }
        return ret;
    }
}