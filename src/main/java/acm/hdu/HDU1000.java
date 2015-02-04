package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HDU1000
{
	public static void main(String[] args) throws IOException
	{
		// Scanner scanner = new Scanner(new BufferedReader(new
		// InputStreamReader(System.in)));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String line = br.readLine();
		String[] parts;
		while (null != line && !line.isEmpty())
		{
			parts = line.split(" ");
			out.println(Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]));
			line = br.readLine();
		}
		out.flush();
	}
}
