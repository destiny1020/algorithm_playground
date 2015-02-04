package introduction.chapter16;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity Selection Greedy Recursive Solution
 * @author Destiny
 *
 */
public class ActivitySelectionRecur
{
	private int N;
	
	private int[] starts;
	private int[] ends;
	
	private List<Integer> activities;
	
	public ActivitySelectionRecur(int[] starts, int[] ends)
	{
		if(starts.length == ends.length)
			this.N = starts.length;
		else
			throw new IllegalArgumentException("starts and ends have different lengths");
		
		this.starts = starts;
		this.ends = ends;
		
		this.activities = new ArrayList<Integer>();
	}
	
	public void process()
	{
		process(0);
	}

	private void process(int previous)
	{
		int next = previous + 1;
		while(next < N && starts[next] <= ends[previous])
		{
			next++;
		}
		
		// Add this activity
		activities.add(next);
		
		// Not including the infinite task and last task, because next recursion will 
		// increment this index
		if(next < N - 2)
			process(next);
		else
			return;
	}

	public List<Integer> getActivities()
	{
		return activities;
	}
}
