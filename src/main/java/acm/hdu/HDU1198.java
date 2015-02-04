package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Farm Irrigation - UF Solution
 * 
 * AC one shot !
 * 
 * @author Destiny
 * 
 */
public class HDU1198 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String[] meta = br.readLine().split("\\s");
		int rows = Integer.parseInt(meta[0]);
		int columns = Integer.parseInt(meta[1]);

		FarmIrrigation fi;
		String line;
		while (rows != -1 && columns != -1) {
			fi = new FarmIrrigation(rows, columns);
			int rIndex = 0, cIndex = 0;
			while (rows > 0) {
				line = br.readLine();
				cIndex = 0;
				for (int i = 0; i < columns; i++) {
					fi.matrix[rIndex][cIndex] = line.charAt(i);
					cIndex++;
				}
				rIndex++;
				rows--;
			}
			fi.process();
			out.println(fi.count());

			// Prepare next
			br.readLine();
			meta = br.readLine().split(" ");
			rows = Integer.parseInt(meta[0]);
			columns = Integer.parseInt(meta[1]);
		}

		out.flush();
	}
}

class FarmIrrigation {
	private static boolean[] UP;
	private static boolean[] RIGHT;
	private static boolean[] BOTTOM;
	private static boolean[] LEFT;

	static {
		UP = new boolean[] { true, true, false, false, true, false, true, true,
				false, true, true };
		RIGHT = new boolean[] { false, true, false, true, false, true, true,
				false, true, true, true };
		BOTTOM = new boolean[] { false, false, true, true, true, false, false,
				true, true, true, true };
		LEFT = new boolean[] { true, false, true, false, false, true, true,
				true, true, false, true };
	}

	private int rows;
	private int columns;

	public char[][] matrix;
	private FarmUF uf;

	public FarmIrrigation(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;

		this.matrix = new char[rows][columns];
		this.uf = new FarmUF(rows * columns);
	}

	public void process() {
		// handle rows
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns - 1; j++) {
				if (RIGHT[matrix[i][j] - 'A'] && LEFT[matrix[i][j + 1] - 'A']) {
					uf.union(i * columns + j, i * columns + j + 1);
				}
			}
		}

		// handle columns
		for (int j = 0; j < columns; j++) {
			for (int i = 0; i < rows - 1; i++) {
				if (BOTTOM[matrix[i][j] - 'A'] && UP[matrix[i + 1][j] - 'A']) {
					uf.union(i * columns + j, (i + 1) * columns + j);
				}
			}
		}
	}

	public int count() {
		return uf.count();
	}
}

class FarmUF {
	private int count;
	private int[] id;
	private int[] size;

	public FarmUF(int N) {
		this.count = N;
		this.id = new int[N];
		this.size = new int[N];

		for (int i = 0; i < this.count; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int find(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int pCom = this.find(p);
		int qCom = this.find(q);

		if (pCom == qCom) {
			return;
		}

		if (size[pCom] > size[qCom]) {
			id[qCom] = pCom;
			size[pCom] += size[qCom];
		} else {
			id[pCom] = qCom;
			size[qCom] += size[pCom];
		}

		count--;
	}

	public int count() {
		return this.count;
	}
}
