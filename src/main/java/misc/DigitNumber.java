package misc;

import org.junit.Test;

/**
 * 统计从1到n中数字x的出现次数，比如当n等于11，x等于1的时候，结果应该为：
 * 1 2 3 4 5 6 7 8 9 10 11 = 4
 * 
 * @author Destiny
 *
 */
public class DigitNumber {

	/**
	 * 复杂度为O(nlogn)的算法
	 */
	public static int countDigitNumberPlain(int upper, int digit) {
		if(upper < 0 || (digit < 0 || digit > 9)) {
			// 这里也可以选择抛出异常
			return -1;
		}
		
		int result = 0;
		for(int i = 0; i <= upper; i++) {
			result += countDigitNumberPlainCore(i, digit);
		}
		
		return result;
	}
	
	private static int countDigitNumberPlainCore(int num, int digit) {
		int count = 0;
		while(num > 0) {
			if(num % 10 == digit) {
				count++;
			}
			num /= 10;
		}
		return count;
	}
	
	/**
	 * 复杂度为O(logn)
	 * @param upper
	 * @param digit
	 * @return
	 */
	public static int countDigitNumber(int upper, int digit) {
		if(upper < 0 || (digit < 0 || digit > 9)) {
			// 这里也可以选择抛出异常
			return -1;
		}
		
		
		// 计算当前upper的位数
		int length = 1;
		int num = upper / 10;
		while(num > 0) {
			length++;
			num /= 10;
		}
		
		return countDigitNumberCore(upper, digit, length); 
	}

	private static int countDigitNumberCore(int upper, int digit, int length) {
		
		// 递归终止条件
		if(length == 1) {
			return (upper >= digit) ? 1 : 0;
		}
		
		// 计算当前段的出现次数
		int result = 0;
		
		// 得到最高位所代表的base，比如当length为3时，那么base为100
		int base = 1;
		for(int i = length; i > 1; i--) {
			base *= 10;
		}
		
		// 得到去除最高位后的数字
		int left = upper % base;
		
		// 首先找到最高位的数字
		int highest = upper / base;
		
		// 如果当最高位数字大于传入的digit且digit不为0的话，加上base数量的出现次数
		if(digit < highest && digit != 0) {
			result += base;
		} else if(digit == highest) {
			// 如果当前最高位数字和需要求的digit相同，那么最高位上digit出现的次数为left + 1
			result += (left + 1);
		}
		
		// 计算除最高位外的出现次数
		// 下面这一行有些tricky，比如对于upper等于21345这样的情况
		// 那么这里考虑的是1346 - 21345这20000个数的情况
		// 比如我们要考虑1出现的次数，因为最高位为2，因此上面的第一个if成立，result加上了10000
		// 然后下面的这一行就为2 * (5 - 1) * (10000 / 10)
		// 5 - 1代表除去最高位外还有4位，然后10000 / 10代表在后面四位中一位取1，其他随便取的情况
		result += highest * (length - 1) * (base / 10);
		
		return result + countDigitNumberCore(left, digit, length - 1); 
	}
	
	@Test
	public void testCountDigit() {
		
//		Clever:	151000148
//		Poor:	151000148
		
		int result = countDigitNumber(135600111, 1);
		System.out.println("Clever:\t" + result);
		
		int result2 = countDigitNumberPlain(135600111, 1);
		System.out.println("Poor:\t" + result2);
	}
	
}
