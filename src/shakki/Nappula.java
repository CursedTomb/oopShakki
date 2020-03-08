package shakki;

import java.util.*;
public abstract class Nappula
{
    protected final boolean vari;
    protected final int tyyppi;
    protected int x;
    protected int y;
    protected ArrayList<Ruutu> liikkeet = new ArrayList<Ruutu>();
    
    /**
     * Luodaan uusi nappula.
     * @param vari Nappulan väri. true = valkoinen, false = musta
     * @param tyyppi Nappulan tyyppi, kokonaisluku: 0(Sotilas), 1(Torni), 2(Ratsu), 3(Lähetti), 4(Kuningas), 5(Kuningatar)
     * @param x Nappulan rivi
     * @param y Nappulan sarake
     */
    public Nappula(boolean vari, int tyyppi, int x, int y)
    {
        this.vari=vari;
        this.tyyppi=tyyppi;
        this.x = x;
        this.y = y;
    }
    
    public Ruutu annaRuutu()
    {
    	return new Ruutu(x,y);
    }
    
    /**
     * Palauttaa nappulan tyypin
     * @return Kokonaisluku: 0(Sotilas), 1(Torni), 2(Ratsu), 3(Lähetti), 4(Kuningas), 5(Kuningatar)
     */
    public int annaTyyppi()
    {
        return tyyppi;
    }    
    
    /**
     * Palauttaa nappulan värin
     * @return String V (valkoinen) tai M (musta)
     */
    public String annaVariS()
    {
        if(vari)
        {
            return "V";
        }
        else return "M";
    }
    
    /**
     * Palauttaa nappulan värin
     * @return boolean
     */
    public boolean annaVari()
    {
        return vari;
    }
    
    /**
     * Palauttaa mahdollisten liikkeiden listan
     * @return Lista, jossa alkiot Ruutu-olioita 
     */
    public ArrayList<Ruutu> annaLiikkeet()
    {
        this.mahdollisetLiikkeet();
        return liikkeet;
    }
    
    /**
     * Lisää listaan liikeet kaikki mahdolliset liikkeet, joita nappula voi tällä vuorolla tehdä.
     */
    abstract protected void mahdollisetLiikkeet();
    
    /**
     * Siirtyy pelilaudalla johonkin ruutuun. Jos ruudussa vastustajan nappula, se syödään.
     * @param kohde Ruutu, johon nappula siirtyy
     */
    protected void liiku(Ruutu kohde)
    {   
        Peli.lauta[kohde.annaX()][kohde.annaY()] = this;
        Peli.lauta[x][y] = null;
        x = kohde.annaX();
        y = kohde.annaY();
        liikkeet.clear();
    }
    
    /**
     * Tarkistaa pelilaudalla määritellyn ruudun tilan. Lisää ruudun mahdollisten siirtojen listaan ja palauttaa true, jos se on
     * tyhjä ruutu. Jos ruudussa on nappula, lisätään se siirtolistaan jos se on vastustajan nappula. Tämän jälkeen palauttaa false.
     * @param dx Ruudun rivi
     * @param dy Ruudun sarake
     * @return True, jos tyhjä ruutu ja tarkistusta voidaan jatkaa. Muuten false.
     */
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
                liikkeet.add(new Ruutu(dx,dy));
            
            return false;
        }
    	
    	return false;
    }
}