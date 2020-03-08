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
		int dx, dy;
		for(int i=-1; i<2; i+=2)
		{
			dx = x;
			dy = y;
			
			while(tarkistaRuutu(--dx, dy+=i)) {}
			
			dx = x;
			dy = y;
			
			while(tarkistaRuutu(++dx, dy+=i)) {}
		}
	}
}
