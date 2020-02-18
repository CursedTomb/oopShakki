package shakki;

import shakki.nappulat.*;
import java.io.*;
import java.util.Scanner;

public class Peli
{
    public static Nappula[][] lauta = new Nappula[8][8];
    
    public Peli() throws FileNotFoundException
    {
        File f = new File("src\\default.txt");
        lataa(f);
    }
    
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
    
    public void printtaaLauta()
    {   
        String aak = "abcdefgh";
        for(int i = 1;i<=8;i++)
        {
            System.out.print("  "+i+ "  ");
        }
        
        for(int i = 0;i<64;i++)
        {
            if(i%8==0) System.out.println("");
            if(lauta[i/8][1%8] != null)
                System.out.print("|"+((Nappula)lauta[i/8][i%8]).annaTyyppi()+" "+lauta[i/8][i%8].annaVari() + "|");
            else
                System.out.print("|   |");
            if(i%8==7) System.out.print(aak.charAt(i/8));
            
        }
    }  
}
