package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * DP Solution - TLE ! http://acm.hdu.edu.cn/viewcode.php?rid=6240061 AC by
 * finding the underlying rules
 * 
 * @author jiangr2
 * 
 */
public class HDU1005 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] parts = br.readLine().split(" ");
		int[] fns = new int[52];

		int A = Integer.parseInt(parts[0]);
		int B = Integer.parseInt(parts[1]);
		int N = Integer.parseInt(parts[2]);

		fns[1] = 1;
		fns[2] = 1;

		int loop = -1;

		while (A != 0 || B != 0 || N != 0) {

			// loop N - 2 times
			for (int i = 3; i <= 51; i++) {
				fns[i] = (A * fns[i - 1] + B * fns[i - 2]) % 7;
				if (fns[i] == 1 && fns[i - 1] == 1) {
					loop = i - 2;
				}
			}
			out.println(fns[N % loop]);

			// restore and read next
			parts = br.readLine().split(" ");

			A = Integer.parseInt(parts[0]);
			B = Integer.parseInt(parts[1]);
			N = Integer.parseInt(parts[2]);
		}

		out.flush();
	}
}
