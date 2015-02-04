package misc;

import org.junit.Test;

/**
 * 没有四则运算的加法，使用位运算实现
 * 
 * 分成三个步骤来实现：
 * 1 进行异或运算得到一个结果，这一步不考虑进位的情况
 * 2 进行与运算得到一个结果，因为只有1&1才会得到1，因此对两数进行与运算之后再向左移一位，得到进位之后的结果
 * 3 将上述两步得到的结果相加，这个过程是递归的，递归终止的条件是，进位结果为0
 * 
 * @author Destiny
 *
 */
public class AddWithoutArithmetic {

	public static int add(int ops1, int ops2) {
		int result = addInternal(ops1, ops2);
		
		return result;
	}
	
	private static int addInternal(int ops1, int ops2) {
		
		// 执行步骤一，异或运算得到不用进位的部分
		int step1 = ops1 ^ ops2;
		
		// 执行步骤二，得到进位部分
		int step2 = (ops1 & ops2) << 1;
		
		// 如果不存在进位结果了，表示可以返回
		if(0 == step2)
			return step1;
		
		return addInternal(step1, step2);
	}
	
	@Test
	public void testAdd() {
		int ops1 = 150;
		int ops2 = 151;
		
		System.out.println(AddWithoutArithmetic.add(ops1, ops2));
	}
}
