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
            for(int tempx = x; tempx <= 7 && tempx >= 0; tempx+=i)
            {
                // Jos ruutu tyhjä
                if(Peli.lauta[tempx][y] == null)
                    liikkeet.add(new Ruutu(tempx, y));
                
                // Jos ruudussa nappula
                else if(Peli.lauta[tempx][y] instanceof Nappula)
                {
                    if(Peli.lauta[tempx][y].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(tempx, y));
                    
                    break;
                }
                    
            }
            
            for(int tempy = y; tempy <= 7 && tempy >= 0; tempy+=i)
            {
                if(Peli.lauta[x][tempy] == null)
                    liikkeet.add(new Ruutu(x, tempy));
                
                else if(Peli.lauta[x][tempy] instanceof Nappula)
                {
                    if(Peli.lauta[x][tempy].annaVari() != this.vari)
                        liikkeet.add(new Ruutu(x, tempy));
                    
                    break;
                }
            }
        }
    }
}
