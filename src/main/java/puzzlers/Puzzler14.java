package puzzlers;

/**
 * 这一题一开始就做对了……
 * @author Destiny
 *
 */
public class Puzzler14 {

	public static void main(String[] args) {
		
		// 对程序的肤浅认识会认为该程序将打印26
		// 进一步认识会认为会打印16，因为每一个转义字符实际上的长度应该为1，因此应该打印出26 - 12 + 2 = 16
		// 但是这里考察的就是：Java对在字符串字面常量中的Unicode转义字符没有提供任何特殊处理！
		// 因此最终的结果也等价于后面的表达式：
		System.out.println("a\u0022.length() + \u0022b".length());
		System.out.println("a".length() + "b".length());
		
	}
	
}
