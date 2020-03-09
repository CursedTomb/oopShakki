package shakki;

// Olio jota käytetään kuvaamaan ruutuja
// (muulla taval ois ollu liika säätöä)

public class Ruutu
{
	private int x;
	private int y;
	
	public Ruutu(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int annaX()
	{
		return x;
	}
	
	public int annaY()
	{
		return y;
	}
}