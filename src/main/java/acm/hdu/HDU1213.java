package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * AC one shot !
 * 
 * UF Application, get the total number of components
 * 
 * @author jiangr2
 * 
 */
public class HDU1213 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int totalCases = Integer.parseInt(br.readLine());

		WeightedQUWithPathCompression uf;

		String[] parts;
		while (totalCases > 0) {
			parts = br.readLine().split(" ");

			// based on 1, not 0
			uf = new WeightedQUWithPathCompression(
					Integer.parseInt(parts[0]) + 1);

			// construct the uf
			int tuples = Integer.parseInt(parts[1]);
			while (tuples > 0) {
				parts = br.readLine().split(" ");
				uf.union(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				tuples--;
			}
			// output
			// excluding the node 0
			out.println(uf.count() - 1);
			br.readLine();
			totalCases--;
		}
		out.flush();
	}
}

class WeightedQUWithPathCompression {
	private int count;
	private int[] id;

	public WeightedQUWithPathCompression(int N) {
		this.count = N;
		this.id = new int[N];

		for (int i = 0; i < this.count; i++) {
			id[i] = i;
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
		id[qCom] = pCom;
		count--;
	}

	public int count() {
		return this.count;
	}
}
