package misc;

import org.junit.Test;

import chap1.LinkedStack;

/**
 * Sum 1...n to target
 * e.g. if target is 7
 * the program will output:
 * 1 2 4
 * 1 6
 * 2 5
 * 3 4
 * 7
 * @author Destiny
 *
 */
public class SumToTarget {
	
	private static LinkedStack<Integer> stack = new LinkedStack<Integer>();

	private static void sum(int i, int currentSum, boolean isDuplicable) {
		if(0 == currentSum) {
			System.out.println(stack.toString());
			return;
		}
		if((i + 1) > currentSum) {
			return;
		} else {
			
			// 唯一的不同就在这个地方
			// 如果是允许元素重复出现的话，j = i作为初始值，否则使用j = i + 1作为初始值
			if(!isDuplicable) {
				for(int j = i + 1; j <= currentSum; j++) {
					stack.push(j);
					sum(j, currentSum - j, isDuplicable);
					stack.pop();
				}
			} else {
				for(int j = i; j <= currentSum; j++) {
					stack.push(j);
					sum(j, currentSum - j, isDuplicable);
					stack.pop();
				}
			}
		}
	}
	
	public static void sumToTarget(int target, boolean isDuplicable) {
		
		for(int i = 1; i <= target; i++) {
			stack.push(i);
			sum(i, target - i, isDuplicable);
			stack.pop();
		}
	}
	
	@Test
	public void testSum() {
		SumToTarget.sumToTarget(10, true);
		
		System.out.println("--------------------------");
		
		SumToTarget.sumToTarget(10, false);
	}
	
}
