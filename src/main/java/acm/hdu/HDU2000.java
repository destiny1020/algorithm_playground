package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * WA * n !!!???
 * 
 * @author jiangr2
 * 
 */
public class HDU2000 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = br.readLine();
		char[] chars;
		while (!line.isEmpty()) {
			chars = line.toCharArray();

			// sorting
			sortWithSentinel(chars);

			out.print(chars[0]);
			out.print(' ');
			out.print(chars[1]);
			out.print(' ');
			out.println(chars[2]);
			line = br.readLine();
		}
		out.flush();
	}

	public static void sortWithSentinel(char[] a) {
		int N = a.length;

		int min = 0;
		for (int i = 1; i < N; i++) {
			if (less(a[i], a[min])) {
				min = i;
			}
		}
		exch(a, 0, min);

		for (int i = 2; i < N; i++) {
			char v = a[i];
			int j = i;

			while (less(v, a[j - 1])) {
				a[j] = a[j - 1];
				j--;
			}

			a[j] = v;
		}
	}

	public static void exch(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static boolean less(int v, int w) {
		return v < w;
	}
}
