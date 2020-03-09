package shakki;

import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// Kysyy onko uusi peli vai jatkaako ja antaa vastauksen peli objektille
		Scanner skanneri = new Scanner(System.in);
		System.out.print("Uusi peli vai jatka u/j: "); 
		String s = skanneri.nextLine();
		Peli p = new Peli(s);
		p.printtaaLauta();
		while(true) // Käy vuoroja kunnes tulee true (luovututtaminen)
			if(p.vuoro(p.annaVuoro())) break;
		if(p.annaVuoro()) // Juhlistaa voittajan
		{
			System.out.println("Musta voitit pelin!");
		}
		else
		{
			System.out.println("Valkoinen voitit pelin!");
		}
	}
}
