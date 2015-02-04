package puzzlers;

public class Puzzler24 {

	public static void main(String[] args) {

		// 这个程序也有一些违反直觉，以为一次byte全范围的循环至少会让b和0x90相等一次，但是最后的结果让人大跌眼镜
		// 这个程序什么都不会打印！
		// 因为0x90是int常量，它代表的数值是144，而byte的取值范围是-128到127！
		System.out.println("Try 1:");
		for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
			if (0x90 == b) {
				System.out.println("Joy!");
			}
		}

		System.out.println("Try 2:");
		// 同时学到的还有，通过屏蔽码来消除符号扩展对于byte的影响：
		for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
			// 首先使用byte和一个int常量作位与运算，使之变成int类型，同时消除了符号扩展的可能性
			if (0x90 == (b & 0xFF)) {
				System.out.println("Joy!");
			}
		}
		
	}
	// 另外还有一个技巧：对于这种特殊类型的常量，可以使用static final进行声明，比如：
	private static final byte TARGET = (byte)0x90;
	// 可以发现以上需要强转，这就提醒了你可能发生的窄转换
}
