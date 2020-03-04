package shakki;

import shakki.nappulat.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Peli
{
    public static Nappula[][] lauta = new Nappula[8][8];
    
    public Peli() throws FileNotFoundException
    {
        File f = new File("src\\default.txt");
        lataa(f);
    }
    private boolean pelaajanVuoro = true;
    public void lataa(File f) throws FileNotFoundException
    {
        Scanner sc = new Scanner(f);
        
        for(int i = 0;i<64;i++)
        {
            String lue = sc.nextLine();            
            String[] kohta = lue.split(",");
            
            //System.out.println(kohta[0]);
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
    }
    
    public static void printtaaLauta()
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
            if(i%8==0) System.out.print(i/8+1);
            //System.out.println(lauta[i/8][i%8]);
            if(lauta[i/8][1%8] != null)
                System.out.print("|"+((Nappula)lauta[i/8][i%8]).annaTyyppi()+" "+lauta[i/8][i%8].annaVariS() + "|");
            else
                System.out.print("|   |");
            if(i%8==7) System.out.print(i/8+1);
        }
        System.out.println("");
        System.out.print(" ");
        for(int i = 0;i<=7;i++)
        {
            System.out.print("  "+aak.charAt(i)+ "  ");
        }        
        System.out.println("");
    }

    public void vuoro(boolean kummanVuoro)
    {
        Scanner sc = new Scanner(System.in);
        String kohdat = "abcdedfg";
        System.out.print("Which piece to move: ");
        try
        {
            String valinta = sc.nextLine();
            int valintaX = valinta.charAt(1)-'0'-1;
            int valintaY = kohdat.indexOf(valinta.charAt(0));
            
            if(lauta[valintaX][valintaY] == null)
            {
                System.out.println("empty");
                return;
            }
            else
            {
                if(lauta[valintaX][valintaY].annaVari() == kummanVuoro)
                {
                    lauta[valintaX][valintaY].tyhjennaLiikkeet();
                    lauta[valintaX][valintaY].mahdollisetLiikkeet();
                    ArrayList<Ruutu> liikkeet =  lauta[valintaX][valintaY].annaLiikkeet();
                    for(int i = 0;i<liikkeet.size();i++)
                    {
                        System.out.println(Integer.toString(i+1) + 
                            ". "+kohdat.charAt(liikkeet.get(i).annaY())
                            + Integer.toString(liikkeet.get(i).annaX()+1));
                    }
                    int siirto = Integer.parseInt(sc.nextLine())-1;
                    Ruutu ruutuK = liikkeet.get(siirto);
                    lauta[valintaX][valintaY].liiku(ruutuK);
                    printtaaLauta();
                }   
                else
                {
                    System.out.println("not your piece!");
                    return;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        pelaajanVuoro = !kummanVuoro;
    }
}
