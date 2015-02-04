package chap1.uf;

/**
 * Weighted Quick Union with Path Compression
 * 
 * @author Destiny
 * 
 */
public class WeightedQUWithPathCompression {
	private int count;
	private int[] id;
	private int[] size;

	public WeightedQUWithPathCompression(int N) {
		this.count = N;
		this.id = new int[N];
		this.size = new int[N];

		for (int i = 0; i < this.count; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int find(int p) {
		while (p != id[p]) {
			// Change p's parent to its grandparent
			// One line to implement path compression !
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
			// Make qCom a child of pCom
			id[qCom] = pCom;
			size[pCom] += size[qCom];
		} else {
			// Make pCom a child of qCom
			id[pCom] = qCom;
			size[qCom] += size[pCom];
		}

		count--;
	}

	// if the id is not the root node, find the root first
	public int componentSize(int id) {
		return size[find(id)];
	}

	public int count() {
		return this.count;
	}
}
