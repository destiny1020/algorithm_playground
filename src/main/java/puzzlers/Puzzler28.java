package puzzlers;

public class Puzzler28 {

	public static void main(String[] args) {

		// 用一个double或float数值来表示无穷大是可以的
		// 将一个很小的浮点数加到一个很大的浮点数上时，将不会改变大浮点数的值
		// 二进制浮点算术只是对实际算术的一种近似
		double max = Double.POSITIVE_INFINITY;
		while(max == max + 1) {
			System.out.println("HAHA");
		}
		
	}
	
}
