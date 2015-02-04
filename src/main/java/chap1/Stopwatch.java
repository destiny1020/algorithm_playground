package chap1;

public class Stopwatch
{
	private long start;
	private long start_nano;

	private long duration;
	private long duration_nano;

	public Stopwatch()
	{
		this.start = System.currentTimeMillis();
		this.start_nano = System.nanoTime();
	}

	public void stop()
	{
		this.duration = System.currentTimeMillis() - this.start;
		this.duration_nano = System.nanoTime() - this.start_nano;
	}

	public double elapsedTimeInSeconds()
	{
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}

	public long elapsedTime()
	{
		long now = System.currentTimeMillis();
		return (now - start);
	}

	public long getDuration()
	{
		return duration;
	}

	public long getDuration_nano()
	{
		return duration_nano;
	}
}
