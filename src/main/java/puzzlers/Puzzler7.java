package puzzlers;

/**
 * 这个程序旨在通过“聪明”的办法来交换两个数，这在C/C++中也许是能行的 但是在Java中，一定不行，因为Java中对于操作符的操作数是从左到右求值的！
 * 这一点不要和表达式的求职顺序弄混了。
 * 
 * @author Destiny
 * 
 */
public class Puzzler7 {

	public static void main(String[] args) {

		int x = 1984;
		int y = 2001;
		x ^= y ^= x ^= y;
		System.out.println(x);
		System.out.println(y);
		
		// 上面复杂无比的表达式可以翻译成下面的表达式：
		int tmp1 = x;	// x第一次出现
		int tmp2 = y;	// y第一次出现
		int tmp3 = x ^ y; 
		x = tmp3;  // 对最后一个x ^= y的计算结果赋值
		y = tmp2 ^ tmp3;
		x = tmp1 ^ y;
	}

}
