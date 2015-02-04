package misc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;

/**
 * 代表了全排列的一个子任务
 * 
 * @author Destiny
 * 
 */
public class PermTask implements Callable<Integer> {

	private String prefix;
	private String remaining;
	private int taskThreshold;
	private CompletionService<Integer> cs;

	private int count;

	public PermTask(String prefix, String remaining, int taskThreshold,
			CompletionService<Integer> cs) {
		this.prefix = prefix;
		this.remaining = remaining;
		this.taskThreshold = taskThreshold;
		this.cs = cs;
	}

	@Override
	public Integer call() throws Exception {

		// 如果剩下的字符多于5个，分小任务
		if (remaining.length() > taskThreshold) {
			for (int i = 0; i < remaining.length(); i++) {
				PermTask task = new PermTask(prefix
						+ remaining.substring(i, i + 1), remaining.substring(0,
						i) + remaining.substring(i + 1), taskThreshold, cs);
				cs.submit(task);
				PermExecutor.taskCounter.addAndGet(1);
			}
			return null;

		} else {
			// 如果剩下的字符少于等于5个，直接执行
			perm2(remaining);

			return count;
		}
	}

	public void perm2(String str) {
		char[] chars = str.toCharArray();
		perm2Internal(chars, str.length());
	}

	private void perm2Internal(char[] chars, int length) {
		if (1 == length) {
			count++;
			// System.out.println(Thread.currentThread().getName() + " : "
			// + prefix + Arrays.toString(chars));
			return;
		}

		for (int i = 0; i < length; i++) {
			// 让索引为1处的字符和最后一个字符交换位置
			exch(chars, i, length - 1);
			perm2Internal(chars, length - 1);
			// 还原
			exch(chars, i, length - 1);
		}
	}

	private void exch(char[] chars, int from, int to) {
		char t = chars[from];
		chars[from] = chars[to];
		chars[to] = t;
	}
}
