package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * WA * n -> AC
 * 
 * trim the prefix 0s ; use Integer.parse(str) is not possible because the
 * number may be too large !!! throws NumberFormatException
 * 
 * @author jiangr2
 * 
 */
public class HDU1002 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		new Summer2(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out));
	}
}

class Summer2 {
	private PrintWriter out;

	public Summer2(BufferedReader br, PrintWriter out)
			throws NumberFormatException, IOException {
		this.out = out;
		String line = br.readLine();
		int total = Integer.parseInt(line);

		for (int i = 1; i <= total; i++) {
			out.print("Case ");
			out.print(i);
			out.println(":");
			calculate(br.readLine().split(" "));
			if (i != total) {
				out.println();
			}
		}

		out.flush();
	}

	private void calculate(String[] split) {
		String str1 = split[0];
		String str2 = split[1];

		// int indexZero = 0;
		// while(str1.charAt(indexZero) == '0') {
		// indexZero++;
		// }
		// str1 = str1.substring(indexZero);
		// indexZero = 0;
		//
		// while(str2.charAt(indexZero) == '0') {
		// indexZero++;
		// }
		// str2 = str2.substring(indexZero);

		int length1 = str1.length();
		int length2 = str2.length();

		out.print(str1);
		out.print(" + ");
		out.print(str2);
		out.print(" = ");

		StringBuilder sb = new StringBuilder();

		boolean flag = false;

		for (int i = 0; i < Math.min(length1, length2); i++) {
			int digit = Integer.parseInt(str1.substring(length1 - i - 1,
					length1 - i))
					+ Integer.parseInt(str2.substring(length2 - i - 1, length2
							- i));
			if (flag) {
				digit += 1;
			}

			flag = digit >= 10 ? true : false;
			sb.append(digit % 10);
		}

		if (length1 > length2) {
			int distance = length1 - length2;
			for (int i = 0; i < distance; i++) {
				int digit = Integer.parseInt(str1.substring(distance - 1 - i,
						distance - i));
				if (flag) {
					digit += 1;
					flag = digit >= 10 ? true : false;
				}
				sb.append(digit % 10);
			}
		} else {
			int distance = length2 - length1;
			for (int i = 0; i < distance; i++) {
				int digit = Integer.parseInt(str2.substring(distance - 1 - i,
						distance - i));
				if (flag) {
					digit += 1;
					flag = digit >= 10 ? true : false;
				}
				sb.append(digit % 10);
			}
		}

		if (flag) {
			sb.append("1");
		}

		String result = sb.reverse().toString();
		int indexZero = 0;
		while (result.charAt(indexZero) == '0') {
			indexZero++;
		}
		result = result.substring(indexZero);

		out.println(result);
	}
}
