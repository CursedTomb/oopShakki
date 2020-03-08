package shakki.nappulat;

import shakki.*;

public class Kuningas extends Nappula
{
	public Kuningas(boolean vari, int sijaintiX, int sijaintiY)
	{
		super(vari, 4, sijaintiX, sijaintiY);
	}
	// Liikkeet hardkoodattu (spagettia ei suositella lukemaan)
	protected void mahdollisetLiikkeet()
	{
		if(x-1>=0)
		{
			if(Peli.lauta[x-1][y] instanceof Nappula)
			{
				if(Peli.lauta[x-1][y].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x-1,y));
				}
			}
			else liikkeet.add(new Ruutu(x-1,y));				
		}
		if(x+1<=7)
		{
			if(Peli.lauta[x+1][y] instanceof Nappula)
			{
				if(Peli.lauta[x+1][y].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x+1,y));
				}
			}
			else liikkeet.add(new Ruutu(x+1,y));				
		}
		if(y-1>=0)
		{
			if(Peli.lauta[x][y-1] instanceof Nappula)
			{
				if(Peli.lauta[x][y-1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x,y-1));				
		}
		if(y+1<=7)
		{
			if(Peli.lauta[x][y+1] instanceof Nappula)
			{
				if(Peli.lauta[x][y+1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x,y+1));
				}
			}
			else liikkeet.add(new Ruutu(x,y+1));				
		}
		if(x-1>=0 && y-1>=0)
		{
			if(Peli.lauta[x-1][y-1] instanceof Nappula)
			{
				if(Peli.lauta[x-1][y-1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x-1,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x-1,y-1));				
		}
		if(x+1<=7 && y-1 >=0)
		{
			if(Peli.lauta[x+1][y-1] instanceof Nappula)
			{
				if(Peli.lauta[x+1][y-1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x+1,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x+1,y-1));				
		}
		if(x-1>=0 && y+1<=7)
		{
			if(Peli.lauta[x-1][y+1] instanceof Nappula)
			{
				if(Peli.lauta[x-1][y+1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x-1,y+1));
				}
			}
			else liikkeet.add(new Ruutu(x-1,y+1));				
		}
		if(x+1<=7 && y-1 >=0)
		{
			if(Peli.lauta[x+1][y-1] instanceof Nappula)
			{
				if(Peli.lauta[x+1][y-1].annaVari()!= this.annaVari())
				{
					liikkeet.add(new Ruutu(x+1,y-1));
				}
			}
			else liikkeet.add(new Ruutu(x+1,y-1));				
		}
	}
}
