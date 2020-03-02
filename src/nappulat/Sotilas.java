package shakki.nappulat;

import shakki.*;

public class Sotilas extends Nappula
{
	
	public Sotilas(boolean vari, int x, int y)
	{
		super(vari, 0, x, y);
	}

	protected void liiku()
	{
		
		if(eka)
		{
			eka = false;
		}
		
	}

	protected void mahdollisetLiikkeet()
	{
		if(vari)
		{
			if(x-1 >=0 && !(peli.lauta[x-1][y] instancef nappula))
			{
				liikkeet.add(Ruutu(x-1,y));
			}
			if(x-2 >=0 && !(peli.lauta[x-2][y] instanceof nappula && eka)
			{
				liikkeet.add(Ruutu(x-2,y));
			}
			if(x-1 >=0 && y-1 >=0 && peli.lauta[x-1][y-1] instanceof nappula)
			{
				if(!peli.lauta[x-1][y-1].annaVari())
				{
					liikkeet.add(Ruutu(x-1,y-1));
				}
			}
			if(x-1>=0 && y+1 <= 7 && peli.lauta[x-1][y+1] instanceof nappula)
			{
				if(!peli.lauta[x-1][y+1].annaVari())
				{
					liikkeet.add(Ruutu(x-1,y+1))
				}
			}
		}
		else
		{
			if(x+1 <=7 && !(peli.lauta[x+1][y] instancef nappula))
			{
				liikkeet.add(Ruutu(x+1,y));
			}
			if(x+2 <=7 && !(peli.lauta[x+2][y] instanceof nappula && eka)
			{
				liikkeet.add(Ruutu(x+2,y));
			}
			if(x+1 <=7 && y-1 >=0 && peli.lauta[x+1][y-1] instanceof nappula)
			{
				if(peli.lauta[x+1][y-1].annaVari())
				{
					liikkeet.add(Ruutu(x+1,y-1));
				}
			}
			if(x+1<=7 && y+1 <= 7 && peli.lauta[x+1][y+1] instanceof nappula)
			{
				if(peli.lauta[x+1][y+1].annaVari())
				{
					liikkeet.add(Ruutu(x+1,y+1))
				}
			}
		}
	}
}