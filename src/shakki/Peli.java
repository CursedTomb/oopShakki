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
        lataa(f);
    }
    private boolean pelaajanVuoro;

    public void lataa(File f) throws FileNotFoundException
    {
        Scanner sc = new Scanner(f);
        
        for(int i = 0;i<64;i++)
        {
            String lue = sc.nextLine();            
            String[] kohta = lue.split(",");
            
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
        pelaajanVuoro = Boolean.valueOf(sc.nextLine());
        sc.close();
    }
    public boolean annaVuoro()
    {
        return pelaajanVuoro;
    }
    public void tallenna() throws IOException
    {
        FileWriter f = new FileWriter("src\\save.txt");
        
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
        f.write(pelaajanVuoro+"");
        f.close();
    }
    
    public void printtaaLauta()
    {   
        String aak = "abcdefgh";
        System.out.print(" ");
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
                
                if(i%8==7) System.out.print(" "+(i/8+1));
            }
            catch(Exception e)
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

    public boolean vuoro(boolean kummanVuoro)
    {

        // Luodaan skanneriolio ja alustetaan kohdat
        Scanner sc = new Scanner(System.in);
        String kohdat = "abcdefgh";

        // Tarkistaa onko tilanne shakki
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
}