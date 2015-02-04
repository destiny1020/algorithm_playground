package chap5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LSD
{
	private String[] a;
	private int lengthOfChars;
	private static int R = 256;
	public LSD(String path) throws FileNotFoundException
	{
		List<String> tempList = new ArrayList<String>();
		Scanner scanner = new Scanner(new File(path));
//		scanner.useDelimiter(" ");
		while(scanner.hasNext())
		{
			tempList.add(scanner.next());
		}
		this.a = tempList.toArray(new String[]{});
		this.lengthOfChars = this.a[0].length();
	}
	
	public void process()
	{
		LSD.sort(a, this.lengthOfChars);
	}
	
	public static void sort(String[] a, int lengthOfChars)
	{
		String[] aux = new String[a.length];
//		int[] count = new int[R + 1];
		
		// Outmost Loop
		for(int i = lengthOfChars - 1; i >= 0; i--)
		{
			// Every loop should re-create the count array
			int[] count = new int[R + 1];
			// Compute the frequency
			for(int j = 0; j < a.length; j++)
			{
				count[a[j].charAt(i) + 1]++;
			}
			// Transform counts to indices
			for(int j = 0; j < R; j++)
			{
				count[j + 1] += count[j];
			}
			// Distribute the records
			for(int j = 0; j < a.length; j++)
			{
				aux[count[a[j].charAt(i)]++] = a[j];
			}
			// Copy back
			for(int j = 0; j < a.length; j++)
			{
				a[j] = aux[j];
			}
		}
	}
	
	public String[] getStrings()
	{
		return this.a;
	}
}
