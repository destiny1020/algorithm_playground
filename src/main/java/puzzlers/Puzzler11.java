package puzzlers;
/**
 * 还是字符串连接和字符“连接”的问题
 * @author Destiny
 *
 */
public class Puzzler11 {

	public static void main(String[] args) {
		
		// 执行的是字符串连接的操作
		System.out.println("H" + "a");
		
		// 执行的拓宽之后的int运算
		System.out.println('H' + 'a');
		
		// 对于任一操作数为字符串的情况，+运算符被重载为字符串连接的操作符
		// 为了对字符char类型也执行连接的操作，可以使用以下的方式：
		
		StringBuilder sb = new StringBuilder();
		sb.append('H');
		sb.append('a');
		System.out.println(sb.toString());
		
		// 或者使用一个空String作为操作数之一
		System.out.println("" + 'H' + 'a');
		
		// 或者使用API
		System.out.printf("%c%c", 'H', 'a');
	}
	
}
