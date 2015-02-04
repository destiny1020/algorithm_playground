package chap1.uf.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HDU-3974
 * 
 * @author jiangr2
 * 
 */
public class AssignTask {

	public AssignTask(Scanner scanner) {
		@SuppressWarnings("unused")
		StrictUF uf = new StrictUF(scanner);
	}
	
	
	private static class StrictUF {
		private int[] id;
		private int count;

		private int[] update;
		private List<Integer> event;
		private int event_id = 0;

		public StrictUF(Scanner scanner) {
			int N = scanner.nextInt();

			this.id = new int[N];
			this.update = new int[N];

			for (int i = 0; i < id.length; i++) {
				this.id[i] = i;
				this.update[i] = -1;
			}

			// init the tree organization
			int child, parent;
			for (int i = 0; i < N - 1; i++) {
				child = scanner.nextInt();
				parent = scanner.nextInt();
				union(child - 1, parent - 1);
			}

			// event number
			int M = scanner.nextInt();
			event = new ArrayList<Integer>();
			
			char type;
			int empId, taskId;

			// init the event array
			for (int i = 0; i < M; i++) {
				type = scanner.next().charAt(0);
				switch (type) {
				case 'C':
					empId = scanner.nextInt();
					taskId = getTaskId(empId - 1);
					// output
					System.out.println(taskId);
					break;
				case 'T':
					empId = scanner.nextInt();
					taskId = scanner.nextInt();
					update[empId - 1] = event_id++;
					event.add(taskId);
					break;
				default:
					throw new IllegalArgumentException("Invalidu Input !");
				}
			}
		}

		private int getTaskId(int empId) {
			int current_event = update[empId];
			// have not arrived at root
			int root = find(empId);
			do {
				empId = id[empId];
				if (update[empId] > current_event) {
					current_event = update[empId];
				}
			} while(empId != root);
			
			if (-1 != current_event) {
				return event.get(current_event);
			}
			return -1;
		}

		private int find(int p) {
			// id[p] plays as a parent-link
			while (p != id[p])
				p = id[p];

			// Return the root
			return p;
		}

		public void union(int p, int q) {
			// Make pCom a child of qCom
			// also means qCom is the immediate parent of pCom
			id[p] = q;
			count--;
		}
	}
}
