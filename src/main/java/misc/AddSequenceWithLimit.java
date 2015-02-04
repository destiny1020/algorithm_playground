package misc;

import org.junit.Test;

/**
 * 和上面的AddWithoutArithmetic类似，也是在操作种类受限的情况下，进行1+2+3+..n的运算
 * @author Destiny
 *
 */
public class AddSequenceWithLimit {

	// 将结果放在了方法体的外面，模拟指针
	public static int result = 0;
	
	public static boolean addInternal(int n) {
		result += n--;
		
		// 以下的n != 0还是用到了条件判断语句，这在C中能够被解决，因为非0的数字放在&&中，代表的就是true
		return ((n != 0) && addInternal(n));
	}
	
	@Test
	public void testAddSequence() {
		addInternal(5);
		System.out.println(AddSequenceWithLimit.result);
	}
}
