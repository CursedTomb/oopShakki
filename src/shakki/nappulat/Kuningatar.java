package shakki.nappulat;

import shakki.*;

public class Kuningatar extends Nappula
{
	public Kuningatar(boolean vari, int sijaintiX, int sijaintiY)
	{
		super(vari, 5, sijaintiX, sijaintiY);
	}

	protected void mahdollisetLiikkeet()
	{
		int dx, dy;
		
		// Käy samat asiat läpi arvoilla -1 ja 1: kulkee taulukossa ensin positiiviseen, sitten negatiiviseen suuntaan.
        for(int i=-1; i<2; i+=2)
        {
        	dx = x;
        	dy = y;
        	while(tarkistaRuutu(dx+=i, dy)) {}
        	
        	dx = x;
        	dy = y;
        	while(tarkistaRuutu(dx, dy+=i)) {}
        	
        	dx = x;
			dy = y;
			while(tarkistaRuutu(--dx, dy+=i)) {}
			
			dx = x;
			dy = y;
			while(tarkistaRuutu(++dx, dy+=i)) {}
        }
	}
}
