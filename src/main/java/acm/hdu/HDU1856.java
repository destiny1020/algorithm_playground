package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * AC one shot ! UF Problem
 * @author jiangr2
 *
 */
public class HDU1856 {

	private static final int MAX = 100000;
	private static int[] sz;
	private static int[] id;
	private static int max = 1;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line;
		String[] parts;
		line = br.readLine();
		while (null != line && !line.isEmpty()) {
			int total = Integer.parseInt(line);
			if(total > 0) {
				id = new int[MAX + 1];
				sz = new int[MAX + 1];
				for (int i = 1; i <= MAX; i++) {
					id[i] = i;
					sz[i] = 1;
				}
			}
			while (total > 0) {
				parts = br.readLine().split(" ");
				union(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				total--;
			}
			out.println(max);
			max = 1;
			line = br.readLine();
		}
		out.flush();
	}

	private static void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot) {
			return;
		}

		if (sz[pRoot] > sz[qRoot]) {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
			if (sz[pRoot] > max) {
				max = sz[pRoot];
			}
		} else {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
			if (sz[qRoot] > max) {
				max = sz[qRoot];
			}
		}
	}

	private static int find(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			// no need to update the sz[] for the children
			p = id[p];
		}
		return p;
	}
}
