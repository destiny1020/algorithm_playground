package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Assign Task, Strict UF Applcation
 * 
 * AC one shot !
 * 
 * @author jiangr2
 * 
 */
public class HDU3974 {

	public static void main(String[] args) throws IOException {
		new StrictUF(new BufferedReader(new InputStreamReader(System.in)),
				new PrintWriter(System.out));
	}

}

class StrictUF {
	private int[] id;

	private int[] update;
	private List<Integer> event;
	private int event_id;

	public StrictUF(BufferedReader br, PrintWriter out) throws IOException {

		int totalCases = Integer.parseInt(br.readLine());

		String[] parts;
		int child, parent;
		for (int j = 0; j < totalCases; j++) {

			out.print("Case #");
			out.print(j + 1);
			out.println(":");

			event_id = 0;

			int N = Integer.parseInt(br.readLine());

			this.id = new int[N];
			this.update = new int[N];

			for (int i = 0; i < id.length; i++) {
				this.id[i] = i;
				this.update[i] = -1;
			}

			// init the tree organization
			for (int i = 0; i < N - 1; i++) {
				parts = br.readLine().split("\\s");
				child = Integer.parseInt(parts[0]);
				parent = Integer.parseInt(parts[1]);
				union(child - 1, parent - 1);
			}

			// event number
			int M = Integer.parseInt(br.readLine());
			event = new ArrayList<Integer>(M);

			char type;
			int empId, taskId;

			// init the event array
			for (int i = 0; i < M; i++) {
				parts = br.readLine().split("\\s");
				type = parts[0].charAt(0);
				switch (type) {
				case 'C':
					empId = Integer.parseInt(parts[1]);
					taskId = getTaskId(empId - 1);
					// output
					out.println(taskId);
					break;
				case 'T':
					empId = Integer.parseInt(parts[1]);
					taskId = Integer.parseInt(parts[2]);
					update[empId - 1] = event_id++;
					event.add(taskId);
					break;
				default:
					throw new IllegalArgumentException("Invalid Input !");
				}
			}
		}

		out.flush();
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
		} while (empId != root);

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
	}
}