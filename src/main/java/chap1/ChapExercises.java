package chap1;

public class ChapExercises
{
	// Convert an int to corresponding binary string, say, 5 to "101"
	public static String intToBinaryString(int a)
	{
		String s = "";
		
		for(int i = a; i > 0; i /= 2)
		{
			s = (i % 2) + s;
		}
		
		return s;
	}
}
