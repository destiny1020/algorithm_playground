package chap4.sp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CriticalPath
{
	private WeightedDigraph wdi;
	private AcyclicLP alp;

	public CriticalPath(String filePath) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int V = Integer.parseInt(reader.readLine());

		wdi = new WeightedDigraph(2 * V + 2);
		int start = 2 * V;
		int sink = 2 * V + 1;

		for (int i = 0; i < V; i++)
		{
			String line = reader.readLine();
			String[] parts = line.split("\\s+");
			double duration = Double.parseDouble(parts[0]);

			// Add virtual and real edges
			wdi.addEdge(i, V + i, duration);
			wdi.addEdge(start, i, 0.0);
			wdi.addEdge(V + i, sink, 0.0);

			if (0 != Integer.parseInt(parts[1]))
			{
				// Add constraint edges
				for (int j = 2; j < parts.length; j++)
				{
					wdi.addEdge(V + i, Integer.parseInt(parts[j]), 0.0);
				}
			}
		}

		alp = new AcyclicLP(wdi, start);
	}

	public double timeConsumed()
	{
		return alp.distTo(wdi.getV() - 1);
	}

	public Iterable<DirectedEdge> criticalPaths()
	{
		return alp.pathsTo(wdi.getV() - 1);
	}
}
