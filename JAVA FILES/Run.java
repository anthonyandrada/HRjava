

public class Run
{
	public static void main(String[] args)
	{
		HireMe program = new HireMe();
		program.DisplayMenu();
		while (true)
		{
			program.ChooseMenu();
		}
	}
}
