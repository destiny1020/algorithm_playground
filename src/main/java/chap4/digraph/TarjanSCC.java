package chap4.digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chap1.LinkedStack;

public class TarjanSCC {

	private Digraph digraph;

	private int V;

	private boolean[] onStack;

	private int[] index;
	private int[] lowlink;

	private LinkedStack<Integer> stack;

	private List<List<Integer>> sccs;

	// used to record the index property of each node
	private int sequence = 1;

	public TarjanSCC(Digraph digraph) {
		this.digraph = digraph;

		this.V = digraph.getV();
		this.onStack = new boolean[V];

		this.lowlink = new int[V];
		this.index = new int[V];
		
		Arrays.fill(this.index, -1);
		
		this.stack = new LinkedStack<Integer>();

		this.sccs = new ArrayList<List<Integer>>();

		process();
	}

	private void process() {
		for (int i = 0; i < V; i++) {
			if (-1 == index[i]) {
				dfs(i);
			}
		}
	}

	private void dfs(int v) {
		this.index[v] = sequence;
		this.lowlink[v] = sequence++;

		// push it into the stack and record it in flag array
		this.stack.push(v);
		this.onStack[v] = true;

		// for every adjacent edge
		for (int w : digraph.adj(v)) {
			if (-1 == index[w]) {
				dfs(w);
				// update the lowlink value
				this.lowlink[v] = Math.min(this.lowlink[v], this.lowlink[w]);
				// if the adjacent vertex is in the stack
			} else if (this.onStack[w]) {
				this.lowlink[v] = Math.min(this.lowlink[v], this.index[w]);
			}
		}

		// if the current vertex is the root
		if (this.index[v] == this.lowlink[v]) {
			List<Integer> scc = new ArrayList<Integer>();
			int w = -1;
			while (w != v) {
				w = this.stack.pop();
				scc.add(w);
				this.onStack[w] = false;
			}
			this.sccs.add(scc);
		}
	}

	public List<List<Integer>> getSccs() {
		return sccs;
	}
}
