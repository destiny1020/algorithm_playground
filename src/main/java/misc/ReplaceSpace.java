package misc;

import org.junit.Test;

/**
 * 将空格替换成为%20
 * @author Destiny
 *
 */
public class ReplaceSpace {

	public static String replaceSpaces(String str) {
		
		if(null == str || 0 == str.length()) {
			return str;
		}
		
		int spaceCount = 0;
		
		// 首先通过一次遍历来统计字符串中空格的个数
		for(int i = 0; i < str.length(); i++) {
			if(' ' == str.charAt(i)) {
				spaceCount++;
			}
		}
		
		// 如果不存在空格，直接返回参数
		if(0 == spaceCount) {
			return str;
		}
		
		// 从尾到头进行替换
		char[] chars = new char[spaceCount * 2 + str.length()];
		int newPointer = chars.length - 1;
		
		for(int oldPointer = str.length() - 1; oldPointer >= 0; oldPointer--) {
			char ch = str.charAt(oldPointer);
			if(' ' == ch) {
				chars[newPointer--] = '0';
				chars[newPointer--] = '2';
				chars[newPointer--] = '%';
			} else {
				chars[newPointer--] = ch;
			}
		}
		
		return new String(chars);
	}
	
	/**
	 * 将%20替换成空格
	 * @param str
	 * @return
	 */
	public static String replaceSpaceReverse(String str) {
		
		if(null == str || 0 == str.length()) {
			return str;
		}
		
		boolean flagPercent = false;
		boolean flagTwo = false;
		
		char space = ' ';
		int newPointer = 0;
		
		char[] chars = str.toCharArray();
		for(int oldPointer = 0; oldPointer < chars.length; oldPointer++) {
			char ch = chars[oldPointer];
			chars[newPointer++] = ch;
			switch (ch) {
			case '%':
				flagPercent = true;
				break;
			case '2':
				if(flagPercent) {
					flagTwo = true;
				}
				break;
			case '0':
				if(flagPercent && flagTwo) {
					// 调整newPointer的值，将%20修改成为一个空格
					// 下面两行有一点绕
					chars[newPointer - 3] = space;
					newPointer -= 2;
					flagPercent = false;
					flagTwo = false;
				}
				break;
			default:
				flagPercent = false;
				flagTwo = false;
				break;
			}
		}
		
		return new String(chars, 0, newPointer);
	}
	
	@Test
	public void testNormal() {
		String rel = replaceSpaces(" 123  456  789 0 ");
		System.out.println(rel);
	}
	
	@Test
	public void testReverse() {
		String rel = replaceSpaceReverse("%20123%20%20456%20%20789%200%20");
		System.out.println(rel);
	}
	
}
