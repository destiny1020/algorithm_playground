package puzzlers;

/**
 * 长整除，注意防止溢出
 * @author Destiny
 *
 */
public class Puzzler3 {

	public static void main(String[] args) {
		
		final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
		
		// Will print 5 ! 因为发生了溢出
		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
		
		// 还是无力回天，因为在计算该final类型的long时，就已经发生了溢出！
		System.out.println((long)MICROS_PER_DAY / MILLIS_PER_DAY);
		
		final long MICROS = 24 * 60 * 60 * 1000 * 1000L;
		final long MILLIS = 24 * 60 * 60 * 1000;
		
		// 对于常量的计算，特别是long类型，需要防止在初始化的时候发生了溢出！
		System.out.println(MICROS / MILLIS);
	}
	
}
