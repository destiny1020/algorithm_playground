package puzzlers;

/**
 * 這個謎題的目標是讓表達式 x += i合法，而x = x + i不合法
 * 
 * @author Destiny
 *
 */
public class Puzzler9 {

	public static void main(String[] args) {
		
		// 需要利用复合赋值表达式的一个规则，使用x += i的时候，实际上是等价于x = (Type X)(x + i)
		// 也就是说，在执行完x + i之后，会有一个hidden cast操作
		short s = 0;
		int i = 123456;
		
		// 这是合法的，因为s和i运算之后会将结果进行narrowing到short类型
		System.out.println(s += i);
		
		// 下面的表达式不合法，不能从int转换到short，因为会丢失精度，需要强制转换，而这个转换在复合赋值操作符中已经自带了
//		s = s + i;
	}
	
}
