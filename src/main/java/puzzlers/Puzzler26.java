package puzzlers;

/**
 * 循环和边界条件
 * @author Destiny
 *
 */
public class Puzzler26 {

	public static final int END = Integer.MAX_VALUE;
	public static final int START = END - 100;
	
	public static void main(String[] args) {
		
		// 这里的循环是一个无限循环，因为END是一个边界值，在这个地方进行++操作时会发生上溢，使当前值回到Integer.MIN_VALUE;
		// 解决办法就是将i的类型变成long，但是效率会下降一些，特别是当迭代的范围特别广时
		// 或者是使用do while循环来做这件事情
		int count = 0;
		for(int i = START; i <= END; i++) {
			count++;
		}
		System.out.println(count);
		
	}
	
}
