package shakki.nappulat;

import shakki.*;

public class Lahetti extends Nappula
{
	public Lahetti(boolean vari, int sijaintiX, int sijaintiY)
	{
		super(vari, 3, sijaintiX, sijaintiY);
	}
	protected void mahdollisetLiikkeet()
	{
		for(int i=-1; i==1; i=1)
		{
			// Väliaikainen x ja y joiden arvot muutettavissa, molemmat nousee yksi askel kerrallaan eikä kumpikaan voi nousta yli 7 tai laskea alle 0
			for(int tempx = x, tempy = y; (tempx <= 7 && tempx >= 0) && (tempy <= 7 && tempy >= 0); tempx++, tempy+=i)
			{
				if(Peli.lauta[tempx][tempy] == null)
					liikkeet.add(new Ruutu(tempx, y));
				
				else if(Peli.lauta[tempx][tempy] instanceof Nappula)
                {
                    if(Peli.lauta[tempx][tempy].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(tempx, y));
                    
                    break;
                }
			}
			
			for(int tempx = x, tempy = y; (tempx <= 7 && tempx >= 0) && (tempy <= 7 && tempy >= 0); tempx--, tempy+=i)
			{
				if(Peli.lauta[tempx][tempy] == null)
					liikkeet.add(new Ruutu(tempx, y));
				
				else if(Peli.lauta[tempx][tempy] instanceof Nappula)
                {
                    if(Peli.lauta[tempx][tempy].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(tempx, y));
                    
                    break;
                }
			}
		}
	}
}
