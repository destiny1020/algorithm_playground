package chap1.uf.examples;

import java.util.LinkedList;
import java.util.Scanner;

import chap1.uf.WeightedQUWithPathCompression;

/**
 * HDU-1272
 * IF YOU WANT TO OUTPUT THE CYCLE. 
 * YOU MUST USE ADJACENT LIST.
 * 
 * @author jiangr2
 */
public class XiaoxiMaze {

	private WeightedQUWithPathCompression uf;
	private boolean valid = true;
	
	private int[] cycleComponents;
	
	// FOR TRACING THE CYCLE
//	private LinkedList<Linked>
	// TODO

	public XiaoxiMaze(Scanner scanner) {
		int N = scanner.nextInt();
		
		uf = new WeightedQUWithPathCompression(N + 1);

		int p = scanner.nextInt();
		int q = scanner.nextInt();
		int count = uf.count();

		while (p != 0 && q != 0) {
			uf.union(p, q);
			
			int temp_count = uf.count();
			if (temp_count + 1 != count) {
				valid = false;
				// get component size
				int size = uf.componentSize(p);
				int component = uf.find(p);
				cycleComponents = new int[size];

				int index = 0;
				// add nodes to this array
				// linear solution, need to improve
				for (int i = 0; i < N; i++) {
					// index based on 1, not 0
					if (uf.find(i + 1) == component) {
						cycleComponents[index++] = i + 1;
					}
				}
				
				// TRACING THE CYCLE.
				// TODO
				
				return;
			}
			count = temp_count;
			p = scanner.nextInt();
			q = scanner.nextInt();
		}
	}

	public int[] getCycleComponents() {
		return cycleComponents;
	}

	public boolean isValid() {
		return valid;
	}
}
