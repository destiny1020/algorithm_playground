package misc;

import chap1.LinkedStack;

/**
 * 求字符串的组合，比如输入字符串abc，那么输出的应该是：
 * a，b，c，ab，ac，bc，abc
 * @author Destiny
 *
 */
public class Combination {

	private static LinkedStack<Character> stack = new LinkedStack<Character>();
	
	public static void getCombinations(String str) {
		
		// i表示的是组合中需要的元素个数，从1开始
		for(int i = 1; i <= str.length(); i++) {
			
			// 从第一个字符开始计算
			for(int j = 0; j < str.length(); j++) {
				
				stack.push(str.charAt(j));
				// 从index为j+1处开始，获取i-1个字符
				getCombinationsInternal(str, i - 1, j + 1);
				stack.pop();
				
			}
		}
	}
	
	private static void getCombinationsInternal(String str, int length, int startIndex) {
		
		// 首先检查栈中元素数量是否已经足够，即检查length是否为0
		if(0 == length) {
			System.out.println(stack);
			return;
		}
		
		// 如果栈中元素数量还不够，那么获取还需要在后面的元素中找一些元素
		for(int i = startIndex; i < str.length(); i++) {
			stack.push(str.charAt(i));
			getCombinationsInternal(str, length - 1, i + 1);
			stack.pop();
		}
	}
	
	public static void main(String[] args) {
		
		Combination.getCombinations("abcd");
		
	}
	
}
