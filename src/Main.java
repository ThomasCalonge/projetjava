public class Main
{
	private static Bateau b1 = new Bateau(BATEAU_TYPE.PORTE_AVION, ORIENTATION.H, new Position(3,3));
	public static void main(String[] args) 
	{
		//Position p = new Position(3,3);
		//System.out.println("x: " + b1.pos.x + "   y: " + b1.pos.y);
		print();
	}
	
	public static void print()
	{
		for(int y = 0; y < 10; ++y)
		{
			for (int x = 0; x < 10; ++x)
			{
				System.out.print(".");
				/*if (b1.o == ORIENTATION.H)
				{
					if (y == b1.pos.y)
					{
						if ((x >= b1.pos.x) && (x < b1.pos.x + BATEAU_TYPE.toInt(b1.type)))
						{
							System.out.print("-");
						}
						else
						{
							System.out.print(".");
						}
					}
				}
				else
				{
					if (x == b1.pos.x)
					{
						if ((y >= b1.pos.y) && (y < b1.pos.y + BATEAU_TYPE.toInt(b1.type)))
						{
							System.out.print("|");
						}
						else
						{
							System.out.print(".");
						}
					}
				}*/
				System.out.print(" ");
			}

			System.out.print("\n");
		}
	}
}