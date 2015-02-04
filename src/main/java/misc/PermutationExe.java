package misc;

import org.junit.Test;

/**
 * 重新写一遍全排列算法，考虑顺序但是稍微低效的算法以及不考虑顺序但是稍微高效的算法
 * @author Destiny
 *
 */
public class PermutationExe {

	public static void perm1(String str) {
		perm1Internal("", str);
	}

	private static void perm1Internal(String current, String remaining) {
		
		// 如果remaining的长度为0，代表一次全排列完成了
		if(0 == remaining.length()) {
//			System.out.println(current);
			return;
		}
		
		for(int i = 0; i < remaining.length(); i++) {
			perm1Internal(current + remaining.substring(i, i + 1), remaining.substring(0, i) + remaining.substring(i + 1));
		}
	}
	
	@Test
	public void testPerm1() {
		// 运行了36.28s
		perm1("ABCDEFGHIJK");
	}
	
	//---------------------------------------------------
	
	private static int count2 = 0;
	
	public static void perm2(String str) {
		char[] chars = str.toCharArray();
		perm2Internal(chars, str.length());
	}
	
	private static void perm2Internal(char[] chars, int length) {
		if(1 == length) {
			count2++;
//			System.out.println(Arrays.toString(chars));
			return;
		}
		
		for(int i = 0; i < length; i++) {
			// 让索引为1处的字符和最后一个字符交换位置
			exch(chars, i, length - 1);
			perm2Internal(chars, length - 1);
			// 还原
			exch(chars, i, length - 1);
		}
	}
	
	private static void exch(char[] chars, int from, int to) {
		char t = chars[from];
		chars[from] = chars[to];
		chars[to] = t;
	}
	
	@Test
	public void testPerm2() {
		// 运行了14.693s
		perm2("ABCDEFGHIJKL");
		System.out.println(count2);
	}
	
}
