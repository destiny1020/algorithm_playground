package chap1;

import gadget.CustomRandom;

public class BirthdayProblem
{
	private int bound;
	private final double result;
	private boolean[] flags;
	private int count;
	private int countTotal;
	private boolean loopFlag = true;
	
	public BirthdayProblem(int bound)
	{
		this.bound = bound;
		this.result = Math.sqrt(Math.PI * (bound / 2.0));
		this.flags = new boolean[bound];
	}
	
	public void process()
	{
		loop:while(Math.abs(((countTotal * 1.0) / count) - result) > 1e-2 || loopFlag)
		{
			while (true)
			{
				this.loopFlag = false;
				int random = CustomRandom.uniform(bound);
				if (flags[random])
				{
					count++;
					this.refresh();
					continue loop;
				} else
				{
					flags[random] = true;
					countTotal++;
				}
			}
		}
		this.printResult();
	}
	
	private void printResult()
	{
		System.out.print("Count Total: " + this.countTotal + " --- Count: " + this.count);
	}

	private void refresh()
	{
		this.flags = new boolean[bound];
	}
}
