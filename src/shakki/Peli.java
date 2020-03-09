package shakki;

import shakki.nappulat.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Peli
{
    public static Nappula[][] lauta = new Nappula[8][8];
    
    public Peli(String s) throws FileNotFoundException
    {
        // Luodaan uusi file objekti ja päätetään kumpaa käytetään
        // Eli jatketaanko peliä vai ei, jos syöte outo niin otetaan default case.
        File f;
        if(s.equals("j"))
            f = new File("src\\save.txt");
        else if(s.equals("u"))
            f = new File("src\\default.txt");
        else
        {
            System.out.println("virheellinen syöte, otetaan default tilanne");
            f = new File("src\\default.txt");
        }
        // Kutsutaan lataa funktio joka alustaa laudan
        lataa(f);
    }
    private boolean pelaajanVuoro; // Tallentaa pelaajan vuoron

    /**
     * Lataa metodille annetun tiedoston, jonka perusteella tämä asettaa pelilaudan tilan.
     * @param f Tekstitiedosto, jossa pelilaudan tila
     * @throws FileNotFoundException
     */
    public void lataa(File f) throws FileNotFoundException
    {
        Scanner sc = new Scanner(f); // Luodaan skanneriobjekti joka lukee tiedoston
        
        for(int i = 0;i<64;i++)
        {
            String lue = sc.nextLine();            
            String[] kohta = lue.split(",");
            // Meidän parseri joka osaa lukea tiedoston ja luoda shakkilaudan sen mukaan
            if(!kohta[0].equals("-1"))
            {
                switch (Integer.parseInt(kohta[0]))
                {
                    case 0:
                        lauta[i/8][i%8] = new Sotilas(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                    case 1:
                        lauta[i/8][i%8] = new Torni(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                    case 2:
                        lauta[i/8][i%8] = new Ratsu(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                    case 3:
                        lauta[i/8][i%8] = new Lahetti(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                    case 4:
                        lauta[i/8][i%8] = new Kuningas(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                    case 5:
                        lauta[i/8][i%8] = new Kuningatar(Boolean.valueOf(kohta[1]),i/8,i%8);
                        break;
                }
            }
        }
        pelaajanVuoro = Boolean.valueOf(sc.nextLine());// Lukee viimeiseltä riviltä kumman pelaajan vuoro
        sc.close();
    }
    
    /**
     * Palauttaa booleanin, joka kertoo kumman pelaajan vuoro on. True = valkoinen, false = musta.
     * @return boolean
     */
    public boolean annaVuoro() // Palauttaa kumman vuoro on
    {
        return pelaajanVuoro;
    }
    
    /**
     * Tallentaa tekstitiedostoon pelilaudan nykyisen tilan ja sen, kumman vuoro pelissä tällä hetkellä on.
     * @throws IOException
     */
    public void tallenna() throws IOException
    {
        FileWriter f = new FileWriter("src\\save.txt"); // Luo FileWriter olion joka osaa tallentaa pelin 
        
        for(int i = 0;i<64;i++)
        {
            if(lauta[i/8][i%8] == null)
            {
                f.write("-1\n");
            }
            else
            {
                f.write(lauta[i/8][i%8].annaTyyppi() + "," + lauta[i/8][i%8].annaVari() + "\n");
            }
        }
        f.write(pelaajanVuoro+""); // Lopulta lisää tiedostoon kumman pelaajan vuoro
        f.close();
    }
    
    // Meidän toteutus printtaa nykyinen laudan tilanne (hyvin spagettia)

    /**
     * Tulostaa pelilaudan tilan pelaajalle. Jokainen ruutu on joko tyhjä tai sisältää nappulan, jonka
     * arvoa merkkaa kokonaisluku ja kirjain V tai M. Kokonaisluvut kuvaavat nappulan tyyppiä, kirjain nappulan väriä.
     */
    public void printtaaLauta()
    {   
        String aak = "abcdefgh";
        System.out.print("  ");
        for(int i = 0;i<=7;i++)
        {
            System.out.print("  "+aak.charAt(i)+ "  ");
        }
        
        for(int i = 0;i<64;i++)
        {
            if(i%8==0) System.out.println("");
            if(i%8==0) System.out.print(i/8+1+" ");
            try
            {
               
                if(lauta[i/8][i%8] instanceof Nappula)
                {
                    System.out.print("|"+
                        lauta[i/8][i%8].annaTyyppi()+
                        " "+lauta[i/8][i%8].annaVariS() + "|");
                    
                }
                
                else if(lauta[i/8][i%8] == null)
                {
                    System.out.print("|   |");     
                }
                
                if(i%8==7) System.out.print(" "+(i/8+1) + "   " + LisaPrintti(i/8));
            }
            catch(Exception e) // Aika turha try catch mutta olkoot
            {
                System.out.print(" ");
                System.out.print(lauta[i/8][i%8] instanceof Nappula);
            }
            
        }
        System.out.println("");
        System.out.print("  ");
        for(int i = 0;i<=7;i++)
        {
            System.out.print("  "+aak.charAt(i)+ "  ");
        }        
        System.out.println("");
    }

    /**
     * Metodi, jossa tapahtuu nappuloiden siirto pelaajan vuorolla. Pyytää pelaajaa valitsemaan
     * nappulan, jonka jälkeen hänelle annetaan lista mahdollisista siirroista. Siirroista voi valita
     * haluamansa ruudun, johon pelaaja haluaa liikuttaa nappulan. Vuoron loputtua tallentaa pelilaudan tilan
     * tiedostoon.
     * @param kummanVuoro Kumman pelaajan vuoro on nyt: true = valkoinen, false = musta.
     * @return Palauttaa vastakkaisen pelaajan vuoron jos vuoro onnistui, muuten vuoro ei vaihdu.
     */
    public boolean vuoro(boolean kummanVuoro)
    {

        // Luodaan skanneriolio ja alustetaan kohdat
        Scanner sc = new Scanner(System.in);
        String kohdat = "abcdefgh";

        // Tarkistaa onko tilanne shakki ja antaa mahdollisuuden luovuttaa pelin 
        // (jos kyseessä on shakkimatti tai muuten vaan haluaa luovuttaa)
        if(shakkiTarkistus())
        {
            System.out.println("Shakki!");
            System.out.println("haluatko luovuttaa? k/e: ");
            String s = sc.nextLine();
            if(s.equals("k")) return true;
        }

        System.out.print("Valitse siirrettävä nappula: ");
        try
        {
            // Luodaan syöte ja muokataan se nollaindeksiksi, jota voi käytää arrayssa
            // Ensin kirjain sitten numero
            String valinta = sc.nextLine();
            int valintaX = valinta.charAt(1)-'0'-1;
            int valintaY = kohdat.indexOf(valinta.charAt(0));

            // Tarkistaa onko valittu nappula tyhjä

            if(lauta[valintaX][valintaY] == null)
            {
                System.out.println("Tyhjä ruutu, yritä uudelleen.");
                return false;
            }
            else
            {
                // Tarkistaa, onko nappula pelaajan
                if(lauta[valintaX][valintaY].annaVari() == kummanVuoro)
                {
                    lauta[valintaX][valintaY].tyhjennaLiikkeet();
                    //lauta[valintaX][valintaY].mahdollisetLiikkeet(); // Laskee kyseisen nappulan liikkeet
                    ArrayList<Ruutu> liikkeet = lauta[valintaX][valintaY].annaLiikkeet(); // Luo uuden arraylistin jossa on liikkeet
                    for(int i = 0;i<liikkeet.size();i++)
                    {
                        // Tulostaa vaihtoehdot ja antaa niille numeron esim. 1. a1
                        System.out.println(Integer.toString(i+1) + 
                            ". "+kohdat.charAt(liikkeet.get(i).annaY())
                            + Integer.toString(liikkeet.get(i).annaX()+1));
                    }

                    // Muuttaa pelaajan valitun numeron integraaliksi
                    int siirto = Integer.parseInt(sc.nextLine())-1;
                    
                    // Ottaa valitun liikkeen ruudun talteen ja antaa sen liikkumismetodille
                    // Joka löytyy Nappula luokasta
                    Ruutu ruutuK = liikkeet.get(siirto);
                    lauta[valintaX][valintaY].liiku(ruutuK);
                    
                    // Tulostaa laudan uudelleen
                    printtaaLauta();
                }   
                else
                {
                    //jos valittu nappula ei ole pelaajan
                    System.out.println("Ei sinun nappulasi, yritä uudelleen.");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("virheellinen syöte");
            return false;
        }
        
        // Vaihtaa vuoroa jos siirto on onnistunut, palauttaa null jos siirto epäonnistui
        pelaajanVuoro = !kummanVuoro;
        try
        {
            tallenna();
        }
        catch(IOException e)
        {
            System.out.println("safe.txt puuttuu");
        }
        return false;
    }
    
    /**
     * Tarkistaa onko nykyinen pelin tilanne shakissa. Käy kaikki laudan ruudut läpi
     * ja etsii kuninkaan, sitten käy kaikkien vastustajien mahdolliset liikkeet läpi ja
     * tarkistaa, onko kuningas vaarassa tulla syödyksi tällä vuorolla.
     * @return Palauttaa true, jos kuningas vaarassa. Muuten false.
     */
    public boolean shakkiTarkistus()
    {
        Ruutu k = new Ruutu(0,0);
        for(Nappula[] lrivi : lauta)
            for(Nappula n : lrivi)
                if(n instanceof Kuningas)
                    if(n.annaVari() == pelaajanVuoro)
                        k = n.annaRuutu();
        
        for(Nappula[] lrivi : lauta)
            for(Nappula n : lrivi)
                if(n != null)
                    if(n.annaVari() != pelaajanVuoro)
                        for(Ruutu r : n.annaLiikkeet())
                            if((k.annaX() == r.annaX()) && (k.annaY()==r.annaY()))
                                return true;
        
        return false;
    }
    
    /**
     * Kutsutaan laudan tulostuksen yhteydessä. Tulostaa pelaajalle näkyviin kaikkien pelilaudalla
     * näkyvien nappuloiden tyyppiä merkkaavat numerot.
     * @param i Rivi, jolle teksti tulostetaan
     * @return Merkkijono, jossa kokonaisluku ja sitä vastaava nappulan tyyppi
     */
    public String LisaPrintti(int i)
    {
        String pal = "";
        switch(i)
        {
            case 1:
                pal = "0 = sotilas";
                break;
            case 2:
                pal = "1 = torni";
                break;
            case 3:
                pal = "2 = hevonen";
                break;
            case 4:
                pal = "3 = lähetti";
                break;
            case 5:
                pal = "4 = kuningas";
                break;
            case 6:
                pal = "5 = kuningatar";
                break;
            default:
                break;
        }
        return pal;
    }
}