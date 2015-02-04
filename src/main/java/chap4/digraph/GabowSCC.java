package chap4.digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chap1.LinkedStack;

public class GabowSCC {

	private Digraph digraph;

	private int V;

	// or use the id[] to record the component id
	// onStack[v] is equal to id[v] == -1
	// both judgements represent that v has no component id yet
	private boolean[] onStack;

	private int[] index;

	private LinkedStack<Integer> mainStack;
	private LinkedStack<Integer> pathStack;

	private List<List<Integer>> sccs;

	private int sequence = 1;

	public GabowSCC(Digraph digraph) {
		this.digraph = digraph;

		V = digraph.getV();
		onStack = new boolean[V];

		index = new int[V];

		Arrays.fill(index, -1);

		// init the stacks
		mainStack = new LinkedStack<Integer>();
		pathStack = new LinkedStack<Integer>();

		sccs = new ArrayList<List<Integer>>();

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
		index[v] = sequence++;

		// push the current vertex into both main/path stack
		mainStack.push(v);
		pathStack.push(v);
		onStack[v] = true;

		for (int w : digraph.adj(v)) {
			if (-1 == index[w]) {
				dfs(w);
			} else if (onStack[w]) {
				// pop the path stack
				while (!pathStack.isEmpty() && index[pathStack.peek()] > index[w]) {
					pathStack.pop();
				}
			}
		}

		// the same meaning as "if the current vertex is the root"
		if (!pathStack.isEmpty() && pathStack.peek() == v) {
			pathStack.pop();
		} else {
			return;
		}

		// pop the main stack to form a scc
		List<Integer> scc = new ArrayList<Integer>();
		int w = -1;
		do {
			w = mainStack.pop();
			scc.add(w);
			
			// pay attention to this !
			onStack[w] = false;
		} while (w != v);

		sccs.add(scc);
	}

	public List<List<Integer>> getSccs() {
		return sccs;
	}
}
