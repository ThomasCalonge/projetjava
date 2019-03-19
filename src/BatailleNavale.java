import java.util.ArrayList;

import core.*;

import gui.*;

/**
 * Classe qui blablabla et
 * blablabla et bliblanlu et
 * bloblobloblan. 
 * <p>De plus,  
 * blabliblablu
 * 
 * @author Adrien COURNAND
 */
public class BatailleNavale 
{
		Start = StartScreen()
		private static ArrayList<Bateau> m_boats;
		public static void main(String[] args) 
		{
			m_boats = new ArrayList<Bateau>();
			m_boats.add(new Bateau(Bateau.TYPE.PORTE_AVION, ORIENTATION.H, new Position(3,3)));
			m_boats.add(new Bateau(Bateau.TYPE.SOUS_MARIN, ORIENTATION.V, new Position(1,1)));
			m_boats.add(new Bateau(Bateau.TYPE.CUIRASSE, ORIENTATION.H, new Position(4,8)));
			m_boats.add(new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.H, new Position(6,5)));
			m_boats.add(new Bateau(Bateau.TYPE.ZODIAC, ORIENTATION.V, new Position(3,6)));
			
			m_boats.get(0).setDamage(1);
			m_boats.get(0).setDamage(0);
			
			m_boats.get(1).setDamage(1);
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
								if ((x >= m_boats.get(i).pos.x) && (x < m_boats.get(i).pos.x + Bateau.TYPE.toInt(m_boats.get(i).type)))
								{
									to_print = m_boats.get(i).getDamage(x - m_boats.get(i).pos.x) ? 'x' : '-';
								}
							}
						}
						else
						{
							if (x == m_boats.get(i).pos.x)
							{
								if ((y >= m_boats.get(i).pos.y) && (y < m_boats.get(i).pos.y + Bateau.TYPE.toInt(m_boats.get(i).type)))
								{
									to_print = m_boats.get(i).getDamage(y - m_boats.get(i).pos.y) ? 'x' : '|';
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
