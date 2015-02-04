package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * WA: (total + 1) * total may overflow ! Use long to get it done ! -> AC
 * 
 * @author Destiny
 * 
 */
public class HDU1001
{
	public static void main(String[] args) throws NumberFormatException,
			IOException
	{
		new Summer(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out));
	}
}

class Summer
{
	private PrintWriter out;

	public Summer(BufferedReader br, PrintWriter out)
			throws NumberFormatException, IOException
	{
		this.out = out;
		String line = br.readLine();
		while (null != line && !line.isEmpty())
		{
			calculate(Integer.parseInt(line));
			line = br.readLine();
		}
	}

	private void calculate(int total)
	{
		out.println(((long) (total + 1) * total) >> 1);
		out.println();
		out.flush();
	}
}
