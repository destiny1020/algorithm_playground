package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Graphic possible judgement algorithm
 * 
 * @author jiangr2
 * 
 */
public class HavelHakimi {

	private String dataFile;
	private int V;
	private Node[] nodes;
	private boolean[][] matrix;
	private boolean ready;

	public HavelHakimi(String dataFile) throws FileNotFoundException {
		this.dataFile = dataFile;

		init();
	}

	public void process() throws HavelHakimiException {
		for (int i = 0; i < this.V - 1; i++) {
			// sorting
			Arrays.sort(this.nodes);

			// First invalid situation:
			if (this.nodes[i].degree > this.V - i - 1) {
				throw new HavelHakimiException(
						"The degree of the first node is too big !");
			} else {
				int counter = this.nodes[i].degree;
				for (int j = i + 1; j < this.V && counter > 0; j++, counter--) {
					if (0 == this.nodes[j].degree && j != (this.V - 1)) {
						throw new HavelHakimiException(
								"One of the node has zero degree !");
					} else {
						this.nodes[j].degree--;
						this.matrix[nodes[i].index][nodes[j].index] = true;
						this.matrix[nodes[j].index][nodes[i].index] = true;
					}
				}
			}
		}
		this.ready = true;
	}

	public boolean[][] getMatrix() {
		if (this.ready) {
			return this.matrix;
		} else {
			return null;
		}
	}

	private void init() throws FileNotFoundException {
		File data = new File(dataFile);
		if(!data.exists()) {
			throw new RuntimeException("Data file is not exist !");
		}
		
		Scanner scanner = new Scanner(data);

		if (scanner.hasNextInt()) {
			this.V = scanner.nextInt();
			this.nodes = new Node[V];
			this.matrix = new boolean[V][V];
		}

		for (int i = 0; i < this.V; i++) {
			if (scanner.hasNextInt()) {
				this.nodes[i] = new Node(i, scanner.nextInt());
			} else {
				throw new RuntimeException("Invalid Data Format !");
			}
		}
	}

	private static class Node implements Comparable<Node> {
		public int index;
		public int degree;

		public Node(int index, int degree) {
			this.index = index;
			this.degree = degree;
		}

		@Override
		public int compareTo(Node other) {
			return (other.degree - this.degree);
		}

		@Override
		public String toString() {
			return "Index: " + this.index + " - Degree: " + this.degree;
		}
	}

	public static class HavelHakimiException extends Exception {

		private static final long serialVersionUID = 1L;

		public HavelHakimiException(String description) {
			super(description);
		}
	}
}
