
public class test{
	public static void main(String[] args)
	{
		Object[] l = new Object[2];
		l[0] = new Object();
		l[1] = l[0];
		l[0] = null;
		System.out.println(l[1]);
		if(l[0] == null) System.out.println("asdf");	
	}
}