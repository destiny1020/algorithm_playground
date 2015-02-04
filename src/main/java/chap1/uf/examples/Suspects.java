package chap1.uf.examples;

import java.util.Scanner;

import chap1.uf.WeightedQUWithPathCompression;

/**
 * POJ-1611
 * 
 * @author jiangr2
 * 
 */
public class Suspects {

	private int number;

	private WeightedQUWithPathCompression uf;

	public Suspects(Scanner scanner) {
		number = scanner.nextInt();

		uf = new WeightedQUWithPathCompression(number);

		int lines = scanner.nextInt();

		scanner.nextLine();

		String line;
		while (lines > 0) {
			line = scanner.nextLine();
			String[] parts = line.split("\\s");
			for (int i = 0; i < Integer.parseInt(parts[0]) - 1; i++) {
				uf.union(Integer.parseInt(parts[1]),
						Integer.parseInt(parts[i + 2]));
			}
			lines--;
		}
		
		System.out.println(uf.componentSize(0));
	}

}
