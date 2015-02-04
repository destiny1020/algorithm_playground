package misc;

import org.junit.Test;

/**
 * 将字符串中的大写字母放到字符串的后面，每个字符的相对位置不能被改变
 * 且不能分配多余空间
 * @author jiangr2
 *
 */
public class StringHandling {

	public static String handle(String str) {
		
		// 只是为了处理简单一点，不算分配空间
		// 如果严格要求，可以使用str.charAt(index)方法
		char[] chars = str.toCharArray();
		
		int upperIndex = 0;
		// 找到第一个大写字母
		while(!Character.isUpperCase(chars[upperIndex])) {
			upperIndex++;
			
			// 字符串中不存在任何大写字母
			if(chars.length == upperIndex) {
				return str;
			}
		}
		
		int lowerIndex = upperIndex + 1;
		
		// 终止条件
		while(lowerIndex != chars.length) {
			
			// 确定小写字母的index
			while(!Character.isLowerCase(chars[lowerIndex])) {
				lowerIndex++;
				
				// 字符串中已经不含有任何小写字母
				if(chars.length == lowerIndex) {
					return new String(chars);
				}
			}
			
			// 现在从upperIndex到lowerIndex之间全部都是大写字母 [upperIndex, lowerIndex)
			int middleIndex = upperIndex + 1;
			while(middleIndex != lowerIndex) {
				exch(chars, upperIndex, middleIndex);
				middleIndex++;
			}
			exch(chars, upperIndex, lowerIndex);
			upperIndex++;
			lowerIndex = upperIndex;
		}
		
		return new String(chars);
	}
	
	private static void exch(char[] src, int from, int to) {
		
		char t = src[from];
		src[from] = src[to];
		src[to] = t;
	}
	
	@Test
	public void testStrHandling() {
		String str = "aBcDeFgHiJkLmN";
		System.out.println(StringHandling.handle(str));
	}
}
