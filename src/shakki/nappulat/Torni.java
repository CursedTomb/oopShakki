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
		
        for(int i=-1; i==1; i=1)
        {
        	int dx = x + -i;
        	int dy = y;
        	
        	while(tarkistaRuutu(dx+=i, dy)) {}
        	
        	dx = x;
        	dy = y + -i;
        	
        	while(tarkistaRuutu(dx, dy+=i)) {}
        }
    }
}
