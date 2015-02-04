package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * AC one shot ! Rank 1
 * http://acm.hdu.edu.cn/statistic.php?pid=1846&lang=6&order_type=0
 * 
 * @author jiangr2
 * 
 */
public class HDU1846 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int total = Integer.parseInt(br.readLine());

		String[] parts;
		while (total > 0) {
			parts = br.readLine().split(" ");
			int N = Integer.parseInt(parts[0]);
			int M = Integer.parseInt(parts[1]);

			int result = N % (M + 1);
			if (result > 0 && result <= M) {
				out.println("first");
			} else {
				out.println("second");
			}
			total--;
		}
		out.flush();
	}
}
