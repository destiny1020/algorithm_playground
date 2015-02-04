package chap4.digraph;

import java.util.ArrayList;
import java.util.List;

public class KosarajuSCC {

	private Digraph digraph;

	private int V;

	private boolean[] visited;

	private int[] components;

	private List<List<Integer>> sccs;

	// record the current component id
	private int current = 0;

	// reverseTopo is not necessarily a topological order, it should be reverse
	// post order instead
	public KosarajuSCC(Digraph digraph, Iterable<Integer> reverseTopo) {
		this.digraph = digraph;
		V = digraph.getV();

		visited = new boolean[V];
		components = new int[V];

		for (int v : reverseTopo) {
			if (!visited[v]) {
				dfs(v);
				current++;
			}
		}
	}

	private void dfs(int v) {
		visited[v] = true;
		components[v] = current;

		for (int w : digraph.adj(v)) {
			if (!visited[w]) {
				dfs(w);
			}
		}
	}

	public int[] getComponents() {
		return components;
	}

	public List<List<Integer>> getSccs() {
		sccs = new ArrayList<List<Integer>>();

		for (int i = 0; i < current; i++) {
			sccs.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < V; i++) {
			sccs.get(components[i]).add(i);
		}

		return sccs;
	}
}
