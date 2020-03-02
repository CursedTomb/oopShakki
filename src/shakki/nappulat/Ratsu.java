package shakki.nappulat;

import shakki.*;

public class Ratsu extends Nappula
{
	public Ratsu(boolean vari, int sijaintiX, int sijaintiY)
	{
		super(vari, 2, sijaintiX, sijaintiY);
	}

	protected void mahdollisetLiikkeet()
	{
		if(x+2<=7 && y+1 <=7)
		{
			if(Peli.lauta[x+2][y+1] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x+2][y+1].annaVari())
				{
					liikkeet.add(new Ruutu(x+2,y+1));
				}
			}
			else liikkeet.add(new Ruutu(x+2,y+1));
		}
		if(x+2<=7 && y-1 >=0)
		{
			if(Peli.lauta[x+2][y-1] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x+2][y-1].annaVari())
				{
					liikkeet.add(new Ruutu(x+2,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x+2,y-1));
		}
		if(x-2>=0 && y+1 <=7)
		{
			if(Peli.lauta[x-2][y+1] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x-2][y+1].annaVari())
				{
					liikkeet.add(new Ruutu(x-2,y+1));
				}
			}
			else liikkeet.add(new Ruutu(x-2,y+1));
		}
		if(x-2>=0 && y-1 >=0)
		{
			if(Peli.lauta[x-2][y-1] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x-2][y-1].annaVari())
				{
					liikkeet.add(new Ruutu(x-2,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x-2,y-1));
		}

		if(x+1<=7 && y+2 <=7)
		{
			if(Peli.lauta[x+1][y+2] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x+1][y+2].annaVari())
				{
					liikkeet.add(new Ruutu(x+1,y+2));
				}
			}
			else liikkeet.add(new Ruutu(x+1,y+2));
		}
		if(x+1<=7 && y-2 >=0)
		{
			if(Peli.lauta[x+1][y-2] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x+1][y-2].annaVari())
				{
					liikkeet.add(new Ruutu(x+1,y-2));
				}
			}
			else liikkeet.add(new Ruutu(x+1,y-2));
		}

		if(x-1>=0 && y+2 <=7)
		{
			if(Peli.lauta[x-1][y+2] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x-1][y+2].annaVari())
				{
					liikkeet.add(new Ruutu(x-1,y+2));
				}
			}
			else liikkeet.add(new Ruutu(x-1,y+2));
		}
		if(x-1>=0 && y-2 >=0)
		{
			if(Peli.lauta[x-1][y-2] instanceof Nappula)
			{
				if(this.annaVari()!=Peli.lauta[x-1][y-2].annaVari())
				{
					liikkeet.add(new Ruutu(x-1,y-2));
				}
			}
			else liikkeet.add(new Ruutu(x-1,y-2));
		}
	}
}