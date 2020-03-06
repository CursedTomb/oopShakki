package shakki;

import java.io.FileNotFoundException;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Peli p = new Peli();
		p.printtaaLauta();
		while(true)
			p.vuoro(p.annaVuoro());
	}
}
