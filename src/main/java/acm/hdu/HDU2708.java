package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Vertical Histogram A 65 Z 90
 * 
 * A-Z Complete Version: http://acm.hdu.edu.cn/viewcode.php?rid=6246573
 * 
 * WA !!! Format Problems
 * 
 * @author Destiny
 * 
 */
public class HDU2708 {
	private static int LINES = 4;
	private static int LETTERS = 26;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int[] stat = new int[LETTERS];

		String line;
		for (int i = 0; i < LINES; i++) {
			line = br.readLine();
			for (int ch : line.toCharArray()) {
				if (ch >= 65 && ch <= 90) {
					stat[ch - 65]++;
				}
			}
		}

		// get the max count
		int max = stat[0];
		for (int i = 1; i < LETTERS; i++) {
			max = (stat[i] > max) ? stat[i] : max;
		}

		// construct draw blank array
		int last = -1; // in order to not print the trailing space
		for (int i = 0; i < LETTERS; i++) {
			stat[i] = max - stat[i];
			if (stat[i] != max) {
				last = i;
			}
		}

		// draw the vertical histogram -- '*'s
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < LETTERS; j++) {
				if (stat[j] == max) {
					continue;
				}

				if (stat[j] > 0) {
					if (j < last) {
						out.print("  ");
					} else {
						out.print(" ");
					}
					stat[j]--;
				} else {
					if (j < last) {
						out.print("* ");
					} else {
						out.print("*");
					}
				}
			}
			out.println();
		}

		// draw the letters
		for (int i = 65; i <= 90; i++) {
			if (stat[i - 65] == max) {
				continue;
			}
			out.print((char) i);
			if (i - 65 < last) {
				out.print(' ');
			}
		}

		out.flush();
	}
}
