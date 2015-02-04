package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * WA * N !! Quit...
 * 
 * @author jiangr2
 * 
 */
public class HDU2137 {

	private static final int DIRECTIONS = 8;
	private static StringBuilder content;
	private static String content_s;
	private static int N;
	private static int size;

	private static String line;
	private static boolean isReverse;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		line = br.readLine();

		while (null != line && !line.isEmpty()) {
			int lastSpace = line.lastIndexOf(' ');
			content_s = line.substring(0, lastSpace + 1);
			N = Integer.parseInt(line.substring(lastSpace + 1));

			size = content_s.length();
			isReverse = false;

			int adjust = ((N % DIRECTIONS) + DIRECTIONS) % 8;
			if (adjust <= 4 && adjust >= 1) {
				isReverse = true;
			}

			// For Full output, lines placeholders
			// if (adjust == 0 || adjust == 4) {
			// for (int i = 0; i < size; i++) {
			// if (i != size >> 1) {
			// for (int j = 0; j < size; j++) {
			// out.print(' ');
			// }
			// out.println();
			// } else {
			// if (isReverse) {
			// content = new StringBuilder(content_s);
			// out.println(content.reverse());
			// } else {
			// out.println(content_s);
			// }
			// }
			// }
			// }

			if (adjust == 0 || adjust == 4) {
				if (isReverse) {
					content = new StringBuilder(content_s);
					out.println(content.reverse());
				} else {
					out.println(content_s);
				}
			}

			if (adjust == 1 || adjust == 5) {
				int index = 0;
				int step = 1;
				int jIndex = size - 1;
				int jStep = -1;
				if (isReverse) {
					index = jIndex;
					step = jStep;
				}
				A1: for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (j != jIndex) {
							out.print(' ');
						} else {
							out.println(content_s.charAt(index));
							jIndex += jStep;
							index += step;
							continue A1;
						}
					}
				}
			}

			if (adjust == 2 || adjust == 6) {
				int index = 0;
				int step = 1;
				if (isReverse) {
					index = size - 1;
					step = -1;
				}
				A2: for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (j != (size >> 1)) {
							out.print(' ');
						} else {
							out.println(content_s.charAt(index));
							index += step;
							continue A2;
						}
					}
				}
			}

			if (adjust == 3 || adjust == 7) {
				int index = size - 1;
				int step = -1;
				int jIndex = 0;
				int jStep = 1;
				if (!isReverse) {
					index = jIndex;
					step = jStep;
				}
				A3: for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (j != jIndex) {
							out.print(' ');
						} else {
							out.println(content_s.charAt(index));
							jIndex += jStep;
							index += step;
							continue A3;
						}
					}
				}
			}

			line = br.readLine();
		}

		out.flush();
	}
}
