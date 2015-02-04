package misc;

import org.junit.Test;

/**
 * 求一个数组中最大的diff，何为diff： 比如对于a[],
 * diff指的是数组中index小的元素减去index大的元素的结果，目标就是要求得diff最大的值和相应的两个操作数的index
 * 
 * 解法一：暴力法，一共检测n^2 / 2次来找出最小的那一个diff
 * 一般而言，暴力法不会有很高的效率，这是因为没有挖掘出其中一些不必要的操作，广义的讲，就是没有进行剪枝操作 比如：如果a[1] - a[2]
 * 是个负值，那么表示a[1]是小于a[2]的，那么在暴力搜索中，必然会检测到a[1] - a[n] 和 a[2] - a[n]
 * 这两种情况，而由于a[1]和a[2]已经有大小关系了，因此前者的检测是不必要的，所以考虑使用动态规划来求解
 * 
 * 解法二：动态规划 设b[i] (i >= 0, index-based) 为 a[t] - a[i + 1]的最大值，比如，b[0] = max(a[t]
 * - a[1]), t >= 0 且 t < 1 又比如b[m] = max(a[t] - a[m + 1]), 其中 t >= 0 且 t < m + 1
 * 那么如何由b[i]推导出b[i + 1]呢？ 由于b[i]由a[t] - a[i]得到，显然这里的a[t]应该是前i -
 * 1个元素中最大的一个，所以，b[i + 1] = max(a[t] - a[i + 1], a[i] - a[i + 1])
 * 
 * 求得b数组之后，找到其中最大的值即可，同时还需要两个变量来保存两个索引信息
 * 
 * @author Destiny
 * 
 */
public class MaxDiffInArray {

	/**
	 * 求解Max-Diff问题，返回数组中含有三个元素，分别是max-diff，start_index 和 end_index
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] getMaxDiff(int[] arr) {

		if (arr.length <= 1) {
			return null;
		}

		int maxDiff = arr[0] - arr[1];
		int start = 0;
		int end = 1;

		// 从计算maxDiff的减数索引开始循环
		for (int i = 2; i < arr.length; i++) {
			int possible1 = arr[start] - arr[i];
			int possible2 = arr[i - 1] - arr[i];

			// 如果两种可能都没有当前maxDiff大，进行下一次循环
			if (possible1 <= maxDiff && possible2 <= maxDiff) {
				continue;
			} else if (possible1 >= possible2) {
				// 使用第一种来更新maxDiff，start，end信息
				maxDiff = possible1;
				// end = i;
			} else {
				// 使用第二种来更新
				maxDiff = possible2;
				start = i - 1;
				// end = i;
			}

			// 将上述的重复代码提取到外部
			end = i;
		}

		int[] results = new int[3];
		results[0] = maxDiff;
		results[1] = start;
		results[2] = end;

		return results;
	}

	@Test
	public void testMaxDiff() {
		int[] test = { 2, 4, 1, 16, 7, 5, 11, 9 };

		int[] results = MaxDiffInArray.getMaxDiff(test);

		System.out.println("Max Diff:\t" + results[0]);
		System.out.println("Start:\t\t" + results[1]);
		System.out.println("End:\t\t" + results[2]);
	}

}
