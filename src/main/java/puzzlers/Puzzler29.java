package puzzlers;

public class Puzzler29 {

	public static void main(String[] args) {
		
		// NaN不等于任何浮点数值，包括它自身在内
		// 任何浮点操作，只要它的一个或多个操作数为NaN，那么其结果为NaN
		// 一旦一个计算产生了NaN，它就被损坏了，没有任何更进一步的计算可以修复这样的损坏
		double nan = Double.NaN;
		while(nan != nan) {
			System.out.println("HAHA");
		}
		
	}
	
}
