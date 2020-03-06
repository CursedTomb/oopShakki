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
			for(int a = x, b = y; (a <= 7 && a >= 0) && (b <= 7 && b >= 0); a++, b+=i)
			{
				if(Peli.lauta[a][b] == null)
					liikkeet.add(new Ruutu(a, b));
				
				else if(Peli.lauta[a][b] instanceof Nappula)
                {
                    if(Peli.lauta[a][b].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(a, b));
                    
                    break;
                }
			}
			
			for(int a = x, b = y; (a <= 7 && a >= 0) && (b <= 7 && b >= 0); a--, b+=i)
			{
				if(Peli.lauta[a][b] == null)
					liikkeet.add(new Ruutu(a, b));
				
				else if(Peli.lauta[a][b] instanceof Nappula)
                {
                    if(Peli.lauta[a][b].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(a, b));
                    
                    break;
                }
			}
		}
	}
}
