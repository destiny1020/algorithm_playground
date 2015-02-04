package puzzlers;

/**
 * 奇数性
 * 
 * @author Destiny
 * 
 */
public class Puzzler1 {
	
	/**
	 * Ill-behaved method. 如果传入参数为负，则会判断失败！
	 * @param i
	 * @return
	 */
	public static boolean isOddWrong(int i) {
		return (i % 2 == 1);
	}
	
	public static boolean isOddCorrect(int i) {
		return (i % 2 != 0);
	}
	
	/**
	 * 最好的判断方法，如果能够使用位操作完成，就使用之，因为位操作的效率最高！
	 * @param i
	 * @return
	 */
	public static boolean isOddBest(int i) {
		return (i & 1) == 1;
	}

	public static void main(String[] args) {
		
		System.out.println("判断-1是否是奇数：");
		System.out.println("错误的方法：" + isOddWrong(-1));
		System.out.println("正确的方法：" + isOddCorrect(-1));
		System.out.println("最好的方法：" + isOddBest(-1));
	}
}
