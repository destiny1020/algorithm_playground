package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * WA * n ......PE * n -> AC
 * 
 * out.printf("%.3f\n", result);
 * 
 * ----- >
 * 
 * out.printf("%.3f", result); out.println();
 * 
 * out.println() is platform independent !
 * 
 * @author jiangr2
 * 
 */
public class HDU1009 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		Value[] values;

		int total, lines;
		String[] parts = br.readLine().split(" ");
		total = Integer.parseInt(parts[0]);
		lines = Integer.parseInt(parts[1]);

		while (-1 != total && -1 != lines) {
			values = new Value[lines];

			int index = 0;
			double result = 0.0;
			if (0 != lines) {
				while (lines > 0) {
					parts = br.readLine().split(" ");
					int a = Integer.parseInt(parts[0]);
					int b = Integer.parseInt(parts[1]);
					values[index] = new Value(a, b);
					index++;
					lines--;
				}
				// sort the ratio and get the result
				Arrays.sort(values);
				index = values.length - 1;
				while (total >= 0 && -1 != index) {
					if (total >= values[index].b) {
						result += values[index].a;
						total -= values[index].b;
					} else {
						result += total * values[index].ratio;
						break;
					}
					index--;
				}
			}
			out.printf("%.3f", result);
			out.println();

			parts = br.readLine().split(" ");
			total = Integer.parseInt(parts[0]);
			lines = Integer.parseInt(parts[1]);
		}
		out.flush();
	}

	private static class Value implements Comparable<Value> {
		int a;
		int b;
		double ratio;

		Value(int a, int b) {
			this.a = a;
			this.b = b;

			if (0 == b) {
				ratio = 1e30;
			} else {
				ratio = a * 1.0 / b;
			}
		}

		@Override
		public int compareTo(Value that) {
			return Double.compare(this.ratio, that.ratio);
		}

	}
}
