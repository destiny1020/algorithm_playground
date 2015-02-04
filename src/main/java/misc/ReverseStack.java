package misc;

import org.junit.Test;

import chap1.LinkedStack;

/**
 * 颠倒一个栈结构，完全使用递归
 * 递归练习
 * 
 * @author jiangr2
 *
 */
public class ReverseStack {

	/**
	 * 将传入的栈进行颠倒
	 * 
	 * @param stack
	 */
	public static void reverseStack(LinkedStack<Integer> stack) {
		
		if(null != stack && !stack.isEmpty()) {
			// 将栈顶元素取出
			Integer top = stack.pop();
			
			// 递归地将剩下的元素颠倒
			reverseStack(stack);
			
			// 将之前取出的元素放到栈底
			insertToStackBottom2(stack, top);
		}
		
	}
	
//	public static void reverseStack(LinkedStack<Integer> stack) {
//		
//		if(null != stack && !stack.isEmpty()) {
//			
//			
//			
//		}
//	}
	
	public static LinkedStack<Integer> reverseStackDirectly(LinkedStack<Integer> stack) {
		
		if(null != stack && !stack.isEmpty()) {
			LinkedStack<Integer> auxiliary = new LinkedStack<Integer>();
			while(!stack.isEmpty()) {
				auxiliary.push(stack.pop());
			}
			return auxiliary;
		}
		
		return stack;
	}


	private static void insertToStackBottom(LinkedStack<Integer> stack,
			Integer bottom) {
		
		// 当栈为空的时候，将待插入的元素放到栈底
		if(stack.isEmpty()) {
			stack.push(bottom);
			return;
		}
		
		// 取出栈顶的元素
		Integer top = stack.pop();
		
		// 将传入的栈底元素放到栈底
		insertToStackBottom(stack, bottom);
		
		// 还原
		stack.push(top);
		
	}
	
	private static void insertToStackBottom2(LinkedStack<Integer> stack, Integer bottom) {
		assert (null != stack);
		LinkedStack<Integer> auxiliary = new LinkedStack<Integer>();
		while(!stack.isEmpty()) {
			auxiliary.push(stack.pop());
		}
		stack.push(bottom);
		while(!auxiliary.isEmpty()) {
			stack.push(auxiliary.pop());
		}
	}
	
	@Test
	public void testReverseStack() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack);
		
		reverseStack(stack);
		System.out.println(stack);
	}
	
}
