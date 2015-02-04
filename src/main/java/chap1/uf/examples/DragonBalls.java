package chap1.uf.examples;

import java.util.Scanner;

import chap1.uf.WeightedQUWithPathCompressionAndTransTime;

/**
 * HDU-3635
 * @author Destiny
 *
 */
public class DragonBalls {

	private WeightedQUWithPathCompressionAndTransTime uf;

	public DragonBalls(Scanner scanner) {
		int N = scanner.nextInt();

		uf = new WeightedQUWithPathCompressionAndTransTime(N + 1);

		int lines = scanner.nextInt();

		int p, q;
		while (lines > 0) {
			char ops = scanner.next().charAt(0);

			switch (ops) {
			case 'T':
				p = scanner.nextInt();
				q = scanner.nextInt();
				transport(p, q);
				break;
			case 'Q':
				p = scanner.nextInt();
				query(p);
				break;
			default:
				break;
			}
			lines--;
		}
	}

	private void query(int p) {
		int root = uf.find(p);
		int size = uf.componentSize(p);
		int trans = uf.getTransTime(p);
		
		System.out.println(root + " " + size + " " + trans);
	}

	private void transport(int p, int q) {
		uf.union(p, q);
	}

}
