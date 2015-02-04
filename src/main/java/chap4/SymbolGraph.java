package chap4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import chap3.ST;

public class SymbolGraph
{
	private ST<String, Integer> st;
	private String[] names;
	private Graph g;

	public SymbolGraph(String filePath, String delimiter) throws IOException
	{
		// Init ST
		st = new ST<String, Integer>();

		// Init split
		String split = (delimiter == null || delimiter.isEmpty()) ? "\\s"
				: delimiter;
		String[] parts;

		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		while (null != line && !line.isEmpty())
		{
			parts = line.split(split);
			for (int i = 0; i < parts.length; i++)
			{
				if (!st.contains(parts[i]))
				{
					st.put(parts[i], st.size());
				}
			}
			line = reader.readLine();
		}

		// Init the inverse index, index => key
		names = new String[st.size()];
		for (String key : st.keys())
		{
			names[st.get(key)] = key;
		}

		// Init the underlying graph
		g = new Graph(st.size());

		reader = new BufferedReader(new FileReader(filePath));
		line = reader.readLine();

		while (null != line && !line.isEmpty())
		{
			parts = line.split(split);
			int start = st.get(parts[0]);
			for (int i = 1; i < parts.length; i++)
			{
				int end = st.get(parts[i]);
				g.addEdge(start, end);
			}
			line = reader.readLine();
		}

		reader.close();
	}

	public boolean contains(String key)
	{
		return st.contains(key);
	}

	public int index(String key)
	{
		Integer index = st.get(key);
		return (index == null) ? -1 : index;
	}

	public String name(int index)
	{
		return names[index];
	}

	public Graph g()
	{
		return g;
	}
}
