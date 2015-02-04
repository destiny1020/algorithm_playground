package chap1.uf.examples;

import java.util.Scanner;

import chap1.uf.WeightedQUWithPathCompression;

/**
 * ZOJ-2833 Friendship, Union-Find Sample
 * 
 * @author jiangr2
 * 
 */
public class Friendship {

	private int number;

	private WeightedQUWithPathCompression uf;

	public Friendship(Scanner scanner) {
		number = scanner.nextInt();

		uf = new WeightedQUWithPathCompression(number);

		int lines = scanner.nextInt();

		int ops1, ops2;

		while (lines > 0) {
			char ops = scanner.next().charAt(0);

			switch (ops) {
			case 'M':
				ops1 = scanner.nextInt();
				ops2 = scanner.nextInt();

				// array index started at 0, but data is based on 1
				uf.union(ops1 - 1, ops2 - 1);
				break;
			case 'Q':
				ops1 = scanner.nextInt();

				// array index started at 0, but data is based on 1
				System.out.println(uf.componentSize(ops1 - 1));
				break;
			default:
				throw new IllegalArgumentException("Invalid Input !");
			}
			lines--;
		}
	}
}
