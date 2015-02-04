package misc;

import java.util.Arrays;

import org.junit.Test;

/**
 * 最长公共子串问题 例如字符串 ABAB 和 BABA的最长子串为： ABA 或者 BAB 长度均为3
 * 
 * @author jiangr2
 * 
 */
public class LargestCommonSubstring {

	/**
	 * 使用这个方法来得到一个字符串中对称部分的最大长度 例如google这个字符串中对称部分为goog，长度为4
	 * 
	 * @param str1
	 * @param str2
	 */
	public static void lcs(String str1, String str2) {

		int len1 = str1.length();
		int len2 = str2.length();

		int[][] matrix = new int[len1 + 1][len2 + 1];

		int lcsLength = 0;
		int end1 = 0;
		int end2 = 0;

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					matrix[i + 1][j + 1] = matrix[i][j] + 1;
					if (matrix[i + 1][j + 1] > lcsLength) {
						lcsLength = matrix[i + 1][j + 1];
						end1 = i;
						end2 = j;
					}
				}
			}
		}

		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}

		System.out.println("End1: " + end1 + " - End2: " + end2);

	}

	@Test
	public void testLCS() {
		lcs("google", "elgoog");
	}

}
