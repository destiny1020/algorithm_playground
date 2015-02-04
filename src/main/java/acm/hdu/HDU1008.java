package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * AC
 * @author jiangr2
 * 
 */
public class HDU1008 {

	public static void main(String[] args) throws IOException {

		int lift = 6;
		int down = -4;
		int stop = 5;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = br.readLine();
		String[] parts;
		int[] steps;
		while (!line.equals("0")) {
			parts = line.split(" ");

			steps = new int[parts.length];
			steps[0] = 0;
			for (int i = 1; i < parts.length; i++) {
				steps[i] = Integer.parseInt(parts[i]);
			}

			int result = stop * steps.length - stop;
			for (int i = 0; i < steps.length - 1; i++) {
				int interval = steps[i + 1] - steps[i];
				result += interval > 0 ? interval * lift : interval * down;
			}
			out.println(result);
			line = br.readLine();
		}
		out.flush();
	}
}
