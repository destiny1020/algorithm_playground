package puzzlers;

import java.math.BigDecimal;

/**
 * 对于有精度要求的计算，不要使用float或者double类型
 * @author Destiny
 *
 */
public class Puzzler2 {

	public static void main(String[] args) {
		
		// 错误的精度计算
		System.out.println(2.00 - 1.10);
		
		// 正确的精度计算，使最小的单位成为1，比如1分也是用1来表示，那么1元就是100！
		System.out.println(200 - 110);
		
		// 正确的精度计算，使用BigDecimal
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
	}
}
