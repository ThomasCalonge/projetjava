import java.util.ArrayList;

import core.*;

public class BatailleNavale 
{
		private static ArrayList<Bateau> m_boats;
		public static void main(String[] args) 
		{
			m_boats = new ArrayList<Bateau>();
			m_boats.add(new Bateau(BATEAU_TYPE.PORTE_AVION, ORIENTATION.H, new Position(3,3)));
			m_boats.add(new Bateau(BATEAU_TYPE.SOUS_MARIN, ORIENTATION.V, new Position(1,1)));
			print();
		}
		
		public static void print()
		{
			for(int y = 0; y < 10; ++y)
			{
				for (int x = 0; x < 10; ++x)
				{
					char to_print = '.';
					
					for (int i = 0; i < m_boats.size(); ++i)
					{
						if (m_boats.get(i).o == ORIENTATION.H)
						{
							if (y == m_boats.get(i).pos.y)
							{
								if ((x >= m_boats.get(i).pos.x) && (x < m_boats.get(i).pos.x + BATEAU_TYPE.toInt(m_boats.get(i).type)))
								{
									to_print = '_';
								}
							}
						}
						else
						{
							if (x == m_boats.get(i).pos.x)
							{
								if ((y >= m_boats.get(i).pos.y) && (y < m_boats.get(i).pos.y + BATEAU_TYPE.toInt(m_boats.get(i).type)))
								{
									to_print = '|';
								}
							}
						}
					}

					System.out.print(to_print + " ");
				}

				System.out.print("\n");
			}
		}
}
