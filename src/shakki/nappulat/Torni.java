package shakki.nappulat;

import shakki.*;

public class Torni extends Nappula
{
	public Torni(boolean vari, int sijaintiX, int sijaintiY)
	{
		super(vari, 1, sijaintiX, sijaintiY);
	}
	protected void mahdollisetLiikkeet()
    {
		int dx, dy;
        for(int i=-1; i<2; i+=2)
        {
        	dx = x;
        	dy = y;
        	
        	while(tarkistaRuutu(dx+=i, dy)) {}
        	
        	dx = x;
        	dy = y;
        	
        	while(tarkistaRuutu(dx, dy+=i)) {}
        }
    }
}
