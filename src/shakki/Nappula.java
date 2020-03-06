package shakki;

import java.util.*;
public abstract class Nappula
{
    protected final boolean vari;
    protected final int tyyppi;
    protected int x;
    protected int y;
    protected ArrayList<Ruutu> liikkeet = new ArrayList<Ruutu>();
    
    public Nappula(boolean vari, int tyyppi, int x, int y)
    {
        this.vari=vari;
        this.tyyppi=tyyppi;
        this.x = x;
        this.y = y;
    }
    
    public int annaTyyppi()
    {
        return tyyppi;
    }    
    
    public String annaVariS()
    {
        if(vari)
        {
            return "V";
        }
        else return "M";
    }
    public boolean annaVari()
    {
        return vari;
    }
    public void tyhjennaLiikkeet()
    {
        liikkeet.clear();
    }
    public ArrayList<Ruutu> annaLiikkeet()
    {
        return liikkeet;
    }
    
    abstract protected void mahdollisetLiikkeet();
    
    protected void liiku(Ruutu kohde)
    {   
        Peli.lauta[kohde.annaX()][kohde.annaY()] = this;
        Peli.lauta[x][y] = null;
        x = kohde.annaX();
        y = kohde.annaY();
    }
    
    protected boolean tarkistaRuutu(int dx, int dy)
    {
    	if(dx>7 || dx<0 || dy>7 || dy<0)
    		return false;
    	
    	if(Peli.lauta[dx][dy] == null)
    	{
            liikkeet.add(new Ruutu(dx, dy));
            return true;
    	}
    	
        // Jos ruudussa nappula
        else if(Peli.lauta[dx][dy] instanceof Nappula)
        {
            if(Peli.lauta[dx][dy].annaVari() != this.vari)
                liikkeet.add(new Ruutu(dx, dy));
            
            return false;
        }
    	
    	return false;
    }
}