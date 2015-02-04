package puzzlers;

/**
 * 依旧是循环的问题，这题强调的是隐式的窄变换
 * @author Destiny
 *
 */
public class Puzzler31 {

	// 会引起死循环
	public static void main(String[] args) {
		
		// 0xFFFF
		short i = (short)-1;
		
		while(0 != i) {
			
			//对于算术操作，总是会提升到int或者long，这里是提升到int，这个根据i的类型进行拓宽
			//如果是short，byte类型，会进行有符号拓宽，char进行无符号拓宽
			//因此这里i拓宽为0xFFFF之后进行无符号移位运算，运算结果为0x7FFFF
			//这个符合操作符会在操作返回时进行隐式的窄变换以恢复到原来的类型，即short
			//而这个窄变换不分三七二十一直接对高16位进行截断，所以得到的i就是0xFFFF
			//因此又回到了原点，运算终止条件永远无法满足
			i >>>= 1;
			
		}
		
	}
	
}
