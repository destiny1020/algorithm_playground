package misc;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 对特殊数据在线性时间内排序 比如对公司员工的年龄进行排序
 * 
 * @author Destiny
 * 
 */
public class SortByLinearTime {

	public static int[] sort(int[] arr) {

		// 如果规定最大年龄为99岁
		int upper = 99;
		int[] ages = new int[upper + 1];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0 || arr[i] > 99) {
				// 如果年龄超出了规定的范围，继续遍历
				continue;
			}

			// 添加到ages数组
			ages[arr[i]]++;
		}

		// 根据ages数组中的信息对arr进行填充/排序
		int arrIndex = 0;
		for (int i = 0; i < ages.length; i++) {
			while (ages[i] > 0) {
				arr[arrIndex] = i;
				ages[i]--;
				arrIndex++;
			}
		}

		return arr;
	}

	@Test
	public void testSortLinearTime() {
		int[] test = { 26, 23, 25, 25, 26, 27, 22, 24, 25, 24, 29, 29, 30, 32,
				24, 35, 26, 23, 25, 25, 26, 27, 22, 24, 25, 24, 29, 29, 30 };
		SortByLinearTime.sort(test);

		System.out.println(Arrays.toString(test));
	}

}
