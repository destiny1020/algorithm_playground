package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * MAX SUM SEQUENCE - DP Problem
 * 
 * Presentation Error * 2 -> AC Pay attention to the out.println(), it is used
 * between two cases, between!!!
 * 
 * @author jiangr2
 * 
 */
public class HDU1003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int total = Integer.parseInt(br.readLine());
		String[] splits = null;
		for (int i = 0; i < total; i++) {
			out.print("Case ");
			out.print(i + 1);
			out.println(":");

			splits = br.readLine().split(" ");
			int length = splits.length;
			int[] param = new int[length - 1];
			for (int j = 0; j < length - 1; j++) {
				param[j] = Integer.parseInt(splits[j + 1]);
			}

			MaxSumSequence mss = new MaxSumSequence(param);

			out.print(mss.getMax());
			out.print(" ");
			out.print(mss.getStart() + 1);
			out.print(" ");
			out.println(mss.getEnd() + 1);
			if (i != total - 1) {
				out.println();
			}
		}

		out.flush();
	}
}

class MaxSumSequence {
	private int length;
	private int[] a;
	private int[] b;

	private int[] start;

	private int end;
	private int max;

	public MaxSumSequence(int[] sequence) {
		length = sequence.length;
		a = sequence;
		b = new int[length];
		start = new int[length];

		b[0] = a[0];
		max = b[0];

		for (int i = 1; i < length; i++) {
			int temp = b[i - 1] + a[i];
			if (temp >= a[i]) {
				b[i] = temp;
				start[i] = start[i - 1];
			} else {
				b[i] = a[i];
				start[i] = i;
			}

			if (b[i] > max) {
				end = i;
				max = b[i];
			}
		}
	}

	public int getMax() {
		return max;
	}

	public int getStart() {
		return start[end];
	}

	public int getEnd() {
		return end;
	}
}
