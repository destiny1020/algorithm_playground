package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * DP Solution, Electricity wiring problem, LIS problem
 * http://blog.csdn.net/ice_crazy/article/details/7536332 WA * n
 * 
 * @author jiangr2
 * 
 */
public class HDU1025 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = br.readLine();
		int caseId = 0;
		int total;
		int[] a;
		String[] parts;
		while (null != line && !line.isEmpty()) {
			total = Integer.parseInt(line);
			caseId++;
			out.print("Case ");
			out.print(caseId);
			out.println(":");
			a = new int[total];
			while (total > 0) {
				parts = br.readLine().split(" ");
				a[Integer.parseInt(parts[0]) - 1] = Integer.parseInt(parts[1]) - 1;
				total--;
			}
			// init the LIS
			LIS lis = new LIS(a);
			lis.process();
			// output
			int result = lis.getResult();
			out.print("My king, at most ");
			out.print(result);
			out.print(" road");
			if (result > 1) {
				out.print("s");
			}
			out.println(" can be built.\n");
			// restore the vars
			line = br.readLine();
		}

		out.flush();
	}
}

class LIS {
	private int a[];
	private int b[];
	private int length;

	public LIS(int[] a) {
		this.a = a;
		this.length = a.length;
		this.b = new int[length];

		// init
		b[0] = 1;
	}

	public int getResult() {
		return b[length - 1];
	}

	public void process() {
		int prev_max = -1;
		int max = a[0];
		for (int i = 1; i < length; i++) {
			if (a[i] > max) {
				b[i] = b[i - 1] + 1;
				prev_max = max;
				max = a[i];
			} else {
				b[i] = b[i - 1];
				if (a[i] < max && a[i] > prev_max) {
					max = a[i];
				}
			}
		}
	}
}
