package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Encoding
 * 
 * Presentation Error (The last println is a must ??!!) -> AC
 * 
 * @author Destiny
 * 
 */
public class HDU1020
{
	public static void main(String[] args) throws NumberFormatException,
			IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int total = Integer.parseInt(br.readLine());

		String line;
		char previous, now;
		int count;
		while (total > 0)
		{
			line = br.readLine();
			previous = line.charAt(0);
			count = 1;

			for (int i = 1; i < line.length(); i++)
			{
				now = line.charAt(i);
				if (now == previous)
				{
					count++;
				} else
				{
					if (count > 1)
					{
						out.print(count);
					}
					out.print(previous);
					count = 1;
					previous = now;
				}
			}
			if (count > 1)
			{
				out.print(count);
			}
			out.print(previous);
			out.println();
			total--;
		}
		out.flush();
	}
}
