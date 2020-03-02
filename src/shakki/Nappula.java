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

    //protected abstract void mahdollisetLiikkeet();
    protected void liiku()
    {

    }
}