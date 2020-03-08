package shakki;

import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Peli p = new Peli();
		p.printtaaLauta();
		while(true)
			if(p.vuoro(p.annaVuoro())) break;
		if(p.annaVuoro())
		{
			System.out.println("Musta voitit pelin!");
		}
		else
		{
			System.out.println("Valkoinen voitit pelin!");
		}

	}
}
